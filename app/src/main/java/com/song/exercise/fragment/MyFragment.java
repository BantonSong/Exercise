package com.song.exercise.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.song.exercise.R;
import com.viewpagerindicator.UnderlinePageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by songyawei on 2017/4/17.
 */
public class MyFragment extends BaseViewPagerFragment {
    private ViewPager viewPager;
    private UnderlinePageIndicator indicator;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_viewpager, null);
        final RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioBtn1:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.radioBtn2:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.radioBtn3:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.radioBtn4:
                        viewPager.setCurrentItem(3);
                        break;
                    default:
                        viewPager.setCurrentItem(0);
                        break;
                }
            }
        });

        viewPager = (ViewPager) view.findViewById(R.id.viewPager);

        List<Fragment> list = new ArrayList<>();
        ViewPagerFragment01 fragment01 = new ViewPagerFragment01();
        list.add(fragment01);
        ViewPagerFragment02 fragment02 = new ViewPagerFragment02();
        list.add(fragment02);
        ViewPagerFragment03 fragment03 = new ViewPagerFragment03();
        list.add(fragment03);
        ViewPagerFragment04 fragment04 = new ViewPagerFragment04();
        list.add(fragment04);
        viewPager.setAdapter(new MyViewPagerAdapter(getActivity().getSupportFragmentManager(), list));
        viewPager.setCurrentItem(0);
        indicator = (UnderlinePageIndicator) view.findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        radioGroup.check(R.id.radioBtn1);
                        break;
                    case 1:
                        radioGroup.check(R.id.radioBtn2);
                        break;
                    case 2:
                        radioGroup.check(R.id.radioBtn3);
                        break;
                    case 3:
                        radioGroup.check(R.id.radioBtn4);
                        break;
                    default:
                        radioGroup.check(R.id.radioBtn1);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    public class MyViewPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragmentList;

        public MyViewPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(fm);
            this.fragmentList = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
}
