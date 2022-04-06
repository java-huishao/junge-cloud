安装jar包

```shell
mvn install:install-file -Dfile=E:\aspose-words-15.8.0-jdk16.jar -DgroupId=com.aspose -DartifactId=aspose-words -Dversion=15.8.0-jdk16 -Dpackaging=jar
```
mvn install:install-file -Dfile=E:\66nao-work\leaf-1.0.0-SNAPSHOT.jar -DgroupId=com.bj66nao.prod.center -DartifactId=leaf -Dversion=1.0.0-SNAPSHOT -Dpackaging=jar



mvn install:install-file -Dfile=E:\66nao-work\leaf-1.0.0-SNAPSHOT.jar -DgroupId=com.bj66nao.prod.center -DartifactId=leaf -Dversion=1.0.0-SNAPSHOT -Dpackaging=jar
mvn deploy:deploy-file -Dfile=E:\66nao-work\leaf-1.0.0-SNAPSHOT.jar -DgroupId=com.bj66nao.prod.center -DartifactId=leaf -Dversion=1.0.0-SNAPSHOT -Dpackaging=jar -Durl=https://zhijingling-maven.pkg.coding.net/repository/66nao-maven-repository/public/ -Drepo.login=houqijun -Drepo.pwd=houqijun723

