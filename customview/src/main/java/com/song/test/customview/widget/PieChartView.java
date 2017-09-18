package com.song.test.customview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.song.test.customview.R;

import java.util.List;

/**
 * 饼状图
 * Created by songyawei on 2017/6/29.
 */
public class PieChartView extends View {
    private TypedArray colors = getResources().obtainTypedArray(R.array.pieColors);
    private List<PieChartModel> chartModelList;
    private float total;

    public void setChartModelList(List<PieChartModel> chartModelList) {
        this.chartModelList = chartModelList;
        for (PieChartModel model : chartModelList) {
            total += model.value;
        }
        invalidate();
    }

    public PieChartView(Context context) {
        super(context);
    }

    public PieChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PieChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (chartModelList == null || chartModelList.size() == 0) {
            this.setVisibility(View.GONE);
            return;
        } else {
            this.setVisibility(View.VISIBLE);
        }

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);
        paint.setAntiAlias(true);
        canvas.drawCircle(500, 500, 202, paint);

        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        RectF rect = new RectF(300, 300, 700, 700);

        float start = 0;

        for (int i = 0; i < chartModelList.size(); i++) {
            PieChartModel model = chartModelList.get(i);
            if (i >= colors.length()) {
                paint.setColor(colors.getColor((i + 3) % colors.length(), Color.GRAY));
            } else {
                paint.setColor(colors.getColor(i, Color.GRAY));
            }
            float sweep = model.value / total * 360;
            canvas.drawArc(rect, start, sweep, true, paint);
            start += sweep;
        }
    }

    public static class PieChartModel {
        String name;
        float value;

        public PieChartModel(String name, float value) {
            this.name = name;
            this.value = value;
        }
    }
}
