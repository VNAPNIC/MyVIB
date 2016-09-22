package com.vnapnic.myvib.fragments.transactionmanagement;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vnapnic.myvib.MainActivity;
import com.vnapnic.myvib.R;
import com.vnapnic.myvib.common.ToolbarTyper;
import com.vnapnic.myvib.fragments.account.PaymentEditDetailFragment;
import com.vnapnic.myvib.fragments.getsecuritycode.GetSecurityCodeFragment;
import com.vnapnic.myvib.model.SecurityCode;

/**
 * Created by Nankai on 9/12/2016.
 */
public class TransactionScheduleFragment extends Fragment implements View.OnClickListener {
    private View viewRoot;

    public static TransactionScheduleFragment newInstance() {
        TransactionScheduleFragment fragment = new TransactionScheduleFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.fragment_transaction_detail, container, false);
        return viewRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.btn_bot).setOnClickListener(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (isAdded()) {
            ((MainActivity) getActivity()).setUptoolBar(ToolbarTyper.NONE_RIGHT_BACK);
            ((MainActivity) getActivity()).setTitle(getActivity().getResources().getString(R.string.pay_someone_detail_title));
            ((MainActivity) getActivity()).setRightText(getActivity().getResources().getString(R.string.delete));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_bot:
                try {
                    next();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }


    public void next() throws Exception {
        SecurityCode contentCode = new SecurityCode();
        contentCode.type = 1;
        contentCode.from = getActivity().getResources().getString(R.string.tien_gui_thanh_toan);
        contentCode.title = getActivity().getResources().getString(R.string.delete_payment_order);
        contentCode.monney = "2";
        contentCode.name = "NGUYỄN VĂN A";
        contentCode.phone = "3508315227";
        contentCode.desc = "Savings";
        ((MainActivity) getActivity()).replaceFragment(GetSecurityCodeFragment.newInstance(contentCode));
    }
}
