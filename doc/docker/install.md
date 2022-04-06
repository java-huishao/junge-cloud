#### docker的版本变化：

Docker从1.13.x版本开始，版本分为企业版EE和社区版CE，版本号也改为按照时间线来发布，比如17.03就是2017年3月，有点类似于ubuntu的版本发布方式。

企业版自然会提供一些额外的服务，当然肯定也是收费的。企业版说明https://blog.docker.com/2017/03/docker-enterprise-edition/

社区版分为stable和edge两种发布方式。

stable版本是季度发布方式，比如17.03, 17.06, 17.09

edge版本是月份发布方式， 比如17.03, 17.04......

Docker的linux发行版的软件仓库从以前的https://apt.dockerproject.org和https://yum.dockerproject.org变更为目前的https://download.docker.com,
软件包名字改为docker-ce和docker-ee。

#### docker仓储配置：

```
sudo yum install -y yum-utils device-mapper-persistent-data lvm2
sudo yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo
sudo yum makecache fast
```

#### docker卸载：

查看旧版本信息：

```
[root@localhost kubelet.service.d]# docker --version
Docker version 1.13.1, build 6e3bb8e/1.13.1
[root@localhost kubelet.service.d]# yum list installed | grep docker
docker.x86_64                      2:1.13.1-74.git6e3bb8e.el7.centos @extras
docker-client.x86_64               2:1.13.1-74.git6e3bb8e.el7.centos @extras
docker-client-latest.x86_64        1.13.1-58.git87f2fab.el7.centos   @extras    
docker-common.x86_64               2:1.13.1-74.git6e3bb8e.el7.centos @extras    
docker-devel.x86_64                1.3.2-4.el7.centos                @extras    
docker-distribution.x86_64         2.6.2-2.git48294d9.el7            @extras    
docker-forward-journald.x86_64     1.10.3-44.el7.centos              @extras    
docker-latest.x86_64               1.13.1-58.git87f2fab.el7.centos   @extras    
docker-latest-logrotate.x86_64     1.13.1-58.git87f2fab.el7.centos   @extras    
docker-latest-v1.10-migrator.x86_64
docker-logrotate.x86_64            2:1.13.1-74.git6e3bb8e.el7.centos @extras    
docker-lvm-plugin.x86_64           2:1.13.1-74.git6e3bb8e.el7.centos @extras    
docker-novolume-plugin.x86_64      2:1.13.1-74.git6e3bb8e.el7.centos @extras    
docker-unit-test.x86_64            2:1.13.1-68.gitdded712.el7.centos @extras    
docker-v1.10-migrator.x86_64       2:1.13.1-74.git6e3bb8e.el7.centos @extras    
python-docker-py.noarch            1.10.6-4.el7                      @extras    
python-docker-pycreds.noarch       1.10.6-4.el7                      @extras 
```

卸载：

```
[root@localhost kubelet.service.d]# sudo yum -y remove docker  docker-common docker-selinux docker-engine
[root@localhost kubelet.service.d]# yum list installed |grep docker
docker-devel.x86_64                1.3.2-4.el7.centos                @extras    
docker-distribution.x86_64         2.6.2-2.git48294d9.el7            @extras    
docker-forward-journald.x86_64     1.10.3-44.el7.centos              @extras    
docker-latest-v1.10-migrator.x86_64
docker-unit-test.x86_64            2:1.13.1-68.gitdded712.el7.centos @extras    
docker-v1.10-migrator.x86_64       2:1.13.1-74.git6e3bb8e.el7.centos @extras    
python-docker-py.noarch            1.10.6-4.el7                      @extras    
python-docker-pycreds.noarch       1.10.6-4.el7                      @extras  
```

#### 查看docker-ce版本：

