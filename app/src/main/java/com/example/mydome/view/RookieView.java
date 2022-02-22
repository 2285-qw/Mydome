package com.example.mydome.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.mydome.R;

public class RookieView extends View {

    private String mContent;
    private boolean mIsShow;
    private int mBackground;
    private int mSelect;

    public RookieView(Context context) {
        this(context, null);
    }

    public RookieView(Context context, @Nullable AttributeSet attrs) {
        this(context, null, 0);
    }

    public RookieView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initStyle(context, attrs);
    }

    private void initStyle(Context context, @Nullable AttributeSet attrs) {
        //获取自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RookieView);
        mContent = typedArray.getString(R.styleable.RookieView_Rook_content);
        mIsShow = typedArray.getBoolean(R.styleable.RookieView_Rook_isShow, true);
        mBackground = typedArray.getColor(R.styleable.RookieView_Rook_background, Color.DKGRAY);
        mSelect = typedArray.getInt(R.styleable.RookieView_Rook_select, 0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //设置控件的宽高，记住这里默认是px，记得要分辨率转换实现适配，这里不做说明
        setMeasuredDimension(getSize(widthMeasureSpec),getSize(heightMeasureSpec));
    }


    private int getSize(int measureSpec){
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        switch (specMode){
            case MeasureSpec.EXACTLY:
                //当layout_width与layout_height 为固定数值走这里
                result = 200;
                break;
            case MeasureSpec.AT_MOST:
                //当layout_width与layout_height定义为 wrap_content　就走这里
                result = Math.min(100,specSize);
                break;
            case MeasureSpec.UNSPECIFIED:
                //如果没有指定大小
                result = 400;
                break;
        }
        return result;
    }
    @Override
    public void layout(int l, int t, int r, int b) {
        super.layout(l, t, r, b);
        Log.d("layout:","l:"+l);
        Log.d("layout:","t:"+t);
        Log.d("layout:","r:"+r);
        Log.d("layout:","b:"+b);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        canvas.drawColor(getResources().getColor(R.color.teal_200));   //设置画布背景颜色
        canvas.drawCircle(50, 50, 50, paint);
    }

}
