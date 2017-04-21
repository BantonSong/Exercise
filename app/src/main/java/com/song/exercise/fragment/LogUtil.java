package com.song.exercise.fragment;

import android.util.Log;

/**
 * Created by songyawei on 2017/4/17.
 */
public class LogUtil {
    public static void log() {
        StackTraceElement stack[] = Thread.currentThread().getStackTrace();
        String callStr = "called by " + stack[4].getClassName() + "." + stack[4].getMethodName() + "/" + stack[4].getFileName();

        Log.e(LogUtil.class.getSimpleName(), callStr);
    }

    public static void log(String msg) {
        StackTraceElement stack[] = Thread.currentThread().getStackTrace();
        String callStr = "called by " + stack[4].getClassName() + "." + stack[4].getMethodName() + "/" + stack[4].getFileName();

        Log.e(LogUtil.class.getSimpleName(), callStr + ":" + msg);
    }

    public static void log(BaseViewPagerFragment fragment, String msg) {
        Log.e(LogUtil.class.getSimpleName(), getCurrentFragmentName(fragment) + " : " + msg);
    }

//    public static String getRunningActivityName() {
//        ActivityManager manager = (ActivityManager) Application.getInstance().getSystemService(Context.ACTIVITY_SERVICE);
//        String activityName = manager.getRunningTasks(1).get(0).topActivity.getClassName();
//        String name = activityName.substring(activityName.lastIndexOf(".") + 1, activityName.length());
//        return name;
//    }

    /**
     * 获取当前显示的Fragment名称
     */
    public static String getCurrentFragmentName(BaseViewPagerFragment fragment) {
        String fragName = fragment.getClass().toString();
        fragName = fragName.substring(fragName.lastIndexOf(".") + 1, fragName.length());
        return fragName;
    }
}
