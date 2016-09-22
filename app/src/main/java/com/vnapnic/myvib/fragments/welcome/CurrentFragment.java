package com.vnapnic.myvib.fragments.welcome;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.vnapnic.myvib.MainActivity;
import com.vnapnic.myvib.R;
import com.vnapnic.myvib.customs.FontTextView;
import com.vnapnic.myvib.fragments.ViewCurrentFragment;

/**
 * Created by vnapnic on 7/19/2016.
 */
public class CurrentFragment extends Fragment {

    private View viewRoot;
    private MainActivity activity;

    public static CurrentFragment newInstance() {
        CurrentFragment fragment = new CurrentFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.layout_current, container, false);
        return viewRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FontTextView textView = (FontTextView) view.findViewById(R.id.welcome);
        textView.setText(getActivity().getResources().getString(R.string.welcome));
        final FontTextView action = (FontTextView) view.findViewById(R.id.action);
//        final FontTextView content = (FontTextView) view.findViewById(R.id.content);
//        final LinearLayout accountLayout = (LinearLayout) view.findViewById(R.id.layout_account);
        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (accountLayout.getVisibility() == View.VISIBLE) {
                ((MainActivity) getActivity()).replaceFragment(ViewCurrentFragment.newInstance());
//                } else {
//                    accountLayout.setVisibility(View.VISIBLE);
//                    content.setVisibility(View.GONE);
//                    action.setText(getActivity().getResources().getString(R.string.other_account));
//                    action.setCompoundDrawables(null, null, null, null);
//                }
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }
}

