package com.example.fragmentbest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NewContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_content);
        String newsTitle=getIntent().getStringExtra("title");
        String newsContent=getIntent().getStringExtra("content");
        NewsContentFragment newsContentFragment=(NewsContentFragment)getSupportFragmentManager().findFragmentById(R.id.news_content_fragment);
        newsContentFragment.refresh(newsTitle,newsContent);
    }
    public static void actionStart(Context context,String title,String content){
        Intent intent=new Intent(context,NewContentActivity.class);
        intent.putExtra("title",title);
        intent.putExtra("content",content);
        context.startActivity(intent);
    }
}
