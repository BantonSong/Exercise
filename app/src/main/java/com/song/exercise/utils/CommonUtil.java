/**
 * Cobub Razor
 * <p/>
 * An open source analytics android sdk for mobile applications
 *
 * @package Cobub Razor
 * @author WBTECH Dev Team
 * @copyright Copyright (c) 2011 - 2015, NanJing Western Bridge Co.,Ltd.
 * @license http://www.cobub.com/products/cobub-razor/license
 * @link http://www.cobub.com/products/cobub-razor/
 * @filesource
 * @since Version 0.1
 */

package com.song.exercise.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class CommonUtil {

    /**
     * checkPermissions
     *
     * @param context
     * @param permission
     * @return true or false
     */
    public static boolean checkPermissions(Context context, String permission) {
        if (context == null || permission.equals("") || permission.equals("")) {
            return false;
        }
        PackageManager pm = context.getPackageManager();
        return pm.checkPermission(permission, context.getPackageName())
                == PackageManager.PERMISSION_GRANTED;
    }

    public static String md5(String str) {
        try {
            MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
            localMessageDigest.update(str.getBytes());
            byte[] arrayOfByte = localMessageDigest.digest();
            StringBuffer localStringBuffer = new StringBuffer();
            for (byte anArrayOfByte : arrayOfByte) {
                int j = 0xFF & anArrayOfByte;
                if (j < 16)
                    localStringBuffer.append("0");
                localStringBuffer.append(Integer.toHexString(j));
            }
            return localStringBuffer.toString();
        } catch (Exception e) {
        }
        return "";
    }

    public static String getUUID() {
        return UUID.randomUUID().toString();
    }


    /**
     * Returns MAC address of the given interface name.
     *
     * @param interfaceName eth0, wlan0 or NULL=use first interface
     * @return mac address or empty string
     */
    public static String getMACAddress(String interfaceName) {
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                if (interfaceName != null) {
                    if (!intf.getName().equalsIgnoreCase(interfaceName)) continue;
                }
                byte[] mac = intf.getHardwareAddress();
                if (mac == null) return "";
                StringBuilder buf = new StringBuilder();
                for (byte aMac : mac) buf.append(String.format("%02X:", aMac));
                if (buf.length() > 0) buf.deleteCharAt(buf.length() - 1);
                return buf.toString();
            }
        } catch (Exception ignored) {
        } // for now eat exceptions
        return null;
    }

    /**
     * 将表示此设备在该程序上的唯一标识符写入程序文件系统中
     *
     * @param uuid uuid
     * @param file 保存唯一标识符的File对象。
     * @throws IOException IO异常。
     */
    private static void writeToFile(File file, String uuid) {
        try {
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            FileOutputStream out = new FileOutputStream(file);

            out.write(uuid.getBytes());
            out.close();
        } catch (Exception e) {
        }
    }

    /**
     * 获取设备ID
     * 方案1：IMEI+Android_id+serial_number
     * 方案2：UUID，生成的一个随机数
     * 保存在SD卡上或应用的存储空间里，尽量保证同一台设备的deviceID唯一和不变。
     */
    public static String getDeviceId(Context context) {
        // 1、首先从存储卡或应用空间内读取
        String deviceIdMd5 = readDeviceIdFromFile(context);

        if (TextUtils.isEmpty(deviceIdMd5)) {
            // 2、重新生成deviceId
            String deviceId = makeDeviceId(context);

            deviceIdMd5 = CommonUtil.md5(deviceId);

            // 3、设备ID写入存储卡或应用空间内
            writeDeviceIdToFile(context, deviceIdMd5);
        }

        return deviceIdMd5;
    }

    /**
     * 生成deviceId
     */
    private static String makeDeviceId(Context context) {
        // 没有本地存储，生成一遍
        String imei = CommonUtil.getDeviceIMEI(context);
        String androidId = CommonUtil.getAnddroidIdValue(context);
        String serial = CommonUtil.getSerialValue();

        String deviceId = imei + androidId + serial;
        if (TextUtils.isEmpty(deviceId)) {
            deviceId = CommonUtil.getUUID();
        }

        return deviceId;
    }

    /**
     * 获取设备IMEI号
     */
    private static String getDeviceIMEI(Context context) {
        String result = "";
        try {
            if (!CommonUtil.checkPermissions(context, Manifest.permission.READ_PHONE_STATE)) {
                result = "";
            }
            result = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
            if (null == result || result.matches("0+")) {
                result = "";
            }
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * 获取设备Android_ID
     */
    private static String getAnddroidIdValue(Context context) {
        // 尝试获取 android_id
        String result = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        // 有些设备会返回同样的 9774d56d682e549c
        if (null == result || "9774d56d682e549c".equals(result)) {
            result = "";
        }
        return result;
    }

    /**
     * 获取设备序列号
     */
    private static String getSerialValue() {
        String result = Build.SERIAL;
        // 排除全是0或者全是*的数据
        if (null == result || result.matches("0+") || result.matches("\\*+")) {
            result = "";
        }
        return result;
    }

    /**
     * 从存储卡或应用空间内读取设备ID
     */
    private static String readDeviceIdFromFile(Context context) {
        String deviceIdMd5 = "";
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) &&
                CommonUtil.checkPermissions(context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            String sdCardRoot = Environment.getExternalStorageDirectory().getAbsolutePath();
            deviceIdMd5 = readDeviceIdFromFile(sdCardRoot + File.separator + "." + getFileName(context));
        }

        if (TextUtils.isEmpty(deviceIdMd5)) {
            deviceIdMd5 = readDeviceIdFromFile(context.getFilesDir() + File.separator + getFileName(context));
            // 如果在应用空间内存在而在存储卡内不存在，则写入存储卡
            if (!TextUtils.isEmpty(deviceIdMd5)) {
                writeDeviceIdToSD(context, deviceIdMd5);
            }
        }

        return deviceIdMd5;
    }

    /**
     * 设备ID写入存储卡或应用空间内
     */
    private static void writeDeviceIdToFile(Context context, String deviceIdMd5) {
        // 保存到SD卡内
        writeDeviceIdToSD(context, deviceIdMd5);

        // 保存到应用空间内
        File fileFromDData = new File(context.getFilesDir(), getFileName(context));// 获取data/data/<package>/files
        writeToFile(fileFromDData, deviceIdMd5);
    }

    /**
     * 设备ID写入存储卡
     */
    private static void writeDeviceIdToSD(Context context, String deviceIdMd5) {
        // 保存到SD卡或应用空间内
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) &&
                CommonUtil.checkPermissions(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            String sdCardRoot = Environment.getExternalStorageDirectory().getAbsolutePath();
            File fileFromSDCard = new File(sdCardRoot + File.separator, "." + getFileName(context));
            writeToFile(fileFromSDCard, deviceIdMd5);
        }
    }

    /**
     * 读出保存在程序文件系统中的表示该设备在此程序上的唯一标识符。。
     */
    private static String readDeviceIdFromFile(String fileName) {
        String deviceIdMd5 = "";

        File file = new File(fileName);
        if (!file.exists()) {
            return "";
        }

        try {
            RandomAccessFile accessFile = new RandomAccessFile(file, "r");
            byte[] bs = new byte[(int) accessFile.length()];
            accessFile.readFully(bs);
            accessFile.close();
            deviceIdMd5 = new String(bs);
        } catch (Exception e) {
        }

        return deviceIdMd5;
    }

    private static String getFileName(Context context) {
        String fileName = context.getPackageName().replace(".", "");
        return fileName;
    }
}
