package com.vnapnic.myvib.fragments.bill;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.vnapnic.myvib.MainActivity;
import com.vnapnic.myvib.R;
import com.vnapnic.myvib.common.ToolbarTyper;
import com.vnapnic.myvib.customs.FontEditext;
import com.vnapnic.myvib.customs.FontTextView;
import com.vnapnic.myvib.fragments.choice.SelectAccountFragment;
import com.vnapnic.myvib.fragments.choice.SelectDescriptionFragment;
import com.vnapnic.myvib.fragments.getsecuritycode.PayABillGetSecurityCodeFragment;
import com.vnapnic.myvib.model.Account;
import com.vnapnic.myvib.model.BillSelect;
import com.vnapnic.myvib.model.SecurityCode;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by vnapnic on 7/13/2016.
 */
public class BillServiceCardDetail extends Fragment implements View.OnTouchListener {
    private View viewRoot;
    private MainActivity activity;
    private FontTextView phone_number, name;
    private FontEditext edt_amount, edt_desc;
    private FontTextView tv_acc_des, tv_acc_id, tv_acc_money, tv_acc_money_title;

    private Account account;

    private ImageView icon_form;
    private static final String CODE = "key.code";
    private static final String PHONE = "key.phone";
    private BillSelect code;
    private String phone;

    public static BillServiceCardDetail newInstance(BillSelect code, String phone) {
        BillServiceCardDetail fragment = new BillServiceCardDetail();
        fragment.setArguments(newBundle(code, phone));
        return fragment;
    }

    private static Bundle newBundle(BillSelect code, String phone) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(CODE, code);
        bundle.putString(PHONE, phone);
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
        code = (BillSelect) savedInstanceState.getSerializable(CODE);
        phone = savedInstanceState.getString(PHONE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.fragment_bill_add_card_detail, container, false);
        return viewRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edt_desc = (FontEditext) view.findViewById(R.id.edt_desc);
        tv_acc_des = (FontTextView) view.findViewById(R.id.tv_acc_des);
        tv_acc_id = (FontTextView) view.findViewById(R.id.tv_acc_id);
        tv_acc_money = (FontTextView) view.findViewById(R.id.tv_acc_money);
        tv_acc_money_title = (FontTextView) view.findViewById(R.id.tv_acc_money_title);

        phone_number = (FontTextView) view.findViewById(R.id.phone);
        name = (FontTextView) view.findViewById(R.id.title);
        icon_form = (ImageView) view.findViewById(R.id.icon);

        phone_number.setText(phone);
        name.setText(code.getTitle());
        icon_form.setImageResource(code.getIcon());

        ((LinearLayout) view.findViewById(R.id.layout_from)).setOnTouchListener(this);
        ((FontEditext) view.findViewById(R.id.edt_desc)).setOnTouchListener(this);

        Account account = new Account();
        account.id = 1;
        account.icon = R.drawable.icon_account_thanhtoan;
        account.cardID = "0487406006030" + 0;
        account.title = getActivity().getResources().getString(R.string.open_account1_name);
        account.soDU = "1,145,66" + 0;
        account.soduKeToan = "1,550,55" + 0 + " VND";
        account.isRemove = false;
        setAccount(account);
    }

    public void next() throws Exception {
        SecurityCode content = new SecurityCode();
        content.type = 1;
        content.from = "TK " + name.getText().toString().trim();
        content.title = getActivity().getResources().getString(R.string.pay3);
        content.monney = "33.331";
        content.phone = phone_number.getText().toString().trim();
        content.name = account.title + " - " + account.cardID;
        content.desc = getDesc();
        ((MainActivity) getActivity()).replaceFragment(PayABillGetSecurityCodeFragment.newInstance(content));
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
            ((MainActivity) getActivity()).setTitle(getActivity().getResources().getString(R.string.pay_bills_details_title));
            ((MainActivity) getActivity()).setRightText(getActivity().getResources().getString(R.string.pay3));
        }
    }

    public void setDataDesc(String desc) {
        if (TextUtils.isEmpty(desc)) {
            edt_desc.setText("");
            return;
        }
        edt_desc.setText(desc);
        if (isAdded()) {
            ((MainActivity) getActivity()).setUptoolBar(ToolbarTyper.NONE_RIGHT_BACK);
            ((MainActivity) getActivity()).setTitle(getActivity().getResources().getString(R.string.pay_anyone_details));
            ((MainActivity) getActivity()).setRightText(getActivity().getResources().getString(R.string.pay3));
        }
    }

    public String getDesc() {
        return TextUtils.isEmpty(edt_desc.getText().toString().trim()) ? getActivity().getResources().getString(R.string.pay_bill_desc) : edt_desc.getText().toString().trim();
    }

    public void setAccount(Account account) {
        try {
            this.account = account;
            tv_acc_des.setText(account.title);
            tv_acc_id.setText(account.cardID);
            tv_acc_money.setText(account.soduKeToan);
            tv_acc_money_title.setText(getActivity().getResources().getString(R.string.available));
        }catch (Exception e){
            e.printStackTrace();
        }
        if (isAdded()) {
            ((MainActivity) getActivity()).setUptoolBar(ToolbarTyper.NONE_RIGHT_BACK);
            ((MainActivity) getActivity()).setTitle(getActivity().getResources().getString(R.string.pay_anyone_details));
            ((MainActivity) getActivity()).setRightText(getActivity().getResources().getString(R.string.pay3));
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.layout_from:
                activity.addFragment(SelectAccountFragment.newInstance());
                break;
            case R.id.edt_desc:
                activity.addFragment(SelectDescriptionFragment.newInstance());
                break;
        }
        return false;
    }
}
