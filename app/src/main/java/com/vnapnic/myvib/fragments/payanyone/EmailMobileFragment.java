package com.vnapnic.myvib.fragments.payanyone;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.vnapnic.myvib.MainActivity;
import com.vnapnic.myvib.R;
import com.vnapnic.myvib.common.ToolbarTyper;
import com.vnapnic.myvib.model.Bill;
import com.vnapnic.myvib.model.PayEnd;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vnapnic on 7/10/2016.
 */
public class EmailMobileFragment extends Fragment implements View.OnClickListener {
    private View viewRoot;
    private MainActivity activity;
    private LinearLayout btn_mobile;
    private LinearLayout btn_email;

    public static EmailMobileFragment newInstance() {
        EmailMobileFragment fragment = new EmailMobileFragment();
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
        viewRoot = inflater.inflate(R.layout.fragment_email_moblie, container, false);
        return viewRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_mobile = (LinearLayout) view.findViewById(R.id.btn_mobile);
        btn_email = (LinearLayout) view.findViewById(R.id.btn_email);
        ((LinearLayout) view.findViewById(R.id.layout_number)).setOnClickListener(this);
        ((LinearLayout) view.findViewById(R.id.layout_email)).setOnClickListener(this);
        btn_mobile.setOnClickListener(this);
        btn_email.setOnClickListener(this);
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
        PayEnd payEnd;
        switch (v.getId()) {
            case R.id.btn_mobile:
                ((MainActivity) getActivity()).replaceFragment(EmailMobileDetailFragment.newInstance(0));
                break;
            case R.id.btn_email:
                ((MainActivity) getActivity()).replaceFragment(EmailMobileDetailFragment.newInstance(1));
                break;
            case R.id.layout_number:
                payEnd = new PayEnd();
                payEnd.name = "NGUYEN VAN D";
                payEnd.id = "+84096190925";
                ((MainActivity) getActivity()).replaceFragment(PayAnyoneEndDetailFragment.newInstance(payEnd));
                break;
            case R.id.layout_email:
                payEnd = new PayEnd();
                payEnd.name = "Nguyễn Văn E";
                payEnd.id = "vib@vib.com.vn";
                ((MainActivity) getActivity()).replaceFragment(PayAnyoneEndDetailFragment.newInstance(payEnd));
                break;
        }
    }
}
