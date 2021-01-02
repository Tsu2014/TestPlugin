package com.tsu.testplugin.common;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class Utils {
    private static final String TAG = "Utils";

    public static String copyAssetAndWrite(Context context , String fileName){
        try{
            File cacheDir = context.getCacheDir();
            if(!cacheDir.exists()){
                cacheDir.mkdirs();
            }
            Log.d(TAG , "cacheDir : "+cacheDir.getAbsolutePath());
            File outFile = new File(cacheDir , fileName);
            InputStream is= context.getAssets().open(fileName);
            int streamAvailable = is.available();

            if(outFile.exists()){
                Log.d(TAG , "outFile is exists : "+outFile.length());
                if(outFile.length()<streamAvailable){
                    outFile.delete();
                }
            }
            if(!outFile.exists()){
                boolean res = outFile.createNewFile();
                if(res){

                    FileOutputStream fos = new FileOutputStream(outFile);
                    byte[] buffer = new byte[streamAvailable];
                    int byteCount;
                    while((byteCount = is.read(buffer))!=-1){
                        Log.d(TAG , "byteCount : "+byteCount);
                        fos.write(buffer , 0 , byteCount);
                    }
                    fos.flush();
                    is.close();
                    fos.close();
                    Log.d(TAG , "Download finish !");
                }
            }
            return outFile.getAbsolutePath();
        }catch(Exception e){
            e.printStackTrace();
        }

        return "";
    }
}
