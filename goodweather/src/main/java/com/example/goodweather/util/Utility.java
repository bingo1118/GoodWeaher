package com.example.goodweather.util;

import android.text.TextUtils;
import android.webkit.WebView;

import com.example.goodweather.db.City;
import com.example.goodweather.db.County;
import com.example.goodweather.db.Province;
import com.example.goodweather.gson.Weather;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/1/3.
 */

public class Utility {
    /**
     * 解析和处理省份数据
     * @param response
     * @return
     */
    public static boolean handleProvinceResponse(String response){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray jsonArray=new JSONArray(response);
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                    Province province=new Province();
                    province.setProvinceName(jsonObject.getString("name"));
                    province.setProvinceCode(jsonObject.getInt("id"));
                    province.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的城市数据
     * @param response
     * @param provinceId
     * @return
     */
    public static boolean handleCityResponse(String response,int provinceId){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray jsonArray=new JSONArray(response);
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                    City city=new City();
                    city.setCityName(jsonObject.getString("name"));
                    city.setCityCode(jsonObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 处理和解析服务器返回的城镇信息
     * @param response
     * @param cityId
     * @return
     */
    public static boolean handleCountyResponse(String response,int cityId){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray jsonArray=new JSONArray(response);
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                    County county=new County();
                    county.setCountyName(jsonObject.getString("name"));
                    county.setWeatherId(jsonObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 将返回的json数据解析成Weather实体类
     * @param response
     * @return
     */
    public static Weather handleWeatherResponse(String response){
        try {
            JSONObject jsonObject=new JSONObject(response);
            JSONArray jsonArray=jsonObject.getJSONArray("HeWeather");
            String weatherContent=jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent,Weather.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
