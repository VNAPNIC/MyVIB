package com.vnapnic.myvib.fragments.payanyone;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.vnapnic.myvib.MainActivity;
import com.vnapnic.myvib.R;
import com.vnapnic.myvib.common.ToolbarTyper;
import com.vnapnic.myvib.customs.FontTextView;
import com.vnapnic.myvib.customs.NumberPad;
import com.vnapnic.myvib.model.Bank;
import com.vnapnic.myvib.model.PayEnd;
import com.vnapnic.myvib.utils.Logger;

/**
 * Created by vnapn on 7/27/2016.
 */
public class FastAnyoneFragment extends Fragment implements View.OnTouchListener, NumberPad.IKeyCode {

    private View viewRoot;
    private MainActivity activity;
    private static final String DATA = "key.data";
    private Bank bank;
    private FontTextView title;
    private EditText card_number;
    private EditText nameDisplay;
    private NumberPad numberPad;
    private int[] number = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};


    public static FastAnyoneFragment newInstance(Bank bank) {
        FastAnyoneFragment fragment = new FastAnyoneFragment();
        fragment.setArguments(newBundle(bank));
        return fragment;
    }

    private static Bundle newBundle(Bank bank) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(DATA, bank);
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
    }


    private void initDataFromBundle(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            bank = (Bank) savedInstanceState.getSerializable(DATA);
        } else {
            bank = new Bank();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.fragment_anyone_fast, container, false);
        return viewRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        title = (FontTextView) view.findViewById(R.id.title);
        numberPad = (NumberPad) view.findViewById(R.id.numberPad);

        card_number = (EditText) view.findViewById(R.id.card_number);
        nameDisplay = (EditText) view.findViewById(R.id.nameDisplay);
        title.setText(Html.fromHtml(getActivity().getResources().getString(R.string.bank) + bank.name + " (" + bank.code + ")"));
        numberPad.setOnNumpadClickListener(this);
        card_number.setOnTouchListener(this);
        title.setOnTouchListener(this);
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
            ((MainActivity) getActivity()).setTitle(getActivity().getResources().getString(R.string.pay_someone_new_title));
            ((MainActivity) getActivity()).setRightText(getActivity().getResources().getString(R.string.next));
        }
    }

    public void next() throws Exception {
        if (isNext()) {
            PayEnd payEnd = new PayEnd();
            payEnd.name = getName();
            payEnd.id = getCode();
            ((MainActivity) getActivity()).replaceFragment(PayAnyoneEndDetailFragment.newInstance(payEnd));
        } else {
            //TODO
        }
    }

    private boolean isNext() {
        return TextUtils.isEmpty(nameDisplay.getText().toString().trim()) ? false : true;
    }

    private String getCode() {
        return card_number.getText().toString().trim();
    }

    private String getName() {
        if (TextUtils.isEmpty(nameDisplay.getText().toString().trim())) {
            return "";
        } else {
            return nameDisplay.getText().toString().trim();
        }
    }

    @Override
    public void returnCode(int code) {
        if (code != 10 && code != 11) {
            Logger.d("namit", code + " ... code");
            for (int i = 0; i < 12; i++) {
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
                for (int j = 11; j >= 0; j--) {
                    if (number[j] != -1) {
                        number[j] = -1;
                        break;
                    }
                }
                setChangeValue();
            } else if (code == 11) {
                try {
                    next();
                } catch (Exception e) {
                    e.printStackTrace();
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
        if (str.length() >= 1) {
            nameDisplay.setText("NGUYEN VAN A");
        } else {
            nameDisplay.setText("");
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.card_number:
                InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(nameDisplay.getWindowToken(), 0);
                nameDisplay.clearFocus();
                numberPad.m14303b();
                break;
            case R.id.title:
                ((MainActivity) getActivity()).onBackPressed();
                break;
        }
        return false;
    }
}


