package com.vnapnic.myvib.fragments.bill;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vnapnic.myvib.MainActivity;
import com.vnapnic.myvib.R;
import com.vnapnic.myvib.common.ToolbarTyper;
import com.vnapnic.myvib.fragments.account.PayOffCritFragment;
import com.vnapnic.myvib.model.Account;
import com.vnapnic.myvib.model.Bill;

/**
 * Created by vnapnic on 7/9/2016.
 */
public class PayOffCreditFragment extends Fragment implements View.OnClickListener {

    private View viewRoot;
    private MainActivity activity;
    private LinearLayout credit;
    private LinearLayout listBill;
    private TextView name, phone;

    public static PayOffCreditFragment newInstance() {
        PayOffCreditFragment fragment = new PayOffCreditFragment();
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
        viewRoot = inflater.inflate(R.layout.fragment_pay_off_credit, container, false);

        return viewRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listBill = (LinearLayout) view.findViewById(R.id.listBill);
        credit = (LinearLayout) view.findViewById(R.id.credit);
        credit.setOnClickListener(this);
        Bill item = new Bill();
        item.id = 0+"";
        item.title = "NGUYEN VAN B";
        item.phone = "51231XXXXXXXX687979";
        setData(item);
    }

    public void setData(Bill item) {
        View v = LayoutInflater.from(activity).inflate(R.layout.item_bill, null);
        v.setId(Integer.parseInt(item.id));
        name = (TextView) v.findViewById(R.id.name);
        phone = (TextView) v.findViewById(R.id.phone);
        ((TextView) v.findViewById(R.id.title)).setText("MC STD VIB");
        name.setText(item.title);
        phone.setText(item.phone);
        v.setOnClickListener(this);
        listBill.addView(v);
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
            ((MainActivity) getActivity()).setUptoolBar(ToolbarTyper.NONE_BACK);
            ((MainActivity) getActivity()).setTitle(getActivity().getResources().getString(R.string.payment));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.credit:
                ((MainActivity) getActivity()).replaceFragment(PayOffCreditDetailFragment.newInstance());
                break;
            case 0:
            case 1:
                //TODO SEND DATA
                Account data = new Account();
                data.title = name.getText().toString().trim();
                data.cardID = phone.getText().toString().trim();
                activity.replaceFragment(PayOffCritFragment.newInstance(data));
                break;
        }
    }
}

