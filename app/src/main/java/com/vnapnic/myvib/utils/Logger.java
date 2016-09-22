package com.vnapnic.myvib.utils;

import android.util.Log;

import com.vnapnic.myvib.common.Config;

/**
 * Created by vnapnic on 7/2/2016.
 */
public class Logger {
    public static void d(String TAG, String ms) {
        if (Config.DEBUG) {
            Log.d(TAG, ms);
        }
    }
}
