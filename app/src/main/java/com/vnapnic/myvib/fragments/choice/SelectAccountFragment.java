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
import com.vnapnic.myvib.adapter.AccountAdapter;
import com.vnapnic.myvib.common.ToolbarTyper;
import com.vnapnic.myvib.customs.MyListView;
import com.vnapnic.myvib.model.Account;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vnapnic on 7/15/2016.
 */
public class SelectAccountFragment extends Fragment implements AccountAdapter.IActionClick {

    private View viewRoot;
    private MainActivity activity;
    private MyListView listView;
    private AccountAdapter adapter;
    private Account account;

    public static SelectAccountFragment newInstance() {
        SelectAccountFragment fragment = new SelectAccountFragment();
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
        viewRoot = inflater.inflate(R.layout.fragment_select_account, container, false);
        return viewRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (MyListView) view.findViewById(R.id.myLv);
        adapter = new AccountAdapter(activity, initData(), this);
        listView.setAdapter(adapter);
    }


    private List<Account> initData() {
        final List<Account> accountList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            if (i == 0) {
                Account account = new Account();
                account.id = 1;
                account.icon = R.drawable.icon_account_thanhtoan;
                account.cardID = "0487406006030" + i;
                account.title = getActivity().getResources().getString(R.string.open_account1_name);
                account.soDU = "1,145,66" + i + " VND";
                account.soduKeToan = "1,550,55" + i + " VND";
                account.isRemove = false;
                accountList.add(account);
            } else if (i == 1) {
                Account account = new Account();
                account.id = 0;
                account.icon = R.drawable.icon_account_saving;
                account.cardID = "0487406006030" + i;
                account.title = getActivity().getResources().getString(R.string.title_519);
                account.soDU = "N/A";
                account.soduKeToan = "1,770,32" + i + " VND";
                account.isRemove = true;
                accountList.add(account);
            }
//            else if (i == 2) {
//                Account account = new Account();
//                account.id = 0;
//                account.icon = R.drawable.online_term_deposit;
//                account.cardID = "0487406006030" + i;
//                account.title = getActivity().getResources().getString(R.string.title_507);
//                account.soDU = "1,145,66" + i + " VND";
//                account.soduKeToan = "1,550,55" + i + " VND";
//                account.isRemove = true;
//                accountList.add(account);
//            }
//            else if (i == 3) {
//                Account account = new Account();
//                account.id = 0;
//                account.icon = R.drawable.icon_account_default;
//                account.cardID = "0487406006030" + i;
//                account.title = getActivity().getResources().getString(R.string.bieu_lai_suat_esaving);
//                account.soDU = "1,145,66" + i + " VND";
//                account.soduKeToan = "1,550,55" + i + " VND";
//                account.isRemove = true;
//                accountList.add(account);
//            }
            else if (i == 4) {
                Account account = new Account();
                account.id = 2;
                account.icon = R.drawable.icon_account_the;
                account.cardID = "0487406006030" + i;
                account.title = "VIB CHIP MC";
                account.soDU = "1,145,66" + i + " VND";
                account.soduKeToan = "1,550,55" + i + " VND";
                account.isRemove = false;
                accountList.add(account);
            }
        }
        return accountList;
    }

    public Account getData() {
        return this.account;
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
            ((MainActivity)getActivity()).setTitle(getActivity().getResources().getString(R.string.select_acc));
        }
    }

    @Override
    public void eventClick(Account account) {
        this.account = account;
        activity.onBackPressed();
    }

    @Override
    public void evnetCLickRemove(Account account) {

    }
}
