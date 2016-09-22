package com.vnapnic.myvib.fragments.payanyone;

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
import com.vnapnic.myvib.adapter.CurrentVsChipAdapter;
import com.vnapnic.myvib.adapter.PayAnyoneAdapter;
import com.vnapnic.myvib.common.ToolbarTyper;
import com.vnapnic.myvib.common.ViewDialog;
import com.vnapnic.myvib.model.Bill;
import com.vnapnic.myvib.model.CurrentVsChipModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vnapnic on 7/10/2016.
 */
public class PayAnyoneFragment extends Fragment implements View.OnClickListener, PayAnyoneAdapter.IActionPayAnyoneAdapter {
    private View viewRoot;
    private MainActivity activity;
    private LinearLayout btn_pay_a_bill;
    private LinearLayout pay_off_credit;

    private ListView listPayAnyone;
    private PayAnyoneAdapter adapter;
    private ArrayList<Bill> datas = new ArrayList<>();

    public static PayAnyoneFragment newInstance() {
        PayAnyoneFragment fragment = new PayAnyoneFragment();
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
        viewRoot = inflater.inflate(R.layout.fragment_pay_anynone, container, false);
        return viewRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btn_pay_a_bill = (LinearLayout) view.findViewById(R.id.btn_pay_a_bill);
        pay_off_credit = (LinearLayout) view.findViewById(R.id.pay_off_credit);
        listPayAnyone = (ListView) view.findViewById(R.id.listPayAnyone);
        listPayAnyone.setAdapter(adapter);

        btn_pay_a_bill.setOnClickListener(this);
        pay_off_credit.setOnClickListener(this);
    }

    private List<Bill> fakeDataListview() {
        List<Bill> billList = new ArrayList<>();

        Bill bill;
        //1
        bill = new Bill();
        bill.title = getActivity().getResources().getString(R.string.pay_vib);
        bill.style = PayAnyoneAdapter.STYLE_TITLE;
        billList.add(bill);

        bill = new Bill();
        bill.icon = R.drawable.account_icon_yellow;
        bill.title = "Nguyễn Văn A";
        bill.phone = "001100400709" + 1;
        bill.address = "NH TMCP NGOAI THUONG (VIETCOMBANK)";
        bill.style = PayAnyoneAdapter.STYLE_DESC;
        billList.add(bill);

        //2
        bill = new Bill();
        bill.title = getActivity().getResources().getString(R.string.localAccHeader);
        bill.style = PayAnyoneAdapter.STYLE_TITLE;
        billList.add(bill);

        bill = new Bill();
        bill.icon = R.drawable.account_icon_yellow;
        bill.title = "Nguyễn Văn B";
        bill.phone = "001100400709" + 2;
        bill.address = "NH TMCP NGOAI THUONG (VIETCOMBANK)";
        bill.style = PayAnyoneAdapter.STYLE_DESC;
        billList.add(bill);

        //3
        bill = new Bill();
        bill.title = getActivity().getResources().getString(R.string.card_nh_khac);
        bill.style = PayAnyoneAdapter.STYLE_TITLE;
        billList.add(bill);

        bill = new Bill();
        bill.icon = R.drawable.account_icon_yellow;
        bill.title = "Nguyễn Văn C";
        bill.phone = "001100400709" + 3;
        bill.address = "NH TMCP NGOAI THUONG (VIETCOMBANK)";
        bill.style = PayAnyoneAdapter.STYLE_DESC;
        billList.add(bill);

        //4
        bill = new Bill();
        bill.title = getActivity().getResources().getString(R.string.mobile_number);
        bill.style = PayAnyoneAdapter.STYLE_TITLE;
        billList.add(bill);

        bill = new Bill();
        bill.icon = R.drawable.account_icon_yellow;
        bill.title = "Nguyễn Văn D";
        bill.phone = "+84096190925";
        bill.style = PayAnyoneAdapter.STYLE_DESC;
        billList.add(bill);

        //5
        bill = new Bill();
        bill.title = getActivity().getResources().getString(R.string.email_address);
        bill.style = PayAnyoneAdapter.STYLE_TITLE;
        billList.add(bill);

        bill = new Bill();
        bill.icon = R.drawable.account_icon_yellow;
        bill.title = "Nguyễn Văn E";
        bill.phone = "vib@vib.com.vn";
        bill.style = PayAnyoneAdapter.STYLE_DESC;
        billList.add(bill);

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
            ((MainActivity) getActivity()).setTitle(getActivity().getResources().getString(R.string.pay_anyone));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_pay_a_bill:
                ((MainActivity) getActivity()).replaceFragment(PayToBankAccountFragment.newInstance());
                break;
            case R.id.pay_off_credit:
                ((MainActivity) getActivity()).replaceFragment(EmailMobileFragment.newInstance());
                break;
        }
    }

    @Override
    public void onClick(Bill bill) {
        ((MainActivity) getActivity()).replaceFragment(PayAnyoneDetailFragment.newInstance(bill));
    }
}
