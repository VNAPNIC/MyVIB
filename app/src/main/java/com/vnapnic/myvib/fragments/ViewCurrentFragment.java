package com.vnapnic.myvib.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.vnapnic.myvib.MainActivity;
import com.vnapnic.myvib.R;
import com.vnapnic.myvib.common.ToolbarTyper;
import com.vnapnic.myvib.customs.FontButton;

/**
 * Created by vnapnic on 7/19/2016.
 */
public class ViewCurrentFragment extends Fragment implements View.OnClickListener {

    private View viewRoot;
    private MainActivity activity;
    private FontButton btnRemove;
    private RelativeLayout btn_account, layout_select, default_layout, choise_layout;
    private ImageView bao_phu;

    public static ViewCurrentFragment newInstance() {
        ViewCurrentFragment fragment = new ViewCurrentFragment();
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
        viewRoot = inflater.inflate(R.layout.fragment_view_current, container, false);
        return viewRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnRemove = (FontButton) view.findViewById(R.id.btnRemove);
        btn_account = (RelativeLayout) view.findViewById(R.id.btn_account);
        bao_phu = (ImageView) view.findViewById(R.id.bao_phu);
        layout_select = (RelativeLayout) view.findViewById(R.id.layout_select);
        default_layout = (RelativeLayout) view.findViewById(R.id.default_layout);
        choise_layout = (RelativeLayout) view.findViewById(R.id.choise_layout);

        btnRemove.setOnClickListener(this);
        btn_account.setOnClickListener(this);
        default_layout.setOnClickListener(this);
        choise_layout.setOnClickListener(this);
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
            ((MainActivity)getActivity()).setUptoolBar(ToolbarTyper.NONE_BACK);
            ((MainActivity)getActivity()).setTitle(getActivity().getResources().getString(R.string.setup_account_activity_title));
        }
    }


    public boolean out() {
        if (layout_select.getVisibility() != View.VISIBLE) {
            return false;
        }
        layout_select.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.view_slide_out_down));
        layout_select.setVisibility(View.GONE);
        bao_phu.setVisibility(View.GONE);
        return true;
    }

    public boolean in() {
        if (layout_select.getVisibility() == View.VISIBLE) {
            return false;
        }
        layout_select.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.view_slide_in_up));
        layout_select.setVisibility(View.VISIBLE);
        bao_phu.setVisibility(View.VISIBLE);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRemove:
                out();
                default_layout.setVisibility(View.VISIBLE);
                choise_layout.setVisibility(View.GONE);
                break;
            case R.id.btn_account:
                default_layout.setVisibility(View.GONE);
                choise_layout.setVisibility(View.VISIBLE);
                out();
                break;
            case R.id.default_layout:
                in();
                break;
            case R.id.choise_layout:
                in();
                break;
        }
    }
}

