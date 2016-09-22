package com.vnapnic.myvib.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.vnapnic.myvib.MainActivity;
import com.vnapnic.myvib.R;
import com.vnapnic.myvib.adapter.AdapterViewPager;
import com.vnapnic.myvib.common.ToolbarTyper;
import com.vnapnic.myvib.customs.FontTextView;
import com.vnapnic.myvib.utils.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vnapnic on 7/4/2016.
 */
public class InterestRatesFragment extends Fragment implements View.OnClickListener {

    private View viewRoot;
    private MainActivity activity;
    private ImageButton prev;
    private ImageView next;
    private ViewPager fragmentContent;
    private AdapterViewPager adapterViewPager;

    public static InterestRatesFragment newInstance() {
        InterestRatesFragment fragment = new InterestRatesFragment();
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
        viewRoot = inflater.inflate(R.layout.fragment_interest_rates, container, false);
        return viewRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        prev = (ImageButton) view.findViewById(R.id.interest_prev);
        next = (ImageButton) view.findViewById(R.id.interest_next);
        fragmentContent = (ViewPager) view.findViewById(R.id.interest_content);
        adapterViewPager = new AdapterViewPager(getChildFragmentManager());
        fragmentContent.setAdapter(adapterViewPager);

        initEvent();
    }

    private void initEvent() {
        fragmentContent.addOnPageChangeListener(onPageChangeListener);
        prev.setOnClickListener(this);
        next.setOnClickListener(this);
        initData();
    }

    private void initData() {
        adapterViewPager.onClear();
        adapterViewPager.addFragment(
                OrdinarySavingsFragment.newInstance(),
                ESavingsFragment.newInstance(),
                DailySavingsFragment.newInstance(),
                ProgressiveSavingFragment.newInstance()
        );
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
        }
    }

    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (position == 0) {
                prev.setVisibility(View.INVISIBLE);
                next.setVisibility(View.VISIBLE);
            } else if (position > 0 && position < adapterViewPager.geLength() - 1) {
                prev.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);
            } else if (position == adapterViewPager.geLength() - 1) {
                prev.setVisibility(View.VISIBLE);
                next.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.interest_next:
                Logger.d("namit", "interest next");
                fragmentContent.setCurrentItem(fragmentContent.getCurrentItem() + 1);
                break;
            case R.id.interest_prev:
                Logger.d("namit", "interest prev");
                fragmentContent.setCurrentItem(fragmentContent.getCurrentItem() -1);
                break;
        }
    }
}

