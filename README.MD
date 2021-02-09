### Java各版本语言特性实验

#### 背景

1991年1991年4月，由James Gosling博士领导的绿色计划（Green Project）开始启动

#### 1995-5-23 Oak语言改名为Java，提出 “Write Once,Run Anywhere”的口号。

#### java 1.0

- Su - Sun Classic VM（虚拟机）
- Applet（java小应用程序）
- AWT（java图形设计）n Classic VM（虚拟机）
- Applet（java小应用程序）
- AWT（java图形设计）

#### java 1(JDK1.1 1997-2-19)

- JAR（jar包）
- JDBC(Java DataBase Connectivity)（连接数据库）
- JavaBeans（java规范）
- RMI（远程调用）
- Inner Class（内部类）
- Reflection（反射）

#### java 2(JDK1.2 1998-12-4)

- J2SE（改名为J2SE）
- J2EE（改名为J2EE）
- J2ME（改名为J2ME）
- JIT（即时编译技术）
- Java Plug-In（运行插件）
- EJB（J2EE的规范）
- Java IDL（平台对象请求代理体系结构）
- Collections（集合）
- 字符串常量做内存映射
- 对打包的Java文件进行数字签名
- 控制授权访问系统资源的策略工具
- JDBC中引入可滚动结果集,BLOB,CLOB,批量更新和用户自定义类型
- 在Applet中添加声音支持

#### java 3(JDK1.3 2000-5-8)

- 数学运算
- Timer API（时间）
- Java Sound API（声音）
- CORBA IIOP实现RMI的通信协议
- Java 2D新特性
- jar文件索引

#### java 4(JDK 1.4 2002-2-13)

- 正则表达式
- 异常链
- NIO（高级流）
- Logging （日志功能）
- XML解析器
- XSLT转换器
- XML处理
- Java打印服务
- Java Web Start
- JDBC 3.0（jdbc高级）
- 断言
- Preferences（可以操作系统的高级功能）
- IPV6
- Imgae I/O（图片流）

#### 5(JDK 1.5 2004-9-30)

- 自动装箱拆箱
- 泛型
- 元数据
- Introspector（内省）
- enum（枚举）
- 静态引入
- 可变长参数（Varargs）
- foreach（高级虚幻）
- JMM（内存模型）
- concurrent（并发包）

#### java 6(JDK6 2006-12-11)

- 命名方式变更
- 脚本语言
- 编译API和微型HTTP服务器API
- 锁与同步
- 垃圾收集
- 类加载
- JDBC 4.0（jdbc高级）
- Java Compiler （Java™ 编程语言编译器的接口）
- 可插拔注解
- Native PKI(公钥基础设)
- Java GSS （通用安全服务）
- Kerberos （ 一种安全认证的系统）
- LDAP （LDAP ）
- Web Services （web服务）

#### java 7(JDK7 2011-7-28)

- switch语句块中允许以字符串作为分支条件
- 创建泛型对象时应用类型推断
- try-with-resources（一个语句块中捕获多种异常）
- null值得自动处理
- 数值类型可以用二进制字符串表示
- 引入Java NIO.2开发
- 动态语言支持
- 安全的加减乘除
- Map集合支持并发请求

#### java 8(JDK8 2014-3-18)

