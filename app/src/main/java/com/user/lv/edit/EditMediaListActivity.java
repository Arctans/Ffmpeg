package com.user.lv.edit;


import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.user.lv.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wyh on 2019/3/20.
 */
public abstract class EditMediaListActivity extends BaseEditActivity {
    private  static final String TAG =EditMediaListActivity.class.getSimpleName();
    protected RecyclerView mRecyclerView;
    protected List<MediaFile> mMediaFileList;
    protected MediaItemAdapter mediaItemAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.activity_media_list);
        mRecyclerView = findViewById(R.id.recycleView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mMediaFileList = new ArrayList<>();


    }
}
