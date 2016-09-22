package com.vnapnic.myvib.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vnapnic.myvib.MainActivity;
import com.vnapnic.myvib.R;
import com.vnapnic.myvib.common.ToolbarTyper;
import com.vnapnic.myvib.customs.LanguageItem2;

import java.util.Locale;

/**
 * Created by vnapnic on 7/14/2016.
 */
public class SettingLanguageFragment extends Fragment implements View.OnClickListener {

    private View viewRoot;
    private MainActivity activity;
    private LanguageItem2 lang1, lang2;
    public static final String LANGUAGE = "language";
    public static final String KEY_LANGUAGE = "key.language";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public static SettingLanguageFragment newInstance() {
        SettingLanguageFragment fragment = new SettingLanguageFragment();
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
        viewRoot = inflater.inflate(R.layout.fragment_setting_language, container, false);
        return viewRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedPreferences = activity.getSharedPreferences(LANGUAGE, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        lang1 = (LanguageItem2) view.findViewById(R.id.lang1);
        lang2 = (LanguageItem2) view.findViewById(R.id.lang2);

        this.lang1.setType(2);
        this.lang1.setStatus(true);
        this.lang2.setType(1);
        this.lang2.setStatus(false);
        this.lang1.setOnClickListener(this);
        this.lang2.setOnClickListener(this);

        if (sharedPreferences.getString(KEY_LANGUAGE, "en").equals("vi")) {
            this.lang1.setStatus(true);
            this.lang2.setStatus(false);
            return;
        }
        this.lang1.setStatus(false);
        this.lang2.setStatus(true);
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
            ((MainActivity) getActivity()).setTitle(getActivity().getResources().getString(R.string.menu_text10));
        }
    }

    public void setLanguage(String lg) {
        Locale locale = new Locale(lg);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getActivity().getResources().updateConfiguration(config, null);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.lang1:
                this.lang2.onSelect(false);
                this.lang1.onSelect(true);
                editor.putString(KEY_LANGUAGE, "vi");
                editor.commit();
                setLanguage("vi");
                getActivity().onBackPressed();
                break;
            case R.id.lang2:
                this.lang2.onSelect(true);
                this.lang1.onSelect(false);
                editor.putString(KEY_LANGUAGE, "en");
                editor.commit();
                setLanguage("en");
                getActivity().onBackPressed();
                break;
        }
    }
}


