package com.vnapnic.myvib.fragments.payanyone;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.vnapnic.myvib.MainActivity;
import com.vnapnic.myvib.R;
import com.vnapnic.myvib.common.ToolbarTyper;
import com.vnapnic.myvib.common.ViewDialog;
import com.vnapnic.myvib.customs.CustomSwitch;
import com.vnapnic.myvib.customs.FontEditext;
import com.vnapnic.myvib.customs.FontTextView;
import com.vnapnic.myvib.customs.NumberPad;
import com.vnapnic.myvib.fragments.listphone.ContactsModel;
import com.vnapnic.myvib.fragments.listphone.PhoneSelectListFragment;
import com.vnapnic.myvib.model.PayEnd;
import com.vnapnic.myvib.utils.Logger;

/**
 * Created by vnapnic on 7/10/2016.
 */
public class EmailMobileDetailFragment extends Fragment implements View.OnClickListener, View.OnTouchListener {
    private View viewRoot;
    private MainActivity activity;
    private LinearLayout btn_mobile;
    private LinearLayout btn_email;
    private LinearLayout content_view;
    private FontEditext card_number;
    private EditText nameDisplay;
    private CustomSwitch sw_biller_book;
    private static final String TYPE = "key.type";
    private static final String DATA = "key.data";
    private int type;
    private ViewDialog viewDialog;
    private PayEnd payEnd;

    public static EmailMobileDetailFragment newInstance(int type) {
        EmailMobileDetailFragment fragment = new EmailMobileDetailFragment();
        fragment.setArguments(newBundle(type));
        return fragment;
    }

    private static Bundle newBundle(int page) {
        Bundle bundle = new Bundle();
        bundle.putInt(TYPE, page);
        return bundle;
    }

    public static EmailMobileDetailFragment newInstance(int type, PayEnd payEnd) {
        EmailMobileDetailFragment fragment = new EmailMobileDetailFragment();
        fragment.setArguments(newBundle(type, payEnd));
        return fragment;
    }

    private static Bundle newBundle(int page, PayEnd payEnd) {
        Bundle bundle = new Bundle();
        bundle.putInt(TYPE, page);
        bundle.putSerializable(DATA, payEnd);
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
        payEnd = (PayEnd) savedInstanceState.getSerializable(DATA);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.fragment_email_mobile_detail, container, false);
        return viewRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_mobile = (LinearLayout) view.findViewById(R.id.btn_mobile);
        btn_email = (LinearLayout) view.findViewById(R.id.btn_email);
        content_view = (LinearLayout) view.findViewById(R.id.content_view);
        btn_mobile.setOnClickListener(this);
        btn_email.setOnClickListener(this);
        viewDialog = new ViewDialog();
        changeLayout(type);
    }

    private void changeLayout(int type) {
        this.type = type;
        View content = new View(activity);
        content_view.removeAllViews();
        if (type == 0) {
            btn_mobile.setBackgroundResource(R.drawable.bg_choice);
            btn_email.setBackgroundResource(R.drawable.bg_choice_tranf);
            content = LayoutInflater.from(activity).inflate(R.layout.item_email_phone_pay_anyone, null);
        } else if (type == 1) {
            btn_mobile.setBackgroundResource(R.drawable.bg_choice_tranf);
            btn_email.setBackgroundResource(R.drawable.bg_choice);
            content = LayoutInflater.from(activity).inflate(R.layout.item_email_phone_pay_anyone, null);
            ((FontTextView) content.findViewById(R.id.title)).setText(getActivity().getResources().getString(R.string.add_new_email));
            ((FontTextView) content.findViewById(R.id.tvKey1)).setText(getActivity().getResources().getString(R.string.email_address));
        }
        ((ImageView) content.findViewById(R.id.contacts)).setOnTouchListener(this);
        nameDisplay = (EditText) content.findViewById(R.id.nameDisplay);
        card_number = (FontEditext) content.findViewById(R.id.card_number);
        if (type == 0) {
            card_number.setInputType(EditorInfo.TYPE_CLASS_PHONE);
            card_number.setHint(getActivity().getResources().getString(R.string.enter_rep_mobile));
        } else {
            card_number.setHint(getActivity().getResources().getString(R.string.enter_your_email_address));
            card_number.setInputType(EditorInfo.TYPE_CLASS_TEXT);
        }

        if (payEnd != null && !TextUtils.isEmpty(payEnd.id) && !TextUtils.isEmpty(payEnd.name)) {
            card_number.setText(payEnd.id);
            nameDisplay.setText(payEnd.name);
        }
        sw_biller_book = (CustomSwitch) content.findViewById(R.id.sw_biller_book);
        content_view.addView(content);
    }

    public void next() throws Exception {
        if (isNext()) {
            PayEnd payEnd = new PayEnd();
            payEnd.name = getName();
            payEnd.id = getCode();
            ((MainActivity) getActivity()).replaceFragment(PayAnyoneEndDetailFragment.newInstance(payEnd));
        } else {
            String title = getResources().getString(R.string.common_google_play_services_invalid_account_title);
            String content = getResources().getString(R.string.dialog_closed_account_mess);
            viewDialog.showDialog1Buton(getActivity(), title, content);
        }
    }

    private boolean isNext() {
        return card_number.getText().toString().trim().length() >= 1;
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


    public void setContactsModel(ContactsModel contactsModel) throws Exception {
        nameDisplay.setText(contactsModel.getContactsName());
        card_number.setText(contactsModel.getContactsPhoneNumber());
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
            ((MainActivity) getActivity()).setTitle(getActivity().getResources().getString(R.string.pay_anyone));
            ((MainActivity) getActivity()).setRightText(getActivity().getResources().getString(R.string.next));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_mobile:
                changeLayout(0);
                break;
            case R.id.btn_email:
                changeLayout(1);
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.contacts:
                ((MainActivity) getActivity()).replaceFragment(PhoneSelectListFragment.newInstance(1, type));
                break;
        }
        return false;
    }
}
