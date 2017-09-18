package com.song.exercise.deeplink;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.song.exercise.R;
import com.song.exercise.progresschart.ProgressChartView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by songyawei on 2017/5/22.
 */
public class ProgressChartActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_char);

        ProgressChartView progressChartView = (ProgressChartView) findViewById(R.id.progressChartView);

        List<ProgressChartView.ProgressChartModel> list = new ArrayList();
        ProgressChartView.ProgressChartModel model = new ProgressChartView.ProgressChartModel();
        model.setAboveText("扣款成功1");
        model.setBelowText("2017-06-09 09:00:00");
        model.setComplete(true);
        list.add(model);

        model = new ProgressChartView.ProgressChartModel();
        model.setAboveText("扣款成功2");
        model.setBelowText("2017-06-09 09:00:00");
        model.setComplete(true);
        list.add(model);

        model = new ProgressChartView.ProgressChartModel();
        model.setAboveText("扣款成功3");
        model.setBelowText("2017-06-09 09:00:00");
        model.setComplete(true);
        list.add(model);

        model = new ProgressChartView.ProgressChartModel();
        model.setAboveText("扣款成功4");
        model.setBelowText("2017-06-09 09:00:00");
        model.setComplete(true);
        list.add(model);

        model = new ProgressChartView.ProgressChartModel();
        model.setAboveText("扣款成功5");
        model.setBelowText("2017-06-09 09:00:00");
        model.setComplete(false);
        list.add(model);

        model = new ProgressChartView.ProgressChartModel();
        model.setAboveText("扣款成功6扣款成功6扣款成功6扣款成功6扣款成功6扣款成功6");
        model.setBelowText("2017-06-09 09:00:002017-06-09 09:00:002017-06-09 09:00:002017-06-09 09:00:002017-06-09 09:00:00");
        model.setComplete(false);
        list.add(model);

        progressChartView.setProgressChartModelList(list);
    }
}
