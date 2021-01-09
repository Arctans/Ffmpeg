package com.user.lv.utils;

import android.view.View;
import android.view.ViewGroup;

public class ViewUtils {

    public static void setViewWidth(View view ,int width){
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = width;
        view.setLayoutParams(layoutParams);
    }
    public static void setViewHeight(View view,int height){
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = height;
        view.setLayoutParams(layoutParams);
    }
    public static void setViewSize(View view,int width,int height){
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = height;
        view.setLayoutParams(layoutParams);
    }
}
