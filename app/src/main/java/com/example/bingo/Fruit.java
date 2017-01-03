package com.example.bingo;

/**
 * Created by Administrator on 2016/12/18.
 */

public class Fruit {
    private String name;
    private int imageID;
    public Fruit(String name,int ID){
        this.name=name;
        this.imageID=ID;
    };
    public String getName(){
        return name;
    }
    public int getImageID(){
        return imageID;
    }
}
