package com.user.lv.utils;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;

/** Arctan create 20210118 ****/
public class FileUtil {
    private  static final String TAG = FileUtil.class.getSimpleName();

    public static final String ROOT_DIR = Environment.getExternalStorageDirectory().getPath() +
            File.separator + "FFmpegCmd";
    public static final String OUTPUT_DIR = ROOT_DIR + File.separator + "Output";
    public static final String OUTPUT_AUDIO_DIR = OUTPUT_DIR + File.separator + "audio";
    public static final String OUTPUT_VIDEO_DIR = OUTPUT_DIR + File.separator + "video";
    public static final String CACHE_DIR = ROOT_DIR + File.separator + "cache";


    public static String getFilePath(final Context context,Uri uri){
        String path =null;
        if(DocumentsContract.isDocumentUri(context,uri)){
            if(FileUtil.isExternalStorageDocument(uri)){
                final String docId = DocumentsContract.getDocumentId(uri);
                String split[] = docId.split(":");
                if("primary".equalsIgnoreCase(split[0])){
                    return Environment.getExternalStorageDirectory()+ "/" +split[1];
                }
            }else if(FileUtil.isMediaDoucument(uri)){
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[] {split[1]};

                return getDataColumn(context, contentUri, selection, selectionArgs);

            }else if(FileUtil.isDownloadsDoucument(uri)){
                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
        }
        return path;
    }
    
    /**
     * 获取此Uri的数据列的值。这对于MediaStore uri和其他基于文件的内容提供程序非常有用。
     */
    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } catch (IllegalArgumentException e){
            //do nothing
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
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
