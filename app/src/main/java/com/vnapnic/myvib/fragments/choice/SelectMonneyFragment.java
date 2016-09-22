package com.vnapnic.myvib.fragments.choice;

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
import com.vnapnic.myvib.customs.FontButton;

/**
 * Created by vnapnic on 7/10/2016.
 */
public class SelectMonneyFragment extends Fragment implements View.OnClickListener {
    private View viewRoot;
    private MainActivity activity;

    public static SelectMonneyFragment newInstance() {
        SelectMonneyFragment fragment = new SelectMonneyFragment();
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
        viewRoot = inflater.inflate(R.layout.fragment_select_money, container, false);
        return viewRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FontButton btn1 = (FontButton) viewRoot.findViewById(R.id.btn1);
        FontButton btn2 = (FontButton) viewRoot.findViewById(R.id.btn2);
        FontButton btn3 = (FontButton) viewRoot.findViewById(R.id.btn3);
        FontButton btn4 = (FontButton) viewRoot.findViewById(R.id.btn4);
        FontButton btn5 = (FontButton) viewRoot.findViewById(R.id.btn5);
        FontButton btn6 = (FontButton) viewRoot.findViewById(R.id.btn6);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
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
            ((MainActivity)getActivity()).setTitle(getActivity().getResources().getString(R.string.amount));
        }
    }

    private String data;

    public String Data() {
        return data;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
            case R.id.btn2:
            case R.id.btn3:
            case R.id.btn4:
            case R.id.btn5:
            case R.id.btn6:
                data = ((FontButton) viewRoot.findViewById(v.getId())).getText().toString().trim();
                activity.onBackPressed();
                break;
        }
    }
}
