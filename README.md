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

##### 6.防火墙开启端口
1. 获取zone名称：firewall-cmd --get-active-zones
2. 执行防火墙开启端口：firewall-cmd --zone=public --add-port=6379/tcp --permanent
3. 重启防火墙：firewall-cmd --reload
4. 查看端口是否开启：firewall-cmd --query-port=6379/tcp
5. 查看服务：chkconfig --list | grep redis

##### 7.添加集成MD5加密
1. 添加依赖：
```
<dependency>
    <groupId>commons-codec</groupId>
    <artifactId>commons-codec</artifactId>
</dependency>
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-lang3</artifactId>
    <version>3.6</version>
</dependency>
```
	
2.调用方法：

	DigestUtils.md5Hex(src);

##### 8.添加JSR303校验
###### 1.添加依赖
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
``` 
###### 2.Controller中Vo上添加注解@Valid

###### 3.自定义注解
    
>1.添加注解类实现方法，注解类模板：
```
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {IsMobileValidator.class })
public @interface IsMobile {

    String message() default "手机号码格式错误";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
```

>2.添加注解校验规则类
```
public class IsMobileValidator implements ConstraintValidator<IsMobile, String> {
    @Override
    public void initialize(IsMobile constraintAnnotation) {
        
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return true;
    }
}
```
2.使用
> Vo对应属性上增加注解，实现校验

#### 9.优化登录成功后，需要多次获取Cookie
> 1.自定义类，继承自WebMvcConfigurerAdapter，并添加@Configuration注解,使用参数解析器，重写addArgumentResolvers方法，添加自定义的解析器

> 2.自定义参数解析器，实现HandlerMethodArgumentResolver接口，重写supportsParameter和resolveArgument。

> 3.这样就可以直接在参数中增加对应的user，直接获取值了

# Jemter压力测试
> 1.官网：[http://jmeter.apache.org/](http://jmeter.apache.org/)

> 2.下载地址[http://jmeter.apache.org/download_jmeter.cgi](http://jmeter.apache.org/download_jmeter.cgi)

> 3.修改界面为中文：

        找到jmeter下的bin目录，打开jmeter.properties 文件
        第三十七行修改为
        language=zh_CN

> 3.基本步骤：
    
        1.添加线程组
        2.添加HTTP请求配置器：
        
# SpringBoot 打war包
> 1.添加依赖

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
            <scope>provided</scope>
        </dependency>
> 2.添加plugins

    <build>
        <finalName>${project.artifactId}</finalName>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
> 3.修改打包方式为war

    <packaging>war</packaging>
    
> 4.mainApplication需要继承SpringBootServletInitializer,并重写configure方法

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MainApplication.class);
    }
> 5.项目根目录打包：mvn clean package
> 6.放tomcat目录下，启动（解决需要增加应用名）
    1.可以把context设置为空
    2.直接放到ROOT目录下
    
# 页面优化
一、页面缓存
   
   把页面渲染好的页面缓存放入redis
   
    实现代码：
    
    1.SpringBoot 2.0写法
    
    //取缓存
    WebContext ctx = new WebContext(request, response, request.getServletContext(), request.getLocale(), model.asMap());
    html = thymeleafViewResolver.getTemplateEngine().process("goodlist", ctx);
    if (!StringUtils.isEmpty(html)) {
        redisService.set(GoodsKey.getGoodList, "", html);
    }
    
    2.SpringBoot 1.0写法
    
    SpringWebContext context = new SpringWebContext(request,response,request.getServletContext(),request.getLocale(),model.asMap(),applicationContext);
    String html = thymeleafViewResolver.getTemplateEngine().process("goods_list",context);

二、对象缓存
> 1.在Service中，取Dao的数据时，先取redis的数据，如果redis中没有才取数据库的数据。
当数据库数据修改时，自动修改redis的数据

三、页面静态化
> 1.vue，angular进行页面静态化，减少服务器请求 





             
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

