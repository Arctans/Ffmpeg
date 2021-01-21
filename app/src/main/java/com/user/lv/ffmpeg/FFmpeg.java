package com.user.lv.ffmpeg;

import androidx.annotation.NonNull;

import com.user.lv.common.App;
import com.user.lv.common.PermissionHelper;

import java.util.List;

enum  FFmpeg {
     ffmpeg;
     static FFmpeg getInstance(){
         return ffmpeg;
    }
    public void run(List<String> list,Callback callback){
        String[] commands =new String[list.size()];
        list.toArray(commands);
        run(commands,callback);
     }

     private void run(@NonNull final String[] cmd,@NonNull final Callback callback){
         if(PermissionHelper.hasWrieteAndReadStoragePermission(App.get())){

         }
         return ;
     }

}
