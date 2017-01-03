package com.example.broadcastbest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class Main2Activity extends BaseActivity {
    private GameView gameView;
    private Button begin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        gameView=(GameView)findViewById(R.id.gameview);
        begin=(Button)findViewById(R.id.btn);
        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameView.start();
            }
        });
    }
}
