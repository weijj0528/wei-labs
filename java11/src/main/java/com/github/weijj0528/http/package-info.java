/**
 * 标准 HTTP Client 升级
 * Java 11 对 Java 9 中引入并在 Java 10 中进行了更新的 Http Client API 进行了标准化，
 * 在前两个版本中进行孵化的同时，Http Client 几乎被完全重写，并且现在完全支持异步非阻塞。
 * 新版 Java 中，Http Client 的包名由 jdk.incubator.http 改为 java.net.http，
 * 该 API 通过 CompleteableFutures 提供非阻塞请求和响应语义，可以联合使用以触发相应的动作，并且 RX Flow 的概念也在 Java 11 中得到了实现。
 * 现在，在用户层请求发布者和响应发布者与底层套接字之间追踪数据流更容易了。这降低了复杂性，并最大程度上提高了 HTTP / 1 和 HTTP / 2 之间的重用的可能性。
 */
package com.github.weijj0528.http;