package com.vnapnic.myvib.fragments;

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
import com.vnapnic.myvib.adapter.OrdinaryAdapter;
import com.vnapnic.myvib.adapter.ProgressiveAdapter;
import com.vnapnic.myvib.model.InterestRatesList;
import com.vnapnic.myvib.model.OrdinarySavings;
import com.vnapnic.myvib.model.ProgressiveSavings;
import com.vnapnic.myvib.utils.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by vnapnic on 7/4/2016.
 */
public class ProgressiveSavingFragment extends Fragment {

    private View viewRoot;
    private MainActivity activity;
    private ListView listView;
    private ProgressiveAdapter adapter;

    public static ProgressiveSavingFragment newInstance() {
        ProgressiveSavingFragment fragment = new ProgressiveSavingFragment();
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
        viewRoot = inflater.inflate(R.layout.fragment_progressive_saving, container, false);
        return viewRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (ListView) view.findViewById(R.id.listView);
        adapter = new ProgressiveAdapter(activity, data());
        listView.setAdapter(adapter);
    }

    public List<ProgressiveSavings> data() {
        Gson gson = new Gson();
        InterestRatesList response = gson.fromJson(getJson(activity, "Interest_rates.json"), InterestRatesList.class);
        return response.progressiveSavingses;
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

