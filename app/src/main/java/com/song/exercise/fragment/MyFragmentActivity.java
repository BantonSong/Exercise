package com.song.exercise.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.song.exercise.R;

/**
 * Created by songyawei on 2017/4/17.
 */
public class MyFragmentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fragment);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MyFragment fragment = new MyFragment();
//        MyFragment fragment2 = new MyFragment();
//        MyFragment fragment3 = new MyFragment();

        fragmentTransaction.add(R.id.frameLayout, fragment);
//        fragmentTransaction.add(R.id.frameLayout2, fragment2);
//        fragmentTransaction.add(R.id.frameLayout3, fragment3);
        fragmentTransaction.commit();
    }
}
