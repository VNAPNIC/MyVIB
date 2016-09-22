package com.vnapnic.myvib.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.vnapnic.myvib.MainActivity;
import com.vnapnic.myvib.R;
import com.vnapnic.myvib.adapter.AdapterViewPager;
import com.vnapnic.myvib.common.ToolbarTyper;
import com.vnapnic.myvib.customs.FontTextView;

/**
 * Created by vnapnic on 7/8/2016.
 */
public class CardManagementFragment extends Fragment implements View.OnClickListener {

    private View viewRoot;
    private MainActivity activity;
    private ViewPager viewPager;
    private AdapterViewPager adapterViewPager;
    private RelativeLayout pev, next;
    private LinearLayout layout3;
    private FontTextView cardHolder, cardNumber;

    public static CardManagementFragment newInstance() {
        CardManagementFragment fragment = new CardManagementFragment();
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
        viewRoot = inflater.inflate(R.layout.fragment_card_management, container, false);
        return viewRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        pev = (RelativeLayout) view.findViewById(R.id.btn_prev1);
        next = (RelativeLayout) view.findViewById(R.id.btn_next1);
        cardHolder = (FontTextView) view.findViewById(R.id.title_cardholder);
        cardNumber = (FontTextView) view.findViewById(R.id.card_number);
        layout3 = (LinearLayout) view.findViewById(R.id.status_layout3);

        pev.setOnClickListener(this);
        next.setOnClickListener(this);
        adapterViewPager = new AdapterViewPager(getChildFragmentManager());
        viewPager.setAdapter(adapterViewPager);
        viewPager.addOnPageChangeListener(onPageChangeListener);
        initData();
    }

    private void initData() {
        next.setVisibility(View.VISIBLE);
        pev.setVisibility(View.GONE);
        layout3.setVisibility(View.GONE);

        adapterViewPager.onClear();
        adapterViewPager.addFragment(
                ImageSlidingFragment2.newInstance()
                , ImageSlidingFragment.newInstance());
    }

    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (position == 0) {
                next.setVisibility(View.VISIBLE);
                pev.setVisibility(View.GONE);
                cardHolder.setText(getActivity().getResources().getString(R.string.owner_credit_card));
                cardNumber.setText("9704416850007163102");
                layout3.setVisibility(View.GONE);
            } else {
                next.setVisibility(View.GONE);
                pev.setVisibility(View.VISIBLE);
                cardHolder.setText(getActivity().getResources().getString(R.string.sup_credit_card));
                cardNumber.setText("97044168xxxxxx3102");
                layout3.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (isAdded()) {
            ((MainActivity)getActivity()).setUptoolBar(ToolbarTyper.NONE_RIGHT_BACK);
            ((MainActivity)getActivity()).setTitle(getActivity().getResources().getString(R.string.card_management_title));
            ((MainActivity)getActivity()).setRightText(getActivity().getResources().getString(R.string.edit));
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next1:
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                break;
            case R.id.btn_prev1:
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                break;
        }
    }
}

