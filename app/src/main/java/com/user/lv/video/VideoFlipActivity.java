package com.user.lv.video;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.user.lv.R;
import com.user.lv.edit.EditMediaListActivity;

import androidx.annotation.Nullable;


/**
 * Created by Arctab on 2021/01/18.
 */
public class VideoFlipActivity extends EditMediaListActivity {
        public static String TAG = VideoFlipActivity.class.getSimpleName();
        public static final int TITLE = R.string.Video_mirror;

        @Override
        protected int getEditTitle() {
                return TITLE;
        }


        @Override
        protected void onMenuClick(int order) {
                Log.d(TAG, "onMenuClick: "+order);
                if(order == 0){
                        if(mMediaFileList.size()==1){
                                Log.d(TAG, "onMenuClick: hava selete video");
                        }
                        pickVideo();
//                        pickAudio();
                }else if(order == 1){
                       deleteLastMediaFile();
                }else if(order == 2){

                }else if(order == 3){

                }
        }

        @Override
        protected void createOptionsMenu(Menu menu) {
                menu.add(R.string.seletc_video);
                menu.add(R.string.delete_video);
                menu.add(R.string.upside_down);
                menu.add(R.string.flip_left_right);
        }

}
