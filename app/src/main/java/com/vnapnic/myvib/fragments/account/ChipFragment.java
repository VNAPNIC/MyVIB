package com.vnapnic.myvib.fragments.account;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import com.vnapnic.myvib.MainActivity;
import com.vnapnic.myvib.R;
import com.vnapnic.myvib.adapter.CurrentVsChipAdapter;
import com.vnapnic.myvib.common.ToolbarTyper;
import com.vnapnic.myvib.customs.AccountViewHolder;
import com.vnapnic.myvib.fragments.CardManagementFragment;
import com.vnapnic.myvib.fragments.receipt.ReceiptFragment;
import com.vnapnic.myvib.model.Account;
import com.vnapnic.myvib.model.CurrentVsChipModel;

import java.util.ArrayList;

public class ChipFragment extends Fragment implements View.OnClickListener, CurrentVsChipAdapter.IActionAdapter {

    private static final String DATA = "key.data";
    private Account account;
    private AccountViewHolder holder;
    private ListView listview;
    private LinearLayout rl_payoff;
    private CurrentVsChipAdapter adapter;
    private ArrayList<CurrentVsChipModel> datas = new ArrayList<>();
    private ImageView actionBalance;

    private Spinner spn;
    private ArrayAdapter<String> spinAdapter;

    public static ChipFragment newInstance(Account account) {
        ChipFragment fragment = new ChipFragment();
        fragment.setArguments(newBundle(account));
        return fragment;
    }

    private static Bundle newBundle(Account account) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(DATA, account);
        return bundle;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            initDataFromBundle(savedInstanceState);
        } else {
            initDataFromBundle(getArguments());
        }
        datas.addAll(fakeDataListView());
        adapter = new CurrentVsChipAdapter(getActivity(), android.R.layout.simple_list_item_1, datas);
        adapter.setActionAdapter(this);
        spinAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, fakeDataSpinner());
        spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    private void initDataFromBundle(Bundle savedInstanceState) {
        account = (Account) savedInstanceState.getSerializable(DATA);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chip, container, false);
        listview = (ListView) view.findViewById(R.id.listview);
        rl_payoff = (LinearLayout) view.findViewById(R.id.menu_account_detail);
        spn = (Spinner) view.findViewById(R.id.spiner);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        holder = new AccountViewHolder(getActivity(), view);
        holder.setData(account);
        spn.setAdapter(spinAdapter);
        rl_payoff.setOnClickListener(this);
        listview.setAdapter(adapter);

        actionBalance = (ImageView) view.findViewById(R.id.action_button_images_simple_balance);
        actionBalance.setImageResource(R.drawable.icon_card_manager);
        actionBalance.setOnClickListener(this);
    }

    private ArrayList<CurrentVsChipModel> fakeDataListView() {
        ArrayList<CurrentVsChipModel> fake = new ArrayList<>();
        fake.add(new CurrentVsChipModel(CurrentVsChipAdapter.STYLE_DESC, "30 " + getActivity().getResources().getString(R.string.jul) + " 2016", "", "32 " + getActivity().getResources().getString(R.string.days_ago)));
        fake.add(new CurrentVsChipModel(CurrentVsChipAdapter.STYLE_CONT, "RUT TIEN TAI ATM VIB", "-50,000 VND", ""));

        fake.add(new CurrentVsChipModel(CurrentVsChipAdapter.STYLE_DESC, "7 " + getActivity().getResources().getString(R.string.jul) + " 2016", "", "55 " + getActivity().getResources().getString(R.string.days_ago)));
        fake.add(new CurrentVsChipModel(CurrentVsChipAdapter.STYLE_CONT, "601 CREDIT INT CAPITALISE", "+11 VND", ""));

        fake.add(new CurrentVsChipModel(CurrentVsChipAdapter.STYLE_DESC, "30 " + getActivity().getResources().getString(R.string.jun) + " 2016", "", "63 " + getActivity().getResources().getString(R.string.days_ago)));
        fake.add(new CurrentVsChipModel(CurrentVsChipAdapter.STYLE_CONT, "Home, Bill", "-1 VND", ""));

        return fake;
    }

    private ArrayList<String> fakeDataSpinner() {
        ArrayList<String> data = new ArrayList<>();
        data.add(getActivity().getResources().getString(R.string.all));
        data.add("30 " + getActivity().getResources().getString(R.string.jul) + " 2016");
        data.add("7 " + getActivity().getResources().getString(R.string.jul) + " 2016");
        data.add("30 " + getActivity().getResources().getString(R.string.jun) + " 2016");
        return data;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menu_account_detail:
                Account data = new Account();
                data.title = "NGUYEN VAN A";
                data.cardID = account.cardID;
                ((MainActivity) getActivity()).replaceFragment(PayOffCritFragment.newInstance(data));
                break;
            case R.id.action_button_images_simple_balance:
                ((MainActivity) getActivity()).replaceFragment(CardManagementFragment.newInstance());
                break;
        }
    }

    //Action adapter
    @Override
    public void onClick(CurrentVsChipModel chipModel) {
        ((MainActivity) getActivity()).replaceFragment(ReceiptFragment.newInstance(chipModel.getContent(), chipModel.getTitle()));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (isAdded()) {
            ((MainActivity) getActivity()).setUptoolBar(ToolbarTyper.NONE_BACK);
            ((MainActivity) getActivity()).setTitle(getActivity().getResources().getString(R.string.account_details));
        }
    }
}
