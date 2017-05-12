package com.song.exercise.location;

import android.Manifest;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.song.exercise.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by songyawei on 2017/4/28.
 */
public class LocationActivity extends AppCompatActivity {
    private TextView tvLatitude;
    private TextView tvLongitude;
    private TextView tvIP;
    private Button btnLocation;

    private LocationManager locationManager;
    private String locationProvider;

    private Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        tvLatitude = (TextView) findViewById(R.id.tvLatitude);
        tvLongitude = (TextView) findViewById(R.id.tvLongitude);
        tvIP = (TextView) findViewById(R.id.tvIP);
        btnLocation = (Button) findViewById(R.id.btnLocation);

        handler = new Handler() {
            @Override
            public void dispatchMessage(Message msg) {
                super.dispatchMessage(msg);
                Bundle bundle = msg.getData();
                tvIP.setText("IP：" + bundle.getString("ip"));
            }
        };

        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLocation();
                new GetIpThread().start();
            }
        });
    }

    private void getLocation() {
        //获取地理位置管理器
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //获取所有可用的位置提供器
        Location location = null;
        try {
            List<String> matchingProviders = locationManager.getAllProviders();
            for (String prociderString : matchingProviders) {
                try {
                    //noinspection MissingPermission
                    locationProvider = prociderString;
                    location = locationManager.getLastKnownLocation(prociderString);
                } catch (Exception e) {
                }
                if (location != null)
                    break;
            }
        } catch (Exception e) {
        }

        if (location != null) {
            //不为空,显示地理位置经纬度
            showLocation(location);
        } else {
            Toast.makeText(this, "无法获取位置信息", Toast.LENGTH_SHORT).show();
        }
        //监视地理位置变化
        if (checkPermission()) {
            locationManager.removeUpdates(locationListener);
            locationManager.requestLocationUpdates(locationProvider, 3000, 1, locationListener);
        }
    }

    class GetIpThread extends Thread {
        @Override
        public void run() {
            super.run();
            String ip = getIpAddress();
            if (!TextUtils.isEmpty(ip)) {
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putString("ip", ip);
                message.setData(bundle);
                message.setTarget(handler);
                message.sendToTarget();
            }
        }

        public String getIpAddress() {
            OkHttpClient client = new OkHttpClient.Builder()
                    .build();

            RequestBody body = new FormBody.Builder()
                    .add("ip", "myip")
                    .build();
            Request request = new Request.Builder()
                    .url("http://ip.taobao.com/service/getIpInfo2.php")
                    .post(body)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                if (response.code() == 200) {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    JSONObject data = jsonObject.getJSONObject("data");

                    return data.getString("ip");
                } else {
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    /**
     * LocationListern监听器
     * 参数：地理位置提供器、监听位置变化的时间间隔、位置变化的距离间隔、LocationListener监听器
     */

    LocationListener locationListener = new LocationListener() {

        @Override
        public void onStatusChanged(String provider, int status, Bundle arg2) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }

        @Override
        public void onLocationChanged(Location location) {
            //如果位置发生变化,重新显示
            showLocation(location);

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (locationManager != null) {
            //移除监听器
            if (checkPermission()) {
                locationManager.removeUpdates(locationListener);
            }
        }
    }

    private boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PermissionChecker.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PermissionChecker.PERMISSION_GRANTED) {
            return true;
        } else {
            Toast.makeText(this, "没有定位权限", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    /**
     * 显示地理位置经度和纬度信息
     *
     * @param location
     */
    private void showLocation(Location location) {
        tvLatitude.setText("经度:" + location.getLatitude());
        tvLongitude.setText("纬度:" + location.getLongitude());
    }
}
