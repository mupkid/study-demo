# HttpClient 学习

[官网](https://hc.apache.org/index.html)

[Http Client 5 的 Java Docs](https://hc.apache.org/httpcomponents-client-5.2.x/current/httpclient5/apidocs/)

[Http Client 4 的 Java Docs](https://www.javadoc.io/doc/org.apache.httpcomponents/httpclient/latest/index.html)

## 创建 HttpClient 实例

使用工具类`HttpClients`来创建`HttpClient`，有两种方式。

```java
// 事先定义好了各种默认参数
HttpClient httpClient=HttpClients.createDefault();
```

入门学习时经常会用这个方法来创建实例。包括在网上很多工具类 HttpClientUtils 文章都会在方法内用这种方法创建实例。

但是创建一个 HttpClient 是很耗时的，且 HttpClient 是线程安全的，程序中只使用一个即可。 而且在实际工作中，我们需要根据业务场景对 HttpClient 进行调优，因此更常用下面这种方式。

```java
HttpClients.custom()
    .setConnectionManager(poolHttpClientConnectionManager)
    .setDefaultRequestConfig(requestConfig)
    .build();
```

接下来会逐渐说明各种类与参数的作用。

## Http 连接池

`ClientConnectPoolManager`维护一个`HttpClientConnections`，并提供来自线程的多个请求。

常用实现类是`PoolingHttpClientConnectionManager`。

| 参数               | 作用                           |
| ------------------ | ------------------------------ |
| maxTotal           | 连接池的最大连接数             |
| defaultMaxPerRoute | 每个路由每次并行接收的请求数量 |

## RequestConfig

| 参数                     | 作用                                       |
| ------------------------ | ------------------------------------------ |
| connectionRequestTimeout | 从连接池中获取连接的超时时间，单位毫秒     |
| connectTimeout           | 客户端与服务器建立连接的超时时间，单位毫秒 |
| socketTimeout            | 两次数据包的最大相隔时间，单位毫秒         |
