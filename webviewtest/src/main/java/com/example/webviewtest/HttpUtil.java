package com.example.webviewtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2016/12/20.
 */

public class HttpUtil {
    public static void getResponse(final String httpurl, final Listener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection=null;
                StringBuilder builder=null;
                try {
                    URL url=new URL(httpurl);
                    connection= (HttpURLConnection) url.openConnection();
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setRequestMethod("GET");
                    InputStream in=connection.getInputStream();
                    BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(in));
                    builder=new StringBuilder();
                    String line="";
                    while((line=bufferedReader.readLine())!=null){
                        builder.append(line);
                    }
                    if(listener!=null){
                        listener.onFinish(builder.toString());
                    }
                } catch (Exception e) {
                    if(listener!=null){
                        listener.onError(e);
                    }
                } finally {
                    if (connection!=null){
                        connection.disconnect();
                    }
                }
            }
        }).start();

    }
    public interface Listener{
        void onFinish(String response);
        void onError(Exception e);
    }
}
