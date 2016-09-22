package com.vnapnic.myvib.fragments.bill;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vnapnic.myvib.MainActivity;
import com.vnapnic.myvib.R;
import com.vnapnic.myvib.common.ToolbarTyper;
import com.vnapnic.myvib.model.Bill;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vnapnic on 7/9/2016.
 */
public class BillServiceList extends Fragment implements View.OnClickListener {
    private View viewRoot;
    private MainActivity activity;
    private LinearLayout listBill;

    public static BillServiceList newInstance() {
        BillServiceList fragment = new BillServiceList();
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
        viewRoot = inflater.inflate(R.layout.fragment_new_service, container, false);
        return viewRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listBill = (LinearLayout) view.findViewById(R.id.listBill);

        for (Bill item : initBill()) {
            View v = LayoutInflater.from(activity).inflate(R.layout.item_anyone, null);
            v.setId(Integer.parseInt(item.id));
            ((ImageView) v.findViewById(R.id.icon)).setImageResource(item.icon);
            ((TextView) v.findViewById(R.id.title)).setText(item.title);
            ((TextView) v.findViewById(R.id.phone)).setVisibility(View.GONE);
            v.setOnClickListener(this);
            listBill.addView(v);
        }
    }

    private List<Bill> initBill() {
        List<Bill> billList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Bill bill = new Bill();
            bill.id = i + "";
            if (i == 0) {
                bill.icon = R.drawable.viettel;
                bill.title = getActivity().getResources().getString(R.string.service_1);
            } else if (i == 1) {
                bill.icon = R.drawable.homephone_billpay;
                bill.title = getActivity().getResources().getString(R.string.service_2);
            } else if (i == 2) {
                bill.icon = R.drawable.evn_billpay;
                bill.title = getActivity().getResources().getString(R.string.service_3);
            } else if (i == 3) {
                bill.icon = R.drawable.water_billpay;
                bill.title = getActivity().getResources().getString(R.string.service_4);
            } else if (i == 4) {
                bill.icon = R.drawable.pstn_billpay;
                bill.title = getActivity().getResources().getString(R.string.service_5);
            } else if (i == 5) {
                bill.icon = R.drawable.adsl_billpay;
                bill.title = getActivity().getResources().getString(R.string.service_6);
            } else if (i == 6) {
                bill.icon = R.drawable.airlineticket_billpay;
                bill.title = getActivity().getResources().getString(R.string.service_7);
            } else if (i == 7) {
                bill.icon = R.drawable.insurance;
                bill.title = getActivity().getResources().getString(R.string.service_8);
            } else if (i == 8) {
                bill.icon = R.drawable.telecommunication;
                bill.title = getActivity().getResources().getString(R.string.service_9);
            } else if (i == 9) {
                bill.icon = R.drawable.ceaditcard;
                bill.title = getActivity().getResources().getString(R.string.service_10);
            }
            billList.add(bill);
        }
        return billList;
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
            ((MainActivity) getActivity()).setTitle(getActivity().getResources().getString(R.string.pay_a_bill));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                Bill bill = initBill().get(v.getId());
                ((MainActivity) getActivity()).replaceFragment(BillServiceAddCard.newInstance(v.getId(), bill.icon, bill.title));
                break;
        }
    }
}
