package com.zhny.gateway.filter;

import com.zhny.starter.common.result.Result;
import com.zhny.starter.common.result.ResultEnum;
import com.zhny.starter.common.utils.GsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

/**
 * 请求url权限校验
 */
@Slf4j
@Component
public class AccessGatewayFilter implements GlobalFilter {
    /**
     * 不需要网关签权的url配置(/oauth,/open)
     * 默认/oauth开头是不需要的
     */
    @Value("${gate.ignore.authentication.startWith:/oauth}")
    private String ignoreUrls = "/oauth";


    /**
     * 1.首先网关检查token是否有效，无效直接返回401，不调用签权服务
     * 2.调用签权服务器看是否对该请求有权限，有权限进入下一个filter，没有权限返回401
     *
     * @param exchange
     * @param chain
     * @return Result 返回结果
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        //获取请求头里的token
        String authentication = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        String uri = request.getURI().toString();
        log.info(uri);
        return chain.filter(exchange);
        //权限白名单
//        if (ignoreAuthentication(url)) {
//            return chain.filter(exchange);
//        }
//        if (null != authentication) {
//            String token = authentication.substring(7);
//            if (authService.validToken(token)) {//如果验证token通过
//                ServerHttpRequest.Builder builder = request.mutate();
//                OAuth2AccessToken oAuth2AccessToken = redisUtil.getReturnClass(SecurityConstants.USER_TOKEN + token,OAuth2AccessToken.class);
//                if(method.equals(HttpMethod.GET.toString())){
//                    String userJson = GsonUtil.gson2String(oAuth2AccessToken);
//                    builder.header(SecurityConstants.AUTH_HEADER, userJson);
//                    return chain.filter(exchange.mutate().request(builder.build()).build());
//                }
//                if(authService.validUserPermissions(oAuth2AccessToken,url)){
//                    String userJson = GsonUtil.gson2String(oAuth2AccessToken);
//                    builder.header(SecurityConstants.AUTH_HEADER, userJson);
//                    return chain.filter(exchange.mutate().request(builder.build()).build());
//                }
//                return response(exchange,ResultEnum.AUTH_ERROR);
//            }
//            return response(exchange,ResultEnum.LOGIN_SESSION_MISS);
//        }
//        return response(exchange,ResultEnum.LOGIN_SESSION_MISS);
    }


    private Mono<Void> response(ServerWebExchange serverWebExchange, ResultEnum resultEnum) {
        ServerHttpResponse response = serverWebExchange.getResponse();
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
        String json = GsonUtil.gson2String(Result.fail(resultEnum));
        byte[] bits = json.getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bits);
        //指定编码，否则在浏览器中会中文乱码
        return response.writeWith(Mono.just(buffer));
    }


    public boolean ignoreAuthentication(String url) {
        return Stream.of(this.ignoreUrls.split(",")).anyMatch(ignoreUrl -> url.startsWith(StringUtils.trim(ignoreUrl)));
    }
}
