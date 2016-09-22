package com.vnapnic.myvib.fragments.transactionmanagement;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vnapnic.myvib.MainActivity;
import com.vnapnic.myvib.R;
import com.vnapnic.myvib.common.ToolbarTyper;
import com.vnapnic.myvib.common.ViewDialog;
import com.vnapnic.myvib.customs.FontTextView;
import com.vnapnic.myvib.customs.swipe.SwipeLayout;
import com.vnapnic.myvib.fragments.account.PaymentDetailFragment;
import com.vnapnic.myvib.fragments.account.PaymentEditDetailFragment;
import com.vnapnic.myvib.fragments.payanyone.PayAnyoneFragment;
import com.vnapnic.myvib.fragments.payanyone.PayToBankAccountFragment;
import com.vnapnic.myvib.fragments.tranfer.TransferBetweenYourAccountFragment;
import com.vnapnic.myvib.utils.Logger;

/**
 * Created by Nankai on 9/6/2016.
 */
enum actionSwap {
    TAB_SETUP, CREATE;
}

public class TransactionManagementFragment extends Fragment implements View.OnClickListener, ViewDialog.IAction2Button {

    private SwipeLayout rlTranSactionHistory;
    private int menu = 0;
    private FontTextView menu1, menu2, textBot;
    private RelativeLayout layout_2;
    private ViewDialog viewDialog;

    public static TransactionManagementFragment newInstance() {
        TransactionManagementFragment fragment = new TransactionManagementFragment();
        return fragment;
    }

    private View viewRoot;
    private MainActivity activity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDialog = new ViewDialog();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.fragment_transaction_management, container, false);
        return viewRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tvEdit = (TextView) view.findViewById(R.id.tvEdit);
        rlTranSactionHistory = (SwipeLayout) view.findViewById(R.id.rl_pending_transaction);
        layout_2 = (RelativeLayout) view.findViewById(R.id.actionClick_2);

        rlTranSactionHistory.addDrag(SwipeLayout.DragEdge.Right, rlTranSactionHistory.findViewById(R.id.bottom_wrapper));
        menu1 = (FontTextView) view.findViewById(R.id.menu1);
        menu2 = (FontTextView) view.findViewById(R.id.menu2);
        textBot = (FontTextView) view.findViewById(R.id.text_bot);

        view.findViewById(R.id.actionClick).setOnClickListener(this);
        view.findViewById(R.id.menu_top).setOnClickListener(this);

        layout_2.setOnClickListener(this);
        view.findViewById(R.id.btn_bot).setOnClickListener(this);
        tvEdit.setOnClickListener(this);

        swapLayout(actionSwap.TAB_SETUP);
    }

    public void swapLayout(actionSwap actionSwap) {
        if (actionSwap == com.vnapnic.myvib.fragments.transactionmanagement.actionSwap.TAB_SETUP) {
            menu = 0;
            rlTranSactionHistory.setVisibility(View.VISIBLE);
            layout_2.setVisibility(View.GONE);
            menu1.setText(getActivity().getResources().getString(R.string.pay_anyone));
            menu2.setText(getActivity().getResources().getString(R.string.tab_schedule_transaction));
            textBot.setText(getActivity().getResources().getString(R.string.btn_new_schedule));
        } else {
            menu = 1;
            rlTranSactionHistory.setVisibility(View.GONE);
            layout_2.setVisibility(View.VISIBLE);
            menu1.setText(getActivity().getResources().getString(R.string.tab_schedule_transaction));
            menu2.setText(getActivity().getResources().getString(R.string.pay_anyone));
            textBot.setText(getActivity().getResources().getString(R.string.btn_new_payment));
        }
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
            ((MainActivity) getActivity()).setTitle(getActivity().getResources().getString(R.string.transaction_management_title));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvEdit:
                ((MainActivity) getActivity()).replaceFragment(PaymentEditDetailFragment.newInstance());
                break;
            case R.id.actionClick:
                Logger.d("namIT", "actionClick");
                ((MainActivity) getActivity()).replaceFragment(PaymentDetailFragment.newInstance());
                break;
            case R.id.actionClick_2:
                ((MainActivity) getActivity()).replaceFragment(TransactionScheduleFragment.newInstance());
                break;
            case R.id.menu_top:
                if (menu == 0) {
                    swapLayout(actionSwap.CREATE);
                } else {
                    swapLayout(actionSwap.TAB_SETUP);
                }
                break;
            case R.id.btn_bot:
                if (menu == 0) {
                    viewDialog.showDialogTransaactionmanament(getActivity(), this);
                } else {
                    ((MainActivity) getActivity()).replaceFragment(PayAnyoneFragment.newInstance());
                }
                break;
        }
    }

    @Override
    public void button1() {
        ((MainActivity) getActivity()).replaceFragment(TransferBetweenYourAccountFragment.newInstance());
    }

    @Override
    public void button2() {
        ((MainActivity) getActivity()).replaceFragment(PayToBankAccountFragment.newInstance());
    }
}