package com.vnapnic.myvib.fragments.payanyone;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.vnapnic.myvib.MainActivity;
import com.vnapnic.myvib.R;
import com.vnapnic.myvib.common.ToolbarTyper;
import com.vnapnic.myvib.common.ViewDialog;
import com.vnapnic.myvib.customs.CustomSwitch;
import com.vnapnic.myvib.customs.FontEditext;
import com.vnapnic.myvib.customs.FontTextView;
import com.vnapnic.myvib.customs.NumberPad;
import com.vnapnic.myvib.fragments.listbank.BankListFragment;
import com.vnapnic.myvib.model.PayEnd;
import com.vnapnic.myvib.utils.Logger;

/**
 * Created by vnapnic on 7/10/2016.
 */
public class PayAnyoneNewCardVSAccountFragment extends Fragment implements View.OnClickListener, View.OnTouchListener, NumberPad.IKeyCode {

    private View viewRoot;
    private MainActivity activity;
    private static final String TYPE = "key.type";
    private int type;
    private LinearLayout btn_vib_account, btn_bank_account, btn_new_card;
    private RelativeLayout content_view;

    private NumberPad numberPad;
    private CustomSwitch sw_biller_book;
    private ViewDialog viewDialog;

    private FontTextView tvKey1;

    private FontEditext nameDisplay;
    private FontEditext card_number;

    private FontEditext nameDisplay2;
    private FontEditext card_number2;

    private FontTextView tvKey2;

    private RelativeLayout vib_account;
    private RelativeLayout bank_account;

    private int[] number = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};

    public static PayAnyoneNewCardVSAccountFragment newInstance(int type) {
        PayAnyoneNewCardVSAccountFragment fragment = new PayAnyoneNewCardVSAccountFragment();
        fragment.setArguments(newBundle(type));
        return fragment;
    }

    private static Bundle newBundle(int page) {
        Bundle bundle = new Bundle();
        bundle.putInt(TYPE, page);
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
        type = savedInstanceState.getInt(TYPE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.pay_new_card_fragment, container, false);
        return viewRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewDialog = new ViewDialog();
        numberPad = (NumberPad) view.findViewById(R.id.numberPad);

        card_number = (FontEditext) view.findViewById(R.id.card_number);
        nameDisplay = (FontEditext) view.findViewById(R.id.nameDisplay);

        card_number2 = (FontEditext) view.findViewById(R.id.card_number2);
        nameDisplay2 = (FontEditext) view.findViewById(R.id.nameDisplay2);


        vib_account = (RelativeLayout) view.findViewById(R.id.vib_account);
        bank_account = (RelativeLayout) view.findViewById(R.id.bank_account);
        btn_vib_account = (LinearLayout) view.findViewById(R.id.btn_vib_account);
        btn_bank_account = (LinearLayout) view.findViewById(R.id.btn_bank_account);
        btn_new_card = (LinearLayout) view.findViewById(R.id.btn_new_card);
        content_view = (RelativeLayout) view.findViewById(R.id.content_view);
        tvKey1 = (FontTextView) view.findViewById(R.id.tvKey1);
        tvKey2 = (FontTextView) view.findViewById(R.id.tvKey2);
        btn_bank_account = (LinearLayout) view.findViewById(R.id.btn_bank_account);
        btn_vib_account = (LinearLayout) view.findViewById(R.id.btn_vib_account);
        btn_new_card = (LinearLayout) view.findViewById(R.id.btn_new_card);

        btn_vib_account.setOnClickListener(this);
        btn_new_card.setOnClickListener(this);
        btn_bank_account.setOnClickListener(this);
        btn_vib_account.setOnClickListener(this);
        btn_bank_account.setOnClickListener(this);
        btn_new_card.setOnClickListener(this);
        card_number.setOnTouchListener(this);
        numberPad.setOnNumpadClickListener(this);

        card_number.setOnTouchListener(this);
        card_number2.setOnTouchListener(this);
        changeLayout(type);
    }

    private void changeLayout(int type) {
        invi();
        number = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        if (type == 0) {
            vib_account.setVisibility(View.VISIBLE);
            bank_account.setVisibility(View.GONE);
            btn_vib_account.setBackgroundResource(R.drawable.bg_choice);
            btn_new_card.setBackgroundResource(R.drawable.bg_choice_tranf);
        } else {
            vib_account.setVisibility(View.GONE);
            bank_account.setVisibility(View.VISIBLE);
            btn_vib_account.setBackgroundResource(R.drawable.bg_choice_tranf);
            btn_new_card.setBackgroundResource(R.drawable.bg_choice);
        }

        card_number.setText("");
        nameDisplay.setText("");
        card_number2.setText("");
        nameDisplay2.setText("");
    }

    public void next() throws Exception {
        if (isNext()) {
            PayEnd payEnd = new PayEnd();
            if (type == 0) {
                payEnd.name = getName();
                payEnd.id = getCode();
            } else {
                payEnd.name = getName2();
                payEnd.id = getCode2();
            }
            ((MainActivity) getActivity()).replaceFragment(PayAnyoneEndDetailFragment.newInstance(payEnd));
        } else {
            String title = getResources().getString(R.string.common_google_play_services_invalid_account_title);
            String content = getResources().getString(R.string.dialog_closed_account_mess);
            viewDialog.showDialog1Buton(getActivity(), title, content);
        }
    }

    private boolean isNext() {
        if (type == 0) {
            return card_number.getText().toString().trim().length() >= 1;
        } else {
            return card_number2.getText().toString().trim().length() >= 1;
        }
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

    private String getCode2() {
        return card_number2.getText().toString().trim();
    }

    private String getName2() {
        if (TextUtils.isEmpty(nameDisplay2.getText().toString().trim())) {
            return "";
        } else {
            return nameDisplay2.getText().toString().trim();
        }
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
            activity.setRightIcon(R.drawable.btn_next_custom);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_vib_account:
                type = 0;
                changeLayout(type);
                break;

            case R.id.btn_bank_account:
                ((MainActivity) getActivity()).replaceFragment(BankListFragment.newInstance());
                break;
            case R.id.btn_new_card:
                type = 1;
                changeLayout(type);
                break;
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
        if (type == 0) {
            card_number.setText(str);
            if (card_number.getText().toString().trim().length() >= 1) {
                nameDisplay.setText("NGUYEN VAN A");
            } else {
                nameDisplay.setText("");
            }
        } else {
            card_number2.setText(str);
            if (card_number2.getText().toString().trim().length() >= 1) {
                nameDisplay2.setText("NGUYEN VAN A");
            } else {
                nameDisplay2.setText("");
            }
        }
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.card_number:
                Logger.d("namit", "card_number Click");
                numberPad.m14303b();
                break;
            case R.id.card_number2:
                Logger.d("namit", "card_number2 Click");
                numberPad.m14303b();
                break;
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
