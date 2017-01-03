package com.example.broadcastbest;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/26.
 */

public class ActivityControlor {
    private static List<Activity> list=new ArrayList<Activity>();
    /**
     *添加活动到list中
     */
    public static void addActivity(Activity activity){
        list.add(activity);
    }
    public static void removeActivty(Activity activity){
        list.remove(activity);
    }
    public static void romoveAllActivity(){
        for(Activity activity:list){
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
    }
}
