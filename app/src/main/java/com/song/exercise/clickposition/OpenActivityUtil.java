package com.song.exercise.clickposition;

import android.content.Context;
import android.content.Intent;

/**
 * Created by songyawei on 2017/4/12.
 */
public class OpenActivityUtil {
    public static void openActivity(Context context) {
        Intent intent = new Intent(context, ClickPositionNormalActivity.class);
        context.startActivity(intent);
    }
}
