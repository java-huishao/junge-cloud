# springcloud-oauth2

##### 生成秘钥

````shell
keytool -genkeypair -alias mycloud-jwt -validity 3650 -keyalg RSA -dname "CN=jwt,OU=jtw,O=jtw,L=zurich,S=zurich,C=CH" -keypass 123456 -keystore mycloud-jwt.jks -storepass 123456
````

````shell
keytool -list -rfc --keystore mycloud-jwt.jks | openssl x509 -inform pem -pubkey


-DskipDockerBuild 跳过 build 镜像
-DskipDockerTag 跳过 tag 镜像
-DskipDockerPush 跳过 push 镜像
-DskipDocker 跳过整个阶段
````

#oauth2 配置
oauth.client_id=lehe
oauth.client_secret=mycloud
oauth.grant_type=password
oauth.scope=server
oauth.userAuthorizationUri=http://localhost:8000/oauth/authorize
oauth.access-token-uri=http://localhost:8000/oauth/token
oauth.userInfoUr=http://localhost:8000/user
oauth.check-token-access=http://localhost:8000/oauth/check_token

