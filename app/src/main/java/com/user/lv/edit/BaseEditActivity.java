package com.user.lv.edit;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileUtils;
import android.provider.MediaStore;

import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.user.lv.BuildConfig;
import com.user.lv.R;
import com.user.lv.common.SecureProgressDialog;
import com.user.lv.utils.FileUtil;

import java.io.File;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

/**
 * Created by Arctan on 20210120
 */
public abstract class BaseEditActivity extends AppCompatActivity {
    private static final String TAG = BaseEditActivity.class.getSimpleName();

    private SecureProgressDialog mProgressDialog;
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
    protected abstract void createOptionsMenu(Menu menu);
    protected abstract  int getEditTitle();
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
        mToolbar.setTitle(getResources().getString(getEditTitle()));
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(isPickFileCode(requestCode) && resultCode == RESULT_OK){
            Uri uri = null;
            if(data != null) {
                uri = data.getData();
            }
            if(uri != null){
                Log.d(TAG, "onActivityResult: "+uri);
                String path =  FileUtil.getFilePath(getApplicationContext(),uri);
                if(path!=null){
                    File file = new File(path);
                    if(file.exists()){
                        MediaFile mediaFile = new MediaFile();
                        mediaFile.setName(file.getName());
                        mediaFile.setPath(file.getPath());
                        mediaFile.setType(requestCode);
                        onPickFile(mediaFile);
                    }
                }
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        menu.clear();
        createOptionsMenu(menu);
        return true;
    }
    /**
     * 初始化正在加载框
     */
    @MainThread
    private void initLoadingDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new SecureProgressDialog(this);
            mProgressDialog.setMessage("正在处理...");
            mProgressDialog.setCanceledOnTouchOutside(false);
        }
    }
    protected void showLoadingDialog(){
        if(isFinishing() || isDestroyed()){
            return ;
        }else{
            initLoadingDialog();
            if(!mProgressDialog.isShowing()){
                mProgressDialog.show();
            }

        }
    }
    /**
     * 隐藏正在加载框
     */
    @MainThread
    protected void dismissLoadingDialog() {
        if (isFinishing() || isDestroyed()) {
            return;
        }
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
    protected void showSaveDoneAndPlayDialog(final String outputAudio, final boolean video) {
        if (isFinishing() || isDestroyed()) {
            return;
        }

    }

    protected boolean hasWritePermissions() {
        int hasWritePermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return hasWritePermission == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_Permission:
                if (!hasWritePermissions()) {
                    Toast.makeText(this, R.string.write_permission, Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
    }

    private void requestWritePermissions() {
        if (!hasWritePermissions()) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_Permission);
        }
    }
    private boolean isPickFileCode(int code){

        return code == REQUEST_CODE_PICK_AUDIO
                || code == REQUEST_CODE_PICK_VIDEO
                || code == REQUEST_CODE_PICK_IMG;
    }
    private void pickMedia(final String filter, int code) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType(filter);
        startActivityForResult(Intent.createChooser(intent, "Select"), code);
    }
    protected void pickVideo(){
        pickMedia("video/*", REQUEST_CODE_PICK_VIDEO);
    }
    protected void pickAudio(){
        pickMedia("audio/*",REQUEST_CODE_PICK_AUDIO);
    }
    protected void pickImg(){
        pickMedia("image/*",REQUEST_CODE_PICK_IMG);
    }
    protected void onPickFile(MediaFile mediaFile){

    }
    protected void onDeleteFile(MediaFile mediaFile){

    }
    protected void deleteLastMediaFile(){

    }
    protected void playVideo(String path) {
//        play(path, "video/*");
        play(path);
    }

    protected void play(String path, String filter) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        File file = new File(path);
        if(file.exists()){
            Uri contentUri = FileProvider.getUriForFile(getApplicationContext(), BuildConfig.APPLICATION_ID + ".fileProvider", file);
            intent.setDataAndType(contentUri, filter);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            startActivity(intent);
        }
    }
    protected void play(String path){
        Intent intent = new Intent("android.intent.action.FFPLAYER");
        intent.putExtra("path",path);
        startActivity(intent);
    }
}
