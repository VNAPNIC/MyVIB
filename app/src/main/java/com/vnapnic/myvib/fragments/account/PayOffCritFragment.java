package com.vnapnic.myvib.fragments.account;

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
import com.vnapnic.myvib.fragments.getsecuritycode.GetSecurityCodeFragment;
import com.vnapnic.myvib.fragments.choice.SelectAccountFragment;
import com.vnapnic.myvib.fragments.choice.SelectDescriptionFragment;
import com.vnapnic.myvib.fragments.choice.SelectMonneyFragment;
import com.vnapnic.myvib.model.Account;
import com.vnapnic.myvib.model.SecurityCode;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by vnapnic on 7/20/2016.
 */
public class PayOffCritFragment extends Fragment implements View.OnTouchListener {

    private View viewRoot;
    private MainActivity activity;
    private static final String DATA = "key.data";
    private Account data;
    private FontTextView tv_name, cardid, tv_date, money1, money2, title_money1, title_money2;
    private FontEditext edt_amount, edt_desc;
    private LinearLayout accountDetailView, money_left, money_right;
    private ImageView editBeneficiary;

    private FontTextView tv_acc_des, tv_acc_id, tv_acc_money, tv_acc_money_title;

    public static final int PLACE_PICKER_REQUEST = 1421;
    public static final String VALUE = "value";

    public static PayOffCritFragment newInstance(Account data) {
        PayOffCritFragment fragment = new PayOffCritFragment();
        fragment.setArguments(newBundle(data));
        return fragment;
    }

    private static Bundle newBundle(Account data) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(DATA, data);
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
        data = (Account) savedInstanceState.getSerializable(DATA);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.fragment_pay_off_crit, container, false);
        return viewRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        money_left = (LinearLayout) view.findViewById(R.id.money_left);
        money_right = (LinearLayout) view.findViewById(R.id.money_right);

        money1 = (FontTextView) view.findViewById(R.id.money1);
        money2 = (FontTextView) view.findViewById(R.id.money2);
        title_money1 = (FontTextView) view.findViewById(R.id.title_money1);
        title_money2 = (FontTextView) view.findViewById(R.id.title_money2);

        tv_date = (FontTextView) view.findViewById(R.id.tv_date);
        tv_acc_des = (FontTextView) view.findViewById(R.id.tv_acc_des);
        tv_acc_id = (FontTextView) view.findViewById(R.id.tv_acc_id);
        tv_acc_money = (FontTextView) view.findViewById(R.id.tv_acc_money);
        tv_acc_money_title = (FontTextView) view.findViewById(R.id.tv_acc_money_title);
        accountDetailView = (LinearLayout) view.findViewById(R.id.accountDetailView);
        editBeneficiary = (ImageView) view.findViewById(R.id.editBeneficiary);

        edt_amount = (FontEditext) view.findViewById(R.id.edt_amount);
        tv_name = (FontTextView) view.findViewById(R.id.tv_name);
        cardid = (FontTextView) view.findViewById(R.id.cardid);
        edt_desc = (FontEditext) view.findViewById(R.id.edt_desc);

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String date = df.format(Calendar.getInstance().getTime());
        tv_date.setText(date);
        cardid.setText(data.cardID);
        tv_name.setText(data.title);
        view.findViewById(R.id.when_layout).setVisibility(View.GONE);
