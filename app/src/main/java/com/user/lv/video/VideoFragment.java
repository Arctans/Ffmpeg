package com.user.lv.video;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.user.lv.R;
import com.user.lv.common.Click;
import com.user.lv.edit.EditItem;
import com.user.lv.edit.EditItemAdapter;

import java.util.ArrayList;
import java.util.List;

public class VideoFragment extends Fragment {
    public static final String TAG = VideoFragment.class.getSimpleName();
    public static final int TITLE = R.string.video;
    private  EditItem editItem;
    private RecyclerView recyclerView;
    private List<EditItem> editItemList;
    private EditItemAdapter mAdapter;
    public static Fragment newInstance(){
        Bundle args = new Bundle();
        VideoFragment fragment = new VideoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: 0");
        editItemList = new ArrayList<EditItem>();
        editItemList.add(new EditItem(R.drawable.ic_trans_code_black_24dp,
                            VideoFlipActivity.TITLE,VideoFlipActivity.class));
        editItemList.add(new EditItem(R.drawable.ic_video_puzz_black_24dp,
                VideoFlipActivity.TITLE, VideoFlipActivity.class));
        editItemList.add(new EditItem(R.drawable.ic_video_watermark_black_24dp,
                VideoFlipActivity.TITLE, VideoFlipActivity.class));
        editItemList.add(new EditItem(R.drawable.ic_un_logo_black_24dp,
                VideoFlipActivity.TITLE, VideoFlipActivity.class));
        editItemList.add(new EditItem(R.drawable.ic_demux_audio_black_24dp,
                VideoFlipActivity.TITLE, VideoFlipActivity.class));
        editItemList.add(new EditItem(R.drawable.ic_demux_video_black_24dp,
                VideoFlipActivity.TITLE, VideoFlipActivity.class));
        editItemList.add(new EditItem(R.drawable.ic_filter_black_24dp,
                VideoFlipActivity.TITLE, VideoFlipActivity.class));
  
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        Log.d(TAG, "onCreateView: ");
        return inflater.inflate(R.layout.fragment_video,container,false);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated: ");
        super.onViewCreated(view, savedInstanceState);
        recyclerView =view.findViewById(R.id.recycleView);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(getContext(),3);
        recyclerView.setLayoutManager(mGridLayoutManager);
        mAdapter = new EditItemAdapter(editItemList);
        mAdapter.setEditItemOnClickListener(new Click.OnObjectClickListener<EditItem>() {
            @Override
            public void onObjectClick(EditItem editItem) {
//                Log.d(TAG, "onObjectClick: "+editItem.getClass());
                Intent intent = new Intent(getContext(),editItem.activityClass);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(mAdapter);
//        recyclerView.setOnClickListener();
    }
}
