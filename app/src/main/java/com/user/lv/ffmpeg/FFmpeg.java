package com.user.lv.ffmpeg;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.user.lv.R;
import com.user.lv.common.App;
import com.user.lv.common.AppExecutors;
import com.user.lv.common.PermissionHelper;

import java.util.List;

enum  FFmpeg {
    ffmpeg;
    private static final String TAG = FFmpeg.class.getSimpleName();
    private Callback mCallBack;
    private volatile boolean mIsRunning = false;


    static FFmpeg getInstance(){
         return ffmpeg;
    }

    private boolean isRunning(){
        return mIsRunning;
    }

    public void run(List<String> list,Callback callback){
        String[] commands =new String[list.size()];
        list.toArray(commands);
        run(commands,callback);
     }

     private void run(@NonNull final String[] cmd,@NonNull final Callback callback){
         if(!PermissionHelper.hasWrieteAndReadStoragePermission(App.get())){
             AppExecutors.executeMain(new Runnable() {
                 @Override
                 public void run() {
                     Toast.makeText(App.get(),App.get().getString(R.string.write_permission),Toast.LENGTH_LONG).show();
                 }
             });
         }
         mCallBack = callback;
         AppExecutors.executeWork(new Runnable() {
             @Override
             public void run() {
                mIsRunning = true;
                int ret = -1;
                 try {
                     ret = FFmpegJni.execute(cmd);
                     done(callback, ret != 1);
                 } catch (Exception e) {
                     done(callback, ret != 1);
                 }

             }
         });
         return ;
     }

    private void done(final Callback callback, final boolean success) {
        if (callback != null) {
            AppExecutors.executeMain(new Runnable() {
                @Override
                public void run() {
                    if (success) {
                        callback.onSuccess();
                    } else {
                        callback.onFail();
                    }
                }
            });
        }
    }


    private final Runnable mProgressRunnable = new Runnable() {
        @Override
        public void run() {
            for (; ; ) {
                if (isRunning()) {
                    if (mCallBack != null) {
                        String log = FFmpegJni.getLog();
                        mCallBack.onLog(log);
                    }
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };
}
