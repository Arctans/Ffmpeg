package com.user.lv.common;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.core.content.ContextCompat;

/**
 * Created by Arctan on 2021/01/21.
 */
public class PermissionHelper {
    private static final String TAG = PermissionHelper.class.getSimpleName();
    public static boolean hasWrieteAndReadStoragePermission(Context context){

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            return true;
        }
        if(ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            return  false;
        }
        return true;
    }
}
