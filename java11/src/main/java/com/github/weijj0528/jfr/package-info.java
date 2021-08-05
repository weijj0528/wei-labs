/**
 * Java 语言中的飞行记录器类似飞机上的黑盒子，是一种低开销的事件信息收集框架，主要用于对应用程序和 JVM 进行故障检查、分析。飞行记录器记录的主要数据源于应用程序、JVM 和 OS，这些事件信息保存在单独的事件记录文件中，故障发生后，能够从事件记录文件中提取出有用信息对故障进行分析。
 *
 * 启用飞行记录器参数如下：
 *
 * -XX:StartFlightRecording
 *
 * 也可以使用 bin/jcmd 工具启动和配置飞行记录器：
 *
 $ -$ jcmd <pid> JFR.start
 $ -$ jcmd <pid> JFR.dump filename=recording.jfr
 $ -$ jcmd <pid> JFR.stop
 */
package com.github.weijj0528.jfr;