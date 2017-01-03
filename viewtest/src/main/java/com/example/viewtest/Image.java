package com.example.viewtest;

/**
 * Created by Administrator on 2017/1/2.
 */

public class Image {
    private String image_data;
    private String image_id;

    public Image(String data,String id){
        image_data=data;
        image_id=id;
    }

    public String getImage_data() {
        return image_data;
    }

    public void setImage_data(String image_data) {
        this.image_data = image_data;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }
}
