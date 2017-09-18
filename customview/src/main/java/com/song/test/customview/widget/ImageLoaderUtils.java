package com.song.test.customview.widget;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

/**
 * 图像显示ImageLoaderUtils.java
 * Created by liuxu on 2016/8/3.
 */
public class ImageLoaderUtils {

    public static void init(Context context) {
//        Picasso picasso = new Picasso.Builder(context)
//                .downloader(new OkHttp3Downloader(context))
//                .build();
//        Picasso.setSingletonInstance(picasso);
    }

    /**
     * 显示图片
     */
    public static void display(Context context, ImageView imageView, @DrawableRes int placeholderResId, String url) {
        RequestCreator requestCreator = Picasso.with(context).load("http://pp.myapp.com/ma_icon/0/icon_6240_1504577009/96");
        requestCreator.placeholder(placeholderResId);
        requestCreator.fit();
        requestCreator.into(imageView, new Callback() {
            @Override
            public void onSuccess() {
                Log.e("ImageLoaderUtils", "onSuccess");
            }

            @Override
            public void onError() {
                Log.e("ImageLoaderUtils", "onError");
            }
        });
    }
}
