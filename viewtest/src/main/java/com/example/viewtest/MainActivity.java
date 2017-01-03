package com.example.viewtest;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.media.*;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private int index;
    private int totalCount;
    private ArrayList<Image> images=new ArrayList<Image>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView)findViewById(R.id.list);
        initdata();
        ImageAdapter imageAdapter=new ImageAdapter(MainActivity.this,R.layout.item,images);
        listView.setAdapter(imageAdapter);
        }


    private void initdata() {
        Context context = MainActivity.this;
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = contentResolver.query(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, null, null, null, null);
        totalCount = cursor.getCount();
        cursor.moveToFirst();
        for (int i = 0; i < totalCount; i++) {
            int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Thumbnails.DATA);
            String id = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Thumbnails.IMAGE_ID));
            String src = cursor.getString(index);
            Image image = new Image(src, id);
            images.add(image);
            index = i;
            cursor.moveToNext();
        }
    }
}
