package com.vnapnic.myvib.fragments.bill;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.vnapnic.myvib.MainActivity;
import com.vnapnic.myvib.R;
import com.vnapnic.myvib.common.ToolbarTyper;
import com.vnapnic.myvib.customs.CustomSwitch;
import com.vnapnic.myvib.customs.FontEditext;
import com.vnapnic.myvib.customs.NumberPad;
import com.vnapnic.myvib.utils.Logger;

/**
 * Created by vnapnic on 7/9/2016.
 */
public class PayOffCreditDetailFragment extends Fragment implements View.OnTouchListener, NumberPad.IKeyCode {

    private View viewRoot;
    private MainActivity activity;
    private EditText card_number;
    private EditText nameDisplay;
    private CustomSwitch sw_biller_book;
    private NumberPad numberPad;
    private int[] number = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};

    public static PayOffCreditDetailFragment newInstance() {
        PayOffCreditDetailFragment fragment = new PayOffCreditDetailFragment();
        return fragment;
    }

    //  lifecycle
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.fragment_pay_off_credit_detail, container, false);
        return viewRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nameDisplay = (EditText) view.findViewById(R.id.nameDisplay);
        sw_biller_book = (CustomSwitch) view.findViewById(R.id.sw_biller_book);

        card_number = (FontEditext) view.findViewById(R.id.card_number);
        numberPad = (NumberPad) view.findViewById(R.id.numberPad);

        card_number.setOnTouchListener(this);
        numberPad.setOnNumpadClickListener(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    public boolean isSave() {
        if (card_number.getText().toString().trim().length() >= 1) {
            return true;
        } else {
//        return sw_biller_book.getStatus();
            return false;
        }
    }

    public String[] getData() {
        return new String[]{nameDisplay.getText().toString(), card_number.getText().toString()};
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (isAdded()) {
            ((MainActivity)getActivity()).setUptoolBar(ToolbarTyper.NONE_RIGHT_BACK);
            ((MainActivity)getActivity()).setTitle(getActivity().getResources().getString(R.string.receipt));
            ((MainActivity)getActivity()).setRightText(getActivity().getResources().getString(R.string.next));
        }
    }

    @Override
    public void returnCode(int code) {
        if (code != 10 && code != 11) {
            Logger.d("namit", code + " ... code");
            for (int i = 0; i < 16; i++) {
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
                for (int j = 15; j >= 0; j--) {
                    if (number[j] != -1) {
                        number[j] = -1;
                        break;
                    }
                }
                setChangeValue();
            } else if (code == 11) {
                if (isSave()) {
//                    activity.switchPage(PayOffCreditFragment.newInstance());
                    activity.onBackPressed();
                } else {
                    //TODO
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
        card_number.setText(str);
        if (card_number.getText().toString().trim().length() >= 1) {
            nameDisplay.setText("NGUYEN VAN A");
        } else {
            nameDisplay.setText("");
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.card_number:
                numberPad.m14303b();
                return true;
        }
        return false;
    }

    public boolean isShow() {
        return numberPad.getVisibility() == View.VISIBLE;
    }

    public void invi() {
        numberPad.out();
    }
}