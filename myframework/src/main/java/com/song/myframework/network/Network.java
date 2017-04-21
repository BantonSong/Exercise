package com.song.myframework.network;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by songyawei on 2017/4/13.
 */
public class Network {
    private static ApiService apiService;
    private static String TAG = Network.class.getSimpleName();

    public static void init() {
        OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();

                Log.e(TAG, request.url().toString());
                if (request.body() != null) {
                    Log.e(TAG, request.body().toString());
                }

                Request newRequest = request.newBuilder().header("apikey", URL.API_KEY).build();
                return chain.proceed(newRequest);
            }
        }).build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(MyGsonFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(URL.API_URL).client(client)
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public static ApiService getApiService() {
        if (apiService == null) {
            throw new IllegalStateException("must call Network.init() first");
        }
        return apiService;
    }

    public static void clear() {
        apiService = null;
    }
}
