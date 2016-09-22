package com.vnapnic.myvib.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vnapnic.myvib.MainActivity;
import com.vnapnic.myvib.R;
import com.vnapnic.myvib.fragments.account.AccountFragment;
import com.vnapnic.myvib.fragments.bill.BillFragment;
import com.vnapnic.myvib.fragments.payanyone.PayAnyoneFragment;
import com.vnapnic.myvib.fragments.topup.TopUpFragment;
import com.vnapnic.myvib.fragments.tranfer.TransferBetweenYourAccountFragment;
import com.vnapnic.myvib.fragments.transactionmanagement.TransactionManagementFragment;
import com.vnapnic.myvib.utils.Util;

/**
 * Created by vnapnic on 7/3/2016.
 */
public class SlidingFragment extends Fragment implements View.OnClickListener {

    private View viewRoot;

    public static SlidingFragment newInstance() {
        SlidingFragment fragment = new SlidingFragment();
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
        viewRoot = inflater.inflate(R.layout.fragment_sliding, container, false);
        return viewRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.menu_option10).setOnClickListener(this);
        view.findViewById(R.id.menu_view_account_balance).setOnClickListener(this);
        view.findViewById(R.id.menu_option6_layout).setOnClickListener(this);

        view.findViewById(R.id.menu_promotion_location).setOnClickListener(this);
        view.findViewById(R.id.menu_apply_product).setOnClickListener(this);

        view.findViewById(R.id.menu_option2).setOnClickListener(this);
        view.findViewById(R.id.menu_option3).setOnClickListener(this);
        view.findViewById(R.id.menu_option8).setOnClickListener(this);
        view.findViewById(R.id.menu_option9).setOnClickListener(this);
        view.findViewById(R.id.menu_pay_any_one).setOnClickListener(this);
        view.findViewById(R.id.menu_pay_a_bill).setOnClickListener(this);
        view.findViewById(R.id.menu_mobile_top_up).setOnClickListener(this);
        view.findViewById(R.id.menu_transfer_between_account).setOnClickListener(this);

        view.findViewById(R.id.menu_option_schedule).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menu_option10:
                ((MainActivity) getActivity()).replaceFragmentDelay(PinFragment.newInstance());
                break;
            case R.id.menu_option6_layout:
                Util.openURLVIB((MainActivity) getActivity());
                break;
            case R.id.menu_view_account_balance:
                ((MainActivity) getActivity()).replaceFragmentDelay(AccountFragment.newInstance());
                break;
            case R.id.menu_promotion_location:
                ((MainActivity) getActivity()).replaceFragmentDelay(MapsFragment.newInstance(1));
                break;
            case R.id.menu_apply_product:
                Util.openAppVIB((MainActivity) getActivity());
                break;
            case R.id.menu_option2:
                ((MainActivity) getActivity()).replaceFragmentDelay(InterestRatesFragment.newInstance());
                break;
            case R.id.menu_option3:
                ((MainActivity) getActivity()).replaceFragmentDelay(ExchangeRateFragment.newInstance());
                break;
            case R.id.menu_option8:
                ((MainActivity) getActivity()).replaceFragmentDelay(SettingLanguageFragment.newInstance());
                break;
            case R.id.menu_option9:
                ((MainActivity) getActivity()).replaceFragmentDelay(PinFragment.newInstance());
                break;
            case R.id.menu_pay_any_one:
                ((MainActivity) getActivity()).replaceFragmentDelay(PayAnyoneFragment.newInstance());
                break;
            case R.id.menu_pay_a_bill:
                ((MainActivity) getActivity()).replaceFragmentDelay(BillFragment.newInstance());
                break;
            case R.id.menu_mobile_top_up:
                ((MainActivity) getActivity()).replaceFragmentDelay(TopUpFragment.newInstance());
                break;
            case R.id.menu_transfer_between_account:
                ((MainActivity) getActivity()).replaceFragmentDelay(TransferBetweenYourAccountFragment.newInstance());
                break;
            case R.id.menu_option_schedule:
                ((MainActivity) getActivity()).replaceFragmentDelay(TransactionManagementFragment.newInstance());
                break;
        }
    }
}
