package com.oven.util;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

/**
 * 提供一个全局context
 * Created by oven on 2017/5/7.
 */

public class MyApplication extends Application {
    //这是一个生命周期伴随整个应用的类，不存在内存泄漏风险
    @SuppressLint("StaticFieldLeak")
    private static Context mContext;
        public void onCreate(){
            super.onCreate();
            mContext = getApplicationContext();
            }
        public static Context getContext(){
            return mContext;
            }

    }
