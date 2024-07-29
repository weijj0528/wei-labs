/**
 * ZGC 即 Z Garbage Collector（垃圾收集器或垃圾回收器），这应该是 Java 11 中最为瞩目的特性，没有之一。
 * ZGC 是一个可伸缩的、低延迟的垃圾收集器，主要为了满足如下目标进行设计：
 * - GC 停顿时间不超过 10ms
 * - 即能处理几百 MB 的小堆，也能处理几个 TB 的大堆
 * - 应用吞吐能力不会下降超过 15%（与 G1 回收算法相比）
 * - 方便在此基础上引入新的 GC 特性和利用 colord
 * - 针以及 Load barriers 优化奠定基础
 * - 当前只支持 Linux/x64 位平台
 * 目前 ZGC 还处于实验阶段，目前只在 Linux/x64 上可用，如果有足够的需求，将来可能会增加对其他平台的支持。
 * 同时作为实验性功能的 ZGC 将不会出现在 JDK 构建中，除非在编译时使用 configure 参数：--with-jvm-features=zgc 显式启用。
 * -XX：+ UnlockExperimentalVMOptions -XX：+ UseZGC -Xmx10g
 */
package com.github.weijj0528.zgc;