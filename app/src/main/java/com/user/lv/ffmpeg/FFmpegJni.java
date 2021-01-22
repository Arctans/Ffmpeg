package com.user.lv.ffmpeg;

import androidx.annotation.NonNull;

public class FFmpegJni {

    public static native int execute(@NonNull String[] cmd);
    public static native String getLog();

}
