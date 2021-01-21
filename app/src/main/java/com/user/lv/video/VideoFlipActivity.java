package com.user.lv.video;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.google.android.material.snackbar.Snackbar;
import com.user.lv.R;
import com.user.lv.common.SnackBarUtil;
import com.user.lv.edit.EditMediaListActivity;
import com.user.lv.edit.MediaFile;
import com.user.lv.ffmpeg.Callback;
import com.user.lv.ffmpeg.FFmpegVideo;
import com.user.lv.utils.FileUtil;

import androidx.annotation.Nullable;

import java.io.File;
import java.util.List;


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
//                                Log.d(TAG, "onMenuClick: hava selete video");
                                SnackBarUtil.showError(mRoot,getString(R.string.has_selected_video));
                        }
                        pickVideo();
//                        pickAudio();
                }else if(order == 1){
                       deleteLastMediaFile();
                }else if(order == 2){
                        run(mMediaFileList,true);
                }else if(order == 3){
                        run(mMediaFileList,false);
                }
        }

        @Override
        protected void createOptionsMenu(Menu menu) {
                menu.add(R.string.seletc_video);
                menu.add(R.string.delete_video);
                menu.add(R.string.upside_down);
                menu.add(R.string.flip_left_right);
        }
        private void run(List<MediaFile> mMediaFileList,Boolean isVertical){
                final String output = FileUtil.OUTPUT_VIDEO_DIR + File.separator +
                        "flip_" + TAG + ".mp4";
                showLoadingDialog();
                FFmpegVideo.flipVideo(mMediaFileList.get(0).getPath(), output, isVertical, new Callback() {
                        @Override
                        public void onLog(String log) {

                        }

                        @Override
                        public void onSuccess() {
                                dismissLoadingDialog();
                                showSaveDoneAndPlayDialog(output,true);
                        }

                        @Override
                        public void onFail() {
                                dismissLoadingDialog();
                                SnackBarUtil.showError(mRoot,getString(R.string.mix_fail));
                        }
                });


        }
}
