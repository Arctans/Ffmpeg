package com.user.lv.video;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.SurfaceView;

import com.user.lv.ffmpeg.FFmpegJni;

public class VideoFFplayView extends SurfaceView {
    private Surface mSurface;
    public VideoFFplayView(Context context) {
        super(context);
        init();
    }
    public VideoFFplayView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public VideoFFplayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }
    private void init() {
        getHolder().setFormat(PixelFormat.RGBA_8888);
        mSurface = getHolder().getSurface();

    }
    public void playVideo(String path){
        final String videoPath  = path;
        new Thread(new Runnable() {

            @Override
            public void run() {
                FFmpegJni.playVideo(videoPath,mSurface);
            }
        }).start();

    }
}
