package com.user.lv.video;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.user.lv.R;
import com.user.lv.edit.EditMediaListActivity;

import androidx.annotation.Nullable;


/**
 * Created by wyh on 2019/3/20.
 */
public class VideoFlipActivity extends EditMediaListActivity {
        public static String TAG = VideoFlipActivity.class.getSimpleName();
        public static final String TITLE = "视频镜像";

        @Override
        protected String getEditTitle() {
                return TITLE;
        }
        private void createOptionMenu(Menu menu){
                menu.add("选择视频");
                menu.add("删除视频");
                menu.add("上下翻转");
                menu.add("左右翻转");
        }
}
