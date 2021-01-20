#Sentinel

### 下载sentinel
https://github.com/alibaba/Sentinel/releases

### 启动sentinel
```
java 
-Dsentinel.dashboard.auth.username=admin // 使用用户名登录
-Dsentinel.dashboard.auth.password=admin // 使用密码登录，如果未指定用户名和密码 默认是sentinel sentinel
-Dserver.port=8080 
-Dcsp.sentinel.dashboard.server=localhost:8080 
-Dproject.name=sentinel-dashboard 
-jar sentinel-dashboard-1.7.2.jar
```
###springboot项目注册到dashboard中
启动时加入命令 -Dcsp.sentinel.dashboard.server=localhost:8080
idea启动时在VM options中配置
