package com.zhny.auth.config;

import com.zhny.auth.converter.CustJwtAccessTokenConverter;
import com.zhny.auth.handler.CustomAccessDeniedHandler;
import com.zhny.auth.handler.CustomAuthEntryPoint;
import com.zhny.auth.handler.CustomWebResponseExceptionTranslator;
import com.zhny.auth.strore.MyRedisTokenStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenGranter;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeTokenGranter;
import org.springframework.security.oauth2.provider.implicit.ImplicitTokenGranter;
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter;
import org.springframework.security.oauth2.provider.refresh.RefreshTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * com.lehe.admin.cloud.oauth2server.config
 * @Description: 认证服务器
 * 2018/4/12 15:16
 */
@Slf4j
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Resource
    private RedisConnectionFactory redisConnectionFactory;

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private DataSource dataSource;

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private CustomAuthEntryPoint customAuthEntryPoint;
    @Resource
    private CustomAccessDeniedHandler customAccessDeniedHandler;
    @Resource
    private CustomWebResponseExceptionTranslator customWebResponseExceptionTranslator;


    //授权端点开放
    //isAuthenticated()
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 开启/oauth/token_key验证端口无权限访问
        security.allowFormAuthenticationForClients()
                .checkTokenAccess("permitAll()")
                .tokenKeyAccess("permitAll()")
                .authenticationEntryPoint(customAuthEntryPoint)
                .accessDeniedHandler(customAccessDeniedHandler);
        log.info("================AuthorizationServerSecurityConfigurer 已启动================");
    }

    /**
     * 配置客户端详情信息(Client Details)
     * clientId：（必须的）用来标识客户的Id。
     * secret：（需要值得信任的客户端）客户端安全码，如果有的话。
     * scope：用来限制客户端的访问范围，如果为空（默认）的话，那么客户端拥有全部的访问范围。
     * authorizedGrantTypes：此客户端可以使用的授权类型，默认为空。
     * authorities：此客户端可以使用的权限（基于Spring Security authorities）。
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetails());
        log.info("======================ClientDetailsServiceConfigurer 已启动=============================");
    }

    /**
     * 配置授权、令牌的访问端点和令牌服务
     * tokenStore：采用redis储存
     * authenticationManager:身份认证管理器, 用于"password"授权模式
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .tokenServices(tokenServices())
                .reuseRefreshTokens(false)
                .exceptionTranslator(customWebResponseExceptionTranslator);
        List<TokenGranter> defaultTokenGranters = getDefaultTokenGranters(endpoints);
        endpoints.tokenGranter(new CompositeTokenGranter(defaultTokenGranters));
        log.info("===============AuthorizationServerEndpointsConfigurer已启动=============");
    }


    /**
     * redis存储方式
     *
     * @return Result 返回结果
     */
    @Bean(name = "redisTokenStore")
    public TokenStore redisTokenStore() {
        TokenStore tokenStore = new MyRedisTokenStore(redisConnectionFactory);
        return tokenStore;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate() {
        return new StringRedisTemplate(redisConnectionFactory);
    }


    /**
     * 客户端信息配置在数据库
     *
     * @return Result 返回结果
     */
    @Bean
    public ClientDetailsService clientDetails() {
        return new JdbcClientDetailsService(dataSource);
    }

    /**
     * 采用RSA加密生成jwt
     *
     * @return Result 返回结果
     */
    @Bean
    public JwtAccessTokenConverter jwtTokenEnhancer() {
        //令牌加密存储工程，用于读取秘钥
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("certificate/mycloud-jwt.jks"), "123456".toCharArray());
        //自定义jwt访问令牌转换器
        CustJwtAccessTokenConverter tokenConverter = new CustJwtAccessTokenConverter();
        //非对称加密，但jwt长度过长
        //tokenConverter.setKeyPair(keyStoreKeyFactory.getKeyPair("mycloud-jwt"));
        //对称加密
        tokenConverter.setSigningKey("123456");
        return tokenConverter;
    }

    /**
     * 配置生成token的有效期以及存储方式（此处用的redis）
     *
     * @return Result 返回结果
     */
    @Bean
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(redisTokenStore());
        defaultTokenServices.setTokenEnhancer(jwtTokenEnhancer());
        defaultTokenServices.setSupportRefreshToken(true);
        defaultTokenServices.setAccessTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(7));
        defaultTokenServices.setRefreshTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(7));
        return defaultTokenServices;
    }

    /**
     * 这部分代码是根据 AuthorizationServerEndpointsConfigurer.getDefaultTokenGranters() 进行了修改
     * 只是做了private method访问的调整，其他代码原封不动
     *
     * @param endpoints
     * @return Result 返回结果
     */
    private List<TokenGranter> getDefaultTokenGranters(AuthorizationServerEndpointsConfigurer endpoints) {
        ClientDetailsService clientDetails = endpoints.getClientDetailsService();
        AuthorizationServerTokenServices tokenServices = endpoints.getTokenServices();
        AuthorizationCodeServices authorizationCodeServices = endpoints.getAuthorizationCodeServices();
        OAuth2RequestFactory requestFactory = endpoints.getOAuth2RequestFactory();
        List<TokenGranter> tokenGranters = new ArrayList<TokenGranter>();
        tokenGranters.add(new AuthorizationCodeTokenGranter(tokenServices, authorizationCodeServices, clientDetails, requestFactory));
        tokenGranters.add(new RefreshTokenGranter(tokenServices, clientDetails, requestFactory));
        ImplicitTokenGranter implicit = new ImplicitTokenGranter(tokenServices, clientDetails, requestFactory);
        tokenGranters.add(implicit);
        tokenGranters.add(new ClientCredentialsTokenGranter(tokenServices, clientDetails, requestFactory));
        if (authenticationManager != null) {
            tokenGranters.add(new ResourceOwnerPasswordTokenGranter(authenticationManager, tokenServices, clientDetails, requestFactory));
            tokenGranters.add(new SmsTokenGranter(tokenServices, clientDetails, requestFactory, userDetailsService));
        }
        return tokenGranters;
    }
}
