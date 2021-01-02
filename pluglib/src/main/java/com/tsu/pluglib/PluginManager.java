package com.tsu.pluglib;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class PluginManager {

    public final String TAG = "PluginManager";
    private static final PluginManager instance = new PluginManager();

    public static PluginManager getInstance(){
        return instance;
    }

    private PluginManager(){

    }

    private Context mContext;
    private PluginAPK mPluginAPK;

    public PluginAPK getPluginAPK(){
        return mPluginAPK;
    }

    public void init(Context context){
        mContext = context;
    }

    public void loadApk(String apkPath){
        PackageInfo packageInfo = mContext.getPackageManager().getPackageArchiveInfo(apkPath , PackageManager.GET_ACTIVITIES | PackageManager.GET_SERVICES);
        if(packageInfo == null){
            Log.d(TAG , "loadApk packageInfo is null");
            return ;
        }

        DexClassLoader classLoader = createDexClassLoader(apkPath);
        AssetManager am = createAssetManager(apkPath);
        Resources resources = createResource(am);

        mPluginAPK = new PluginAPK(packageInfo , resources , classLoader);
    }

    private DexClassLoader createDexClassLoader(String apkPath){
        File file = mContext.getDir("dex" , Context.MODE_PRIVATE);
        return new DexClassLoader(apkPath , file.getAbsolutePath() , null , mContext.getClassLoader());
    }

    private AssetManager createAssetManager(String apkPath){
        try{
            AssetManager am = AssetManager.class.newInstance();
            Method method = AssetManager.class.getDeclaredMethod("addAssetPath" , String.class);
            method.invoke(am , apkPath);
            return am;
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    private Resources createResource(AssetManager am){
        Resources res = mContext.getResources();
        return new Resources(am , res.getDisplayMetrics() , res.getConfiguration());
    }

}
