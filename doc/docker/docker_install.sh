#! /bin/bash
#docker仓储配置
sudo yum install -y yum-utils device-mapper-persistent-data lvm2
sudo yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo
sudo yum makecache fast

#卸载docker
sudo yum -y remove docker docker-common docker-selinux docker-engine
#重新安装docker-ce
sudo yum -y install docker-ce
# 启动Docker
sudo systemctl start docker
# 安装完后设置为系统开机自动启动服务
sudo systemctl enable docker.service
# 测试一下
docker info

sudo mkdir -p /etc/docker
sudo tee /etc/docker/daemon.json <<-'EOF'
{
  "registry-mirrors": ["https://hub-mirror.c.163.com","https://unwy5ykd.mirror.aliyuncs.com","https://mirror.baidubce.com"]
}
EOF

sudo systemctl daemon-reload
sudo systemctl restart docker

# 下载docker-compose
sudo curl -L "https://github.com/docker/compose/releases/download/v2.0.1/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
# 添加可执行权限(这里不懂可以看一下菜鸟教程-linux教程-文件权限)
sudo chmod +x /usr/local/bin/docker-compose
# 查看docker-compose版本
docker-compose --version
