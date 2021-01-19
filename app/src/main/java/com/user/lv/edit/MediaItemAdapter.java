package com.user.lv.edit;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.user.lv.R;
import com.user.lv.common.Click;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MediaItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{
    private static final String TAG = MediaItemAdapter.class.getSimpleName();
    private List<MediaFile> mMediaFileList;
    private Click.OnObjectClickListener<MediaFile>mMediaFileOnClickListener;

    public MediaItemAdapter(List<MediaFile> mediaFileList){
        mMediaFileList = mediaFileList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_media,parent,false);
//        view.setOnClickListener(this);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ItemViewHolder){
            ItemViewHolder itemViewHolder = (ItemViewHolder)holder;
            itemViewHolder.bind(mMediaFileList.get(position),position);
        }
    }

    public void setEditItemOnClickListener(Click.OnObjectClickListener<MediaFile> listener){
        mMediaFileOnClickListener = listener;
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "2 onClick: "+v.getTag());
        if(v.getTag() instanceof MediaFile){
            if(mMediaFileOnClickListener !=null){
                mMediaFileOnClickListener.onObjectClick((MediaFile)v.getTag());
            }
        }
    }

    @Override
    public int getItemCount() {
        return mMediaFileList.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView tvTitle;
        private TextView tvPath;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_media);
            tvTitle = itemView.findViewById(R.id.tv_media_title);
            tvPath = itemView.findViewById(R.id.tv_media_path);
            itemView.setOnClickListener(MediaItemAdapter.this);
        }
        public void bind(MediaFile mediaFile,int position){
            switch (mediaFile.getType()){
                case MediaFile.TYPE_AUDIO:
                    Glide.with(imageView).load(R.drawable.audio_black).into(imageView);
                    break;
                case MediaFile.TYPE_IMG:
                    Glide.with(imageView).asBitmap().load(mediaFile.getPath()).into(imageView);
                    break;
                case MediaFile.TYPE_VIDEO:
                    Glide.with(imageView).asBitmap().load(mediaFile.getPath()).into(imageView);
                    break;
            }
            tvTitle.setText(mediaFile.getName());
            tvPath.setText(mediaFile.getPath());
            itemView.setTag(mediaFile);
        }
    }
}
