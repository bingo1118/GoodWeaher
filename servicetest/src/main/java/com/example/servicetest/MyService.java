package com.example.servicetest;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {

    private DownloadBinder downloadBinder=new DownloadBinder();

    public class DownloadBinder extends Binder{
        public void startDownload(){
            Log.e("MyServise", "startDownload" );
        }
        public void getProgress(){
            Log.e("MyServise", "getProgress" );
        }
    }
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("MyServise","Create Servise");
        Intent intent=new Intent(this,MainActivity.class);
        PendingIntent pi=PendingIntent.getActivity(this,0,intent,0);
        Notification notification=new NotificationCompat.Builder(this)
                                    .setContentTitle("This is a title")
                                    .setContentText("this is text")
                                    .setWhen(System.currentTimeMillis())
                                    .setSmallIcon(R.mipmap.ic_launcher)
                                    .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                                    .setContentIntent(pi)
                                    .build();
        startForeground(1,notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("MyServise","Start Servise");
        System.out.print("123");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("MyServise","Stop Servise");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e("Myservise", "onBind" );
        return downloadBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("MyService", "onUnbind" );
        return super.onUnbind(intent);
    }
}
