package com.vnapnic.myvib.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ListView;

import com.google.gson.Gson;
import com.vnapnic.myvib.MainActivity;
import com.vnapnic.myvib.R;
import com.vnapnic.myvib.adapter.ExchangeRateAdapter;
import com.vnapnic.myvib.common.ToolbarTyper;
import com.vnapnic.myvib.customs.FontTextView;
import com.vnapnic.myvib.model.AtmLocator;
import com.vnapnic.myvib.model.EchangeRateList;
import com.vnapnic.myvib.model.ExchangeRate;
import com.vnapnic.myvib.utils.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vnapnic on 7/4/2016.
 */
public class ExchangeRateFragment extends Fragment {

    private View viewRoot;
    private MainActivity activity;
    private ListView listView;
    private ExchangeRateAdapter adapter;

    public static ExchangeRateFragment newInstance() {
        ExchangeRateFragment fragment = new ExchangeRateFragment();
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
        viewRoot = inflater.inflate(R.layout.fragment_exchange_rate, container, false);
        return viewRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_exchange_rate);

        FontTextView txtTitle = (FontTextView) dialog.findViewById(R.id.dialog_title);
        txtTitle.setText(getActivity().getResources().getString(R.string.thong_bao));
        FontTextView content = (FontTextView) dialog.findViewById(R.id.dialog_message);
        content.setText(getActivity().getResources().getString(R.string.thong_bao_desc));

        FontTextView dialogButton = (FontTextView) dialog.findViewById(R.id.dialog_btn1);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String page = "com.vn.vib.mobileapp";
                Intent isCheckIntent = activity.getPackageManager().getLaunchIntentForPackage(page);
                if (isCheckIntent != null) {
                    isCheckIntent.addCategory(Intent.CATEGORY_LAUNCHER);
                    activity.startActivity(isCheckIntent);
                } else {
                    try {
                        activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + page)));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + page)));
                    }
                }
                dialog.dismiss();
            }
        });

        FontTextView dialogButton2 = (FontTextView) dialog.findViewById(R.id.dialog_btn2);
        dialogButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });

        dialog.show();

        listView = (ListView) view.findViewById(R.id.listView);
        adapter = new ExchangeRateAdapter(activity, data());
        listView.setAdapter(adapter);
    }

    public List<ExchangeRate> data() {
        Gson gson = new Gson();
        EchangeRateList response = gson.fromJson(getJson(activity, "exchange_rate.json"), EchangeRateList.class);
        Logger.d("namit", ".........JSON MAP size: " + response.exchangeRates.size());
        return response.exchangeRates;
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
            ((MainActivity) getActivity()).setTitle(getActivity().getResources().getString(R.string.exchange_rates));
        }
    }
}

