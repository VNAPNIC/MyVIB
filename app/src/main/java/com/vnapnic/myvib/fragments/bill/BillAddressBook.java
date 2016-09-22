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
import android.widget.ListView;
import android.widget.TextView;

import com.vnapnic.myvib.MainActivity;
import com.vnapnic.myvib.R;
import com.vnapnic.myvib.adapter.PayAnyoneAdapter;
import com.vnapnic.myvib.common.ToolbarTyper;
import com.vnapnic.myvib.common.ViewDialog;
import com.vnapnic.myvib.model.Bill;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vnapnic on 7/9/2016.
 */
public class BillAddressBook extends Fragment implements View.OnClickListener, PayAnyoneAdapter.IActionPayAnyoneAdapter {
    private View viewRoot;
    private LinearLayout btn_pay_a_bill;

    private ListView listPayAnyone;
    private PayAnyoneAdapter adapter;
    private ArrayList<Bill> datas = new ArrayList<>();

    public static BillAddressBook newInstance() {
        BillAddressBook fragment = new BillAddressBook();
        return fragment;
    }

    //  lifecycle
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        datas.addAll(fakeDataListview());
        adapter = new PayAnyoneAdapter(getActivity(), R.layout.list_item, datas);
        adapter.setActionAdapter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.fragment_bill_address, container, false);
        return viewRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_pay_a_bill = (LinearLayout) view.findViewById(R.id.btn_pay_a_bill);
        listPayAnyone = (ListView) view.findViewById(R.id.listPayAnyone);
        listPayAnyone.setAdapter(adapter);

        btn_pay_a_bill.setOnClickListener(this);
    }

    private List<Bill> fakeDataListview() {
        List<Bill> billList = new ArrayList<>();

        Bill bill;
        //1
        bill = new Bill();
        bill.title = getActivity().getResources().getString(R.string.credit_card);
        bill.style = PayAnyoneAdapter.STYLE_TITLE;
        billList.add(bill);

        bill = new Bill();
        bill.id = "1";
        bill.icon = R.drawable.icon_card_yellow;
        bill.title = "MC STD VIB";
        bill.phone = "NGUYEN VAN B - 512824xxxxxxxx5281";
        bill.style = PayAnyoneAdapter.STYLE_DESC;
        billList.add(bill);

        //2
        bill = new Bill();
        bill.title = getActivity().getResources().getString(R.string.biller2);
        bill.style = PayAnyoneAdapter.STYLE_TITLE;
        billList.add(bill);

        bill = new Bill();
        bill.id = "2";
        bill.icon = R.drawable.evn_billpay;
        bill.title = "Vietnam Electricity(EVN)";
        bill.phone = "BAN DOI THUONG GPMB QUAN 5 - 512824xxxxxxxx5281";
        bill.style = PayAnyoneAdapter.STYLE_DESC;
        billList.add(bill);

        bill = new Bill();
        bill.icon = R.drawable.viettel;
        bill.title = "Mobifone";
        bill.phone = "- 096319092" + 1;
        bill.style = PayAnyoneAdapter.STYLE_DESC;
        billList.add(bill);

        bill = new Bill();
        bill.icon = R.drawable.viettel;
        bill.title = "VinaPhone";
        bill.phone = "- 096319092" + 2;
        bill.style = PayAnyoneAdapter.STYLE_DESC;
        billList.add(bill);

        bill = new Bill();
        bill.icon = R.drawable.viettel;
        bill.title = "Viettel";
        bill.phone = "- 096319092" + 3;
        bill.style = PayAnyoneAdapter.STYLE_DESC;
        billList.add(bill);

        return billList;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
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
            case R.id.btn_pay_a_bill:
                ((MainActivity) getActivity()).replaceFragment(BillServiceList.newInstance());
                break;
        }
    }

    @Override
    public void onClick(Bill bill) {
        ((MainActivity) getActivity()).replaceFragment(BillDetailMobileFragment.newInstance(bill));
    }
}
