package com.bc.wd.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

/**
 * @program: whl-project
 * @description: 爬虫工具类
 * @author: Mr.Wang
 * @create: 2020-04-23 10:40
 **/
@Component
@Slf4j
public class HttpUtils {

    /**
     * 创建网络连接管理池
     */
    private PoolingHttpClientConnectionManager cm;

    public HttpUtils() {
        this.cm = new PoolingHttpClientConnectionManager();
        //最大连接数
        this.cm.setMaxTotal(200);
        //设置每个数据的连接数
        this.cm.setDefaultMaxPerRoute(20);
    }

    //需要定义两个Method方法
    /**
     * 第一个用来访问连接
     *
     * @param html
     * @return
     */
    public String getHtml(String html) {
        //创建访问对象
        CloseableHttpClient build = HttpClients.custom().setConnectionManager(cm).build();
        //声明httpget来进行请求响应
        HttpGet httpGet = new HttpGet(html);
        // 如果不伪装浏览器的话会跳到登录页
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:62.0) Gecko/20100101 Firefox/62.0");
        httpGet.setConfig(this.getrequestConfig());
        CloseableHttpResponse closeableHttpResponse = null;
        try {
            closeableHttpResponse = build.execute(httpGet);
            if (closeableHttpResponse.getStatusLine().getStatusCode() == 200) {
                //此次状态请求200 成功 定义一个html的字符串页面
                String htmlStr = "";
                //并判断当前请求数据是否又返回数据内容等信息
                if (closeableHttpResponse.getEntity() != null) {
                    htmlStr = EntityUtils.toString(closeableHttpResponse.getEntity(), "utf8");
                }
                return htmlStr;
            }
        } catch (Exception e) {
            log.info("爬虫异常:" + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (closeableHttpResponse != null) {
                    closeableHttpResponse.close();
                }
            } catch (IOException e) {
                log.info("关闭异常:" + e.getMessage());
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 第二个用来保存图片
     * @param url
     * @return
     */
    public String getImage(String url) {
        //创建访问对象
        CloseableHttpClient build = HttpClients.custom().setConnectionManager(cm).build();
        //声明httpget来进行请求响应
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(this.getrequestConfig());
        CloseableHttpResponse closeableHttpResponse = null;
        try {
            closeableHttpResponse = build.execute(httpGet);

            if (closeableHttpResponse.getStatusLine().getStatusCode() == 200) {
                //获取文件类型 MIME类型属性
                String endName = url.substring(url.lastIndexOf("."));
                //根据UUID来生成文件名称
                String fileName = UUID.randomUUID().toString() + endName;
                OutputStream outputStream = new FileOutputStream("C:\\Users\\asus\\Desktop\\images\\" + fileName);
                //字节流写入指定的文件夹中
                closeableHttpResponse.getEntity().writeTo(outputStream);
                return fileName;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (closeableHttpResponse != null) {
                    closeableHttpResponse.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 此方法用来设置连接参数
     *
     * @return
     */
    private RequestConfig getrequestConfig() {
        //设置创建连接的超时时间
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(3000)
                //设置获取连接的超时时间
                .setConnectionRequestTimeout(3000)
                //设置连接的超时时间
                .setSocketTimeout(1000).build();
        return requestConfig;

    }
}
