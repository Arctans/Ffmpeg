package com.user.lv.edit;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MediaItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<MediaFile> mMediaFileList;
    public MediaItemAdapter(List<MediaFile> mediaFileList){
        mMediaFileList = mediaFileList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mMediaFileList.size();
    }
}
