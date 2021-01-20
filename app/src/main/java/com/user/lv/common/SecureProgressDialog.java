package com.user.lv.common;

import android.app.ProgressDialog;
import android.content.Context;

import androidx.lifecycle.LifecycleObserver;

public class SecureProgressDialog extends ProgressDialog implements LifecycleObserver {
    private Context context;

    public SecureProgressDialog(Context context){
        super(context);
        this.context = context;
    }

}
