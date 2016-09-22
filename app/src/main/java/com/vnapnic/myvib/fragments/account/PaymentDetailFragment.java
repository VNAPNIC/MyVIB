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
import com.vnapnic.myvib.common.ToolbarTyper;
import com.vnapnic.myvib.common.ViewDialog;
import com.vnapnic.myvib.customs.FontTextView;

/**
 * Created by vnapnic on 7/7/2016.
 */
public class PaymentDetailFragment extends Fragment implements View.OnClickListener {

    private View viewRoot;
    private MainActivity activity;
    private LinearLayout viewLayout;
    private ViewDialog viewDialog;

    public static PaymentDetailFragment newInstance() {
        PaymentDetailFragment fragment = new PaymentDetailFragment();
        return fragment;
    }

    //  lifecycle
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDialog = new ViewDialog();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.fragment_payment_detail, container, false);
        return viewRoot;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewLayout = (LinearLayout) view.findViewById(R.id.view_payment);

        viewLayout = (LinearLayout) view.findViewById(R.id.view_payment);
        viewLayout.setVisibility(View.VISIBLE);
        ((FontTextView) view.findViewById(R.id.tvViewAllDetails)).setOnClickListener(this);
        ((FontTextView) view.findViewById(R.id.edit_payment)).setOnClickListener(this);
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
            ((MainActivity)getActivity()).setUptoolBar(ToolbarTyper.NONE_RIGHT_BACK);
            ((MainActivity)getActivity()).setTitle(getActivity().getResources().getString(R.string.schedule_payment_detail_title));
            ((MainActivity)getActivity()).setRightText(getActivity().getResources().getString(R.string.next));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvViewAllDetails:
                String title = ": NH TMCP NGOAI THUONG\n(VIETCOMBANK)";
                String content = ": CN NGOAI THUONG VN CN BA DINH\nCty: HA NOI";

                viewDialog.showDialog1Buton(activity, getActivity().getResources().getString(R.string.txtBankName) + title, getActivity().getResources().getString(R.string.txtBranch) + content);
                break;
            case R.id.edit_payment:
                ((MainActivity)getActivity()).replaceFragment(PaymentEditDetailFragment.newInstance());
                break;
        }
    }
}

