package com.example.mydome.view;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.mydome.R;

public class MyView extends View {

    private Paint mPaint;

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);          //抗锯齿
        mPaint.setColor(getResources().getColor(R.color.black));//画笔颜色
        mPaint.setStyle(Paint.Style.FILL);  //画笔风格
        mPaint.setTextSize(36);             //绘制文字大小，单位px
        mPaint.setStrokeWidth(5);           //画笔粗细
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //extracted(canvas);
        canvas.drawColor(getResources().getColor(R.color.teal_200));   //设置画布背景颜色


       /* canvas.drawCircle(200, 200, 100, mPaint);           //画实心圆
        canvas.drawRect(0, 0, 200, 100, mPaint);     //画矩形
        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_lol_icon), 300, 300, mPaint);
        canvas.drawArc(new RectF(500, 500, 700, 700), 0, 90, true, mPaint);  //绘制弧形区域
        canvas.drawRoundRect(new RectF(210, 10, 410, 110), 15, 15, mPaint); //画圆角矩形
        canvas.drawOval(new RectF(200, 200, 400, 500), mPaint); //画椭圆
*/

        extracted1(canvas);

    }

    //绘制多边形
    private void extracted1(Canvas canvas) {
        Path path = new Path();
        path.moveTo(10, 10); //移动到 坐标10,10
        path.lineTo(100, 50);
        path.lineTo(200, 40);
        path.lineTo(300, 20);
        path.lineTo(200, 10);
        path.lineTo(100, 70);
        path.lineTo(50, 40);
        path.close();
        canvas.drawPath(path, mPaint);
    }

    //画时钟
    private void extracted(Canvas canvas) {
        canvas.translate(canvas.getWidth() / 2, 200); //将位置移动画纸的坐标点:150,150
        canvas.drawCircle(0, 0, 100, mPaint); //画圆圈

        //使用path绘制路径文字
        canvas.save();
        canvas.translate(-75, -75);
        Path path = new Path();
        path.addArc(new RectF(0, 0, 150, 150), -180, 180);
        Paint citePaint = new Paint(mPaint);
        citePaint.setTextSize(14);
        citePaint.setStrokeWidth(1);
        canvas.drawTextOnPath("绘制表盘~", path, 28, 0, citePaint);
        canvas.restore();

        Paint tmpPaint = new Paint(mPaint); //小刻度画笔对象
        tmpPaint.setStrokeWidth(1);

        float y = 100;
        int count = 60; //总刻度数

        for (int i = 0; i < count; i++) {
            if (i % 5 == 0) {
                canvas.drawLine(0f, y, 0, y + 12f, mPaint);
                canvas.drawText(String.valueOf(i / 5 + 1), -4f, y + 25f, tmpPaint);

            } else {
                canvas.drawLine(0f, y, 0f, y + 5f, tmpPaint);
            }
            canvas.rotate(360 / count, 0f, 0f); //旋转画纸
        }

        //绘制指针
        tmpPaint.setColor(Color.GRAY);
        tmpPaint.setStrokeWidth(4);
        canvas.drawCircle(0, 0, 7, tmpPaint);
        tmpPaint.setStyle(Paint.Style.FILL);
        tmpPaint.setColor(Color.YELLOW);
        canvas.drawCircle(0, 0, 5, tmpPaint);
        canvas.drawLine(0, 10, 0, -65, mPaint);
    }
}
