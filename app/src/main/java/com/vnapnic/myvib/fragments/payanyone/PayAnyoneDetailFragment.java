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
import android.widget.EditText;
import android.widget.LinearLayout;

import com.vnapnic.myvib.MainActivity;
import com.vnapnic.myvib.R;
import com.vnapnic.myvib.common.ToolbarTyper;
import com.vnapnic.myvib.customs.FontEditext;
import com.vnapnic.myvib.customs.FontTextView;
import com.vnapnic.myvib.fragments.choice.SelectAccountFragment;
import com.vnapnic.myvib.fragments.choice.SelectDescriptionFragment;
import com.vnapnic.myvib.fragments.getsecuritycode.PayAnyoneGetSecurityCodeFragment;
import com.vnapnic.myvib.model.Account;
import com.vnapnic.myvib.model.Bill;
import com.vnapnic.myvib.model.SecurityCode;

/**
 * Created by vnapnic on 7/10/2016.
 */
public class PayAnyoneDetailFragment extends Fragment implements View.OnTouchListener {

    private View viewRoot;
    private MainActivity activity;
    private static final String DATA = "key.data";
    private Bill bill;
    private FontTextView tv_acc_des, tv_acc_id, tv_acc_money, tv_acc_money_title, city, bank;
    private FontEditext edt_amount, edt_desc;
    private LinearLayout accountDetailView;

    public static PayAnyoneDetailFragment newInstance(Bill bill) {
        PayAnyoneDetailFragment fragment = new PayAnyoneDetailFragment();
        fragment.setArguments(newBundle(bill));
        return fragment;
    }

    private static Bundle newBundle(Bill bill) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(DATA, bill);
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
        bill = (Bill) savedInstanceState.getSerializable(DATA);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.fragment_anyone_detail, container, false);
        return viewRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((FontTextView) view.findViewById(R.id.name)).setText(bill.title);
        ((FontTextView) view.findViewById(R.id.cardID)).setText(bill.phone);
        ((FontTextView) view.findViewById(R.id.branch)).setText(Html.fromHtml("<b>" + getActivity().getString(R.string.branch) + ": </b>") + bill.address);
        city = (FontTextView) view.findViewById(R.id.city);
        bank = (FontTextView) view.findViewById(R.id.bank);

        if (TextUtils.isEmpty(bill.city)) {
            city.setText(Html.fromHtml("<b>" + getActivity().getString(R.string.city) + ": </b>") + " Hà Nội");
        } else {
            city.setText(Html.fromHtml("<b>" + getActivity().getString(R.string.city) + ": </b>") + bill.city);
        }

        if (TextUtils.isEmpty(bill.bank)) {
            bank.setVisibility(View.GONE);
        } else {
            bank.setVisibility(View.VISIBLE);
            bank.setText(Html.fromHtml("<b>" + bill.bank + "</b>"));
        }
        view.findViewById(R.id.backlList).setOnTouchListener(this);

        tv_acc_des = (FontTextView) view.findViewById(R.id.tv_acc_des);
        tv_acc_id = (FontTextView) view.findViewById(R.id.tv_acc_id);
        tv_acc_money = (FontTextView) view.findViewById(R.id.tv_acc_money);
        tv_acc_money_title = (FontTextView) view.findViewById(R.id.tv_acc_money_title);
        accountDetailView = (LinearLayout) view.findViewById(R.id.accountDetailView);

        edt_amount = (FontEditext) view.findViewById(R.id.edt_amount);
        edt_desc = (FontEditext) view.findViewById(R.id.edt_desc);

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

    private String[] getData() {
        String monney = ((FontTextView) viewRoot.findViewById(R.id.tv_acc_money)).getText().toString();
        String des = ((EditText) viewRoot.findViewById(R.id.edt_desc)).getText().toString();
        if (TextUtils.isEmpty(monney)) {
            monney = "";
        }
        if (TextUtils.isEmpty(des)) {
            des = "";
        }

        String[] strings = {
                getActivity().getResources().getString(R.string.pay_anyone),
                monney,
                tv_acc_des.getText().toString(),
                bill.phone,
                bill.title,
                "048704060060307",
                des};
        return strings;
    }

    public void next() throws Exception {
        String[] ms = getData();
        SecurityCode securityCode = new SecurityCode();
        securityCode.type = 2;
        securityCode.title = ms[0].toString().trim();
        securityCode.monney = ms[1].toString().trim();
        securityCode.msTyper = ms[2].toString().trim();
        securityCode.phone = ms[3].toString().trim();
        securityCode.name = ms[4].toString().trim();
        securityCode.from = ms[5].toString().trim();
        securityCode.desc = ms[6].toString().trim();

        ((MainActivity) getActivity()).replaceFragment(PayAnyoneGetSecurityCodeFragment.newInstance(securityCode));
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
            ((MainActivity) getActivity()).setRightText(getActivity().getResources().getString(R.string.pay));
        }
    }

    public void setDataDesc(String data) {
        if (TextUtils.isEmpty(data))
            return;
        edt_desc.setText(data);
        if (isAdded()) {
            ((MainActivity) getActivity()).setUptoolBar(ToolbarTyper.NONE_RIGHT_BACK);
            ((MainActivity) getActivity()).setTitle(getActivity().getResources().getString(R.string.pay_bills_details_title));
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
            ((MainActivity) getActivity()).setTitle(getActivity().getResources().getString(R.string.pay_bills_details_title));
            ((MainActivity) getActivity()).setRightText(getActivity().getResources().getString(R.string.pay));
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.backlList:
                ((MainActivity) getActivity()).onBackPressed();
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


