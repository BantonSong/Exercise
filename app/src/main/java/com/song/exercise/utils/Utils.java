package com.song.exercise.utils;

import android.util.Log;

/**
 * Created by songyawei on 2017/3/31.
 */
public class Utils {
    public static String printLine() {
        StackTraceElement[] trace = new Throwable().getStackTrace();
        // 下标为0的元素是上一行语句的信息, 下标为1的才是调用printLine的地方的信息
        StackTraceElement tmp = trace[2];
        return tmp.getClassName() + "." + tmp.getMethodName() + "(" + tmp.getFileName() + ":" + tmp.getLineNumber() + ")";
    }

    public static String printLine2() {
        StackTraceElement[] trace = Thread.currentThread().getStackTrace();
        StackTraceElement tmp = trace[4];
        return tmp.getClassName() + "." + tmp.getMethodName() + "(" + tmp.getFileName() + ":" + tmp.getLineNumber() + ")";
    }

    public static void log(String tagName) {
        Log.e(tagName, printLine2());
        Log.e(tagName, "currentThreadId:" + Thread.currentThread().getId());
    }

    public static void log(String tagName, String message) {
        Log.e(tagName, printLine2());
        Log.e(tagName, message);
    }
}
