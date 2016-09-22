package com.vnapnic.myvib.fragments.account;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.vnapnic.myvib.MainActivity;
import com.vnapnic.myvib.R;
import com.vnapnic.myvib.customs.FontTextView;
import com.vnapnic.myvib.fragments.receipt.ReceiptFragment;

public class TransactionHistoryFragment extends Fragment implements View.OnClickListener {

    private View viewRoot;
    private MainActivity activity;

    private FontTextView title1, title2, title3, title4;
    private FontTextView money1, money2, money3, money4;

    private AppCompatSpinner filter;

    private String businessType[];
    private ArrayAdapter<String> adapterBusinessType;


    public static TransactionHistoryFragment newInstance() {
        TransactionHistoryFragment fragment = new TransactionHistoryFragment();
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
        viewRoot = inflater.inflate(R.layout.fragment_transaction_history, container, false);
        return viewRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((ImageView) view.findViewById(R.id.view_receipt1)).setOnClickListener(this);
        ((ImageView) view.findViewById(R.id.view_receipt2)).setOnClickListener(this);
        ((ImageView) view.findViewById(R.id.view_receipt3)).setOnClickListener(this);
        ((ImageView) view.findViewById(R.id.view_receipt4)).setOnClickListener(this);

        title1 = (FontTextView) view.findViewById(R.id.title_history1);
        title2 = (FontTextView) view.findViewById(R.id.title_history2);
        title3 = (FontTextView) view.findViewById(R.id.title_history3);
        title4 = (FontTextView) view.findViewById(R.id.title_history4);

        money1 = (FontTextView) view.findViewById(R.id.money_history1);
        money2 = (FontTextView) view.findViewById(R.id.money_history2);
        money3 = (FontTextView) view.findViewById(R.id.money_history3);
        money4 = (FontTextView) view.findViewById(R.id.money_history4);


        filter = (AppCompatSpinner) view.findViewById(R.id.filter);
        businessType = new String[]{
                getActivity().getResources().getString(R.string.all), "7 thg 7 2016", "27 thg 4 2016", "30 thg 7 2016"};
        adapterBusinessType = new ArrayAdapter<String>(activity,
                android.R.layout.simple_spinner_item, businessType);
        adapterBusinessType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filter.setAdapter(adapterBusinessType);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view_receipt1:
                ((MainActivity) getActivity()).replaceFragment(ReceiptFragment.newInstance(money1.getText().toString(), title1.getText().toString()));
                break;
            case R.id.view_receipt2:
                ((MainActivity) getActivity()).replaceFragment(ReceiptFragment.newInstance(money2.getText().toString(), title2.getText().toString()));
                break;
            case R.id.view_receipt3:
                ((MainActivity) getActivity()).replaceFragment(ReceiptFragment.newInstance(money3.getText().toString(), title3.getText().toString()));
                break;
            case R.id.view_receipt4:
                ((MainActivity) getActivity()).replaceFragment(ReceiptFragment.newInstance(money4.getText().toString(), title4.getText().toString()));
                break;
        }
    }
}
