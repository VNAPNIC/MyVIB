package com.vnapnic.myvib.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.vnapnic.myvib.MainActivity;
import com.vnapnic.myvib.R;
import com.vnapnic.myvib.customs.FontTextView;

/**
 * Created by vnapnic on 7/9/2016.
 */
public class ImageSlidingFragment2 extends Fragment {

    private View viewRoot;
    private MainActivity activity;
    private ImageView img_slide;
    private FontTextView textView;

    public static ImageSlidingFragment2 newInstance() {
        ImageSlidingFragment2 fragment = new ImageSlidingFragment2();
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
        viewRoot = inflater.inflate(R.layout.fragment_image_sliding, container, false);
        return viewRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        img_slide = (ImageView) view.findViewById(R.id.img_slide);
        textView = (FontTextView) view.findViewById(R.id.textView);
        textView.setText("VIB Classic Credit Card");
        img_slide.setImageResource(R.drawable.card_manager2);
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

