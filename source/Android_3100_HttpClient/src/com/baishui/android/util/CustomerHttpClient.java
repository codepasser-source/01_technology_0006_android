package com.baishui.android.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class CustomerHttpClient {

    private static final String CHARSET = HTTP.UTF_8;
    private static DefaultHttpClient customerHttpClient;
    private static final String TAG = "CustomerHttpClient";

    private CustomerHttpClient() {
    }

    public static synchronized DefaultHttpClient getHttpClient() {
        if (null == customerHttpClient) {
            HttpParams params = new BasicHttpParams();
            // 设置一些基本参数
            HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(params, CHARSET);
            HttpProtocolParams.setUseExpectContinue(params, true);
            HttpProtocolParams
                    .setUserAgent(
                            params,
                            "Mozilla/5.0(Linux;U;Android 2.2.1;en-us;Nexus One Build.FRG83) "
                                    + "AppleWebKit/553.1(KHTML,like Gecko) Version/4.0 Mobile Safari/533.1");
            // 超时设置
            /* 从连接池中取连接的超时时间 */
            ConnManagerParams.setTimeout(params, 1000);
            /* 连接超时 */
            HttpConnectionParams.setConnectionTimeout(params, 2000);
            /* 请求超时 */
            HttpConnectionParams.setSoTimeout(params, 4000);
            // 设置我们的HttpClient支持HTTP和HTTPS两种模式
            SchemeRegistry schReg = new SchemeRegistry();
            schReg.register(new Scheme("http", PlainSocketFactory
                    .getSocketFactory(), 80));
            schReg.register(new Scheme("https", SSLSocketFactory
                    .getSocketFactory(), 443));

            // 使用线程安全的连接管理来创建HttpClient
            ClientConnectionManager conMgr = new ThreadSafeClientConnManager(
                    params, schReg);
            customerHttpClient = new DefaultHttpClient(conMgr, params);
        }
        return customerHttpClient;
    }

    public static String httpPost(String url, NameValuePair... params) {
        try {
            // 编码参数
            List<NameValuePair> formparams = new ArrayList<NameValuePair>(); // 请求参数
            for (NameValuePair p : params) {
                formparams.add(p);
            }
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams,
                    CHARSET);
            // 创建POST请求
            HttpPost request = new HttpPost(url);
            request.setEntity(entity);
            HttpClient client = getHttpClient();
            // 设置ContentType
            client.getParams().setParameter("Content-Type",
                    "application/x-www-form-urlencoded");
            // 发送请求
            HttpResponse response = client.execute(request);
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                return "请求失败";
            } else {
                HttpEntity resEntity = response.getEntity();
                return (resEntity == null) ? null : EntityUtils.toString(
                        resEntity, CHARSET);
            }
        } catch (UnsupportedEncodingException e) {
            Log.w(TAG, e.getMessage());
            return null;
        } catch (ClientProtocolException e) {
            Log.w(TAG, e.getMessage());
            return null;
        } catch (IOException e) {
            throw new RuntimeException("连接失败", e);
        }
    }

    public static String httpGet(String url) {
        try {
            // 创建Get请求
            HttpGet request = new HttpGet(url);
            // 发送请求
            DefaultHttpClient client = getHttpClient();
            // 设置代理服务器域名、端口
            HttpHost proxy = new HttpHost("dl-proxy.neusoft.com", 8080, "http");
            client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,
                    proxy);
            // 设置代理服务器用户密码
            client.getCredentialsProvider().setCredentials(AuthScope.ANY,
                    new UsernamePasswordCredentials("chenmzh", "2wsx#EDC"));

            HttpResponse response = client.execute(request);
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                return "请求失败";
            } else {
                HttpEntity resEntity = response.getEntity();
                return (resEntity == null) ? null : EntityUtils.toString(
                        resEntity, CHARSET);
            }
        } catch (UnsupportedEncodingException e) {
            Log.w(TAG, e.getMessage());
            return null;
        } catch (ClientProtocolException e) {
            Log.w(TAG, e.getMessage());
            return null;
        } catch (IOException e) {
            throw new RuntimeException("连接失败", e);
        }
    }
}
