package com.user.lv.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class DeviceUtils {
    private static final String TAG = DeviceUtils.class.getSimpleName();

    /**
     * 获取屏幕实际物理高度
     *
     * @return 屏幕物理高度
     */
    public static int getDisplayHeight(Context context) {
        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getRealMetrics(metrics);
        int height = metrics.heightPixels;
        return height;
    }
    /**
     * 获取屏幕实际物理宽度
     *
     * @return 屏幕宽度
     */
    public static  int getDisplayWidth(Context context) {
        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getRealMetrics(metrics);
        int width = metrics.widthPixels;
        return width;
    }

    /**
     * 获取屏幕高度
     *
     * @return 屏幕高度
     */
    public static int getScreenHeight(Context context){
        int height;
        Resources resources = context.getResources();
        DisplayMetrics dm =  resources.getDisplayMetrics();
        height = dm.heightPixels;
        return height;
    }

    /**
     * 获取屏幕宽度
     *
     * @return 屏幕宽度
     */
    public static int getScreenWidth(Context context){
        int width = 0;
        Resources resources = context.getResources();
        DisplayMetrics dm =  resources.getDisplayMetrics();
        width = dm.widthPixels;
        return width;
    }

    /**
     * 密度转换像素
     *
     * @param dipValue dp值
     * @return 像素
     */
    public static int dip2px(Context context, float dipValue) {
        DisplayMetrics dm = context.getApplicationContext().getResources().getDisplayMetrics();
        return (int) (dipValue * dm.density + 0.5f);
    }

}
