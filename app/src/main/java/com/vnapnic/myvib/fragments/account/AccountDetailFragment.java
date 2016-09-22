package com.vnapnic.myvib.fragments.account;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

import com.vnapnic.myvib.MainActivity;
import com.vnapnic.myvib.R;
import com.vnapnic.myvib.adapter.CurrentVsChipAdapter;
import com.vnapnic.myvib.common.ToolbarTyper;
import com.vnapnic.myvib.customs.AccountViewHolder;
import com.vnapnic.myvib.customs.FontTextView;
import com.vnapnic.myvib.fragments.receipt.ReceiptFragment;
import com.vnapnic.myvib.model.Account;
import com.vnapnic.myvib.model.CurrentVsChipModel;

import java.util.ArrayList;

/**
 * Created by vnapnic on 7/6/2016.
 */
public class AccountDetailFragment extends Fragment implements View.OnClickListener, CurrentVsChipAdapter.IActionAdapter {
    private static final String DATA = "key.data";
    private Account account;
    private AccountViewHolder holder;
    private ImageView actionBalance;
    private View viewRoot;

    private ListView listview;
    private CurrentVsChipAdapter adapter;
    private ArrayList<CurrentVsChipModel> datas = new ArrayList<>();
    private Spinner spn;
    private ArrayAdapter<String> spinAdapter;

    public static AccountDetailFragment newInstance(Account account) {
        AccountDetailFragment fragment = new AccountDetailFragment();
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

        datas.addAll(fakeDataListview(((MainActivity) getActivity()).getResources().getString(R.string.open_saving_account) + " " + account.cardID));
        adapter = new CurrentVsChipAdapter(getActivity(), R.layout.list_item, datas);
        adapter.setActionAdapter(this);
        spinAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, fakeDataSpinner());
        spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    private ArrayList<CurrentVsChipModel> fakeDataListview(String title) {
        ArrayList<CurrentVsChipModel> fake = new ArrayList<>();
        fake.add(new CurrentVsChipModel(CurrentVsChipAdapter.STYLE_DESC, "30 " + getActivity().getResources().getString(R.string.jul) + " 2016", "", "32 " + getActivity().getResources().getString(R.string.days_ago)));
        fake.add(new CurrentVsChipModel(CurrentVsChipAdapter.STYLE_CONT, title, "-50,000 VND", ""));
        return fake;
    }

    private ArrayList<String> fakeDataSpinner() {

        ArrayList<String> data = new ArrayList<>();
        data.add(getActivity().getResources().getString(R.string.all));
        data.add("30 " + getActivity().getResources().getString(R.string.jul) + " 2016");
        return data;
    }

    private void initDataFromBundle(Bundle savedInstanceState) {
        account = (Account) savedInstanceState.getSerializable(DATA);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.fragmennt_account_details, container, false);
        return viewRoot;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        holder = new AccountViewHolder(getActivity(), view);
        holder.setData(account);
        actionBalance = (ImageView) view.findViewById(R.id.action_button_images_simple_balance);


        spn = (Spinner) view.findViewById(R.id.spiner);
        spn.setAdapter(spinAdapter);

        listview = (ListView) view.findViewById(R.id.listview);
        listview.setAdapter(adapter);
        actionBalance.setVisibility(View.INVISIBLE);
    }

    public void addFragment(Fragment fragment) {
        String backStateName = fragment.getClass().getName();
        String fragmentTag = backStateName;
        FragmentManager mFragmentManager = getChildFragmentManager();
        boolean fragmentPopped = mFragmentManager.popBackStackImmediate(backStateName, 0);
        if (!fragmentPopped && mFragmentManager.findFragmentByTag(fragmentTag) == null) { //fragment not in back stack, create it.
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            transaction.add(R.id.content_fragment, fragment, fragmentTag);
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            transaction.addToBackStack(backStateName);
            transaction.commit();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (isAdded()) {
            ((MainActivity) getActivity()).setUptoolBar(ToolbarTyper.NONE_BACK);
            ((MainActivity) getActivity()).setTitle(getActivity().getResources().getString(R.string.account_details));
        }
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
        }
    }

    @Override
    public void onClick(CurrentVsChipModel chipModel) {
        ((MainActivity) getActivity()).replaceFragment(ReceiptFragment.newInstance(chipModel.getContent(), chipModel.getTitle()));
    }
}