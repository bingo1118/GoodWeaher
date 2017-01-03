package com.example.servicetest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button,button2,button3,button4;
    final String TAG="MyServise";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate: ");
        button=(Button)findViewById(R.id.button);
        button2=(Button)findViewById(R.id.button2);
        button3=(Button)findViewById(R.id.button3);
        button4=(Button)findViewById(R.id.button4);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
    }

    private ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.DownloadBinder mBinder=(MyService.DownloadBinder)service;
            mBinder.startDownload();
            mBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                Intent intent=new Intent(MainActivity.this,MyService.class);
                startService(intent);
                break;
            case R.id.button2:
                Intent intent2=new Intent(this,MyService.class);
                stopService(intent2);
                break;
            case R.id.button3:
                Intent intent3=new Intent(this,MyService.class);
                bindService(intent3,serviceConnection,BIND_AUTO_CREATE);
                break;
            case R.id.button4:
                Intent intent4=new Intent(this,MyService.class);
                unbindService(serviceConnection);
            default:
                break;
        }
    }
}
