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
import android.widget.TextView;

import com.vnapnic.myvib.MainActivity;
import com.vnapnic.myvib.R;
import com.vnapnic.myvib.adapter.CurrentVsChipAdapter;
import com.vnapnic.myvib.common.ToolbarTyper;
import com.vnapnic.myvib.customs.AccountViewHolder;
import com.vnapnic.myvib.customs.swipe.SwipeLayout;
import com.vnapnic.myvib.fragments.CardManagementFragment;
import com.vnapnic.myvib.fragments.receipt.ReceiptFragment;
import com.vnapnic.myvib.fragments.bill.BillFragment;
import com.vnapnic.myvib.fragments.payanyone.PayAnyoneFragment;
import com.vnapnic.myvib.fragments.tranfer.TransferBetweenYourAccountFragment;
import com.vnapnic.myvib.model.Account;
import com.vnapnic.myvib.model.CurrentVsChipModel;
import com.vnapnic.myvib.utils.Logger;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountCurrentFragment extends Fragment implements View.OnClickListener, CurrentVsChipAdapter.IActionAdapter {

    private static final String DATA = "key.data";
    private Account account;
    private AccountViewHolder holder;
    private TextView txtTransaction, txtPending;
    private LinearLayout llPendingTransation;
    private SwipeLayout rlTranSactionHistory;
    private ImageView actionBalance;

    private LinearLayout btn_bill;
    private LinearLayout btn_anyone;
    private LinearLayout transfer_account;


    private ListView listview;
    private CurrentVsChipAdapter adapter;
    private ArrayList<CurrentVsChipModel> datas = new ArrayList<>();
    private Spinner spn;
    private ArrayAdapter<String> spinAdapter;

    public static AccountCurrentFragment newInstance(Account account) {
        AccountCurrentFragment fragment = new AccountCurrentFragment();
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
        datas.addAll(fakeDataListview());
        adapter = new CurrentVsChipAdapter(getActivity(), R.layout.list_item, datas);
        adapter.setActionAdapter(this);
        spinAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, fakeDataSpinner());
        spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    private void initDataFromBundle(Bundle savedInstanceState) {
        account = (Account) savedInstanceState.getSerializable(DATA);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current, container, false);
        btn_bill = (LinearLayout) view.findViewById(R.id.btn_bill);
        btn_anyone = (LinearLayout) view.findViewById(R.id.btn_anyone);
        transfer_account = (LinearLayout) view.findViewById(R.id.transfer_account);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        holder = new AccountViewHolder(getActivity(), view);
        holder.setData(account);

        actionBalance = (ImageView) view.findViewById(R.id.action_button_images_simple_balance);
        actionBalance.setImageResource(R.drawable.icon_card_manager);

        llPendingTransation = (LinearLayout) view.findViewById(R.id.ll_transaction_history);

        TextView tvEdit = (TextView) view.findViewById(R.id.tvEdit);

        rlTranSactionHistory = (SwipeLayout) view.findViewById(R.id.rl_pending_transaction);
        rlTranSactionHistory.addDrag(SwipeLayout.DragEdge.Right, rlTranSactionHistory.findViewById(R.id.bottom_wrapper));


        txtTransaction = (TextView) view.findViewById(R.id.txt_transaction_history);
        txtPending = (TextView) view.findViewById(R.id.txt_pending_transaction);

        spn = (Spinner) view.findViewById(R.id.spiner);
        spn.setAdapter(spinAdapter);

        listview = (ListView) view.findViewById(R.id.listview);
        listview.setAdapter(adapter);

        tvEdit.setOnClickListener(this);
        txtTransaction.setOnClickListener(this);
        txtPending.setOnClickListener(this);
        actionBalance.setOnClickListener(this);
        btn_bill.setOnClickListener(this);
        btn_anyone.setOnClickListener(this);
        transfer_account.setOnClickListener(this);

        view.findViewById(R.id.actionClick).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_pending_transaction:
                rlTranSactionHistory.setVisibility(View.VISIBLE);
                llPendingTransation.setVisibility(View.GONE);
                txtTransaction.setBackgroundColor(getActivity().getResources().getColor(R.color.black_transparent));
                txtPending.setBackgroundColor(getActivity().getResources().getColor(R.color.bg_white_press));
                txtPending.setTextColor(getActivity().getResources().getColor(R.color.black));
                txtTransaction.setTextColor(getActivity().getResources().getColor(R.color.bg_white_press));
                break;
            case R.id.txt_transaction_history:
                llPendingTransation.setVisibility(View.VISIBLE);
                rlTranSactionHistory.setVisibility(View.GONE);
                txtTransaction.setBackgroundColor(getActivity().getResources().getColor(R.color.bg_white_press));
                txtPending.setBackgroundColor(getActivity().getResources().getColor(R.color.black_transparent));
                txtPending.setTextColor(getActivity().getResources().getColor(R.color.bg_white_press));
                txtTransaction.setTextColor(getActivity().getResources().getColor(R.color.black));
                break;
            case R.id.action_button_images_simple_balance:
                ((MainActivity) getActivity()).replaceFragment(CardManagementFragment.newInstance());
                break;
            case R.id.tvEdit:
                ((MainActivity) getActivity()).replaceFragment(PaymentEditDetailFragment.newInstance());
                break;
            case R.id.btn_bill:
                ((MainActivity) getActivity()).replaceFragment(BillFragment.newInstance());
                break;
            case R.id.btn_anyone:
                ((MainActivity) getActivity()).replaceFragment(PayAnyoneFragment.newInstance());
                break;
            case R.id.transfer_account:
                ((MainActivity) getActivity()).replaceFragment(TransferBetweenYourAccountFragment.newInstance());
                break;
            case R.id.actionClick:
                Logger.d("namIT", "actionClick");
                ((MainActivity) getActivity()).replaceFragment(PaymentDetailFragment.newInstance());
                break;
        }
    }

    private ArrayList<CurrentVsChipModel> fakeDataListview() {
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
