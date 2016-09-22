package com.vnapnic.myvib.fragments.getsecuritycode;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.vnapnic.myvib.MainActivity;
import com.vnapnic.myvib.R;
import com.vnapnic.myvib.common.ToolbarTyper;
import com.vnapnic.myvib.common.ViewDialog;
import com.vnapnic.myvib.customs.FontEditext;
import com.vnapnic.myvib.customs.FontTextView;
import com.vnapnic.myvib.customs.NumberPad;
import com.vnapnic.myvib.fragments.receipt.ReceiptFragment;
import com.vnapnic.myvib.model.SecurityCode;
import com.vnapnic.myvib.utils.Logger;

/**
 * Created by vnapnic on 7/7/2016.
 */
public class PayAnyoneGetSecurityCodeFragment extends Fragment implements View.OnClickListener, NumberPad.IKeyCode, View.OnTouchListener, ViewDialog.IAction2ButtonV2 {

    private View viewRoot;
    private MainActivity activity;
    private ViewDialog dialog;
    private FontEditext edtCode;
    private static final String DATA = "key.data";
    private SecurityCode securityCode;
    private NumberPad numberPad;
    private int[] number = {-1, -1, -1, -1, -1, -1};

    public static PayAnyoneGetSecurityCodeFragment newInstance() {
        PayAnyoneGetSecurityCodeFragment fragment = new PayAnyoneGetSecurityCodeFragment();
        return fragment;
    }

    public static PayAnyoneGetSecurityCodeFragment newInstance(SecurityCode securityCode) {
        PayAnyoneGetSecurityCodeFragment fragment = new PayAnyoneGetSecurityCodeFragment();
        fragment.setArguments(newBundle(securityCode));
        return fragment;
    }

    private static Bundle newBundle(SecurityCode account) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(DATA, account);
        return bundle;
    }

    //  lifecycle
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            initDataFromBundle(savedInstanceState);
        } else {
            initDataFromBundle(getArguments());
        }
        dialog = new ViewDialog();
        Log.i("tho", "onCreate: Pay anyone get security code");
    }

    private void initDataFromBundle(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            securityCode = (SecurityCode) savedInstanceState.getSerializable(DATA);
        } else {
            securityCode = new SecurityCode();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.fragment_get_security_code, container, false);
        return viewRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((FontTextView) view.findViewById(R.id.getSecurity)).setOnClickListener(this);
        ((FontTextView) view.findViewById(R.id.sent_to)).setText(String.format(getActivity().getResources().getString(R.string.otp_text2), "092 xxxx 026"));
        edtCode = (FontEditext) view.findViewById(R.id.edtCode);
        numberPad = (NumberPad) view.findViewById(R.id.numberPad);

        edtCode.setOnTouchListener(this);
        numberPad.setOnNumpadClickListener(this);
    }

    public boolean isCheckCode() {
        String code = edtCode.getText().toString().trim();
        if (code.equals("123456")) {
            return true;
        } else {
            return false;
        }
    }

    public SecurityCode getData() {
        return securityCode;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (isAdded()) {
            ((MainActivity) getActivity()).setUptoolBar(ToolbarTyper.NONE_RIGHT_BACK);
            ((MainActivity) getActivity()).setTitle(getActivity().getResources().getString(R.string.otp_title));
            ((MainActivity) getActivity()).setRightText(getActivity().getResources().getString(R.string.done));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.getSecurity:
                dialog.showDialog1Buton(activity, getActivity().getResources().getString(R.string.sent_otp), getActivity().getResources().getString(R.string.sent_otp_ms_ss));
                break;
        }
    }

    public boolean isShow() {
        return numberPad.getVisibility() == View.VISIBLE;
    }

    public void invi() {
        numberPad.out();
    }

    @Override
    public void returnCode(int code) {
        if (code != 10 && code != 11) {
            Logger.d("namit", code + " ... code");
            for (int i = 0; i < 6; i++) {
                if (number[i] == -1) {
                    Logger.d("namit", i + "... number = " + code);
                    number[i] = code;
                    break;
                }
            }
            setChangeValue();
        } else {
            //TODO
            Logger.d("namit", code + " ... code event");
            if (code == 10) {
                for (int j = 5; j >= 0; j--) {
                    if (number[j] != -1) {
                        number[j] = -1;
                        break;
                    }
                }
                setChangeValue();
            } else if (code == 11) {
                if (isCheckCode()) {
                    int typer = securityCode.type;
                    if (typer == 1) {
                        String btn1 = getResources().getString(R.string.confirm_transaction);
                        String btn2 = getResources().getString(R.string.cancel);
                        int icon = R.drawable.icon_confirm;
                        dialog.showDialog2Buton(activity, securityCode, btn1, btn2, icon, this);
                    } else if (typer == 2) {
                        String btn1 = getResources().getString(R.string.confirm_transaction);
                        String btn2 = getResources().getString(R.string.cancel);
                        int icon = R.drawable.icon_confirm;
                        if (TextUtils.isEmpty(securityCode.for_card)) {
                            securityCode.for_card = getActivity().getResources().getString(R.string.cardid);
                        }
                        String[] action = {btn1, btn2};
                        dialog.showDialog2ButonBill(activity, securityCode, icon, action, this);
                    } else {
                        String form = securityCode.from;
                        String title = securityCode.title;
                        String money = securityCode.monney;
                        String btn1 = getResources().getString(R.string.confirm_transaction);
                        String btn2 = getResources().getString(R.string.cancel);
                        int icon = R.drawable.icon_confirm;
                       dialog.showDialog2Buton(activity, securityCode, btn1, btn2, icon, this);
                    }
                } else {
                    //TODO
                    new AlertDialog.Builder(getActivity())
                            .setTitle(getActivity().getResources().getString(R.string.title_get_code_dialog))
                            .setMessage(getActivity().getResources().getString(R.string.get_code_dialog))
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                    dialog.dismiss();
                                }
                            })
                            .show();
                }
            }
        }
    }

    private void setChangeValue() {
        String str = "";
        for (int i = 0; i < number.length; i++) {
            if (number[i] != -1) {
                str += number[i];
            }
        }
        edtCode.setText(str);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.edtCode:
                numberPad.m14303b();
                return true;
        }
        return false;
    }

    @Override
    public void button2V2() {

    }

    @Override
    public void button1V2(SecurityCode securityCode) {
        ((MainActivity) getActivity()).replaceFragment(ReceiptFragment.newInstance(securityCode.monney + " VND", securityCode.name, securityCode.phone, securityCode.desc, true, 1));
    }

    @Override
    public void button1V3(SecurityCode securityCode) {
       // ((MainActivity) getActivity()).replaceFragment(ReceiptBillFragment.newInstance(securityCode));
        // gọi vào khi click confirm transfer ở dialog
        // phần này bỏ đi vì đã replaceFragment tại method khác ở dialog
    }
}

