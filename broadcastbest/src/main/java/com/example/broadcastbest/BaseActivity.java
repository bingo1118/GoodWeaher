package com.example.broadcastbest;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AlertDialog;

/**
 * Created by Administrator on 2016/12/26.
 */

public class BaseActivity extends Activity {

    private ForceOfflineReciver reciver;
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        ActivityControlor.addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        reciver=new ForceOfflineReciver();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("com.example.broadcastbest.FORCE_OFFLINE");
        registerReceiver(reciver,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(reciver!=null){
            unregisterReceiver(reciver);
            reciver=null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityControlor.removeActivty(this);
    }
    class ForceOfflineReciver extends BroadcastReceiver{

        @Override
        public void onReceive(final Context context, final Intent intent) {
            AlertDialog.Builder builder=new AlertDialog.Builder(context);
            builder.setTitle("Force Offline");
            builder.setMessage("You have to reload your application!");
            builder.setCancelable(false);
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityControlor.romoveAllActivity();
                    Intent intent1=new Intent(context,LoginActivity.class);
                    context.startActivity(intent);
                }
            });
            builder.show();
        }
    }
}
