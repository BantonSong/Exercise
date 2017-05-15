package com.song.myframework.network;

import android.util.Log;

import com.song.myframework.model.DetailModel;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
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
    private static String apiBaseUrl = URL.API_URL;
    private Retrofit retrofit;
    /**
     * activity销毁时不应该清空 apiService，到其他界面还是要用的，
     * 需要解绑observable 和 observer的绑定关系，Retrofit2.2.0 subscribe的时候
     * 没有返回Subscription，暂不知道如何解绑？还是说会自动解绑？
     */
    private APIService apiService;
    private static String TAG = Network.class.getSimpleName();

    private OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
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

    /**
     * 私有构造方法
     */
    private Network() {
        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(apiBaseUrl)
                .addConverterFactory(MyGsonFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiService = retrofit.create(APIService.class);
    }

    /**
     * 运行时改变baseUrl
     *
     * @param newApiBaseUrl 新的api地址
     */
    public void changeApiBaseUrl(String newApiBaseUrl) {
        apiBaseUrl = newApiBaseUrl;
        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(apiBaseUrl)
                .addConverterFactory(MyGsonFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiService = retrofit.create(APIService.class);
    }

    private static final class SingletonHolder {
        private static Network instance = new Network();
    }

    public static Network getInstance() {
        return SingletonHolder.instance;
    }

    public void query(Observer<DetailModel> observer, String chengyu) {
        doSubscribe(apiService.query(chengyu), observer);
    }

    public void queryArray(Observer<List<DetailModel>> observer, String chengyu) {
        doSubscribe(apiService.queryArray(chengyu), observer);
    }

    public void queryString(Observer<String> observer, String chengyu) {
        doSubscribe(apiService.queryString(chengyu), observer);
    }

    /**
     * 订阅
     *
     * @param observable 被观察者
     * @param observer   观察者
     */
    private void doSubscribe(Observable observable, Observer observer) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}
