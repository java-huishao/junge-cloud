#!/usr/bin/env bash
set -e
set -x

/sbin/ip route | awk '/default/ { print  $3,"\tdockerhost" }' >>/etc/hosts
java -javaagent:/assets/pinpoint-agent/pinpoint-bootstrap-2.3.0.jar -Dpinpoint.agentId=springbootdemo -Dpinpoint.applicationName=springbootdemo -Djava.security.egd=file:/dev/./urandom -jar /app.jar
