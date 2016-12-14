package net.oschina.app;

import android.app.Application;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import okhttp3.OkHttpClient;

/**
 * 创建者:   ming001
 * 创建时间: 2016/11/4 16:44
 * 描述：    TODO
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {

        //public LoggerInterceptor(String tag, boolean showResponse) ,设置为true 显示body
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggerInterceptor("osChina",true))
               /* //其他配置
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)*/

                .build();
        OkHttpUtils.initClient(okHttpClient);

    }
}
