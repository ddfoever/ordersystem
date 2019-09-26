##使用微服务搭建订餐系统

+ 订餐系统的需求分析
 大致功能模块
 客户端：针对普通用户，`用户登录`，`用户退出`，`菜品订购`，`我的订单`
 
 + 后台管理系统：针对管理员，`管理员登录`，`管理员退出`，`添加菜品`，`删除菜品`，`修改菜品`，`查询菜品`，`订单处理`
 `添加用户`，`查询用户`，`删除用户`；
 
 
+ 根据业务拆分服务
account 提供账户服务：管理员和用户的登录和退出
menu 菜单服务 添加菜品，删除菜品，修改菜品 查询菜品
order 订单服务 添加订单，查询订单 删除订单，处理订单；
user 用户服务 添加用户，查询用户，删除用户

分离一个服务消费来调用这四个微服务 ，服务消费者包含客户端和后台接口、后台管理系统页面和后台接口，通过Feign来实现负载均衡。

所以有四个服务提供者和一个服务消费者在注册中心进行注册，并且通过配置中心来对配置文件进行管理；
一共七个服务 


### 开始搭建微服务工程环境
1. 搭建父工程环境 为各个微服务
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.ddfoever</groupId>
    <artifactId>order-system</artifactId>
    <version>1.0-SNAPSHOT</version>
    <!--spring boot dependency base on 2.0.7 RELEASE-->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.0.7.RELEASE</version>
    </parent>
    <!--配配common的地方 统一使用Finchley.R2 version
    <properties>
        <spring-cloud.version>Finchley.R2</spring-cloud.version>
    </properties>-->

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
    </dependencies>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>Finchley.R2</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

</project>
```
2. 搭建注册中心 `eureka-server`

`pom.xml`
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>order-system</artifactId>
        <groupId>com.ddfoever</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>eureka-server</artifactId>

    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
        </dependency>
        
    </dependencies>

</project>
```
>注册中心配置 `application.yml`
```yaml
server:
  port: 8761
eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
    register-with-eureka: false #if register by itself
    fetch-registry: false #if synchronzied data from other eureka server 
```
### 搭建config server 中心


 
