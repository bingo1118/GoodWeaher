package com.example.viewtest;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/1/2.
 */

public class ImageAdapter extends ArrayAdapter<Image> {
    int resouseID;
    public ImageAdapter(Context context, int resource, ArrayList<Image> objects) {
        super(context, resource, objects);
        resouseID=resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view= LayoutInflater.from(getContext()).inflate(resouseID,parent,false);
        Image image=getItem(position);
        ImageView iv=(ImageView)view.findViewById(R.id.item_image);
        TextView tv=(TextView)view.findViewById(R.id.item_text);
        iv.setImageURI(Uri.parse(image.getImage_data()));
        tv.setText(image.getImage_id());
        return view;
    }
}
