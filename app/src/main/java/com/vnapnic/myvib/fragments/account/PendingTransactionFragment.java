package com.vnapnic.myvib.fragments.account;


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
import com.vnapnic.myvib.customs.swipe.SwipeLayout;

public class PendingTransactionFragment extends Fragment implements View.OnClickListener {

    private View viewRoot;
    private MainActivity activity;
    private LinearLayout linearLayout;

    public static PendingTransactionFragment newInstance() {
        PendingTransactionFragment fragment = new PendingTransactionFragment();
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
        viewRoot = inflater.inflate(R.layout.fragment_pending_transaction, container, false);
        return viewRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        linearLayout = (LinearLayout) view.findViewById(R.id.actionClick);
        linearLayout.setOnClickListener(this);
        SwipeLayout swipeLayout = (SwipeLayout) view.findViewById(R.id.swipe);
        swipeLayout.addDrag(SwipeLayout.DragEdge.Right, swipeLayout.findViewById(R.id.bottom_wrapper));
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.actionClick:
                ((MainActivity)getActivity()).replaceFragment(PaymentDetailFragment.newInstance());
                break;
        }
    }
}
