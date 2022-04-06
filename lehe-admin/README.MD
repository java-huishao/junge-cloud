### docker 启动本项目

````shell
docker ps docker ps -a docker rm 容器id前4位

docker run -d -p 9050:9050 -p 9051:9051 -e NACOS_HOST=192.168.182.128 --name lehe-admin
````

````
本地启动pinpoint
-javaagent:E:\lehe-work\lehe-cloud\doc\pinpoint-agent-2.3.0\pinpoint-bootstrap-2.3.0.jar -Dpinpoint.agentId=111222 -Dpinpoint.applicationName=lehe-admin
````

```
本地nacos配置环境变量
CONFIG_SERVER=192.168.133.135
CONFIG_NAMESPACE=a66cbfc0-9bc5-4dee-9c84-269e5cc5f9a5
```

### pinpinit 启动指令

````shell
-javaagent:E:\pinpoint-agent-2.3.0\pinpoint-agent-2.3.0\pinpoint-bootstrap-2.3.0.jar
-Dpinpoint.agentId=lehe-admin
-Dpinpoint.applicationName=lehe-admin
````

### 日志发送

```yaml
lehe:
  trace:
    send:
      #开启报错日志发送到日志服务
      enabled: true
```