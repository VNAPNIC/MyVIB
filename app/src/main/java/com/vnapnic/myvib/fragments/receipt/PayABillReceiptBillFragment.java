package com.vnapnic.myvib.fragments.receipt;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vnapnic.myvib.MainActivity;
import com.vnapnic.myvib.R;
import com.vnapnic.myvib.common.ToolbarTyper;
import com.vnapnic.myvib.customs.FontTextView;
import com.vnapnic.myvib.model.SecurityCode;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by vnapnic on 7/15/2016.
 */
public class PayABillReceiptBillFragment extends Fragment implements View.OnClickListener {
    private View viewRoot;
    private MainActivity activity;
    private static final String MS = "key.ms";
    private SecurityCode securityCode;

    public static PayABillReceiptBillFragment newInstance(SecurityCode securityCode) {
        PayABillReceiptBillFragment fragment = new PayABillReceiptBillFragment();
        fragment.setArguments(newBundle(securityCode));
        return fragment;
    }

    private static Bundle newBundle(SecurityCode securityCode) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(MS, securityCode);
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

        Log.i("tho", "onCreate: PayABillFragment");
    }

    private void initDataFromBundle(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            securityCode = (SecurityCode) savedInstanceState.getSerializable(MS);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.fragment_receipt_bill, container, false);
        return viewRoot;
    }

    //    String[] ms = {title, money, msTyper, phone, name, "048704060060307", desc, btn1, btn2};
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((FontTextView) view.findViewById(R.id.tv_amount)).setText(securityCode.monney);
        ((FontTextView) view.findViewById(R.id.tv_phone_no)).setText(securityCode.phone);
        ((FontTextView) view.findViewById(R.id.tv_name_id)).setText(securityCode.name);
        ((FontTextView) view.findViewById(R.id.tv_via_id)).setText(securityCode.msTyper);
        ((FontTextView) view.findViewById(R.id.tv_description_id)).setText(securityCode.desc);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String date = df.format(Calendar.getInstance().getTime());
        ((FontTextView) view.findViewById(R.id.tv_country)).setText(date);
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
            ((MainActivity) getActivity()).setTitle(getActivity().getResources().getString(R.string.receipt));
            ((MainActivity) getActivity()).setRightText(getActivity().getResources().getString(R.string.done));
        }
    }

    @Override
    public void onClick(View v) {

    }
}
