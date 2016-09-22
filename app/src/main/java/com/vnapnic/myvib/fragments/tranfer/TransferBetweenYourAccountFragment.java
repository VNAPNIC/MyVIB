package com.vnapnic.myvib.fragments.tranfer;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.vnapnic.myvib.MainActivity;
import com.vnapnic.myvib.R;
import com.vnapnic.myvib.common.ToolbarTyper;
import com.vnapnic.myvib.customs.FontEditext;
import com.vnapnic.myvib.fragments.choice.SelectDescriptionFragment;
import com.vnapnic.myvib.fragments.getsecuritycode.TranferGetSecurityCodeFragment;
import com.vnapnic.myvib.model.SecurityCode;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by vnapnic on 7/10/2016.
 */
public class TransferBetweenYourAccountFragment extends Fragment implements View.OnTouchListener {

    private View viewRoot;
    private MainActivity activity;
    private FontEditext edt_amount, edt_desc, edt_date;

    public static TransferBetweenYourAccountFragment newInstance() {
        TransferBetweenYourAccountFragment fragment = new TransferBetweenYourAccountFragment();
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
        viewRoot = inflater.inflate(R.layout.fragment_transfer_details, container, false);
        return viewRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edt_amount = (FontEditext) view.findViewById(R.id.edt_amount);
        edt_desc = (FontEditext) view.findViewById(R.id.edt_desc);
        edt_date = (FontEditext) view.findViewById(R.id.edt_date);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String date = df.format(Calendar.getInstance().getTime());
        edt_date.setText(getActivity().getResources().getString(R.string.transfer_now) + " " + date);
        edt_amount.setOnTouchListener(this);
        edt_desc.setOnTouchListener(this);
    }

    public void next() throws Exception {
        SecurityCode content = new SecurityCode();
        content.type = 2;
        content.from = getResources().getString(R.string.open_account1_name);
        content.title = getResources().getString(R.string.pay);
        content.monney = edt_amount.getText().toString().trim();
        content.name = getResources().getString(R.string.other_account_title);
        content.for_card = "024579500658979";
        content.phone = getResources().getString(R.string.cardid);
        content.msTyper = getResources().getString(R.string.open_account1_name);
        content.desc = getDesc();
        ((MainActivity) getActivity()).replaceFragment(TranferGetSecurityCodeFragment.newInstance(content));
    }

    public void setAmount(String amount) {
        edt_amount.setText(amount);
    }

    public void setDataDesc(String desc) {
        if (TextUtils.isEmpty(desc)) {
            edt_desc.setText("");
            return;
        }
        edt_desc.setText(desc);
        if (isAdded()) {
            ((MainActivity) getActivity()).setUptoolBar(ToolbarTyper.NONE_RIGHT_BACK);
            ((MainActivity) getActivity()).setTitle(getActivity().getResources().getString(R.string.transfer_details));
            ((MainActivity) getActivity()).setRightText(getActivity().getResources().getString(R.string.transfer_popup_title));
        }
    }

    public String getDesc() {
        return TextUtils.isEmpty(edt_desc.getText().toString().trim()) ? getActivity().getResources().getString(R.string.transfer_between) : edt_desc.getText().toString().trim();
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
            ((MainActivity) getActivity()).setTitle(getActivity().getResources().getString(R.string.transfer_details));
            ((MainActivity) getActivity()).setRightText(getActivity().getResources().getString(R.string.transfer_popup_title));
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.edt_amount:
                activity.addFragment(MonneyFragment.newInstance());
                break;
            case R.id.edt_desc:
                activity.addFragment(SelectDescriptionFragment.newInstance());
                break;
        }
        return false;
    }
}

