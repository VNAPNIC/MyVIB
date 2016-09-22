package com.vnapnic.myvib.common;

import android.app.Activity;
import android.app.Dialog;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.vnapnic.myvib.MainActivity;
import com.vnapnic.myvib.R;
import com.vnapnic.myvib.customs.FontTextView;
import com.vnapnic.myvib.fragments.getsecuritycode.MobiePhoneGetSecurityCodeFragment;
import com.vnapnic.myvib.fragments.getsecuritycode.PayABillGetSecurityCodeFragment;
import com.vnapnic.myvib.fragments.getsecuritycode.PayAnyoneGetSecurityCodeFragment;
import com.vnapnic.myvib.fragments.getsecuritycode.TranferGetSecurityCodeFragment;
import com.vnapnic.myvib.fragments.receipt.MobiePhoneReceiptBillFragment;
import com.vnapnic.myvib.fragments.receipt.PayABillReceiptBillFragment;
import com.vnapnic.myvib.fragments.receipt.PayAnyoneReceiptBillFragment;
import com.vnapnic.myvib.fragments.receipt.TranferReceiptBillFragment;
import com.vnapnic.myvib.model.SecurityCode;

/**
 * Created by vnapnic on 7/6/2016.
 */
public class ViewDialog {

    public void showDialog1Buton(Activity activity, String title, String msg) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);

        dialog.setContentView(R.layout.dialog_1_button);
        FontTextView txtTitle = (FontTextView) dialog.findViewById(R.id.dialog_title);
        txtTitle.setText(title);
        FontTextView content = (FontTextView) dialog.findViewById(R.id.dialog_message);
        content.setText(msg);

        FontTextView dialogButton = (FontTextView) dialog.findViewById(R.id.dialog_btn1);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public interface IAction2Button {
        void button1();

        void button2();
    }

    public interface IAction2ButtonV2 {
        void button2V2();

        void button1V2(SecurityCode securityCode);

        void button1V3(SecurityCode securityCode);
    }

    public void showDialog2Buton(Activity activity, String title, String msg, final IAction2Button action2Button) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);

        dialog.setContentView(R.layout.dialog_2_button);
        FontTextView txtTitle = (FontTextView) dialog.findViewById(R.id.dialog_title);
        txtTitle.setText(title);
        FontTextView content = (FontTextView) dialog.findViewById(R.id.dialog_message);
        content.setText(msg);


        FontTextView dialogButton = (FontTextView) dialog.findViewById(R.id.dialog_btn1);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action2Button.button1();
                dialog.dismiss();
            }
        });

        FontTextView dialogButton2 = (FontTextView) dialog.findViewById(R.id.dialog_btn2);
        dialogButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action2Button.button2();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void showDialogTransaactionmanament(Activity activity , final IAction2Button action2Button) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);

        dialog.setContentView(R.layout.dialog_transactionmanament);
        FontTextView dialogButton = (FontTextView) dialog.findViewById(R.id.dialog_btn1);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action2Button.button1();
                dialog.dismiss();
            }
        });

        FontTextView dialogButton2 = (FontTextView) dialog.findViewById(R.id.dialog_btn2);
        dialogButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action2Button.button2();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void showDialog2Buton(final Activity activity, final SecurityCode securityCode, String button1, String button2, int icon, final IAction2ButtonV2 action2Button) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);

        dialog.setContentView(R.layout.dialog_2_button);
        ImageView img = (ImageView) dialog.findViewById(R.id.dialog_icon);
        img.setImageResource(icon);
        String strMoney = TextUtils.isEmpty(securityCode.monney) ? "0" : securityCode.monney;
        String cvTitle = securityCode.title + " " + strMoney + " VND";
        FontTextView txtTitle = (FontTextView) dialog.findViewById(R.id.dialog_title);
        txtTitle.setText(cvTitle);
        FontTextView content = (FontTextView) dialog.findViewById(R.id.dialog_message);
        String msg = activity.getResources().getString(R.string.transfer_detail_from) + " " + securityCode.from + " " + activity.getResources().getString(R.string.to) +
                "\n" + securityCode.name + "\n"
                + activity.getString(R.string.reference_no_) + " " + securityCode.phone;
        content.setText(msg);
        FontTextView dialogButton = (FontTextView) dialog.findViewById(R.id.dialog_btn1);
        dialogButton.setText(button1);
        FontTextView dialogButton2 = (FontTextView) dialog.findViewById(R.id.dialog_btn2);
        dialogButton2.setText(button2);

        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action2Button.button1V3(securityCode);
                dialog.dismiss();
            }
        });


        dialogButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action2Button.button2V2();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    /**
     * @param activity
     * @param icon
     * @param action2Button 0: title  <br />
     *                      1: monney  <br />
     *                      2: type   <br />
     *                      3: phone  <br />
     *                      4: account  <br />
     *                      5: card ID <br />
     *                      6: desc <br />
     *                      7: button1 <br />
     *                      8: button2  <br />
     */
    public void showDialog2ButonBill(final Activity activity, final SecurityCode securityCode, int icon, String[] action, final IAction2ButtonV2 action2Button) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);

        dialog.setContentView(R.layout.dialog_2_button);
        ImageView img = (ImageView) dialog.findViewById(R.id.dialog_icon);
        img.setImageResource(icon);
        String strMoney = TextUtils.isEmpty(securityCode.monney) ? "0" : securityCode.monney;
        String cvTitle = securityCode.title + " " + strMoney;
        FontTextView txtTitle = (FontTextView) dialog.findViewById(R.id.dialog_title);
        txtTitle.setText(cvTitle);
        FontTextView content = (FontTextView) dialog.findViewById(R.id.dialog_message);

        String msg = activity.getResources().getString(R.string._for) + " " + securityCode.msTyper + " " + securityCode.phone + "\n"
                + activity.getResources().getString(R.string.transfer_detail_from) + " " + securityCode.name + " - " + securityCode.from;

        content.setText(msg);
        FontTextView dialogButton = (FontTextView) dialog.findViewById(R.id.dialog_btn1);
        dialogButton.setText(action[0]);
        FontTextView dialogButton2 = (FontTextView) dialog.findViewById(R.id.dialog_btn2);
        dialogButton2.setText(action[1]);

        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action2Button.button1V3(securityCode);
                dialog.dismiss();
            }
        });


        dialogButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action2Button.button2V2();
                dialog.dismiss();
            }
        });

        dialog.show();
    }


    //-------------------------------------------------------------------------------------------------------------------------------------------------------
    public void myShowDialog2ButonBill(final Activity activity, final SecurityCode securityCode, int icon, final String[] action, final IAction2ButtonV2 action2Button, final Fragment fragment) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);

        dialog.setContentView(R.layout.dialog_2_button);
        ImageView img = (ImageView) dialog.findViewById(R.id.dialog_icon);
        img.setImageResource(icon);
        String strMoney = TextUtils.isEmpty(securityCode.monney) ? "0" : securityCode.monney;
        String cvTitle = securityCode.title + " " + strMoney;
        FontTextView txtTitle = (FontTextView) dialog.findViewById(R.id.dialog_title);
        txtTitle.setText(cvTitle);
        FontTextView content = (FontTextView) dialog.findViewById(R.id.dialog_message);

        String msg = activity.getResources().getString(R.string._for) + " " + securityCode.msTyper + " " + securityCode.phone + "\n"
                + activity.getResources().getString(R.string.transfer_detail_from) + " " + securityCode.name + " - " + securityCode.from;

        content.setText(msg);
        FontTextView dialogButton = (FontTextView) dialog.findViewById(R.id.dialog_btn1);
        dialogButton.setText(action[0]);
        FontTextView dialogButton2 = (FontTextView) dialog.findViewById(R.id.dialog_btn2);
        dialogButton2.setText(action[1]);

        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (fragment instanceof TranferGetSecurityCodeFragment){
                    ((MainActivity) activity).replaceFragment(TranferReceiptBillFragment.newInstance(securityCode));
                } else if (fragment instanceof MobiePhoneGetSecurityCodeFragment){
                    ((MainActivity) activity).replaceFragment(MobiePhoneReceiptBillFragment.newInstance(securityCode));
                } else if(fragment instanceof PayAnyoneGetSecurityCodeFragment){
                    ((MainActivity) activity).replaceFragment(PayAnyoneReceiptBillFragment.newInstance(securityCode));
                }else if (fragment instanceof PayABillGetSecurityCodeFragment){
                    ((MainActivity) activity).replaceFragment(PayABillReceiptBillFragment.newInstance(securityCode));
                }


                dialog.dismiss();
            }
        });


        dialogButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action2Button.button2V2();
                dialog.dismiss();
            }
        });

        dialog.show();
    }
    public void myShowDialog2Buton(final Activity activity, final SecurityCode securityCode, String button1, String button2, int icon, final IAction2ButtonV2 action2Button, final Fragment fragment) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);

        dialog.setContentView(R.layout.dialog_2_button);
        ImageView img = (ImageView) dialog.findViewById(R.id.dialog_icon);
        img.setImageResource(icon);
        String strMoney = TextUtils.isEmpty(securityCode.monney) ? "0" : securityCode.monney;
        String cvTitle = securityCode.title + " " + strMoney + " VND";
        FontTextView txtTitle = (FontTextView) dialog.findViewById(R.id.dialog_title);
        txtTitle.setText(cvTitle);
        FontTextView content = (FontTextView) dialog.findViewById(R.id.dialog_message);
        String msg = activity.getResources().getString(R.string.transfer_detail_from) + " " + securityCode.from + " " + activity.getResources().getString(R.string.to) +
                "\n" + securityCode.name + "\n"
                + activity.getString(R.string.reference_no_) + " " + securityCode.phone;
        content.setText(msg);
        FontTextView dialogButton = (FontTextView) dialog.findViewById(R.id.dialog_btn1);
        dialogButton.setText(button1);
        FontTextView dialogButton2 = (FontTextView) dialog.findViewById(R.id.dialog_btn2);
        dialogButton2.setText(button2);

        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragment instanceof PayABillGetSecurityCodeFragment){
                    ((MainActivity)activity).replaceFragment(PayABillReceiptBillFragment.newInstance(securityCode));
                }
                dialog.dismiss();
            }
        });


        dialogButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action2Button.button2V2();
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}

