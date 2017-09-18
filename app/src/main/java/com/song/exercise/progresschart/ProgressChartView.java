package com.song.exercise.progresschart;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.song.exercise.R;

import java.util.List;

/**
 * Created by songyawei on 2017/6/16.
 */
public class ProgressChartView extends LinearLayout {
    private float aboveTextSize;
    private float belowTextSize;
    private int aboveTextColor;
    private int belowTextColor;
    private Drawable progressCompleteDrawable;
    private Drawable progressIncompleteDrawable;
    private int lineCompleteColor;
    private int lineIncompleteColor;
    private float leftRightSpace;
    private float aboveBlewTextSpace;
    private float belowRowSpace;
    private float verticalLineWidth;

    private Context context;
    private LayoutInflater inflater;
    private List<ProgressChartModel> progressChartModelList;

    public ProgressChartView(Context context) {
        this(context, null);
    }

    public ProgressChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.setOrientation(VERTICAL);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ProgressChartView);
        aboveTextSize = typedArray.getDimension(R.styleable.ProgressChartView_aboveTextSize, dip2px(16));
        belowTextSize = typedArray.getDimension(R.styleable.ProgressChartView_belowTextSize, dip2px(12));
        aboveTextColor = typedArray.getColor(R.styleable.ProgressChartView_aboveTextColor, Color.BLACK);
        belowTextColor = typedArray.getColor(R.styleable.ProgressChartView_belowTextColor, Color.GRAY);
        progressCompleteDrawable = typedArray.getDrawable(R.styleable.ProgressChartView_progressCompleteDrawable);
        if (progressCompleteDrawable == null) {
            progressCompleteDrawable = context.getResources().getDrawable(R.drawable.complete);
        }
        progressIncompleteDrawable = typedArray.getDrawable(R.styleable.ProgressChartView_progressIncompleteDrawable);
        if (progressIncompleteDrawable == null) {
            progressIncompleteDrawable = context.getResources().getDrawable(R.drawable.incomplete);
        }
        lineCompleteColor = typedArray.getColor(R.styleable.ProgressChartView_lineCompleteColor, Color.BLUE);
        lineIncompleteColor = typedArray.getColor(R.styleable.ProgressChartView_lineIncompleteColor, Color.GRAY);
        leftRightSpace = typedArray.getDimension(R.styleable.ProgressChartView_leftRightSpace, dip2px(30));
        aboveBlewTextSpace = typedArray.getDimension(R.styleable.ProgressChartView_aboveBlewTextSpace, dip2px(5));
        belowRowSpace = typedArray.getFloat(R.styleable.ProgressChartView_belowRowSpace, 1.0f);
        verticalLineWidth = typedArray.getDimension(R.styleable.ProgressChartView_verticalLineWidth, dip2px(1));
        typedArray.recycle();
    }

    private void draw() {
        if (progressChartModelList == null || progressChartModelList.size() == 0) {
            removeAllViews();
            return;
        }
        for (int i = 0; i < progressChartModelList.size(); i++) {
            View view;
            if (i == progressChartModelList.size() - 1) {
                view = draw(progressChartModelList.get(i), null);
            } else {
                view = draw(progressChartModelList.get(i), progressChartModelList.get(i + 1));
            }
            addView(view);
        }
    }

    private View draw(ProgressChartModel model, ProgressChartModel nextModel) {
        View view = inflater.inflate(R.layout.view_progress_chart, null);
        ViewHolder holder = new ViewHolder(view);
        if (nextModel == null) {
            holder.topLine.setVisibility(View.GONE);
            holder.belowLine.setVisibility(View.GONE);
        } else {
            if (nextModel.isComplete()) {
                holder.belowLine.setBackgroundColor(lineCompleteColor);
            } else {
                holder.belowLine.setBackgroundColor(lineIncompleteColor);
            }
        }

        if (model.isComplete()) {
            holder.ivLeft.setImageDrawable(progressCompleteDrawable);
            holder.topLine.setBackgroundColor(lineCompleteColor);
        } else {
            holder.ivLeft.setImageDrawable(progressIncompleteDrawable);
            holder.topLine.setBackgroundColor(lineIncompleteColor);
        }
        holder.tvAbove.setText(model.getAboveText());
        holder.tvBelow.setText(model.getBelowText());

        RelativeLayout.LayoutParams topParams = new RelativeLayout.LayoutParams(holder.topLine.getLayoutParams());
        topParams.leftMargin = progressCompleteDrawable.getMinimumWidth() / 2;
        topParams.addRule(RelativeLayout.BELOW, holder.ivLeft.getId());
        holder.topLine.setLayoutParams(topParams);

        RelativeLayout.LayoutParams blewParams = new RelativeLayout.LayoutParams(holder.belowLine.getLayoutParams());
        blewParams.leftMargin = progressCompleteDrawable.getMinimumWidth() / 2;
        blewParams.addRule(RelativeLayout.BELOW, holder.topLine.getId());
        holder.belowLine.setLayoutParams(blewParams);

        holder.tvAbove.setTextSize(TypedValue.COMPLEX_UNIT_PX, aboveTextSize);
        holder.tvBelow.setTextSize(TypedValue.COMPLEX_UNIT_PX, belowTextSize);
        holder.tvAbove.setTextColor(aboveTextColor);
        holder.tvBelow.setTextColor(belowTextColor);
        holder.tvBelow.setLineSpacing(0, belowRowSpace);
        holder.topLine.setMinimumWidth((int) verticalLineWidth);
        holder.belowLine.setMinimumWidth((int) verticalLineWidth);

        RelativeLayout.LayoutParams leftUpParams = new RelativeLayout.LayoutParams(holder.tvAbove.getLayoutParams());
        leftUpParams.leftMargin = (int) leftRightSpace;
        leftUpParams.addRule(RelativeLayout.RIGHT_OF, holder.ivLeft.getId());
        holder.tvAbove.setLayoutParams(leftUpParams);

        RelativeLayout.LayoutParams leftDownParams = new RelativeLayout.LayoutParams(holder.tvBelow.getLayoutParams());
        leftDownParams.leftMargin = (int) leftRightSpace;
        leftDownParams.topMargin = (int) aboveBlewTextSpace;
        leftDownParams.addRule(RelativeLayout.RIGHT_OF, holder.ivLeft.getId());
        leftDownParams.addRule(RelativeLayout.BELOW, holder.tvAbove.getId());
        holder.tvBelow.setLayoutParams(leftDownParams);

        return view;
    }

    public void setProgressChartModelList(List<ProgressChartModel> progressChartModelList) {
        this.progressChartModelList = progressChartModelList;
        draw();
    }

    class ViewHolder {
        private ImageView ivLeft;
        private View topLine;
        private View belowLine;
        private TextView tvAbove;
        private TextView tvBelow;

        public ViewHolder(View view) {
            ivLeft = (ImageView) view.findViewById(R.id.ivLeft);
            topLine = view.findViewById(R.id.topLine);
            belowLine = view.findViewById(R.id.belowLine);
            tvAbove = (TextView) view.findViewById(R.id.tvAbove);
            tvBelow = (TextView) view.findViewById(R.id.tvBelow);
        }
    }

    public static class ProgressChartModel {
        private String aboveText;
        private String belowText;
        private boolean isComplete;

        public String getAboveText() {
            return aboveText;
        }

        public void setAboveText(String aboveText) {
            this.aboveText = aboveText;
        }

        public String getBelowText() {
            return belowText;
        }

        public void setBelowText(String belowText) {
            this.belowText = belowText;
        }

        public boolean isComplete() {
            return isComplete;
        }

        public void setComplete(boolean complete) {
            isComplete = complete;
        }
    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    private int dip2px(float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
