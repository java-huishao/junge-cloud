https://www.jianshu.com/p/2d78ce6bc504
chmod 777 /root/elk/elasticsearch/data

logstash中安装json_lines插件并重启logstash

docker exec -it elk_logstash /bin/bash -c  "cd /bin && logstash-plugin install logstash-codec-json_lines"
docker restart elk_logstash