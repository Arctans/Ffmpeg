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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.user.lv.R;

import java.io.File;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

/**
 * Created by wyh on 2019/3/17.
 */
public abstract class BaseEditActivity extends AppCompatActivity {

    protected Toolbar mToolbar;
    protected View mRoot;

    private Boolean mShowSaveDoneAndPlayDialog;
    private String mOutputPath;
    private boolean mIsVideo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        mRoot = findViewById(R.id.root);
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle(getEditTitle());

    }

    protected abstract  String getEditTitle();
}
