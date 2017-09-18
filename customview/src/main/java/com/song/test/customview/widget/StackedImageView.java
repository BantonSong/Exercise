package com.song.test.customview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.song.test.customview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by songyawei on 2017/9/14.
 * 层叠图片
 */
public class StackedImageView extends FrameLayout {
    private static final int DEFAULT_IMAGE_WIDTH = 50;
    private static final int DEFAULT_IMAGE_HEIGHT = 50;

    private List<Integer> imageIdList = new ArrayList<>();
    private List<String> imageUrlList = new ArrayList<>();
    private int groupWidth;
    private int mImageWidth;
    private int mImageHeight;
    private int placeholderResId;
    private Context mContext;
    private boolean isFirst = true;

    public StackedImageView(@NonNull Context context) {
        this(context, null);
    }

    public StackedImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StackedImageView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this.mContext = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.StackedImageView);
        mImageWidth = (int) typedArray.getDimension(R.styleable.StackedImageView_imageWidth, dip2px(DEFAULT_IMAGE_WIDTH));
        mImageHeight = (int) typedArray.getDimension(R.styleable.StackedImageView_imageHeight, dip2px(DEFAULT_IMAGE_HEIGHT));
        placeholderResId = typedArray.getResourceId(R.styleable.StackedImageView_placeholderResId, R.drawable.logo_douban);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        groupWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
        int groupHeight = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();
        if (groupWidth > 0 && groupHeight > 0 && isFirst) {
            mImageWidth = mImageWidth > groupWidth ? groupWidth : mImageWidth;
            mImageHeight = mImageHeight > groupHeight ? groupHeight : mImageHeight;
            isFirst = false;
            addViews();
        }
    }

    private void addViews() {
        if (groupWidth <= 0) {
            return;
        }
        removeAllViews();
        boolean isFromUrl = true;
        int size = imageUrlList.size();
        if (imageIdList.size() > 0) {
            isFromUrl = false;
            size = imageIdList.size();
        }

        int margin;
        if (size * mImageWidth < groupWidth) {
            margin = mImageWidth;
        } else {
            margin = (groupWidth - mImageWidth) / (size - 1);
        }

        for (int i = 0; i < size; i++) {
            addImageView(margin, i, isFromUrl);
        }
    }

    private void addImageView(int margin, int index, boolean isFromUrl) {
        ImageView imageView = new ImageView(mContext);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(mImageWidth, mImageHeight);
        layoutParams.leftMargin = margin * index;
        imageView.setLayoutParams(layoutParams);
        if (isFromUrl) {
            ImageLoaderUtils.display(mContext, imageView, placeholderResId, imageUrlList.get(index));
        } else {
            imageView.setImageResource(imageIdList.get(index));
        }
        addView(imageView);
    }

    public void setImageIdList(List<Integer> imageIdList) {
        this.imageUrlList.clear();
        this.imageIdList = imageIdList;
        isFirst = true;
        addViews();
    }

    public void setImageUrlList(List<String> imageUrlList) {
        this.imageIdList.clear();
        this.imageUrlList = imageUrlList;
        isFirst = true;
        addViews();
    }

    public void setImageWidth(int imageWidth) {
        this.mImageWidth = dip2px(imageWidth);
    }

    public void setImageHeight(int imageHeight) {
        this.mImageHeight = dip2px(imageHeight);
    }

    public void setPlaceholderResId(int placeholderResId) {
        this.placeholderResId = placeholderResId;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    private int dip2px(float dpValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}