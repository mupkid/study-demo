package org.ohx.studyhttpclient;

import com.alibaba.fastjson2.JSONObject;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

/**
 * @author mudkip
 * @date 2022/12/27
 */
public final class HttpClientUtils {
    /**
     * 单例
     */
    private static final HttpClient httpClient;

    static {
        httpClient = HttpClients.custom()
            .setConnectionManager(buildPoolConnectionManager())
            .setKeepAliveStrategy(buildConnectionKeepAliveStrategy())
            .setDefaultRequestConfig(buildRequestConfig())
            .setRetryHandler(buildHttpRequestRetryHandler())
            .build();
    }

    public static JSONObject get(String url) {
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String jsonString = EntityUtils.toString(response.getEntity());
                return JSONObject.parseObject(jsonString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }

    private static PoolingHttpClientConnectionManager buildPoolConnectionManager() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(
            RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", buildSSLConnectionFactory())
                .build());
        // 连接池最大连接数
        connectionManager.setMaxTotal(500);
        // 每个 http 路由最大连接数
        connectionManager.setDefaultMaxPerRoute(50);

        return connectionManager;
    }

    private static SSLConnectionSocketFactory buildSSLConnectionFactory() {
        SSLConnectionSocketFactory socketFactory;
        try {
            SSLContext sslContext = SSLContexts.custom()
                .setProtocol("TLSv1.2")
                .setKeyStoreType("jks")
                .loadTrustMaterial((c, s) -> true)
                .build();
            socketFactory = new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());
        } catch (NoSuchAlgorithmException | KeyStoreException | KeyManagementException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return socketFactory;
    }

    private static RequestConfig buildRequestConfig() {
        return RequestConfig.custom()
            // 客户端和服务器建立连接的timeout
            .setConnectTimeout(500)
            // 指从连接池获取连接的timeout
            .setConnectionRequestTimeout(500)
            // 客户端从服务器读取数据的timeout
            .setSocketTimeout(20000)
            .build();
    }

    /**
     * 维持连接策略
     */
    private static ConnectionKeepAliveStrategy buildConnectionKeepAliveStrategy() {
//        return new DefaultConnectionKeepAliveStrategy();
        return (httpResponse, httpContext) -> {
            HeaderElementIterator it = new BasicHeaderElementIterator(
                httpResponse.headerIterator(HTTP.CONN_KEEP_ALIVE));
            while (it.hasNext()) {
                HeaderElement headerElement = it.nextElement();
                String name = headerElement.getName();
                String value = headerElement.getValue();
                if (value != null && "timeout".equalsIgnoreCase(name)) {
                    return Long.parseLong(value) * 1000;
                }
            }

            // 如果没有约定，则默认定义时长60s
            return 60 * 1000;
        };
    }

    /**
     * 重试策略
     */
    private static HttpRequestRetryHandler buildHttpRequestRetryHandler() {
//        return new DefaultHttpRequestRetryHandler();
        return (exception, executionCount, context) -> {
            if (executionCount > 3) {
                return false;
            }

            if (exception instanceof NoHttpResponseException) {
                return true;
            }

            if (exception instanceof ConnectTimeoutException) {
                return false;
            }

            if (exception instanceof InterruptedIOException) {
                return false;
            }

            if (exception instanceof UnknownHostException) {
                return false;
            }

            HttpClientContext httpClientContext = HttpClientContext.adapt(context);
            HttpRequest request = httpClientContext.getRequest();
            return !(request instanceof HttpEntityEnclosingRequest);
        };
    }
}
