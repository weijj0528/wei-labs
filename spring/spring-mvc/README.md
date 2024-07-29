## Spring MVC

##### Spring mvc基础环境搭建与注意事项
1. POM依赖
```xml
<dependencies>
    <!-- Step1：SpringMVC依赖-->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>${spring.version}</version>
    </dependency>
    <!--
        Step1.1：若项目需要返回JSON格式响应还需要引入jackson
        若出现java.lang.ClassNotFoundException: com.fasterxml.jackson.core.util.DefaultPrettyPrinter错误
        请检查版本是否兼容，以及IDE编译时是否有引入依赖（IDEA：Project Structure->Artifacts）
     -->
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.9.8</version>
    </dependency>
    <!-- servlet -->
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>javax.servlet-api</artifactId>
        <version>3.1.0</version>
        <scope>provided</scope>
    </dependency>
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>jstl</artifactId>
        <version>1.2</version>
        <scope>provided</scope>
    </dependency>
</dependencies>
```
2. web.xml配置
```xml
<web-app>
    <display-name>Archetype Created Web Application</display-name>

    <!--
        *Spring配置，与SpringMVC的区别，建议直接使用SpringMVC配置文件
        如使用该配置需要注意注解的扫描范围，不要有交集，
    -->
    <!--<context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:applicationContext.xml</param-value>
    </context-param>-->

    <!--Step2：配置Servlet-->
    <servlet>
        <servlet-name>DispatcherServlet</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <!--Step3：创建Servlet配置文件，并设置初始化参数-->
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:applicationContext.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>DispatcherServlet</servlet-name>
        <!-- ***进入DispatcherServlet后该部分url与ContextUrl不参与handler的匹配 -->
        <url-pattern>/mvc/*</url-pattern>
    </servlet-mapping>

    <!--Step2.1：首页，可不配置-->
    <welcome-file-list>
        <welcome-file>index.jsp</welcome-file>
    </welcome-file-list>

</web-app>
```
3. spring配置
```xml
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <!--Step4：Spring配置-->
    <!--  非注解方式配置处理器  -->
    <bean name="/one" class="com.weiun.spring.mvc.controller.OneController"/>
    <bean name="/second" class="com.weiun.spring.mvc.controller.SecondController"/>
    <bean name="/third" class="com.weiun.spring.mvc.controller.ThirdController">
        <property name="cacheSeconds" value="0"/>
    </bean>
    
    <!-- *开启注解扫描-->
    <context:annotation-config/>
    <!-- *组件扫描的包路径-->
    <context:component-scan base-package="com.weiun.spring.mvc.controller"/>

    <!-- 如需要返回JSON格式响应，需要配置转换器，默认使用Jackson，可配置其他转换器，如Fastjson-->
    <mvc:annotation-driven>
        <mvc:message-converters>
            <bean class="org.springframework.http.converter.StringHttpMessageConverter">
                <property name="supportedMediaTypes">
                    <list>
                        <value>text/html;charset=UTF-8</value>
                        <value>text/json;charset=UTF-8</value>
                        <value>application/json;charset=UTF-8</value>
                    </list>
                </property>
            </bean>
            <!--*Jackson转换器默认可以不配置，如需要改成其他如FastJSON则需要显式配置-->
            <bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
                <property name="supportedMediaTypes">
                    <list>
                        <value>text/html;charset=UTF-8</value>
                        <value>text/json;charset=UTF-8</value>
                        <value>application/json;charset=UTF-8</value>
                    </list>
                </property>
            </bean>
        </mvc:message-converters>
    </mvc:annotation-driven>

    <!--
        实图解析器，如前后端分离开发可不配置，如非JSP可配置为其他视图解析器
        注意配置：{prefix}{viewNames}{suffix} '/'的位置
        eg:/WEB-INF/www/hello.jsp  /的位置可以配置在prefix中也可以在viewNames中，建议在prefix中
    -->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="contentType" value="text/html;charset=UTF-8"/>
        <property name="viewNames">
            <list>
                <value>**</value>
            </list>
        </property>
        <property name="prefix" value="/WEB-INF/www/"/>
        <property name="suffix" value=".jsp"/>
        <property name="order" value="256"/>
    </bean>

</beans>
```
#### Spring六种处理器的配置
0. ZeroController

原生Servlet的支持，可继承HttpServlet或实现Servlet接口，该种方法需要在Spring配置文件中配置其请求路径并配置处理适配器
```java
public class OneController implements HttpRequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().write("Hello One!");
    }
}
```
```xml
<bean name="/one" class="com.weiun.spring.mvc.controller.OneController"/>
<!-- 原生Servlet处理适配器 -->
<bean class="org.springframework.web.servlet.handler.SimpleServletHandlerAdapter"/>
```

1. OneController

实现HttpRequestHandler#handleRequest接口方法，该种方法需要在Spring配置文件中配置其请求路径
```java
public class OneController implements HttpRequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().write("Hello One!");
    }
}
```
```xml
<bean name="/one" class="com.weiun.spring.mvc.controller.OneController"/>
```
2. SecondController
   
实现Controller#handleRequest接口方法，该种方法同样需要在Spring配置文件中配置其请求路径
```java
public class SecondController implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.getWriter().write("Hello Second!");
        return null;
    }
}
```
```xml
<bean name="/second" class="com.weiun.spring.mvc.controller.SecondController"/>
```
3. ThirdController
   
继承 AbstractController并实现handleRequestInternal抽象方法，该种方法为方法2扩展，增加了多种配置项同样需要在Spring配置文件中配置其请求路径
```java
public class ThirdController extends AbstractController {
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.getWriter().write("Hello Third!");
        return null;
    }
}
```
```xml
<bean name="/third" class="com.weiun.spring.mvc.controller.ThirdController">
    <property name="cacheSeconds" value="0"/>
</bean>
```
4. DemoController

通过@Controller，@RequestMapping配置某一方法为处理器，也是目前主流的使用方法，需要在配置文件中开启注解扫描，并确保注解的类能能够被扫描到

```java
@Controller
@RequestMapping("/demo")
public class DemoController {

    @RequestMapping("/hello")
    public String hello() {
        System.out.println("hello");
        return "hello";
    }

}
```
```xml
<!-- *开启注解扫描-->
<context:annotation-config/>
<!-- *组件扫描的包路径-->
<context:component-scan base-package="com.weiun.spring.mvc.controller"/>
```
5. RestDemoController

通过@RestController注解已支持Rest，该种方法扩展于 @Controller 同 @Controller + @ResponseBody方式不用进行视图的渲染

```java
@RestController
@RequestMapping("rest")
public class RestDemoController {
    @GetMapping("/hello")
    public Map<String, String> hello() {
        Map<String, String> map = new HashMap<>(8);
        map.put("name", "Hello World!");
        return map;
    }

}
```
启动应用访问（ 注意端口与ContextUrl的配置）
- http://localhost:8080/mvc/one
- http://localhost:8080/mvc/second
- http://localhost:8080/mvc/third
- http://localhost:8080/mvc/four/hello
- http://localhost:8080/mvc/five/hello

