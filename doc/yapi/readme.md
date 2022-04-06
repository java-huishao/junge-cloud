### 进入mongodb容器，新增yapi用户

````shell
docker -exec -it mongodb容器id bash
````

### 连接数据库

````
mongo localhost:27017
use admin;
db.auth("root", "root");
````

#### 创建 yapi 数据库

````
use yapi;
````

### 创建给 yapi 使用的账号和密码，限制权限

````
db.createUser({
  user: 'yapi',
  pwd: 'yapi',
  roles: [
 { role: "dbAdmin", db: "yapi" },
 { role: "readWrite", db: "yapi" }
  ]
});
````

### 退出 Mongo Cli

````
exit
````

### 退出容器

````
exit
````

### 相关文档

````
https://blog.51cto.com/u_15155097/2731009
````
ymfe.org