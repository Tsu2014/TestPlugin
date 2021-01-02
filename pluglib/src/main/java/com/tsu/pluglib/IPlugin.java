package com.tsu.pluglib;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public interface IPlugin {

    int FROM_INTERNAL = 0; //外部
    int FROM_EXTERNAL = 1;  //内部

    void attach(Activity proxyActivity);
    void onCreate(Bundle saveInstanceState);
    void onStart();
    void onRestart();
    void onActivityResult(int requestCode , int resultCode , Intent data);
    void onResume();
    void onPause();
    void onStop();
    void onDestroy();
}
