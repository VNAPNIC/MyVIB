package com.vnapnic.myvib.fragments.bill;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.vnapnic.myvib.MainActivity;
import com.vnapnic.myvib.R;
import com.vnapnic.myvib.adapter.EsavingAdapter;
import com.vnapnic.myvib.model.Account;
import com.vnapnic.myvib.model.Bill;
import com.vnapnic.myvib.model.ESavings;
import com.vnapnic.myvib.model.InterestRatesList;
import com.vnapnic.myvib.utils.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by vnapn on 8/5/2016.
 */
public class PayOfCreateCardDetailFragment extends Fragment {

    private View viewRoot;
    private MainActivity activity;
    private ListView listView;
    private EsavingAdapter adapter;
    private static final String DATA = "key.data";
    private Bill item;

    public static PayOfCreateCardDetailFragment newInstance(Bill item) {
        PayOfCreateCardDetailFragment fragment = new PayOfCreateCardDetailFragment();
        fragment.setArguments(newBundle(item));
        return fragment;
    }

    private static Bundle newBundle(Bill item) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(DATA, item);
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
        item = (Bill) savedInstanceState.getSerializable(DATA);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.fragment_off_create_card_detail, container, false);
        return viewRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (ListView) view.findViewById(R.id.listView);
        adapter = new EsavingAdapter(activity, data());
        listView.setAdapter(adapter);
    }


    public List<ESavings> data() {
        Gson gson = new Gson();
        InterestRatesList response = gson.fromJson(getJson(activity, "Interest_rates.json"), InterestRatesList.class);
        return response.eSavingses;
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
    }
}

