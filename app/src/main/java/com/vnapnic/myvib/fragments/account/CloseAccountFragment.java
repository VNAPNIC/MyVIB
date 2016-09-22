package com.vnapnic.myvib.fragments.account;

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
import com.vnapnic.myvib.customs.FontEditext;
import com.vnapnic.myvib.fragments.DailySavingsFragment;
import com.vnapnic.myvib.model.Account;
import com.vnapnic.myvib.model.SecurityCode;

/**
 * Created by vnapnic on 7/24/2016.
 */
public class CloseAccountFragment extends Fragment {

    private View viewRoot;
    private MainActivity activity;
    private static final String DATA = "key.data";
    private Account account;
    private FontEditext edt_amount, edt_princ, edt_interest, edt_fee, edt_settlement, edt_reason;

    public static CloseAccountFragment newInstance(Account account) {
        CloseAccountFragment fragment = new CloseAccountFragment();
        fragment.setArguments(newBundle(account));
        return fragment;
    }

    private static Bundle newBundle(Account account) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(DATA, account);
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
        account = (Account) savedInstanceState.getSerializable(DATA);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.fragment_close_account, container, false);
        return viewRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edt_amount = (FontEditext) view.findViewById(R.id.edt_amount);
        edt_princ = (FontEditext) view.findViewById(R.id.edt_princ);
        edt_interest = (FontEditext) view.findViewById(R.id.edt_interest);
        edt_fee = (FontEditext) view.findViewById(R.id.edt_fee);
        edt_settlement = (FontEditext) view.findViewById(R.id.edt_settlement);
        edt_reason = (FontEditext) view.findViewById(R.id.edt_reason);

        edt_amount.setText(account.cardID);
        edt_princ.setText(account.soduKeToan);
        edt_interest.setText("36 VND");
        edt_fee.setText("0 VND");
        edt_settlement.setText(account.cardID);
        edt_reason.setText(getActivity().getResources().getString(R.string.optional));
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
            ((MainActivity)getActivity()).setUptoolBar(ToolbarTyper.NONE_RIGHT_BACK);
            ((MainActivity)getActivity()).setTitle(getActivity().getResources().getString(R.string.close_account_title));
            ((MainActivity)getActivity()).setRightText(getActivity().getResources().getString(R.string.next));
        }
    }

    public Account getData() {
        return account;
    }
}