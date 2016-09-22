package com.vnapnic.myvib.fragments.receipt;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vnapnic.myvib.MainActivity;
import com.vnapnic.myvib.R;
import com.vnapnic.myvib.common.ToolbarTyper;
import com.vnapnic.myvib.customs.FontTextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by vnapnic on 7/7/2016.
 */
public class ReceiptFragment extends Fragment {

    private View viewRoot;
    private MainActivity activity;
    private static final String MONNEY = "key.money";
    private static final String HOA_DON = "key.hoa.don";
    private static final String TEN_GD = "key.ten.gd";
    private static final String SO_GD = "key.so.gd";
    private static final String DESCRIPTION = "key.description";
    private static final String IS_DATE = "key.is.date";

    private static final String NO = "key.no";

    private String monney;
    private String tengd;
    private String sogd;
    private String desc;
    private boolean isDate;
    private int no;

    public static ReceiptFragment newInstance(String money, String desc) {
        ReceiptFragment fragment = new ReceiptFragment();
        fragment.setArguments(newBundle(money, desc));
        return fragment;
    }

    private static Bundle newBundle(String money, String desc) {
        Bundle bundle = new Bundle();
        bundle.putString(MONNEY, money);
        bundle.putString(DESCRIPTION, desc);
        return bundle;
    }


    public static ReceiptFragment newInstance(String money, String nameGD, String soGD, String desc, boolean isDate, int no) {
        ReceiptFragment fragment = new ReceiptFragment();
        fragment.setArguments(newBundle(money, nameGD, soGD, desc, isDate, no));
        return fragment;
    }

    private static Bundle newBundle(String money, String nameGD, String soGD, String desc, boolean isDate, int no) {
        Bundle bundle = new Bundle();
        bundle.putString(MONNEY, money);
        bundle.putString(TEN_GD, nameGD);
        bundle.putString(SO_GD, soGD);
        bundle.putString(DESCRIPTION, desc);
        bundle.putBoolean(IS_DATE, isDate);
        bundle.putInt(NO, no);
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
        monney = savedInstanceState.getString(MONNEY, "");
        tengd = savedInstanceState.getString(TEN_GD, "");
        sogd = savedInstanceState.getString(SO_GD, "");
        desc = savedInstanceState.getString(DESCRIPTION, "");
        isDate = savedInstanceState.getBoolean(IS_DATE, false);
        no = savedInstanceState.getInt(NO, 0);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.fragment_receipt, container, false);
        return viewRoot;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((FontTextView) view.findViewById(R.id.tv_amount)).setText(monney);
        ((FontTextView) view.findViewById(R.id.tv_description)).setText(desc);
        if (no == 1) {
            ((FontTextView) view.findViewById(R.id.tvReceiptType)).setText(getActivity().getResources().getString(R.string.transaction_success));
            ((FontTextView) view.findViewById(R.id.tv_from_name)).setText("TK " + getActivity().getResources().getString(R.string.pay3));
            ((FontTextView) view.findViewById(R.id.tv_to_name)).setText(tengd);
            ((FontTextView) view.findViewById(R.id.tv_to_id)).setText("DG: 23390102");

            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            String date = df.format(Calendar.getInstance().getTime());
            ((FontTextView) view.findViewById(R.id.tv_date)).setText(date);

            DateFormat dfTZ = DateFormat.getTimeInstance();
            dfTZ.setTimeZone(TimeZone.getTimeZone("gmt"));
            ((FontTextView) view.findViewById(R.id.tv_country)).setText(dfTZ.getTimeZone().getDisplayName());
        } else {
            ((FontTextView) view.findViewById(R.id.tvReceiptType)).setText(String.format(getActivity().getResources().getString(R.string.receipt_title), getActivity().getResources().getString(R.string.view_receipt)));
        }
    }

    public int getNo() {
        return no;
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
            ((MainActivity)getActivity()).setTitle(getActivity().getResources().getString(R.string.receipt));
            ((MainActivity)getActivity()).setRightText(getActivity().getResources().getString(R.string.done));
        }
    }
}

