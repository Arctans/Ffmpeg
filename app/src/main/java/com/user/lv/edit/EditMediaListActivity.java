package com.user.lv.edit;


import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.user.lv.R;
import com.user.lv.common.Click;

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
        mediaItemAdapter = new MediaItemAdapter(mMediaFileList);
        mRecyclerView.setAdapter(mediaItemAdapter);
        mediaItemAdapter.setEditItemOnClickListener(new Click.OnObjectClickListener<MediaFile>() {
            @Override
            public void onObjectClick(MediaFile mediaFile) {
                onMediaFileClick(mediaFile);
            }
        });
    }

    @Override
    protected void onPickFile(MediaFile mediaFile) {
        mMediaFileList.add(mediaFile);
        mediaItemAdapter.notifyItemInserted(mMediaFileList.size());
    }

    @Override
    protected void deleteLastMediaFile() {
        int size = mMediaFileList.size();
        if(size < 1) {
            return ;
        }
        mMediaFileList.remove(size-1);
        mediaItemAdapter.notifyItemRemoved(size-1);
    }
    protected void onMediaFileClick(@NonNull MediaFile mediaFile) {
        Log.d(TAG, "onMediaFileClick: "+mediaFile.getPath());
        if (mediaFile.getType() == MediaFile.TYPE_VIDEO) {
            playVideo(mediaFile.getPath());

        } else if (mediaFile.getType() == MediaFile.TYPE_AUDIO) {
//            playAudio(mediaFile.getPath());
        }
    }
}