1. Lambda表达式
2. 改进的类型推断
3. Java类型的注释
4. 重复注释
5. 方法参数反射
6. TLS 1.1和TLS 1.2默认启用
7. 有限的doPrivileged
8. 基于密码加密的更强算法
9. JSSE服务器中的SSL / TLS服务器名称指示（SNI）扩展支持
10. 支持AEAD算法
11. KeyStore增强功能
12. SHA-224消息摘要
13. 对NSA Suite B加密的增强支持
14. 对于高熵随机数生成更好的支持
15. 新PKIXRevocationChecker类
16. 适用于Windows的64位PKCS11
17. Kerberos中的新rcache类型5重放缓存
18. 协议转换和约束委派的Kerberos 5
19. 默认情况下禁用弱加密
20. GSS-API / Kerberos 5机制的未绑定SASL
21. 用于多个主机名的SASL服务
22. JNI桥接到Mac OS X上的本机JGSS
23. 在SunJSSE提供程序中支持更强大的临时DH密钥
24. 支持JSSE中的密码套件首选项自定义
25. 全新的JavaFX（详情请点击这里）
26. java工具的操作增强
27. Unicode增强功能，包括对Unicode 6.2.0的支持
28. 采用Unicode CLDR数据和java.locale.providers系统属性
29. 新日历和区域设置API
30. 引入新的Date Time API
31. Nashorn JavaScript引擎
32. 并行阵列排序
33. 标准编码和解码Base64
34. 无符号算术支持
35. 新的并发
36. Java XML技术增强功能
37. 虚拟机操作增强

#### java 9(JDK9 2017-9-22)

1. 模块系统：模块是一个包的容器，Java 9 最大的变化之一是引入了模块系统（Jigsaw 项目）。
2. REPL (JShell)：交互式编程环境。
3. HTTP 2 客户端：HTTP/2标准是HTTP协议的最新版本，新的 HTTPClient API 支持 WebSocket 和 HTTP2 流以及服务器推送特性。
4. 改进的 Javadoc：Javadoc 现在支持在 API 文档中的进行搜索。另外，Javadoc 的输出现在符合兼容 HTML5 标准。
5. 多版本兼容 JAR 包：多版本兼容 JAR 功能能让你创建仅在特定版本的 Java 环境中运行库程序时选择使用的 class 版本。
6. 集合工厂方法：List，Set 和 Map 接口中，新的静态工厂方法可以创建这些集合的不可变实例。
7. 私有接口方法：在接口中使用private私有方法。我们可以使用 private 访问修饰符在接口中编写私有方法。
8. 进程 API: 改进的 API 来控制和管理操作系统进程。引进 java.lang.ProcessHandle 及其嵌套接口 Info 来让开发者逃离时常因为要获取一个本地进程的 PID 而不得不使用本地代码的窘境。
9. 改进的 Stream API：改进的 Stream API 添加了一些便利的方法，使流处理更容易，并使用收集器编写复杂的查询。
10. 改进 try-with-resources：如果你已经有一个资源是 final 或等效于 final 变量,您可以在 try-with-resources 语句中使用该变量，而无需在 try-with-resources
    语句中声明一个新变量。
11. 改进的弃用注解 @Deprecated：注解 @Deprecated 可以标记 Java API 状态，可以表示被标记的 API 将会被移除，或者已经破坏。
12. 改进钻石操作符(Diamond Operator) ：匿名类可以使用钻石操作符(Diamond Operator)。
13. 改进 Optional 类：java.util.Optional 添加了很多新的有用方法，Optional 可以直接转为 stream。
14. 多分辨率图像 API：定义多分辨率图像API，开发者可以很容易的操作和展示不同分辨率的图像了。
15. 改进的 CompletableFuture API ： CompletableFuture 类的异步机制可以在 ProcessHandle.onExit 方法退出时执行操作。
16. 轻量级的 JSON API：内置了一个轻量级的JSON API
17. 响应式流（Reactive Streams) API: Java 9中引入了新的响应式流 API 来支持 Java 9 中的响应式编程。

#### java 10(JDK10 2018-7-17)

1. 基于时间的发布版本控制（JEP 322）
2. 局部变量型推理（JEP 286）
3. 实验性基于Java的JIT编译器（JEP 317）
4. 应用类 - 数据共享（JEP 310）
5. G1的并行全GC（JEP 307）
6. 垃圾收集器接口（JEP 304）
7. 其他Unicode语言标记扩展（JEP 314）
8. 根证书（JEP 319）
9. 线程局部握手（JEP 312）
10. 备用存储设备上的堆分配（JEP 316）
11. 删除Native-Header生成工具 - javah（JEP 313）
12. 将JDK Forest整合到单个存储库中（JEP 296）
13. API更改

#### java 11(JDK11 2018-7-17)
