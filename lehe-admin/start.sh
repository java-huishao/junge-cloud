#! /bin/bash
################################环境变量配置#################################
export NACOS_HOST=127.0.0.1
export REDIS_HOST=127.0.0.1
################################环境变量配置#################################
#lehe-auth 服务
LEHE_ADMIN=lehe-admin.jar
PID1=$(ps -ef | grep $LEHE_ADMIN | grep -v grep | awk '{print $2}')
if [ $PID1 ]; then
  echo $PID1
  kill -9 $PID1
fi
nohup java -jar \
  -Xdebug -Xrunjdwp:transport=dt_socket,address=5678,server=y,suspend=y \
  -Dfile.encoding=UTF-8 \
  -XX:MetaspaceSize=128m \
  -XX:MaxMetaspaceSize=128m \
  -Xms1024m \
  -Xmx1024m \
  -Xmn256m \
  -Xss256k \
  -XX:SurvivorRatio=8 \
  -XX:+UseConcMarkSweepGC lehe-admin.jar >admin.log &
sleep 10s
echo "============================================lehe-admin 重启成功========================================="
