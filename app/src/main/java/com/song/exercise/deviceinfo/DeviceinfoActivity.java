package com.song.exercise.deviceinfo;

import android.Manifest;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.song.exercise.R;
import com.song.exercise.utils.CommonUtil;

/**
 * Created by songyawei on 2017/5/10.
 */
public class DeviceinfoActivity extends AppCompatActivity {
    private TextView tvIMEIValue;
    private TextView tvIMSIValue;
    private TextView tvAnddroidIdValue;
    private TextView tvMacValue;
    private TextView tvSerialValue;
    private TextView tvSIMSerialValue;
    private TextView tvUUIDValue;
    private TextView tvDeviceIdValue;

    private Button btnDeviceInfo;

    private TelephonyManager telephonyManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_info);

        tvIMEIValue = (TextView) findViewById(R.id.tvIMEIValue);
        tvIMEIValue = (TextView) findViewById(R.id.tvIMEIValue);
        tvIMSIValue = (TextView) findViewById(R.id.tvIMSIValue);
        tvAnddroidIdValue = (TextView) findViewById(R.id.tvAnddroidIdValue);
        tvMacValue = (TextView) findViewById(R.id.tvMacValue);
        tvSerialValue = (TextView) findViewById(R.id.tvSerialValue);
        tvSIMSerialValue = (TextView) findViewById(R.id.tvSIMSerialValue);
        tvUUIDValue = (TextView) findViewById(R.id.tvUUIDValue);
        tvDeviceIdValue = (TextView) findViewById(R.id.tvDeviceIdValue);

        btnDeviceInfo = (Button) findViewById(R.id.btnDeviceInfo);
        btnDeviceInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvIMEIValue.setText(getIMEIValue());
                tvIMSIValue.setText(getIMSIValue());
                tvAnddroidIdValue.setText(getAnddroidIdValue());
                tvMacValue.setText(getMacValue());
                tvSerialValue.setText(getSerialValue());
                tvSIMSerialValue.setText(getSIMSerialValue());
                tvUUIDValue.setText(getUUIDValue());
                tvDeviceIdValue.setText(CommonUtil.getDeviceId(DeviceinfoActivity.this));
            }
        });

        try {
            telephonyManager = (TelephonyManager) (this.getSystemService(Context.TELEPHONY_SERVICE));
        } catch (Exception e) {
        }
    }

    private String getIMEIValue() {
        String result = "没获取到";
        try {
            if (!CommonUtil.checkPermissions(this, Manifest.permission.READ_PHONE_STATE)) {
                return "没获取到";
            }
            result = telephonyManager.getDeviceId();
            if (result == null)
                result = "没获取到";
        } catch (Exception e) {
        }
        return result;
    }

    private String getIMSIValue() {
        String result = "没获取到";
        try {
            if (!CommonUtil.checkPermissions(this, Manifest.permission.READ_PHONE_STATE)) {
                return "没获取到";
            }
            result = telephonyManager.getSubscriberId();
            if (result == null)
                return "没获取到";
            return result;
        } catch (Exception e) {
        }

        return result;
    }

    private String getAnddroidIdValue() {
        String result = null;
        //尝试获取 android_id
        String androidId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        //有些设置会返回同样的 9774d56d682e549c
        if (!"9774d56d682e549c".equals(androidId)) {
            result = androidId;
        } else {
            result = "失效的:" + androidId;
        }
        return result;
    }

    private String getMacValue() {
        return CommonUtil.getMACAddress("eth0");
    }

    private String getSerialValue() {
        String serial = Build.SERIAL;
        return serial;
    }

    private String getSIMSerialValue() {
        String result = "没获取到";
        try {
            if (!CommonUtil.checkPermissions(this, Manifest.permission.READ_PHONE_STATE)) {
                return "没获取到";
            }
            result = telephonyManager.getSimSerialNumber();
            if (result == null)
                return "没获取到";
            return result;
        } catch (Exception e) {
        }

        return result;
    }

    private String getUUIDValue() {
        return CommonUtil.getUUID();
    }
}
