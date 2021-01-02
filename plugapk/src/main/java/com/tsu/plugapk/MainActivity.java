package com.tsu.plugapk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.tsu.pluglib.PluginAPK;
import com.tsu.pluglib.PluginActivity;

public class MainActivity extends PluginActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}