```
[root@localhost kubelet.service.d]# yum list docker-ce --showduplicates | sort -r
docker-ce.x86_64            18.06.1.ce-3.el7                    docker-ce-stable
docker-ce.x86_64            18.06.0.ce-3.el7                    docker-ce-stable
docker-ce.x86_64            18.03.1.ce-1.el7.centos             docker-ce-stable
docker-ce.x86_64            18.03.0.ce-1.el7.centos             docker-ce-stable
docker-ce.x86_64            17.12.1.ce-1.el7.centos             docker-ce-stable
docker-ce.x86_64            17.12.0.ce-1.el7.centos             docker-ce-stable
docker-ce.x86_64            17.09.1.ce-1.el7.centos             docker-ce-stable
docker-ce.x86_64            17.09.0.ce-1.el7.centos             docker-ce-stable
docker-ce.x86_64            17.06.2.ce-1.el7.centos             docker-ce-stable
docker-ce.x86_64            17.06.1.ce-1.el7.centos             docker-ce-stable
docker-ce.x86_64            17.06.0.ce-1.el7.centos             docker-ce-stable
docker-ce.x86_64            17.03.3.ce-1.el7                    docker-ce-stable
docker-ce.x86_64            17.03.2.ce-1.el7.centos             docker-ce-stable
docker-ce.x86_64            17.03.1.ce-1.el7.centos             docker-ce-stable
docker-ce.x86_64            17.03.0.ce-1.el7.centos             docker-ce-stable
```

#### 重新安装docker-ce:

```
[root@localhost kubelet.service.d]# sudo yum -y install docker-ce
[root@localhost kubelet.service.d]# systemctl enable docker.service
Created symlink from /etc/systemd/system/multi-user.target.wants/docker.service to /usr/lib/systemd/system/docker.service.
[root@localhost kubelet.service.d]# systemctl restart docker
[root@localhost kubelet.service.d]# docker version
Client:
 Version:           18.06.1-ce
 API version:       1.38
 Go version:        go1.10.3
 Git commit:        e68fc7a
 Built:             Tue Aug 21 17:23:03 2018
 OS/Arch:           linux/amd64
 Experimental:      false

Server:
 Engine:
  Version:          18.06.1-ce
  API version:      1.38 (minimum version 1.12)
  Go version:       go1.10.3
  Git commit:       e68fc7a
  Built:            Tue Aug 21 17:25:29 2018
  OS/Arch:          linux/amd64
  Experimental:     false
```

查看组件：

```
[root@localhost kubelet.service.d]# yum list installed | grep docker
docker-ce.x86_64                   18.06.1.ce-3.el7                  @docker-ce-stable
docker-devel.x86_64                1.3.2-4.el7.centos                @extras    
docker-distribution.x86_64         2.6.2-2.git48294d9.el7            @extras    
docker-forward-journald.x86_64     1.10.3-44.el7.centos              @extras    1
docker-latest-v1.10-migrator.x86_64
docker-unit-test.x86_64            2:1.13.1-68.gitdded712.el7.centos @extras    
docker-v1.10-migrator.x86_64       2:1.13.1-74.git6e3bb8e.el7.centos @extras    
python-docker-py.noarch            1.10.6-4.el7                      @extras    
python-docker-pycreds.noarch       1.10.6-4.el7                      @extras    
```

#### 命令收集：

```
# 安装必要工具集
$ sudo yum install -y yum-utils
# 安装Docker官方源
$ sudo yum-config-manager \
--add-repo \
https://download.docker.com/linux/centos/docker-ce.repo
# 更新yum缓存
$ sudo yum makecache fast
# 安装Dcoker
$ sudo yum -y install docker-ce
# 启动Docker
$ sudo systemctl start docker
# 安装完后设置为系统开机自动启动服务
$ sudo systemctl enable docker.service
# 测试一下



$ docker info
```

##### 参考：

https://blog.csdn.net/chenhaifeng2016/article/details/68062414/

https://www.cnblogs.com/Peter2014/p/7704306.html

https://www.jianshu.com/p/31bee0cecaf2

相关资源：[《探错笔记》之*docke*r*版本升级*造成容器无法启动_*docke*r*
升级*容器...](https://download.csdn.net/download/weixin_38715094/14043278?spm=1001.2101.3001.5697)