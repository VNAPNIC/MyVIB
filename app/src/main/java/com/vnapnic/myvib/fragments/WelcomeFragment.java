package com.vnapnic.myvib.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vnapnic.myvib.R;
import com.vnapnic.myvib.MainActivity;
import com.vnapnic.myvib.common.ToolbarTyper;
import com.vnapnic.myvib.utils.Logger;
import com.vnapnic.myvib.utils.Util;

/**
 * Created by vnapnic on 7/2/2016.
 */
public class WelcomeFragment extends Fragment implements View.OnClickListener {

    private View viewRoot;
    private MainActivity activity;

    public static WelcomeFragment newInstance() {
        WelcomeFragment fragment = new WelcomeFragment();
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
        viewRoot = inflater.inflate(R.layout.fragment_welcome, container, false);
        return viewRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.page_interest_rates).setOnClickListener(this);
        view.findViewById(R.id.page_exchange_rate).setOnClickListener(this);
        view.findViewById(R.id.page_chat).setOnClickListener(this);
        view.findViewById(R.id.page_apply_vib).setOnClickListener(this);
        view.findViewById(R.id.page_atm_locator).setOnClickListener(this);
        view.findViewById(R.id.page_card_locator).setOnClickListener(this);
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
            ((MainActivity)getActivity()).setUptoolBar(ToolbarTyper.VIB_ICON_WELCOME);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.page_interest_rates:
                Logger.d("namit", "page_rates click");
                ((MainActivity)getActivity()).replaceFragment(InterestRatesFragment.newInstance());
                break;
            case R.id.page_exchange_rate:
                Logger.d("namit", "page_rates click");
                ((MainActivity)getActivity()).replaceFragment(ExchangeRateFragment.newInstance());
                break;
            case R.id.page_chat:
            case R.id.page_apply_vib:
                Logger.d("namit", "page_apply & page_chat click");
                Util.openAppVIB(activity);
                break;
            case R.id.page_atm_locator:
                Logger.d("namit", "page_atm click");
                ((MainActivity)getActivity()).replaceFragment(MapsFragment.newInstance(1));
                break;
            case R.id.page_card_locator:
                Logger.d("namit", "page_card click");
                ((MainActivity)getActivity()).replaceFragment(MapsFragment.newInstance(2));
                break;
        }
    }


}
