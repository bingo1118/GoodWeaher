package com.example.viewtest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/1/1.
 */

public class MyView extends View {
    private Bitmap mbitmap=null;
    private final  Paint mPaint=new Paint();
    private final String title="Let's feel this new view.";
    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint.setColor(Color.GREEN);
    }
    public MyView(Context context, AttributeSet attrs,int defStyle){
        super(context,attrs,defStyle);
        mPaint.setColor(Color.GREEN);
    }
    public boolean initBitmap(int w,int h,int c){
        mbitmap=Bitmap.createBitmap(w,h, Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(mbitmap);
        Paint p=new Paint();
        String familyName="隶书";
        Typeface font=Typeface.create(familyName,Typeface.BOLD);
        p.setColor(Color.RED);
        p.setTypeface(font);
        p.setTextSize(22);
        canvas.drawText(title,0,100,p);
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mbitmap!=null){
            Matrix matrix=new Matrix();
            matrix.setRotate(90,120,120);
            canvas.drawBitmap(mbitmap,matrix,mPaint);
        }
    }
}
