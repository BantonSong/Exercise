package com.song.myframework.network;

import com.song.myframework.model.DetailModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by songyawei on 2017/4/13.
 */
public interface ApiService {
    @GET("chengyu")
    Observable<DetailModel> query(@Query("chengyu") String chengyu);
}
