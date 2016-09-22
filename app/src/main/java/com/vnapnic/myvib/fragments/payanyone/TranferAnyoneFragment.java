package com.vnapnic.myvib.fragments.payanyone;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.vnapnic.myvib.MainActivity;
import com.vnapnic.myvib.R;
import com.vnapnic.myvib.common.ToolbarTyper;
import com.vnapnic.myvib.customs.FontTextView;
import com.vnapnic.myvib.model.Bank;

/**
 * Created by vnapn on 7/27/2016.
 */
public class TranferAnyoneFragment extends Fragment implements View.OnClickListener {

    private View viewRoot;
    private MainActivity activity;
    private static final String DATA = "key.data";
    private Bank bank;
    private FontTextView title, desc, desc2;
    private RelativeLayout normal, fast;

    public static TranferAnyoneFragment newInstance(Bank bank) {
        TranferAnyoneFragment fragment = new TranferAnyoneFragment();
        fragment.setArguments(newBundle(bank));
        return fragment;
    }

    private static Bundle newBundle(Bank bank) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(DATA, bank);
        return bundle;
    }

    //  lifecycle
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            initDataFromBundle(savedInstanceState);
        } else {
            initDataFromBundle(getArguments());
        }
    }


    private void initDataFromBundle(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            bank = (Bank) savedInstanceState.getSerializable(DATA);
        } else {
            bank = new Bank();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.fragment_anyone_tranfer, container, false);
        return viewRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        title = (FontTextView) view.findViewById(R.id.title);
        desc = (FontTextView) view.findViewById(R.id.desc);
        desc2 = (FontTextView) view.findViewById(R.id.desc2);
        normal = (RelativeLayout) view.findViewById(R.id.normal);
        fast = (RelativeLayout) view.findViewById(R.id.fast);
        title.setText(Html.fromHtml(getActivity().getResources().getString(R.string.bank) + bank.name + " (" + bank.code + ")"));
        desc.setText(String.format(getActivity().getResources().getString(R.string.normal_transfer_desc), "0.03", "13,200"));
        desc2.setText(String.format(getActivity().getResources().getString(R.string.fast_transfer_desc), "11,000"));
        normal.setOnClickListener(this);
        fast.setOnClickListener(this);
        
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
            ((MainActivity)getActivity()).setTitle(getActivity().getResources().getString(R.string.pay_someone_new_title));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.normal:
                ((MainActivity)getActivity()).replaceFragment(NormalAnyoneFragment.newInstance(bank));
                break;
            case R.id.fast:
                ((MainActivity)getActivity()).replaceFragment(FastAnyoneFragment.newInstance(bank));
                break;
        }
    }
}


