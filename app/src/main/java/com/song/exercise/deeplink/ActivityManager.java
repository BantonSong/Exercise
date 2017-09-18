package com.song.exercise.deeplink;

import android.app.Activity;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by songyawei on 2017/5/22.
 */
public class ActivityManager {
    private final static String TAG = ActivityManager.class.getSimpleName();
    private static List<Activity> activityList = new LinkedList();

    /**
     * 将当前创建的activity加入activity链表中
     */
    public static void addActivity(Activity activity) {
        activityList.add(activity);
        Log.e(TAG, "addActivity:" + activity.getLocalClassName());
    }

    /**
     * 移除已销毁的Activity
     */
    public static void removeActivity(Activity activity) {
        activityList.remove(activity);
        Log.e(TAG, "removeActivity:" + activity.getLocalClassName());
    }

    public static Activity getCurrentActivity() {
        if (activityList != null && activityList.size() > 0) {
            Activity activity = activityList.get(activityList.size() - 1);
            Log.e(TAG, "getCurrentActivity:" + activity.getLocalClassName());
            return activity;
        }
        return null;
    }
}
