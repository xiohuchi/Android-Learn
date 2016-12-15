package com.finddreams.retrofit.api.net;


import com.finddreams.retrofit.api.config.ConstantApi;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit的实体类
 */
public class RestApiAdapter {
    private static Retrofit retrofit = null;

    public static Retrofit getInstance() {
        if (retrofit == null) {
            GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create();
            OkHttpClient okHttpClient = new OkHttpClient();
            OkHttpClient.Builder builder = okHttpClient.newBuilder();
            builder.retryOnConnectionFailure(true);
            retrofit = new Retrofit.Builder().client(okHttpClient)
                    .baseUrl(ConstantApi.BaiduUrl)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
