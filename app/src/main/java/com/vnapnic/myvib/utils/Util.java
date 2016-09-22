package com.vnapnic.myvib.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.vnapnic.myvib.MainActivity;

/**
 * Created by vnapnic on 7/2/2016.
 */
public class Util {
    public static void openAppVIB(Activity activity) {
        String page = "com.vn.vib.mobileapp";
        Intent isCheckIntent = activity.getPackageManager().getLaunchIntentForPackage(page);
        if (isCheckIntent != null) {
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setData(Uri.parse("vib://open_account"));
                activity.startActivity(intent);
            } catch (Exception e) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setData(Uri.parse("market://details?id=" + page));
                activity.startActivity(intent);
            }

        } else {
            isCheckIntent = new Intent(Intent.ACTION_VIEW);
            isCheckIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            isCheckIntent.setData(Uri.parse("market://details?id=" + page));
            activity.startActivity(isCheckIntent);
        }
    }

    public static void openURLVIB(MainActivity activity) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://vib.com.vn/"));
        activity.startActivity(browserIntent);
    }

    public static int delay() {
        return 0;
    }
}

