package com.user.lv.utils;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.util.Log;

/** Arctan create 20210118 ****/
public class FileUtil {
    private  static final String TAG = FileUtil.class.getSimpleName();
    public static String getFilePath(final Context context,Uri uri){
        String path =null;
        if(DocumentsContract.isDocumentUri(context,uri)){
            if(FileUtil.isExternalStorageDocument(uri)){
                final String docId = DocumentsContract.getDocumentId(uri);
                String split[] = docId.split(":");
                if("primary".equalsIgnoreCase(split[0])){
                    return Environment.getExternalStorageDirectory()+ "/" +split[1];
                }
            }else if(FileUtil.isDownloadsDoucument(uri)){

            }else if(FileUtil.isDownloadsDoucument(uri)){

            }
        }
        return path;
    }
    public static boolean isExternalStorageDocument(Uri uri){
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }
    public static boolean isDownloadsDoucument(Uri uri){
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }
    public static boolean isMediaDoucument(Uri uri){
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

}
