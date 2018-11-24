# SecKillShop
主要实现一个商品秒杀的功能，梳理如何应对高并发的的技术的思路。

[![Twitter](https://img.shields.io/badge/SpringBoot-2.1.0-yellow.svg)](https://wwww.chengxiaoxiao.com/) 
[![Twitter](https://img.shields.io/badge/JSR%20303-1.0-brightgreen.svg)](https://github.com/iquanzhan/SecKillShop) 
[![Twitter](https://img.shields.io/badge/MyBatis-1.3.1-green.svg)](https://github.com/iquanzhan/SecKillShop/) 
[![Twitter](https://img.shields.io/badge/RabbitMQ-3.7.9-yellowgreen.svg)](https://github.com/iquanzhan/) 
[![Twitter](https://img.shields.io/badge/Redis-5.0.0-orange.svg)](https://github.com/iquanzhan/)


### 所用技术
1.后端：SpringBoot、JSR303、MyBatis

2.前端：Thymeleaf、BootStrap、Jquery

3.中间件：RabbitMQ、Redis、Druid

### 项目搭建
##### 1.搭建SpringBoot
    1.添加maven
        <parent>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-parent</artifactId>
            <version>1.5.9.RELEASE</version>
        </parent>
        <properties>
            <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        </properties>

        <dependencies>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-web</artifactId>
            </dependency>
        </dependencies>


##### 2.集成thymeleaf
        1.导入依赖
            <dependency>
                  <groupId>org.springframework.boot</groupId>
                  <artifactId>spring-boot-starter-thymeleaf</artifactId>
            </dependency>

        2.添加配置项：
          spring.thymeleaf.cache=false
          spring.thymeleaf.content-type=text/html
          spring.thymeleaf.enabled=true
          spring.thymeleaf.encoding=UTF-8
          spring.thymeleaf.mode=HTML5
          spring.thymeleaf.prefix=classpath:/templates/
          spring.thymeleaf.suffix=.html

        3.添加thymeleaf模版
          <!DOCTYPE HTML>
          <html xmlns:th="http://www.thymeleaf.org">
          <head>
            <title>hello</title>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
          </head>
          <body>
            <p th:text="'hello:'+${name}" ></p>
          </body>
          </html>
##### 3.集成MyBatis

      1.添加依赖
        <dependency>
          <groupId>org.mybatis.spring.boot</groupId>
          <artifactId>mybatis-spring-boot-starter</artifactId>
          <version>1.3.1</version>
        </dependency>
      2.添加配置
        # mybatis
        mybatis.type-aliases-package=com.chengxiaoxiao.seckillshop.domain
        mybatis.configuration.map-underscore-to-camel-case=true
        mybatis.configuration.default-fetch-size=100
        mybatis.configuration.default-statement-timeout=3000
        mybatis.mapperLocations = classpath:com/chengxiaoxiao/seckillshop/dao/*.xml

##### 4.添加MySql和Druid
        1.添加依赖
            <dependency>
              <groupId>mysql</groupId>
              <artifactId>mysql-connector-java</artifactId>
            </dependency>

            <dependency>
              <groupId>com.alibaba</groupId>
              <artifactId>druid</artifactId>
              <version>1.0.5</version>
            </dependency>
        2.添加配置
            spring.datasource.url=jdbc:mysql://localhost:3306/miaosha?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&useSSL=false&serverTimezone=GMT%2B8
            spring.datasource.username=root
            spring.datasource.password=123456
            spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
            spring.datasource.type=com.alibaba.druid.pool.DruidDataSource
            spring.datasource.filters=stat
            spring.datasource.maxActive=2
            spring.datasource.initialSize=1
            spring.datasource.maxWait=60000
            spring.datasource.minIdle=1
            spring.datasource.timeBetweenEvictionRunsMillis=60000
            spring.datasource.minEvictableIdleTimeMillis=300000
            spring.datasource.validationQuery=select 'x'
            spring.datasource.testWhileIdle=true
            spring.datasource.testOnBorrow=false
            spring.datasource.testOnReturn=false
            spring.datasource.poolPreparedStatements=true
            spring.datasource.maxOpenPreparedStatements=20
            logging.leve.com.chengxiaoxiao.seckillshop=debug

##### 5.安装redis
        1.官网：https://redis.io/download
       2.解压安装：
          1.解压文件：tar -zxvf redis-5.0.0.tar.gz
          2.移动到安装目录：mv redis-5.0.0 /usr/local/redis
          3.编译一下：make -j 4
            编译依赖gcc，安装如下：
              1.yum  install  gcc
              2.验证是否安装成功：rpm -qa |grep gcc
          4.make install
          5.修改配置文件
            vi redis.config
              1.修改可访问IP：修改bind 127.0.0.1  为bind 0.0.0.0
              2.修改后台启用：daemonize yes
              3.添加密码：requirepass
          6.运行：redis-server ./redis.conf
          7.重启：redis-cli     shutdown save
          8.命令行登录：auth password
          9.修改redis为系统服务
            1.cd utils
            2.  ./install_server.sh
            config:  /usr/local/redis/redis.config
            log:	/usr/local/redis/redis.log
            data:	/usr/local/redis/data
          10.查看后台进程是否存在
            1.查看进程：ps -ef |grep redis
            2.端口是否在监听：netstat -lntp | grep 6379
        3.添加依赖
              <dependency>
                <groupId>redis.clients</groupId>
                <artifactId>jedis</artifactId>
              </dependency>

              <dependency>
                <groupId>com.alibaba</groupId>
                <artifactId>fastjson</artifactId>
                <version>1.2.38</version>
              </dependency>
        4.添加配置：
              #redis            
              redis.host=192.168.220.128
              redis.port=6379
              redis.timeout=3
              redis.password=123456
              redis.poolMaxTotal=10
              redis.poolMaxIdle=10
              redis.poolMaxWait=3
              
             
## 错误记录
1.
    (1).报错
    
```
    An attempt was made to call the method javax.servlet.ServletContext.getVirtualServerName()Ljava/lang/String; but it does not exist. Its class, javax.servlet.ServletContext, is available from the following locations:

    jar:file:/Users/ac/.gradle/caches/modules-2/files-2.1/javax.servlet/servlet-api/2.4/3fc542fe8bb8164e8d3e840fe7403bc0518053c0/servlet-api-2.4.jar!/javax/servlet/ServletContext.class
    jar:file:/Users/ac/.gradle/caches/modules-2/files-2.1/org.apache.tomcat.embed/tomcat-embed-core/8.5.34/a038040d68a90397f95dd1e11b979fe364a5000f/tomcat-embed-core-8.5.34.jar!/javax/servlet/ServletContext.class
    
```


    (2).解决：servlet-api版本太低，升级即可，
        1.下载tomcat.zip包（https://tomcat.apache.org/download-80.cgi），
        2.找到lib/servlet-api.jar，复制到java jdk目录下/jre/lib/ext下

