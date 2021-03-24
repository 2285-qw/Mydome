package com.example.mydome;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

/*项目名：   Mydome
 *包名：     com.example.mydome
 *文件名：   Mytextview
 *创建者：   CEH
 *创建时间： 2021/3/22 19:53
 *描述：     TODO
 */
public class Mytextview extends View {
    private String mText;
    private int mTextColor = Color.BLACK;
    private int mTextSize = 15;

    private Paint mPaint;

    public Mytextview(Context context) {
        this(context, null);
    }

    public Mytextview(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Mytextview(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //获取自定义属性
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.Mytextview);

        mText = array.getString(R.styleable.Mytextview_Mytext);
        mTextColor = array.getColor(R.styleable.Mytextview_MytextColor, mTextColor);
        mTextSize = array.getDimensionPixelSize(R.styleable.Mytextview_MytextSize, mTextSize);

        //回收
        array.recycle();

        mPaint = new Paint();
        //抗拒齿
        mPaint.setAntiAlias(true);
        mPaint.setColor(mTextColor);
        mPaint.setTextSize(mTextSize);
    }

    public Mytextview(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //获取宽高的模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        //确定的值
        int width = MeasureSpec.getSize(widthMeasureSpec);

        //给定的wrap_content需要计算
        if (widthMode == MeasureSpec.AT_MOST) {

            Rect bounds = new Rect();
            mPaint.getTextBounds(mText, 0, mText.length(), bounds);
            width=bounds.width();
        }

        //确定的值
        int height = MeasureSpec.getSize(heightMeasureSpec);

        //给定的wrap_content需要计算
        if (heightMode == MeasureSpec.AT_MOST) {

            Rect bounds = new Rect();
            mPaint.getTextBounds(mText, 0, mText.length(), bounds);
            height=bounds.height();
        }

        setMeasuredDimension(width,height);
    }


}
