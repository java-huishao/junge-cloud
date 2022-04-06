# lehe-cloud

mvn clean package docker:build -DpushImage -DskipTests

删除none的镜像，要先删除镜像中的容器。要删除镜像中的容器，必须先停止容器。

$ docker images

$ docker rmi $(docker images | grep "none" | awk '{print $3}')
直接删除带none的镜像，直接报错了。提示先停止容器。

$ docker stop $(docker ps -a | grep "Exited" | awk '{print $1 }') //停止容器

$ docker rm $(docker ps -a | grep "Exited" | awk '{print $1 }') //删除容器

$ docker rmi $(docker images | grep "none" | awk '{print $3}') //删除镜像

### 删除所有镜像

$ docker rmi -f $(docker images -qa)

## springcloud，springboot 版本说明请参考

``
https://github.com/alibaba/spring-cloud-alibaba/wiki/%E7%89%88%E6%9C%AC%E8%AF%B4%E6%98%8E
``

按照上面的配置之后，可以使用如下命令生成一个镜像

```shell
mvn clean package docker:build -DskipTests
```

将生成的镜像推送到镜像注册中心，通过pushImage标签

```shell
mvn clean package docker:build -DskipTests -DpushImage
```

如果推送制定tags 的镜像，可使用pushImageTag标签

```shell
mvn clean package docker:build -DpushImageTag
```

为了是的上述的命令执行成功，需要在pom中配置imageTag,可以配置多个imageTag

以上示例，当我们执行mvn package时，执行 build、tag 操作，当执行mvn deploy时，执行build、tag、push 操作。如果我们想跳过 docker 某个过程时，只需要：

-DskipDockerBuild 跳过 build 镜像 -DskipDockerTag 跳过 tag 镜像 -DskipDockerPush 跳过 push 镜像 -DskipDocker 跳过整个阶段 例如：我们想执行 package
时，跳过 tag 过程，那么就需要mvn package -DskipDockerTag。 ———————————————— 版权声明：本文为CSDN博主「哎_小羊_168」的原创文章，遵循CC 4.0
BY-SA版权协议，转载请附上原文出处链接及本声明。 原文链接：https://blog.csdn.net/aixiaoyang168/article/details/77453974