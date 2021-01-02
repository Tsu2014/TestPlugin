package com.tsu.pluglib;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class ProxyActivity extends Activity {

    private String mClassName;
    private PluginAPK mPluginApk;
    private IPlugin mIplugin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mClassName = getIntent().getStringExtra("className");
        mPluginApk = PluginManager.getInstance().getPluginAPK();

        launchPluginActivity();
    }

    private void launchPluginActivity(){
        if(mPluginApk == null){
            throw new RuntimeException("Load your apk file first please!");
        }

        try{
            Class<?> clazz = mPluginApk.mDexClassLoader.loadClass(mClassName);
            Object object = clazz.newInstance();
            if(object instanceof IPlugin){
                mIplugin = (IPlugin) object;
                mIplugin.attach(this);
                Bundle bundle = new Bundle();
                bundle.putInt(Consts.FROM , IPlugin.FROM_EXTERNAL);
                mIplugin.onCreate(bundle);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Resources getResources() {
        return mPluginApk!=null ? mPluginApk.mResources : super.getResources();
    }

    @Override
    public AssetManager getAssets() {
        return mPluginApk !=null ? mPluginApk.mAssetManager : super.getAssets();
    }

    @Override
    public ClassLoader getClassLoader() {
        return mPluginApk != null ? mPluginApk.mDexClassLoader : super.getClassLoader();
    }
}
