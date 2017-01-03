package com.example.broadcastbest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    private Button sendBroadcast;
    private Button btn1,btn2,btn3,btn4,btn5;
    private ImageView img;
    private MyImageView myimage;
    private Animation alpha=null;
    private Animation scale=null;
    private Animation translate=null;
    private Animation rolate=null;
    private int sum=0;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendBroadcast=(Button)findViewById(R.id.send);
        sendBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction("com.example.broadcastbest.FORCE_OFFLINE");
                sendBroadcast(intent);
            }
        });
        img=(ImageView)findViewById(R.id.image);
        btn1=(Button)findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
        btn2=(Button)findViewById(R.id.btn2);
        btn2.setOnClickListener(this);
        btn3=(Button)findViewById(R.id.btn3);
        btn3.setOnClickListener(this);
        btn4=(Button)findViewById(R.id.btn4);
        btn4.setOnClickListener(this);
        btn5=(Button)findViewById(R.id.btn5);
        btn5.setOnClickListener(this);
        myimage=(MyImageView)findViewById(R.id.myimage);
        myimage.setOnClickListener(this);
        tv=(TextView)findViewById(R.id.text);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                alpha=new AlphaAnimation(0.1f,1.0f);
                alpha.setDuration(5000);
                img.startAnimation(alpha);
                break;
            case R.id.btn2:
                scale= AnimationUtils.loadAnimation(MainActivity.this,R.anim.scale_anim);
                img.startAnimation(scale);
                break;
            case R.id.btn3:
                translate=new TranslateAnimation(0,1000,0,1000);
                translate.setDuration(10000);
                img.startAnimation(translate);
                break;
            case R.id.btn4:
//                rolate=new RotateAnimation(1f,+360f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,5f);
//                rolate.setDuration(1000);
//                sendBroadcast.startAnimation(rolate);
                if(myimage.getVisibility()==View.INVISIBLE){
                    myimage.setVisibility(View.VISIBLE);
                }else{
                    myimage.startAnimation();
                }
                break;
            case R.id.btn5:
               Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
                break;
            case R.id.myimage:

                sum+=1;
                tv.setText(sum+"");
                break;
            default:
                break;
        }
    }
}
