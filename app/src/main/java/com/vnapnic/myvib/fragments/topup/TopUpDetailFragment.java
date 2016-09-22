package com.vnapnic.myvib.fragments.topup;

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
import com.vnapnic.myvib.fragments.choice.SelectMonneyFragment;
import com.vnapnic.myvib.fragments.getsecuritycode.MobiePhoneGetSecurityCodeFragment;
import com.vnapnic.myvib.model.Account;
import com.vnapnic.myvib.model.DataFix;
import com.vnapnic.myvib.model.SecurityCode;

/**
 * Created by vnapnic on 7/14/2016.
 */
public class TopUpDetailFragment extends Fragment implements View.OnTouchListener {

    private View viewRoot;
    private MainActivity activity;
    private static final String PHONE = "key.phone";
    private static final String NAME = "key.name";
    private String phone;
    private String name;
    private FontTextView tv_name, tv_id;
    private FontEditext edt_amount, edt_desc;
    private LinearLayout accountDetailView;
    private ImageView editBeneficiary;

    private FontTextView tv_acc_des, tv_acc_id, tv_acc_money, tv_acc_money_title;

    public static final int PLACE_PICKER_REQUEST = 1421;
    public static final String VALUE = "value";

    public static TopUpDetailFragment newInstance(String name, String phone) {
        TopUpDetailFragment fragment = new TopUpDetailFragment();
        fragment.setArguments(newBundle(name, phone));
        return fragment;
    }

    private static Bundle newBundle(String name, String phone) {
        Bundle bundle = new Bundle();
        bundle.putString(NAME, name);
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
        phone = savedInstanceState.getString(PHONE);
        name = savedInstanceState.getString(NAME);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.fragment_top_up_detail, container, false);
        return viewRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_acc_des = (FontTextView) view.findViewById(R.id.tv_acc_des);
        tv_acc_id = (FontTextView) view.findViewById(R.id.tv_acc_id);
        tv_acc_money = (FontTextView) view.findViewById(R.id.tv_acc_money);
        tv_acc_money_title = (FontTextView) view.findViewById(R.id.tv_acc_money_title);
        accountDetailView = (LinearLayout) view.findViewById(R.id.accountDetailView);
        editBeneficiary = (ImageView) view.findViewById(R.id.editBeneficiary);
//        ((LinearLayout) view.findViewById(R.id.when_layout)).findViewById(R.id.when_layout).setVisibility(View.GONE);
        edt_amount = (FontEditext) view.findViewById(R.id.edt_amount);
        tv_name = (FontTextView) view.findViewById(R.id.tv_name);
        tv_id = (FontTextView) view.findViewById(R.id.tv_id);
        edt_desc = (FontEditext) view.findViewById(R.id.edt_desc);
        view.findViewById(R.id.when_layout).setVisibility(View.GONE);
        tv_id.setText(phone + "");

        String[][] data = DataFix.phoneContact;
        int showContact = -1;
        for (int i = 0; i < data.length; i++) {
            if (data[i][1].equals(phone.trim())) {
                showContact = i;
                break;
            }
        }

        if (showContact == -1) {
            tv_name.setText(name);
        } else {
            tv_name.setText(data[showContact][0]);
        }

        edt_amount.setOnTouchListener(this);
        edt_desc.setOnTouchListener(this);
        accountDetailView.setOnTouchListener(this);

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

    public void next() throws Exception {
        SecurityCode content = new SecurityCode();
        content.type = 2;
        content.desc = getDesc();
        content.from = getResources().getString(R.string.mobile);
        content.title = getResources().getString(R.string.top_up);
        content.monney = edt_amount.getText().toString().trim();
        content.name = tv_name.getText().toString().trim();
        content.phone = tv_id.getText().toString().trim();
        ((MainActivity) getActivity()).replaceFragment(MobiePhoneGetSecurityCodeFragment.newInstance(content));
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
            ((MainActivity) getActivity()).setTitle(getActivity().getResources().getString(R.string.top_up_detail));
            ((MainActivity) getActivity()).setRightText(getActivity().getResources().getString(R.string.top_up));
        }
    }

    public void setDataMonney(String data) {
        if (TextUtils.isEmpty(data))
            return;
        edt_amount.setText(data);
        if (isAdded()) {
            ((MainActivity) getActivity()).setUptoolBar(ToolbarTyper.NONE_RIGHT_BACK);
            ((MainActivity) getActivity()).setTitle(getActivity().getResources().getString(R.string.top_up_detail));
            ((MainActivity) getActivity()).setRightText(getActivity().getResources().getString(R.string.top_up));
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
            ((MainActivity) getActivity()).setTitle(getActivity().getResources().getString(R.string.top_up_detail));
            ((MainActivity) getActivity()).setRightText(getActivity().getResources().getString(R.string.top_up));
        }
    }

    public String getDesc() {
        return TextUtils.isEmpty(edt_desc.getText().toString().trim()) ? getActivity().getResources().getString(R.string.top_up_desc) : edt_desc.getText().toString().trim();
    }

    public void setAccount(Account account) throws Exception {
        tv_acc_des.setText(account.title);
        tv_acc_id.setText(account.cardID);
        tv_acc_money.setText(account.soduKeToan);
        tv_acc_money_title.setText(getActivity().getResources().getString(R.string.available));
        if (isAdded()) {
            ((MainActivity) getActivity()).setUptoolBar(ToolbarTyper.NONE_RIGHT_BACK);
            ((MainActivity) getActivity()).setTitle(getActivity().getResources().getString(R.string.top_up_detail));
            ((MainActivity) getActivity()).setRightText(getActivity().getResources().getString(R.string.top_up));
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
                ((MainActivity) getActivity()).addFragment(SelectMonneyFragment.newInstance());
                break;
            case R.id.edt_desc:
                ((MainActivity) getActivity()).addFragment(SelectDescriptionFragment.newInstance());
                break;
            case R.id.accountDetailView:
                ((MainActivity) getActivity()).addFragment(SelectAccountFragment.newInstance());
                break;
            case R.id.editBeneficiary:
                ((MainActivity) getActivity()).onBackPressed();
                break;
        }
        return false;
    }
}
