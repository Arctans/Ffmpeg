package com.user.lv.ffmpeg;

import androidx.annotation.NonNull;

public class FFmpegJni {

    static {
            System.loadLibrary("ffmpeg_jni");
    }
    public static native int execute(String[] commands);
    public static native String getLog();
}
