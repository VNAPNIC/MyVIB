package com.vnapnic.myvib;

import android.app.Application;
import android.support.multidex.MultiDex;

/**
 * Created by Nankai on 9/10/2016.
 */
public class AppController extends Application {
    public static final String TAG = AppController.class
            .getSimpleName();

    private static AppController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        mInstance = this;
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }
}
