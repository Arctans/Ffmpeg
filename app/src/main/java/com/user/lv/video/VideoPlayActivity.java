package com.user.lv.video;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.user.lv.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class VideoPlayActivity extends AppCompatActivity {
    private static final String TAG = VideoPlayActivity.class.getSimpleName();
    private String playPath;
    private VideoFFplayView videoFFplayView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);
        Intent intent = getIntent();
        playPath = intent.getStringExtra("path");
        videoFFplayView = findViewById(R.id.videoView);
        Log.d(TAG, "onCreate: "+playPath+" videoFFplayView "+videoFFplayView);
        videoFFplayView.playVideo(playPath);
    }
}
