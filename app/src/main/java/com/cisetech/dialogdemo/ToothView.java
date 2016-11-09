package com.cisetech.dialogdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * author：yinqingy
 * date：2016-10-18 22:07
 * blog：http://blog.csdn.net/vv_bug
 * desc：
 */

public class ToothView extends View {
    private Paint mPaint;
    private Path mPath;
    private float degree=45;
    private int lineHeight=20;
    public ToothView(Context context) {
        this(context,null);
    }

    public ToothView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ToothView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.WHITE);
        mPath=new Path();
        setWillNotDraw(false);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
        mPath.reset();
        int height=getHeight();
        int width=getWidth();
        lineHeight=getHeight();
        mPath.moveTo(0,height-lineHeight);
        int itemWidth= (int) Math.floor(2*lineHeight*Math.tan(Math.toRadians(degree/2)));
        int itemCount= (int) Math.floor((width-getPaddingLeft()-getPaddingRight())*1f/itemWidth);
        for (int i = -1; i < itemCount*2+1; i++) {
            if(i%2==0){
                mPath.lineTo(itemWidth*(i*1f/2),height-lineHeight);
            }else{
                mPath.lineTo(itemWidth*(i*1f/2),height-lineHeight+lineHeight);
            }
        }
        canvas.drawPath(mPath,mPaint);
    }
}
