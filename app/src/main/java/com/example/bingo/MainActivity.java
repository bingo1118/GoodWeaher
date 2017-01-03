package com.example.bingo;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.AndroidCharacter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navview;
    private Button button;
    private FloatingActionButton fab;

    private Fruit[] fruits={new Fruit("Apple",R.drawable.apple),new Fruit("Banana",R.drawable.banana),new Fruit("Barry",R.drawable.barry),
                                new Fruit("Hamy",R.drawable.hamy),new Fruit("Lemon",R.drawable.lemon),new Fruit("Lianwu",R.drawable.lianwu),
                                  new Fruit("Lizhi",R.drawable.lizhi),new Fruit("Oriange",R.drawable.oriange),new Fruit("Peak",R.drawable.peak),
                                      new Fruit("Tomato",R.drawable.tomato),new Fruit("Yeah",R.drawable.yeah),new Fruit("Ying",R.drawable.ying)};
    private List<Fruit> list=new ArrayList<Fruit>();
    private FruitAdapter adapter;

    private SwipeRefreshLayout swipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawerlayout);
        ActionBar actionbar=getSupportActionBar();
        if(actionbar!=null){
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeAsUpIndicator(R.drawable.track);
        }
        navview=(NavigationView)findViewById(R.id.nav_view);
        navview.setCheckedItem(R.id.nav_call);
        navview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawer(GravityCompat.START);
                Toast.makeText(MainActivity.this,item.getTitle(),Toast.LENGTH_LONG).show();
                return true;
            }
        });

        button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this).setIcon(R.mipmap.ic_launcher)
                        .setMessage("Exit?").setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        }).setNegativeButton("Canel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this,"Come back.",Toast.LENGTH_LONG).show();
                            }
                        });
                builder.show();
            }
        });

        fab=(FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"fabButton",Snackbar.LENGTH_LONG).setAction("undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this,"undo",Toast.LENGTH_LONG).show();
                    }
                }).show();
            }
        });

        initFruits();
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycle_view);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter=new FruitAdapter(list);
        recyclerView.setAdapter(adapter);

        swipe=(SwipeRefreshLayout)findViewById(R.id.swipe_refresh);
        swipe.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFruit();
            }
        });

    }

    private void refreshFruit(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(2000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initFruits();
                        adapter.notifyDataSetChanged();
                        swipe.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private void initFruits() {
        list.clear();
        for(int i=0;i<50;i++){
            Random random=new Random();
            int index=random.nextInt(fruits.length);
            list.add(fruits[index]);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.backup:
                Toast.makeText(this,"backup",Toast.LENGTH_LONG).show();
                break;
            case R.id.delete:
                Toast.makeText(this,"delete",Toast.LENGTH_LONG).show();
                break;
            case R.id.setting:
                Toast.makeText(this,"setting",Toast.LENGTH_LONG).show();
                break;
            default:
        }
        return true;
    }
}
