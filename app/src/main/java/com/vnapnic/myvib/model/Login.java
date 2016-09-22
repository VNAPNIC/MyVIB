package com.vnapnic.myvib.model;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;

import com.vnapnic.myvib.R;
import com.vnapnic.myvib.common.ViewDialog;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by vnapnic on 7/2/2016.
 */
enum Validate {
    USER_NULL,
    PASS_NULL,
    LOGIN_FAIL,
    PIN_FAIL
}

public class Login {
    public static final String USER = "vib@vib.com.vn";
    public static final String PASSWORD = "vib123";
    public static final int[] PIN = {6, 6, 6, 6};
    private ViewDialog viewDialog;

    public Login() {
        viewDialog = new ViewDialog();
    }

    public boolean validateLogin(String user, String password, Activity context) {
        if (TextUtils.isEmpty(user)) {
            showDialogValidate(Validate.USER_NULL, context);
            return false;
        } else if (TextUtils.isEmpty(password)) {
            showDialogValidate(Validate.PASS_NULL, context);
            return false;
        } else if (USER.equals(user) && password.equals(PASSWORD)) {
            return true;
        } else {
            showDialogValidate(Validate.LOGIN_FAIL, context);
        }
        return false;
    }

    public boolean validatePin(int[] pin, Activity context) {
        if (Arrays.equals(PIN, pin)) {
            return true;
        } else {
            showDialogValidate(Validate.PIN_FAIL, context);
            return false;
        }
    }

    //Check Show dialog
    private void showDialogValidate(Validate Validate, Activity context) {
        String error = "";
        switch (Validate) {
            case USER_NULL:
                error = context.getResources().getString(R.string.no_username_title);
                break;
            case PASS_NULL:
                error = context.getResources().getString(R.string.no_password_title);
                break;
            case LOGIN_FAIL:
                error = context.getResources().getString(R.string.common_google_play_services_sign_in_failed_title);
                break;
            case PIN_FAIL:
                error = context.getResources().getString(R.string.invalid_pin_not_same);
                break;
        }
        viewDialog.showDialog1Buton(context, "Logon unsuccessful", error);
    }

    public static void showDialogAskingVoiceCall(final Context context, String ms) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(
                context);
        builder.setMessage(ms);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

}
