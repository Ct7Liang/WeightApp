package com.ct7liang.weight.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.ct7liang.tangyuan.utils.LogUtils;

public class LinearVerticalView extends View {

    private int measuredHeight;
    private float size;
    private Paint paint;
    private int measuredWidth;

    public LinearVerticalView(Context context) {
        super(context);
    }

    public LinearVerticalView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LinearVerticalView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        LogUtils.write("onMeasure()");

        measuredWidth = getMeasuredWidth();
        measuredHeight = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        LogUtils.write("onDraw()");

        if (paint == null){
            paint = new Paint();
        }
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#558FF2"));

        int i = measuredHeight / 100;
        float v = (size - 75) * 10 * i;

        canvas.drawRect(0, measuredHeight - v, measuredWidth, measuredHeight, paint);
    }


    public void setSize(float size){
        this.size = size;
        invalidate();
    }
}
