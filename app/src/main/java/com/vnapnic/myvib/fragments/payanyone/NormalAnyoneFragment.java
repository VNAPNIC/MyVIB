package com.vnapnic.myvib.fragments.payanyone;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.vnapnic.myvib.MainActivity;
import com.vnapnic.myvib.R;
import com.vnapnic.myvib.common.ToolbarTyper;
import com.vnapnic.myvib.customs.FontEditext;
import com.vnapnic.myvib.customs.FontTextView;
import com.vnapnic.myvib.fragments.DialogCustom;
import com.vnapnic.myvib.model.Bank;
import com.vnapnic.myvib.model.BankList;
import com.vnapnic.myvib.model.Bill;
import com.vnapnic.myvib.model.PayEnd;
import com.vnapnic.myvib.utils.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vnapnic on 7/24/2016.
 */
public class NormalAnyoneFragment extends Fragment implements View.OnClickListener, DialogCustom.onActionDialog {

    private View viewRoot;
    private MainActivity activity;
    private static final String DATA = "key.data";
    private Bank bank;
    private FontTextView tvCity, tvBankName, title_bank;
    private FontEditext bank_id, bank_account;
    private List<Bank> bankList;
    private String city[];
    private List<String> branch;


    public static NormalAnyoneFragment newInstance(Bank bank) {
        NormalAnyoneFragment fragment = new NormalAnyoneFragment();
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
        viewRoot = inflater.inflate(R.layout.fragment_bak_list_detail, container, false);
        return viewRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bank_id = (FontEditext) view.findViewById(R.id.bank_id);
        bank_account = (FontEditext) view.findViewById(R.id.bank_account);

        title_bank = (FontTextView) view.findViewById(R.id.title_bank);
        title_bank.setText(Html.fromHtml(getActivity().getResources().getString(R.string.bank) + bank.name + " (" + bank.code + ")"));
        tvCity = (FontTextView) view.findViewById(R.id.tvCity);
        tvBankName = (FontTextView) view.findViewById(R.id.tvBankName);
        ((RelativeLayout) view.findViewById(R.id.choise_city)).setOnClickListener(this);
        ((RelativeLayout) view.findViewById(R.id.choise_branch)).setOnClickListener(this);
        city = new String[3];
        city[0] = "HÀ NỘI";
        city[1] = "ĐÀ NẴNG";
        city[2] = "TP HỒ CHÍ MINH";

        bankList = data();
        branch = new ArrayList<>();
        for (int i = 0; i < bankList.size(); i++) {
            if (bank.code.equals(bankList.get(i).code)) {
                branch.add(bankList.get(i).branch);
            }
        }
    }

    public void next(){
        Bill bill = getPay();
        ((MainActivity)getActivity()).replaceFragment(PayAnyoneDetailFragment.newInstance(bill));
    }

    public List<Bank> data() {
        Gson gson = new Gson();
        BankList response = gson.fromJson(getJson(activity, "bank.json"), BankList.class);
        return response.bankList;
    }

    public String getJson(Context context, String json) {
        String jsonString = parseFileToString(context, json);
        return jsonString;
    }

    public String parseFileToString(Context context, String filename) {
        try {
            InputStream stream = context.getAssets().open(filename);
            int size = stream.available();

            byte[] bytes = new byte[size];
            stream.read(bytes);
            stream.close();

            return new String(bytes);

        } catch (IOException e) {
            Logger.d("GuiFormData", "IOException: " + e.getMessage());
        }
        return null;
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
            ((MainActivity) getActivity()).setUptoolBar(ToolbarTyper.NONE_RIGHT_BACK);
            ((MainActivity) getActivity()).setTitle(getActivity().getResources().getString(R.string.pay_someone_new_title));
            ((MainActivity) getActivity()).setRightText(getActivity().getResources().getString(R.string.next));
        }
    }

    public Bill getPay() {
        Bill bill = new Bill();
        bill.phone = bank_id.getText().toString();
        bill.city = tvCity.getText().toString();
        bill.title = bank_account.getText().toString();
        bill.address = tvBankName.getText().toString();
        bill.bank = title_bank.getText().toString();
        return bill;
    }

    @Override
    public void onClick(View v) {
        DialogCustom dialogCustom;
        switch (v.getId()) {
            case R.id.choise_city:
                dialogCustom = new DialogCustom(getActivity(), city, this, 0);
                dialogCustom.show();
                break;
            case R.id.choise_branch:
                String[] data = new String[branch.size()];
                for (int i = 0; i < data.length; i++) {
                    data[i] = branch.get(i).toString();
                }
                dialogCustom = new DialogCustom(getActivity(), data, this, 1);
                dialogCustom.show();
                break;
        }
    }

    @Override
    public void onAction(String item, int index) {
        if (TextUtils.isEmpty(item))
            return;
        if (index == 0) {
            tvCity.setText(item);
        } else {
            tvBankName.setText(item);
        }
    }
}

