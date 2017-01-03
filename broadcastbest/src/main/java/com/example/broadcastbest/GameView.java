package com.example.broadcastbest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/12/26.
 */

public class GameView extends View {
    private AnimationDrawable frameAnimation=null;
    private Context mContext;
    private Drawable drawable=null;
    public GameView(Context context) {
        super(context);
        mContext=context;
        frameAnimation=new AnimationDrawable();
        for(int i=1;i<=9;i++){
            int id=getResources().getIdentifier("a"+i,"drawable",mContext.getPackageName());
            drawable=getResources().getDrawable(id);
            frameAnimation.addFrame(drawable,100);
        }
        frameAnimation.setOneShot(false);
        this.setBackgroundDrawable(frameAnimation);
    }

    public GameView(Context context,AttributeSet attrs){
        super(context, attrs);
        mContext=context;
        frameAnimation=new AnimationDrawable();
        for(int i=1;i<=9;i++){
            int id=getResources().getIdentifier("a"+i,"drawable",mContext.getPackageName());
            drawable=getResources().getDrawable(id);
            frameAnimation.addFrame(drawable,100);
        }
        frameAnimation.setOneShot(false);
        this.setBackgroundDrawable(frameAnimation);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    public boolean start(){
        frameAnimation.start();
        return true;
    }
}
