package com.user.lv.edit;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.user.lv.R;

import java.io.File;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

/**
 * Created by wyh on 2019/3/17.
 */
public abstract class BaseEditActivity extends AppCompatActivity {
    private static final String TAG = BaseEditActivity.class.getSimpleName();

    private static final int REQUEST_CODE_Permission = 1;
    protected static final int REQUEST_CODE_PICK_AUDIO = MediaFile.TYPE_AUDIO;
    protected static final int REQUEST_CODE_PICK_VIDEO = MediaFile.TYPE_VIDEO;
    protected static final int REQUEST_CODE_PICK_IMG = MediaFile.TYPE_IMG;
    protected Toolbar mToolbar;
    protected View mRoot;

    private Boolean mShowSaveDoneAndPlayDialog;
    private String mOutputPath;
    private boolean mIsVideo;

    protected abstract void onMenuClick(int order);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        Log.d(TAG, "setContentView: ");
        mRoot = findViewById(R.id.root);
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle(getEditTitle());
        mToolbar.setOverflowIcon(getDrawable(R.drawable.ic_more_horiz_white_24dp));
        setSupportActionBar(mToolbar);
        mToolbar.inflateMenu(R.menu.menu_toolbar);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Menu menu = mToolbar.getMenu();
                for(int i = 0; i <menu.size(); i++){
                    if(menu.getItem(i) == item){
                        onMenuClick(i);
                    }
                }
                return false;
            }
        });
        requestWritePermissions();
    }
    protected boolean hasWritePermissions() {
        int hasWritePermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return hasWritePermission == PackageManager.PERMISSION_GRANTED;
    }

    private void requestWritePermissions() {
        if (!hasWritePermissions()) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_Permission);
        }
    }
    protected abstract  String getEditTitle();
}
