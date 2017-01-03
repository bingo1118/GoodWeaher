package com.example.broadcastbest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/12/28.
 */

public class MyImageView extends ImageView {


    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void startAnimation(){
        AnimationSet animationSet=new AnimationSet(true);
        RotateAnimation rotateAnimation=new RotateAnimation(0,180,RotateAnimation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setDuration(1000);
        rotateAnimation.setRepeatCount(100);
        rotateAnimation.setStartOffset(2000);
        rotateAnimation.setRepeatMode(Animation.REVERSE);

        ScaleAnimation scaleAnimation=new ScaleAnimation(1,0,1,0,ScaleAnimation.RELATIVE_TO_SELF,0.5f,ScaleAnimation.RELATIVE_TO_SELF,0.5f);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(500);
        scaleAnimation.setStartOffset(2500);
        scaleAnimation.setRepeatCount(100);
        scaleAnimation.setRepeatMode(Animation.REVERSE);

        AlphaAnimation alphaAnimation=new AlphaAnimation(1,0);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setDuration(500);
        alphaAnimation.setStartOffset(2500);
        alphaAnimation.setRepeatCount(100);
        Animation.AnimationListener animationListener=new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                if(MyImageView.this.isClickable()){
                    MyImageView.this.setClickable(false);
                    Toast.makeText(getContext(),"false",Toast.LENGTH_SHORT).show();
                }else{
                    MyImageView.this.setClickable(true);
                    Toast.makeText(getContext(),"true",Toast.LENGTH_SHORT).show();
                }
            }
        };
        alphaAnimation.setAnimationListener(animationListener);
        alphaAnimation.setRepeatMode(Animation.REVERSE);

        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);

        animationSet.setRepeatMode(Animation.REVERSE);
        animationSet.setRepeatCount(100);
        this.startAnimation(animationSet);
//        this.setVisibility(INVISIBLE);
    }
}
