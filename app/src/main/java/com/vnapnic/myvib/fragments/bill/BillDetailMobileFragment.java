package com.vnapnic.myvib.fragments.bill;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.vnapnic.myvib.MainActivity;
import com.vnapnic.myvib.R;
import com.vnapnic.myvib.common.ToolbarTyper;
import com.vnapnic.myvib.customs.FontEditext;
import com.vnapnic.myvib.customs.FontTextView;
import com.vnapnic.myvib.fragments.getsecuritycode.PayABillGetSecurityCodeFragment;
import com.vnapnic.myvib.model.Account;
import com.vnapnic.myvib.model.Bill;
import com.vnapnic.myvib.model.SecurityCode;

/**
 * Created by vnapn on 8/18/2016.
 */
public class BillDetailMobileFragment extends Fragment {

    private View viewRoot;
    private MainActivity activity;
    private static final String DATA = "key.data";
    private FontTextView tv_acc_des, tv_acc_id, tv_acc_money, tv_acc_money_title, tv_title, tv_phone, phone_name;
    private ImageView icon;
    private Bill bill;
    private Account account;
    private FontEditext edt_desc;

    public static BillDetailMobileFragment newInstance(Bill bill) {
        BillDetailMobileFragment fragment = new BillDetailMobileFragment();
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
        viewRoot = inflater.inflate(R.layout.bill_detail_mobile_fragment, container, false);
        tv_acc_des = (FontTextView) viewRoot.findViewById(R.id.tv_acc_des);
        tv_acc_id = (FontTextView) viewRoot.findViewById(R.id.tv_acc_id);
        tv_acc_money = (FontTextView) viewRoot.findViewById(R.id.tv_acc_money);
        tv_acc_money_title = (FontTextView) viewRoot.findViewById(R.id.tv_acc_money_title);
        tv_title = (FontTextView) viewRoot.findViewById(R.id.title);
        tv_phone = (FontTextView) viewRoot.findViewById(R.id.phone);
        phone_name = (FontTextView) viewRoot.findViewById(R.id.phone_name);
        edt_desc = (FontEditext) viewRoot.findViewById(R.id.edt_desc);
        icon = (ImageView) viewRoot.findViewById(R.id.icon);
        return viewRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!TextUtils.isEmpty(bill.id) && (bill.id.equals("1") || bill.id.equals("2"))) {
            phone_name.setText(bill.phone);
            tv_phone.setVisibility(View.GONE);
        } else {
            tv_phone.setText(bill.phone);
        }
        tv_title.setText(bill.title);
        icon.setImageResource(bill.icon);

        account = new Account();
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
        content.type = 1;
        content.from = "TK " + tv_title.getText().toString().trim();
        content.title = getActivity().getResources().getString(R.string.pay3);
        content.monney = "33.331";
        content.phone = tv_phone.getText().toString().trim();
        content.name = account.title + " - " + account.cardID;
        content.desc = getDesc();
        ((MainActivity) getActivity()).replaceFragment(PayABillGetSecurityCodeFragment.newInstance(content));
    }


    public String getDesc() {
        return TextUtils.isEmpty(edt_desc.getText().toString().trim()) ? getActivity().getResources().getString(R.string.pay_bill_desc) : edt_desc.getText().toString().trim();
    }


    public void setAccount(Account account) throws Exception {
        tv_acc_des.setText(account.title);
        tv_acc_id.setText(account.cardID);
        tv_acc_money.setText(account.soduKeToan);
        tv_acc_money_title.setText(getActivity().getResources().getString(R.string.available));
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
}
