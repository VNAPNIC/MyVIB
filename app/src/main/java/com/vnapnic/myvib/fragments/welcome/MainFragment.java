package com.vnapnic.myvib.fragments.welcome;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.augustopicciani.drawablepageindicator.widget.DrawablePagerIndicator;
import com.vnapnic.myvib.MainActivity;
import com.vnapnic.myvib.R;
import com.vnapnic.myvib.adapter.AdapterViewPager;
import com.vnapnic.myvib.common.ToolbarTyper;
import com.vnapnic.myvib.customs.FontButton;
import com.vnapnic.myvib.fragments.account.AccountFragment;
import com.vnapnic.myvib.fragments.tranfer.TransferBetweenYourAccountFragment;
import com.vnapnic.myvib.fragments.bill.BillFragment;
import com.vnapnic.myvib.fragments.payanyone.PayAnyoneFragment;
import com.vnapnic.myvib.fragments.topup.TopUpFragment;
import com.vnapnic.myvib.utils.Logger;
import com.vnapnic.myvib.utils.Util;

/**
 * Created by vnapnic on 7/3/2016.
 */
public class MainFragment extends Fragment implements View.OnClickListener {

    private View viewRoot;
    private ViewPager header;
    private AdapterViewPager adapterViewPager;

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
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
        viewRoot = inflater.inflate(R.layout.fragment_main, container, false);
        return viewRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.view_account).setOnClickListener(this);
        header = (ViewPager) view.findViewById(R.id.header);
        adapterViewPager = new AdapterViewPager(getChildFragmentManager());
        header.setAdapter(adapterViewPager);
        adapterViewPager.addFragment(
                HomeFragment.newInstance(),
                CurrentFragment.newInstance(),
                LocationFragment.newInstance());

        view.findViewById(R.id.open_an_account).setOnClickListener(this);
        view.findViewById(R.id.pay_a_bill).setOnClickListener(this);
        view.findViewById(R.id.pay_anyone).setOnClickListener(this);
        view.findViewById(R.id.transfer_account).setOnClickListener(this);
        view.findViewById(R.id.btn_top_up).setOnClickListener(this);

        DrawablePagerIndicator drawablePagerIndicator = (DrawablePagerIndicator) view.findViewById(R.id.drawableIndicator);
        drawablePagerIndicator.setViewPager(header);

        header.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ((MainActivity) getActivity()).onCloseSliding();
                return false;
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (isAdded()) {
            ((MainActivity) getActivity()).setUptoolBar(ToolbarTyper.VIB_ICON_MAIN);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view_account:
                Logger.d("namit", "view account");
                ((MainActivity) getActivity()).replaceNotAnimFragment(AccountFragment.newInstance());
                break;
            case R.id.open_an_account:
                final Dialog dialog = new Dialog(getActivity());
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.custom_dialog_open_account, null);
                FontButton yes = (FontButton) view.findViewById(R.id.yes);
                FontButton no = (FontButton) view.findViewById(R.id.no);
                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Logger.d("namit", "open an account");
                        Util.openAppVIB(getActivity());
                    }
                });
                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(view);
                dialog.show();

                break;
            case R.id.pay_a_bill:
                ((MainActivity) getActivity()).replaceNotAnimFragment(BillFragment.newInstance());
                break;
            case R.id.pay_anyone:
                ((MainActivity) getActivity()).replaceNotAnimFragment(PayAnyoneFragment.newInstance());
                break;
            case R.id.transfer_account:
                ((MainActivity) getActivity()).replaceNotAnimFragment(TransferBetweenYourAccountFragment.newInstance());
                break;
            case R.id.btn_top_up:
                ((MainActivity) getActivity()).replaceNotAnimFragment(TopUpFragment.newInstance());
                break;
        }
    }
}