//        edt_amount.setOnTouchListener(this);
        edt_desc.setOnTouchListener(this);
        accountDetailView.setOnTouchListener(this);
        money_left.setOnTouchListener(this);
        money_right.setOnTouchListener(this);
        actionLeft();

        Account account = new Account();
        account.id = 1;
        account.icon = R.drawable.icon_account_thanhtoan;
        account.cardID = "0487406006030" + 0;
        account.title = getActivity().getResources().getString(R.string.open_account1_name);
        account.soDU = "1,145,66" + 0;
        account.soduKeToan = "1,550,55" + 0 + " VND";
        account.isRemove = false;
        try {
            setAccount(account);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void actionLeft() {
        money_left.setBackgroundResource(R.color.orange_text);
        money_right.setBackgroundResource(0);
        edt_amount.setText(money1.getText().toString().trim());

        title_money1.setTextColor(getActivity().getResources().getColor(R.color.white));
        title_money2.setTextColor(getActivity().getResources().getColor(R.color.black));
        money1.setTextColor(getActivity().getResources().getColor(R.color.white));
        money2.setTextColor(getActivity().getResources().getColor(R.color.dialog_blue_text));
    }

    private void actionRight() {
        money_left.setBackgroundResource(0);
        money_right.setBackgroundResource(R.color.orange_text);
        edt_amount.setText(money2.getText().toString().trim());
        title_money1.setTextColor(getActivity().getResources().getColor(R.color.black));
        title_money2.setTextColor(getActivity().getResources().getColor(R.color.white));
        money1.setTextColor(getActivity().getResources().getColor(R.color.dialog_blue_text));
        money2.setTextColor(getActivity().getResources().getColor(R.color.white));
    }

    public void next() {
        SecurityCode content = new SecurityCode();
        content.type = 2;
        content.from = getResources().getString(R.string.mobile);
        content.title = getResources().getString(R.string.top_up);
        content.monney = getMonney();
        content.name = getName();
        content.phone = getPhone();
        content.msTyper = getResources().getString(R.string.description);
        content.desc = getDesc();
        ((MainActivity) getActivity()).replaceFragment(GetSecurityCodeFragment.newInstance(content));
    }

    public String getName() {
        return tv_name.getText().toString().trim();
    }

    public String getPhone() {
        return cardid.getText().toString().trim();
    }

    public String getMonney() {
        return edt_amount.getText().toString().trim();
    }

    public String getCode() {
        return "0000000";
    }

    public String getDesc() {
        return TextUtils.isEmpty(edt_desc.getText().toString().trim()) ? getActivity().getResources().getString(R.string.pay_mc) : edt_desc.getText().toString();
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
            ((MainActivity) getActivity()).setTitle(getActivity().getResources().getString(R.string.payment));
            ((MainActivity) getActivity()).setRightText(getActivity().getResources().getString(R.string.pay));
        }
    }

    public void setDataMonney(String data) {
        if (TextUtils.isEmpty(data))
            return;
        edt_amount.setText(data);
        if (isAdded()) {
            ((MainActivity) getActivity()).setUptoolBar(ToolbarTyper.NONE_RIGHT_BACK);
            ((MainActivity) getActivity()).setTitle(getActivity().getResources().getString(R.string.payment));
            ((MainActivity) getActivity()).setRightText(getActivity().getResources().getString(R.string.pay));
        }
    }

    public void setDataDesc(String data) {
        if (TextUtils.isEmpty(data)) {
            edt_desc.setText("");
            return;
        }
        edt_desc.setText(data);
        if (isAdded()) {
            ((MainActivity) getActivity()).setUptoolBar(ToolbarTyper.NONE_RIGHT_BACK);
            ((MainActivity) getActivity()).setTitle(getActivity().getResources().getString(R.string.payment));
            ((MainActivity) getActivity()).setRightText(getActivity().getResources().getString(R.string.pay));
        }
    }

    public void setAccount(Account account) throws Exception {
        tv_acc_des.setText(account.title);
        tv_acc_id.setText(account.cardID);
        tv_acc_money.setText(account.soduKeToan);
        tv_acc_money_title.setText(getActivity().getResources().getString(R.string.available));
        if (isAdded()) {
            ((MainActivity) getActivity()).setUptoolBar(ToolbarTyper.NONE_RIGHT_BACK);
            ((MainActivity) getActivity()).setTitle(getActivity().getResources().getString(R.string.payment));
            ((MainActivity) getActivity()).setRightText(getActivity().getResources().getString(R.string.pay));
        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.edt_amount:
                activity.addFragment(SelectMonneyFragment.newInstance());
                break;
            case R.id.edt_desc:
                activity.addFragment(SelectDescriptionFragment.newInstance());
                break;
            case R.id.accountDetailView:
                activity.addFragment(SelectAccountFragment.newInstance());
                break;
            case R.id.editBeneficiary:
                activity.onBackPressed();
                break;
            case R.id.money_left:
                actionLeft();
                break;
            case R.id.money_right:
                actionRight();
                break;
        }
        return false;
    }
}