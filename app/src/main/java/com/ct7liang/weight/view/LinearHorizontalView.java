package com.ct7liang.weight.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.ct7liang.tangyuan.utils.LogUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class LinearHorizontalView extends View {

    private int measuredHeight;
    private float size;
    private float minW;
    private float maxW;
    private int pW;
    private Paint paint;
    private int measuredWidth;
    public static final int ZERO = 15;

    public LinearHorizontalView(Context context) {
        super(context);
    }

    public LinearHorizontalView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LinearHorizontalView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measuredWidth = getMeasuredWidth();
        measuredHeight = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (paint == null){
            paint = new Paint();
        }
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#558FF2"));
        int i = (measuredWidth-ZERO)/pW;
        if (size > maxW){
            size = maxW;
        }
        if (size < minW){
            size = minW;
        }
        float v = (size - minW) * 10 * i;
        canvas.drawRect(0, 0, v+ZERO, measuredHeight, paint);
    }

    public void setSize(float size, float minW, float maxW, int pW){
        this.size = size;
        this.minW = minW;
        this.maxW = maxW;
        this.pW = pW;
        invalidate();
    }
}
