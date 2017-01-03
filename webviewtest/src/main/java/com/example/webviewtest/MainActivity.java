package com.example.webviewtest;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private WebView webview;
    private TextView textView;

    private void showResult(final String response){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText(response);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webview=(WebView)findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl("http://www.hao123.com");

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                OkHttpClient client=new OkHttpClient();
//                Request request=new Request.Builder().url("http://172.27.35.2/getdata.xml").build();
//                try {
//                    Response response=client.newCall(request).execute();
//                    String s=response.body().string();
//                    parseXMLWithPull(s);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                HttpUtil.getResponse("http://172.27.247.2/getdata.xml", new HttpUtil.Listener() {
                    @Override
                    public void onFinish(String response) {
//                        parseXMLWithPull(response);
                        textView.setText(response);
                    }

                    @Override
                    public void onError(Exception e) {
                        e.printStackTrace();
                    }
                });

//            }
//        }).start();

        textView=(TextView)findViewById(R.id.tv);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                HttpURLConnection connection=null;
//                BufferedReader buffered=null;
//                try {
//                    URL url=new URL("http://www.hao123.com");
//                    connection=(HttpURLConnection)url.openConnection();
//                    connection.setRequestMethod("POST");
//                    DataOutputStream out=new DataOutputStream(connection.getOutputStream());
//                    out.writeBytes("name=admin&password=123456");
//                    connection.setConnectTimeout(8000);
//                    connection.setReadTimeout(8000);
//                    InputStream inputStream=connection.getInputStream();
//                    buffered=new BufferedReader(new InputStreamReader(inputStream));
//                    String line;
//                    StringBuilder builder=new StringBuilder();
//                    while((line=buffered.readLine())!=null){
//                        builder.append(line);
//                    }
//                    showResult(builder.toString());
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                }catch (IOException e){
//                    e.printStackTrace();
//                }finally {
//                    if(buffered!=null){
//                        try {
//                            buffered.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        if(connection!=null){
//                            connection.disconnect();
//                        }
//                    }
//                }
//            }
//        }).start();
    }

    private void parseXMLWithPull(String xmlData){

        StringBuilder builder=new StringBuilder();
        try {
            XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
            XmlPullParser parser=factory.newPullParser();
            parser.setInput(new StringReader(xmlData));
            int eventType=parser.getEventType();
            String id="";
            String name="";
            String version="";
            while(eventType!=XmlPullParser.END_DOCUMENT){
                String nodename=parser.getName();
                switch (eventType){
                    case XmlPullParser.START_TAG:{
                        if("id".equals(nodename)){
                            id=parser.nextText();
                        }else if("name".equals(nodename)){
                            name=parser.nextText();
                        }else if("version".equals(nodename)){
                            version=parser.nextText();
                        }
                        break;
                    }
                    case XmlPullParser.END_TAG:{
                        if("app".equals(nodename)){
                            builder.append(id+" ");
                            builder.append(name+" ");
                            builder.append(version+"\r\n");
                        }
                        break;
                    }
                    default: break;
                }
                eventType=parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        showResult(builder.toString());
    }

}
