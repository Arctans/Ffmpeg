package com.user.lv.common;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.user.lv.R;

/**
 * Created by Arctan on 2021/01/21.
 */
public class SnackBarUtil {

    public static void showInfo(View root, String message) {
        Snackbar snackbar = Snackbar.make(root, message, Snackbar.LENGTH_SHORT);
        setSnackBarColor(snackbar, Color.WHITE, Color.BLACK);
        snackbar.show();
    }
    public static void showError(View root,String message){
        Snackbar snackbar = Snackbar.make(root,message,Snackbar.LENGTH_LONG);
        setSnackBarColor(snackbar, Color.WHITE, Color.RED);
        snackbar.show();
    }

    public static final void setSnackBarColor(Snackbar snackbar,int textColor,int backGroundColor){
        View view = snackbar.getView();
        view.setBackgroundColor(backGroundColor);
        ((TextView) view.findViewById(R.id.snackbar_text)).setTextColor(textColor);
    }
    public static final void setSnackBarColor(Snackbar snackbar,int textColor){

    }
}
