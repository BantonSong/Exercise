package com.song.exercise;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.song.exercise.clickposition.OpenActivityUtil;
import com.song.exercise.deeplink.DeepLinkAActivity;
import com.song.exercise.deeplink.DeepLinkActivity;
import com.song.exercise.deeplink.ProgressChartActivity;
import com.song.exercise.deviceinfo.DeviceinfoActivity;
import com.song.exercise.fragment.MyFragmentActivity;
import com.song.exercise.fragment.MyViewPagerActivity;
import com.song.exercise.h5.H5WebActivity;
import com.song.exercise.location.LocationActivity;
import com.song.exercise.mvp.activity.LoginActivity;
import com.song.exercise.rxjava.RxJavaTest;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        if (intent != null) {
            String url = intent.getStringExtra("URL");
            if (!TextUtils.isEmpty(url)) {
                Intent openIntent = new Intent(this, DeepLinkActivity.class);
                openIntent.putExtra("URL", url);
                startActivity(openIntent);
                intent.putExtra("URL", "");
            }
        }

        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTasks = manager.getRunningTasks(1);
        ActivityManager.RunningTaskInfo cinfo = runningTasks.get(0);
        ComponentName component = cinfo.topActivity;
        Log.e("current activity is ", component.getPackageName());
        Log.e("current activity is ", component.getShortClassName());
        Log.e("current activity is ", component.getClassName());
    }

    public void mvp(View view) {
        Log.e(MainActivity.class.getSimpleName(), view.getPaddingTop() + "");
        Log.e(MainActivity.class.getSimpleName(), view.getPaddingLeft() + "");
        Log.e(MainActivity.class.getSimpleName(), view.getTop() + "");
        Log.e(MainActivity.class.getSimpleName(), view.getTranslationY() + "");
        Log.e(MainActivity.class.getSimpleName(), view.getY() + "");

        View content_main = findViewById(R.id.content_main);
        Log.e(MainActivity.class.getSimpleName(), "content_main:" + content_main.getPaddingTop());

        view.setTranslationY(100);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void rxJava(View view) {
        RxJavaTest.test();
    }

    public void h5Test(View view) {
        Intent intent = new Intent(this, H5WebActivity.class);
        startActivity(intent);
    }

    public void viewPager(View view) {
        Intent intent = new Intent(this, MyViewPagerActivity.class);
        startActivity(intent);
    }

    public void fragmentViewPager(View view) {
        Intent intent = new Intent(this, MyFragmentActivity.class);
        startActivity(intent);
    }

    public void location(View view) {
        Intent intent = new Intent(this, LocationActivity.class);
        startActivity(intent);
    }

    public void deviceInfo(View view) {
        Intent intent = new Intent(this, DeviceinfoActivity.class);
        startActivity(intent);
    }

    public void collectData(View view) {
        OpenActivityUtil.openActivity(this);
    }

    public void deeplink(View view) {
        Intent intent = new Intent(this, DeepLinkAActivity.class);
        startActivity(intent);
    }

    public void progressChartView(View view) {
        Intent intent = new Intent(this, ProgressChartActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            Toast.makeText(this, "X:" + ev.getX() + ",Y:" + ev.getY(), Toast.LENGTH_SHORT).show();
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
