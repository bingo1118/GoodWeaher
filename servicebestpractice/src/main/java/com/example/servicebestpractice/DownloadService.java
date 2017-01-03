package com.example.servicebestpractice;

import android.app.DownloadManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import java.io.File;

public class DownloadService extends Service {

    private DownloadTast downloadTast;
    private String downloadurl;
    private DownloadListener listener=new DownloadListener() {
        @Override
        public void onProgress(int progress) {
            getNotificationManege().notify(1,getNotification("Downloading...",progress));
        }

        @Override
        public void onSuccess() {
            downloadTast=null;
            stopForeground(true);
            getNotificationManege().notify(1,getNotification("Downloading Success",-1));
            Toast.makeText(DownloadService.this,"Success",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailed() {
            downloadTast=null;
            stopForeground(true);
            getNotificationManege().notify(1,getNotification("Downloading Failed",-1));
            Toast.makeText(DownloadService.this,"Failed",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPause() {
            downloadTast=null;
            getNotificationManege().notify(1,getNotification("Downloading Pause",-1));
            Toast.makeText(DownloadService.this,"Pauseed",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel() {
            downloadTast=null;
            stopForeground(true);
            Toast.makeText(DownloadService.this,"Cenceled",Toast.LENGTH_SHORT).show();
        }
    };

    private DownloadBinder binder =new DownloadBinder();

    public class DownloadBinder extends Binder{
        public void start(String url){
            if(downloadTast==null){
                downloadurl=url;
                downloadTast=new DownloadTast(listener);
                downloadTast.execute(downloadurl);
                startForeground(1,getNotification("Downloading...",0));
                Toast.makeText(DownloadService.this,"Downloading...",Toast.LENGTH_SHORT).show();
            }
        }
        public void pause(){
            if(downloadTast!=null){
                downloadTast.pauseDownload();
            }
        }
        public void cencel(){
            if(downloadTast!=null){
                downloadTast.canceldownload();
            }else{
                if(downloadurl!=null){
                    String name=downloadurl.substring(downloadurl.lastIndexOf("/"));
                    String directory= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
                    File file=new File(directory+name);
                    if(file.exists()){
                        file.delete();
                    }
                    getNotificationManege().cancel(1);
                    stopForeground(true);
                    Toast.makeText(DownloadService.this,"Cenceled...",Toast.LENGTH_SHORT).show();
                }
            }

        }
    }
    public DownloadService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
    public NotificationManager getNotificationManege(){
        return (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
    }
    public Notification getNotification(String title,int progress){
        Intent intent=new Intent(this,MainActivity.class);
        PendingIntent pi=PendingIntent.getActivity(this,0,intent,0);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
        builder.setContentIntent(pi);
        builder.setContentTitle(title);
        if(progress>0){
            builder.setContentText(progress+"%");
            builder.setProgress(100,progress,false);
        }
        return builder.build();
    }
}
