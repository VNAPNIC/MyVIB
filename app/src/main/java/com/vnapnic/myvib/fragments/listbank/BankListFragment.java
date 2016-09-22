package com.vnapnic.myvib.fragments.listbank;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.vnapnic.myvib.MainActivity;
import com.vnapnic.myvib.R;
import com.vnapnic.myvib.common.ToolbarTyper;
import com.vnapnic.myvib.fragments.listphone.ContactsModel;
import com.vnapnic.myvib.fragments.payanyone.NormalAnyoneFragment;
import com.vnapnic.myvib.fragments.payanyone.TranferAnyoneFragment;
import com.vnapnic.myvib.fragments.selectlist.SlideSelector;
import com.vnapnic.myvib.fragments.topup.TopUpAddNewFragment;
import com.vnapnic.myvib.model.Bank;
import com.vnapnic.myvib.model.BankList;
import com.vnapnic.myvib.utils.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vnapnic on 7/10/2016.
 */
public class BankListFragment extends Fragment implements BankClick {

    public static final int TITLE = 0;
    public static final int CONTENT = 1;
    private View viewRoot;
    private MainActivity activity;
    private ListView listBanks;
    private BanksAdapter adapter;
    private String[] arrTitle;
    private LinearLayout llAreaTitle;
    private ArrayList<BanksModel> arrBanks = new ArrayList<>();

    public static BankListFragment newInstance() {
        BankListFragment fragment = new BankListFragment();
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
        viewRoot = inflater.inflate(R.layout.fragment_bank_list, container, false);
        return viewRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        arrTitle = getActivity().getResources().getStringArray(R.array.arrTitle);
        llAreaTitle = (LinearLayout) view.findViewById(R.id.ll_area_title);

        listBanks = (ListView) view.findViewById(R.id.lv_bank);
        adapter = new BanksAdapter(getActivity(), android.R.layout.simple_list_item_1, arrBanks, this);
        listBanks.setAdapter(adapter);
        ArrayList<Bank> arrayList = new ArrayList<>();
        arrayList.addAll(data());
        arrBanks.addAll(convertData(arrayList));
        adapter.notifyDataSetChanged();

        llAreaTitle.setOrientation(LinearLayout.VERTICAL);
        for (int i = 0; i < arrTitle.length; i++) {
            TextView tvT = new TextView(getActivity());
            tvT.setTextColor(getActivity().getResources().getColor(R.color.black));
            tvT.setText(arrTitle[i]);

            LinearLayout.LayoutParams centerHorizontal = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT, 1);

            centerHorizontal.gravity = Gravity.CENTER_HORIZONTAL;
            tvT.setLayoutParams(centerHorizontal);
            llAreaTitle.addView(tvT);
        }
    }

    public ArrayList<BanksModel> convertData(ArrayList<Bank> arrayList) {
        String firstName = arrayList.get(0).code;
        String title = firstName.substring(0, 1);

        ArrayList<BanksModel> temp = new ArrayList<>();

        temp.add(new BanksModel(null, null, title, null, null, TITLE));

        for (int i = 0; i < arrayList.size(); i++) {
            String firstCharaterOfName = arrayList.get(i).code.substring(0, 1);

            if (firstCharaterOfName.equals(title)) {
                temp.add(new BanksModel(
                        arrayList.get(i).id,
                        arrayList.get(i).bankId,
                        arrayList.get(i).code,
                        arrayList.get(i).name,
                        arrayList.get(i).city,
                        CONTENT));

            } else {
                title = firstCharaterOfName;
                temp.add(new BanksModel(null, null, title, null, null, TITLE));
                temp.add(new BanksModel(
                        arrayList.get(i).id,
                        arrayList.get(i).bankId,
                        arrayList.get(i).code,
                        arrayList.get(i).name,
                        arrayList.get(i).city,
                        CONTENT));
            }
        }

        return temp;
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
            ((MainActivity) getActivity()).setUptoolBar(ToolbarTyper.NONE_BACK);
            ((MainActivity) getActivity()).setTitle(getActivity().getResources().getString(R.string.txtSelectLocalBankTitle));
        }
    }

    @Override
    public void onItemListViewClickListener(BanksModel bank) {
        if (bank == null) {
            Log.e("namit", "onItemListViewClickListener: " + "null");
        }
        Bank bnk = new Bank();
        bnk.id = bank.getId();
        bnk.name = bank.getName();
        bnk.city = bank.getCity();
        bnk.code = bank.getCode();
        bnk.bankId = bank.getBankId();
        switch (bnk.id) {
            case "1":
            case "2":
            case "4":
            case "5":
            case "6":
                ((MainActivity) getActivity()).replaceFragment(NormalAnyoneFragment.newInstance(bnk));
                break;
            default:
                ((MainActivity) getActivity()).replaceFragment(TranferAnyoneFragment.newInstance(bnk));
                break;
        }
    }
}
