package com.tsu.pluglib;

import android.content.pm.PackageInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;

import dalvik.system.DexClassLoader;

public class PluginAPK {

    public PackageInfo mPackageInfo;
    public Resources mResources;
    public AssetManager mAssetManager;
    public DexClassLoader mDexClassLoader;

    public PluginAPK(PackageInfo mPackageInfo , Resources mResources ,  DexClassLoader mDexClassLoader){
        this.mPackageInfo = mPackageInfo;
        this.mResources = mResources;
        this.mAssetManager = mAssetManager;
        this.mDexClassLoader = mDexClassLoader;
    }

}
