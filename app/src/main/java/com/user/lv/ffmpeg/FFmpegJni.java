package com.user.lv.ffmpeg;

import android.view.Surface;

import androidx.annotation.NonNull;

public class FFmpegJni {

    static {
            System.loadLibrary("ffmpeg_jni");
    }
    public static native int execute(String[] commands);
    public static native String getLog();
    public static native void playVideo(String videoPath, Surface surface);
}
