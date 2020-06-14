# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Security](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#boot-features-security)
* [JDBC API](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#boot-features-sql)
* [MyBatis Framework](https://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Accessing Relational Data using JDBC with Spring](https://spring.io/guides/gs/relational-data-access/)
* [Managing Transactions](https://spring.io/guides/gs/managing-transactions/)
* [MyBatis Quick Start](https://github.com/mybatis/spring-boot-starter/wiki/Quick-Start)

### Spring Security 过滤器链
Security Context Persistence Filter
            ↓    
BasicAuthenticationFilter
UsernamePasswordAuthenticationFilter
RememberMeAuthenticationFilter
SmsCodeAuthenticationFilter
SocialAuthenticationFilter
OAuth2AuthenticationProcessingFilter
OAuth2ClientAuthenticationProcessingFilter
            ↓
Anonymous AuthenticationFilter
            ↓
Exception Translation Filter
            ↓
Filter Security Interceptor
            ↓
        REST API
        
### Spring Security 与 session 的创建和使用
always:     当前请求没有session，自动创建一个
never:      如何已经存在则使用该session，spring security 不会主动创建
ifRequired: 默认选项，在需要时才创建session
stateless:  不创建session，适合无状态应用

### session 保护
默认情况下，Spring Security 启用了 migrateSession保护
· 即，对同一个cookies 的 sessionid 用户每次登录都将创建一个新的 HTTP 会话，旧的会话会失效，并且就会话属性将被复制
· 设置为"none"时，原始会话不会无效
· 设置"newSession"后，将创建一个干净的会话，不会复制就会话中的任何属性
    ...
    .sessionManagement()
    .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
    .invalidSessionUrl("/login.html")// session 失效时 指定重新登录的页面
    .sessionFixation().migrateSession()
    ...
    
### Cookie 的安全
· httpOnly: 如果为true，则浏览器脚本 将无法访问cookie
· secure: 如果为true，仅通过HTTPS链接发送cookie，HTTP无法携带cookie
    ...
    server:
      port: 8080
      servlet:
        session:
          timeout: 10s
          cookie:
            http-only: false
            secure: false
    ...

### session 超时 和 多端登录踢出机制
· 实现 SessionInformationExpiredStrategy
    ...
    .maximumSessions(1) // 最大允许同时登录数为一个
    .maxSessionsPreventsLogin(false)// 允许再次登录，之前的登录会下线
    .expiredSessionStrategy(new ExpiredSessionStrategy())
    ...


### Role-Based Access Control
· 用户:系统接口 及 访问的操作者
· 权限:能够访问某个接口或做某操作的授权资格
· 角色:具有一类相同操作权限的用户的总称

