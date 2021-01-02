package com.tsu.testplugin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.LocaleDisplayNames;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tsu.pluglib.PluginManager;
import com.tsu.pluglib.ProxyActivity;
import com.tsu.testplugin.common.Utils;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "MainActivity";
    private TextView textView;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setListeners();
        PluginManager.getInstance().init(this);
    }

    private void initViews(){
        textView = findViewById(R.id.main_textview);
        button1 = findViewById(R.id.main_button1);
        button2 = findViewById(R.id.main_button2);
        button3 = findViewById(R.id.main_button3);
        button4 = findViewById(R.id.main_button4);
    }

    private void setListeners(){
        button1.setOnClickListener(onClickListener);
        button2.setOnClickListener(onClickListener);
        button3.setOnClickListener(onClickListener);
        button4.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.main_button1:
                    action1();
                    break;
                case R.id.main_button2:
                    action2();
                    break;
                case R.id.main_button3:
                    action3();
                    break;
                case R.id.main_button4:
                    action4();
                    break;
            }
        }
    };

    private void action1(){
        Log.d(TAG , "action1");
        loadPluginLib();
    }

    private void action2(){
        Log.d(TAG , "action2");
        startPluginActivity();
    }

    private void action3(){
        Log.d(TAG , "action3");
    }

    private void action4(){
        Log.d(TAG , "action4");
    }

    private void loadPluginLib(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String apkPath = Utils.copyAssetAndWrite(MainActivity.this , "plugin.apk");
                Log.d(TAG , "apkPath : "+apkPath);
                PluginManager.getInstance().loadApk(apkPath);
            }
        }).start();
    }

    private void startPluginActivity(){
        Intent intent = new Intent();
        intent.setClass(this , ProxyActivity.class);
        intent.putExtra("className" , "com.tsu.plugapk.MainActivity");
        startActivity(intent);
    }
}