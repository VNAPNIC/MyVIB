package com.vnapnic.myvib.fragments.topup;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vnapnic.myvib.MainActivity;
import com.vnapnic.myvib.R;
import com.vnapnic.myvib.common.ToolbarTyper;
import com.vnapnic.myvib.fragments.getsecuritycode.MobiePhoneGetSecurityCodeFragment;
import com.vnapnic.myvib.fragments.listphone.ContactsModel;
import com.vnapnic.myvib.fragments.listphone.PhoneSelectListFragment;
import com.vnapnic.myvib.model.Bill;
import com.vnapnic.myvib.model.SecurityCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vnapnic on 7/10/2016.
 */
public class TopUpFragment extends Fragment implements View.OnClickListener {
    private View viewRoot;
    private MainActivity activity;
    private LinearLayout content_view;
    private LinearLayout btn_add_new;
    private LinearLayout btn_phone_list;
    private LinearLayout btn_instant;

    public static TopUpFragment newInstance() {
        TopUpFragment fragment = new TopUpFragment();
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
        viewRoot = inflater.inflate(R.layout.fragment_top_up, container, false);
        return viewRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        content_view = (LinearLayout) view.findViewById(R.id.content_view);
        btn_add_new = (LinearLayout) view.findViewById(R.id.btn_add_new);
        btn_phone_list = (LinearLayout) view.findViewById(R.id.btn_phone_list);
        btn_instant = (LinearLayout) view.findViewById(R.id.btn_instant);

        for (Bill item : initBill()) {
            View v = LayoutInflater.from(activity).inflate(R.layout.item_anyone, null);
            v.setId(Integer.parseInt(item.id));
            ((ImageView) v.findViewById(R.id.icon)).setImageResource(item.icon);
            ((TextView) v.findViewById(R.id.title)).setText(item.title);
            ((TextView) v.findViewById(R.id.phone)).setText(item.phone);
            v.setOnClickListener(this);
            content_view.addView(v);
        }

        btn_add_new.setOnClickListener(this);
        btn_phone_list.setOnClickListener(this);
        btn_instant.setOnClickListener(this);
    }

    private List<Bill> initBill() {
        List<Bill> billList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Bill bill = new Bill();
            if (i == 0) {
                bill.id = i+"";
                bill.icon = R.drawable.top_up_icon_yellow;
                bill.title = "Nguyễn Văn A";
                bill.phone = "096319092" + i;
            } else if (i == 1) {
                bill.id = i+"";
                bill.icon = R.drawable.top_up_icon_yellow;
                bill.title = "Nguyễn Văn B";
                bill.phone = "091322939" + i;
            } else {
                bill.id = i+"";
                bill.icon = R.drawable.top_up_icon_yellow;
                bill.title = "Nguyễn Văn C";
                bill.phone = "097273942" + i;
            }
            billList.add(bill);
        }
        return billList;
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
            ((MainActivity) getActivity()).setTitle(getActivity().getResources().getString(R.string.top_up_title));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_new:
                ContactsModel contact = new ContactsModel();
                ((MainActivity) getActivity()).replaceFragment(TopUpAddNewFragment.newInstance(contact));
                break;
            case R.id.btn_phone_list:
                ((MainActivity) getActivity()).replaceFragment(PhoneSelectListFragment.newInstance(0, 0));
                break;
            case R.id.btn_instant:
                SecurityCode content = new SecurityCode();
                content.type = 2;
                content.desc = "";
                content.from = "0923190026";
                content.title = "";
                content.monney = "109,864 VND";
                content.name = "NGUYEN VAN A";
                content.phone = "0963190920";
                ((MainActivity) getActivity()).replaceFragment(MobiePhoneGetSecurityCodeFragment.newInstance(content));
                break;
            case 0:
            case 1:
            case 2:
                String name = ((TextView) v.findViewById(R.id.title)).getText().toString().trim();
                String c = ((TextView) v.findViewById(R.id.phone)).getText().toString().trim();
                ((MainActivity) getActivity()).replaceFragment(TopUpDetailFragment.newInstance(name, c));
                break;
        }
    }
}
