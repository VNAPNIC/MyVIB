package com.vnapnic.myvib.fragments.account;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.vnapnic.myvib.MainActivity;
import com.vnapnic.myvib.R;
import com.vnapnic.myvib.common.ToolbarTyper;
import com.vnapnic.myvib.common.ViewDialog;
import com.vnapnic.myvib.customs.FontTextView;

/**
 * Created by vnapnic on 7/7/2016.
 */
public class PaymentEditDetailFragment extends Fragment implements View.OnClickListener {

    private View viewRoot;
    private MainActivity activity;
    private LinearLayout viewLayout;
    private ViewDialog viewDialog;
    private EditText etAmount, etContent;

    public static PaymentEditDetailFragment newInstance() {
        PaymentEditDetailFragment fragment = new PaymentEditDetailFragment();
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
        viewRoot = inflater.inflate(R.layout.fragment_payment_detail_edit, container, false);
        return viewRoot;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewLayout = (LinearLayout) view.findViewById(R.id.view_payment);
        viewLayout = (LinearLayout) view.findViewById(R.id.view_payment);
        ((FontTextView) view.findViewById(R.id.tvViewAllDetails)).setOnClickListener(this);
        ((FontTextView) view.findViewById(R.id.next_payment)).setOnClickListener(this);
        etAmount = (EditText) view.findViewById(R.id.etAmount);
        etContent = (EditText) view.findViewById(R.id.etContent);
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
            ((MainActivity) getActivity()).setTitle(getActivity().getResources().getString(R.string.edit_schedule_payment_title));
            ((MainActivity) getActivity()).setRightText(getActivity().getResources().getString(R.string.next));
        }
    }

    public void next() throws Exception {
        String strMonney = etAmount.getText().toString().trim();
        String strDesc = etContent.getText().toString().trim();
        ((MainActivity) getActivity()).replaceFragment(PaymentoldDetailFragment.newInstance(strMonney, strDesc));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvViewAllDetails:
                String title = ": NH TMCP NGOAI THUONG\n(VIETCOMBANK)";
                String content = ": CN NGOAI THUONG VN CN BA DINH\nCty: HA NOI";

                viewDialog.showDialog1Buton(activity, getActivity().getResources().getString(R.string.txtBankName) + title, getActivity().getResources().getString(R.string.txtBranch) + content);
                break;
            case R.id.next_payment:
                try {
                    next();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}

