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
import com.vnapnic.myvib.fragments.getsecuritycode.GetSecurityCodeFragment;
import com.vnapnic.myvib.model.SecurityCode;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by vnapnic on 7/7/2016.
 */
public class PaymentoldDetailFragment extends Fragment implements View.OnClickListener {

    private View viewRoot;
    private MainActivity activity;
    private LinearLayout viewLayout;
    private ViewDialog viewDialog;
    private static final String MONEY = "key.monney";
    private static final String DESC = "key.desc";
    private String mMony;
    private String mDesc;
    private FontTextView tvAccountFromName, tvAccountFromId, tvAccountOwner, tvAccountToId, tvAmount, tvDesc;

    public static PaymentoldDetailFragment newInstance(String monney, String desc) {
        PaymentoldDetailFragment fragment = new PaymentoldDetailFragment();
        fragment.setArguments(newBundle(monney, desc));
        return fragment;
    }

    private static Bundle newBundle(String monney, String desc) {
        Bundle bundle = new Bundle();
        bundle.putString(MONEY, monney);
        bundle.putString(DESC, desc);
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
        viewDialog = new ViewDialog();
    }

    private void initDataFromBundle(Bundle savedInstanceState) {
        mMony = savedInstanceState.getString(MONEY);
        mDesc = savedInstanceState.getString(DESC);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.fragment_payment_old_detail, container, false);
        return viewRoot;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewLayout = (LinearLayout) view.findViewById(R.id.view_payment);

        viewLayout = (LinearLayout) view.findViewById(R.id.view_payment);
        viewLayout.setVisibility(View.VISIBLE);


        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String date = df.format(Calendar.getInstance().getTime());

        ((FontTextView) view.findViewById(R.id.tvAmount)).setText(mMony);
        ((FontTextView) view.findViewById(R.id.tvCreatedDate)).setText(date);
        ((FontTextView) view.findViewById(R.id.txtPayLater)).setText(date);

        ((FontTextView) view.findViewById(R.id.tvViewAllDetails)).setOnClickListener(this);
        ((FontTextView) view.findViewById(R.id.next_payment)).setOnClickListener(this);

        tvDesc = (FontTextView) view.findViewById(R.id.tvContent);
        tvAccountFromName = (FontTextView) view.findViewById(R.id.tvAccountFromName);
        tvAccountFromId = (FontTextView) view.findViewById(R.id.tvAccountFromId);
        tvAccountOwner = (FontTextView) view.findViewById(R.id.tvAccountOwner);
        tvAccountToId = (FontTextView) view.findViewById(R.id.tvAccountToId);
        tvAmount = (FontTextView) view.findViewById(R.id.tvAmount);

        tvDesc.setText(mDesc);
    }

    public void next() throws Exception {
        SecurityCode contentCode = new SecurityCode();
        contentCode.type = 1;
        contentCode.from = tvAccountFromName.getText().toString();
        contentCode.title = getActivity().getResources().getString(R.string.pay);
        contentCode.monney = tvAmount.getText().toString();
        contentCode.name = tvAccountOwner.getText().toString();
        contentCode.phone = tvAccountToId.getText().toString();
        contentCode.desc = tvDesc.getText().toString().trim();
        ((MainActivity) getActivity()).replaceFragment(GetSecurityCodeFragment.newInstance(contentCode));
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
            ((MainActivity) getActivity()).setTitle(getActivity().getResources().getString(R.string.edit_schedule_payment_review_title));
            ((MainActivity) getActivity()).setRightText(getActivity().getResources().getString(R.string.next));
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

