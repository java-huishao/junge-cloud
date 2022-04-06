#! /bin/bash
################################各个服务里需要的环境变量配置#################################
#lehe-auth 服务
LEHE_ADMIN=lehe-admin.jar
PID1=$(ps -ef | grep $LEHE_ADMIN | grep -v grep | awk '{print $2}')
if [ $PID1 ]; then
  echo $PID1
  kill -9 $PID1
fi
nohup java -javaagent:./skywalking-agent.jar -Dskywalking.agent.service_name=lehe-admin -Dskywalking.collector.backend_service=127.0.0.1:11800 -jar \
  -Dfile.encoding=UTF-8 \
  -DCONFIG_SERVER=172.21.255.14 \
  -DCONFIG_NAMESPACE=a66cbfc0-9bc5-4dee-9c84-269e5cc5f9a5 \
  -XX:MetaspaceSize=128m \
  -XX:MaxMetaspaceSize=128m \
  -Xms1024m \
  -Xmx1024m \
  -Xmn256m \
  -Xss256k \
  -XX:SurvivorRatio=8 \
  -XX:+UseConcMarkSweepGC lehe-admin.jar >./logs/admin.log &
sleep 10s
echo "============================================lehe-admin 重启成功========================================="

#lehe-gateway 服务
LEHE_GATEWAY=lehe-gateway.jar
PID4=$(ps -ef | grep $LEHE_GATEWAY | grep -v grep | awk '{print $2}')
if [ $PID4 ]; then
  echo $PID4
  kill -9 $PID4
fi
sleep 10s
nohup java -jar -Dfile.encoding=UTF-8 -Dfile.encoding=UTF-8 -Xms512m -Xmx1024m lehe-gateway.jar >./logs/gateway.log &
echo "============================================lehe-gateway 重启成功========================================="
