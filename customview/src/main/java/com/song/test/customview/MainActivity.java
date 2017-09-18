package com.song.test.customview;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.song.test.customview.widget.ImageLoaderUtils;
import com.song.test.customview.widget.PieChartView;
import com.song.test.customview.widget.StackedImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Integer[] imageIDs = {R.drawable.logo_douban
            , R.drawable.logo_email,
            R.drawable.logo_evernote, R.drawable.logo_douban, R.drawable.logo_email,
            R.drawable.logo_evernote, R.drawable.logo_facebook, R.drawable.logo_foursquare,
            R.drawable.logo_googleplus, R.drawable.logo_kaixin, R.drawable.logo_linkedin,
            R.drawable.logo_neteasemicroblog
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PieChartView chartView = (PieChartView) findViewById(R.id.chartView);
        List<PieChartView.PieChartModel> chartModelList = new ArrayList<>();
        PieChartView.PieChartModel model = new PieChartView.PieChartModel("第一个", 18f);
        chartModelList.add(model);
        model = new PieChartView.PieChartModel("第二个", 18f);
        chartModelList.add(model);
        model = new PieChartView.PieChartModel("第三个", 180f);
        chartModelList.add(model);
        model = new PieChartView.PieChartModel("第四个", 28f);
        chartModelList.add(model);
        model = new PieChartView.PieChartModel("第五个", 280f);
        chartModelList.add(model);
        model = new PieChartView.PieChartModel("第六个", 55f);
        chartModelList.add(model);
        model = new PieChartView.PieChartModel("第七个", 120f);
        chartModelList.add(model);
        chartView.setChartModelList(chartModelList);

        final List<ImageView> imageViewList = new ArrayList<>();

        for (int i = 0; i < imageIDs.length; i++) {
            ImageView imageView = new ImageView(this);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            imageView.setLayoutParams(layoutParams);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(imageIDs[i]);
            imageViewList.add(imageView);
        }

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new MyAdapter(this, imageViewList));
        viewPager.setPageMargin(-200);
        viewPager.setOffscreenPageLimit(3);

        List<String> imageUrlList = new ArrayList<>();
        imageUrlList.add("");
        imageUrlList.add("");
        imageUrlList.add("");
        imageUrlList.add("");

        ImageLoaderUtils.init(this);
        StackedImageView stackedImageView = (StackedImageView) findViewById(R.id.stackedImageView);
        stackedImageView.setImageIdList(Arrays.asList(imageIDs));
//        stackedImageView.setImageUrlList(imageUrlList);
    }

    class MyAdapter extends PagerAdapter {
        private Context mContext;
        private List<ImageView> imageViewList;

        public MyAdapter(Context context, List<ImageView> imageViewList) {
            this.mContext = context;
            this.imageViewList = imageViewList;
        }

        @Override
        public int getCount() {
            return imageViewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = imageViewList.get(position);
            if (position == 0) {
                imageView.setPadding(0, 0, 110, 0);
            } else if (position == getCount() - 1) {
                imageView.setPadding(110, 0, 0, 0);
            } else {
                imageView.setPadding(110, 0, 110, 0);
            }
            container.addView(imageView);
            return imageView;
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
