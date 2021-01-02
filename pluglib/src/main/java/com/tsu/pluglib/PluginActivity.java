package com.tsu.pluglib;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class PluginActivity extends Activity implements IPlugin {

    private int mFrom = FROM_INTERNAL;
    private Activity mProxyActivity;

    @Override
    public void attach(Activity proxyActivity) {
        this.mProxyActivity = proxyActivity;
    }

    @Override
    public void onCreate(Bundle saveInstanceState) {
        if(saveInstanceState!=null){
            mFrom = saveInstanceState.getInt(Consts.FROM);
        }
        if(mFrom == FROM_INTERNAL){
            super.onCreate(saveInstanceState);
            mProxyActivity = this;
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        if(mFrom == FROM_INTERNAL){
            super.setContentView(layoutResID);
        }else{
            mProxyActivity.setContentView(layoutResID);
        }
    }

    @Override
    public void onStart() {
        if(mFrom == FROM_INTERNAL){
            super.onStart();
        }else{

        }
    }

    @Override
    public void onRestart() {
        if(mFrom == FROM_INTERNAL){
            super.onRestart();
        }else{

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(mFrom == FROM_INTERNAL){
            super.onActivityResult(requestCode , resultCode , data);
        }else{

        }
    }

    @Override
    public void onResume() {
        if(mFrom == FROM_INTERNAL){
            super.onResume();
        }else{

        }
    }

    @Override
    public void onPause() {
        if(mFrom == FROM_INTERNAL){
            super.onPause();
        }else{

        }
    }

    @Override
    public void onStop() {
        if(mFrom == FROM_INTERNAL){
            super.onStop();
        }else{

        }
    }

    @Override
    public void onDestroy() {
        if(mFrom == FROM_INTERNAL){
            super.onDestroy();
        }else{

        }
    }
}
