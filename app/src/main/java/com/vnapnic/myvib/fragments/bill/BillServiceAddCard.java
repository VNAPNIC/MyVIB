package com.vnapnic.myvib.fragments.bill;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vnapnic.myvib.MainActivity;
import com.vnapnic.myvib.R;
import com.vnapnic.myvib.adapter.SelectBillAdapter;
import com.vnapnic.myvib.common.ToolbarTyper;
import com.vnapnic.myvib.customs.CustomSwitch;
import com.vnapnic.myvib.customs.FontEditext;
import com.vnapnic.myvib.customs.FontTextView;
import com.vnapnic.myvib.customs.NumberPad;
import com.vnapnic.myvib.model.BillSelect;
import com.vnapnic.myvib.utils.Logger;

import java.util.ArrayList;

/**
 * Created by vnapnic on 7/10/2016.
 */
public class BillServiceAddCard extends Fragment implements View.OnTouchListener, NumberPad.IKeyCode, SelectBillAdapter.ActionItem {
    private View viewRoot;
    private MainActivity activity;
    private CustomSwitch sw_biller_book;

    private static final String CODE = "key.code";
    private static final String TITLE = "key.title";
    private static final String ICON = "key.icon";
    private int code;
    private NumberPad numberPad;
    private int[] number = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};

    private FontTextView tvKey1;
    private FontEditext card_number;


    private LinearLayout llviewBillers;
    private ImageView imv_arrow_down, imv_arrow_up;
    private ListView listBiller;
    private ArrayList<BillSelect> arrBill = new ArrayList<>();
    private SelectBillAdapter adapter;
    private ImageView imv_icon;
    private TextView tv_Title;

    private static String title;
    private static int imageResourceId;
    private int imgs;

    public static BillServiceAddCard newInstance(int code, int imageResourceId, String title) {
        BillServiceAddCard fragment = new BillServiceAddCard();
        fragment.setArguments(newBundle(code, title, imageResourceId));

        return fragment;
    }

    private static Bundle newBundle(int code, String title, int imageResourceId) {
        Bundle bundle = new Bundle();
        bundle.putInt(CODE, code);
        bundle.putString(TITLE, title);
        bundle.putInt(ICON, imageResourceId);
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
        code = savedInstanceState.getInt(CODE);
        title = savedInstanceState.getString(TITLE);
        imageResourceId = savedInstanceState.getInt(ICON);

        switch (code) {
            case 0: //Mobile BillPay
                arrBill.clear();
                arrBill.add(new BillSelect(imageResourceId, "Viettel"));
                arrBill.add(new BillSelect(imageResourceId, "VinaPhone"));
                arrBill.add(new BillSelect(imageResourceId, "Mobifone"));
                arrBill.add(new BillSelect(imageResourceId, "VNPT HCM"));
                break;
            case 1: //Homephone BillPay
                arrBill.clear();
                arrBill.add(new BillSelect(imageResourceId, "Viettel"));
                arrBill.add(new BillSelect(imageResourceId, "HomePhone HCM"));
                break;
            case 2: //EVN BillPay
                break;
            case 3: //Water BillPay
                arrBill.clear();
                arrBill.add(new BillSelect(imageResourceId, "Gia Dinh Supply JSC"));
                arrBill.add(new BillSelect(imageResourceId, "Hue"));
                arrBill.add(new BillSelect(imageResourceId, "Phu Hoa Tan"));
                arrBill.add(new BillSelect(imageResourceId, "Tan Hoa"));
                arrBill.add(new BillSelect(imageResourceId, "Thu Duc"));
                arrBill.add(new BillSelect(imageResourceId, "Trung AN"));
                arrBill.add(new BillSelect(imageResourceId, "Nha be"));
                arrBill.add(new BillSelect(imageResourceId, "Cho Lon"));
                arrBill.add(new BillSelect(imageResourceId, "Ben Thanh"));
                break;
            case 4: //PSTN BillPay
                arrBill.clear();
                arrBill.add(new BillSelect(imageResourceId, "Viettel"));
                break;
            case 5: //ADSL BillPay
                arrBill.clear();
                arrBill.add(new BillSelect(imageResourceId, "Viettel"));
                arrBill.add(new BillSelect(imageResourceId, "FPT Telecom"));
                arrBill.add(new BillSelect(imageResourceId, "VNPT HCM"));
                break;
            case 6: //Airline ticket BillPlay
                arrBill.clear();
                arrBill.add(new BillSelect(imageResourceId, "Jetstar Pacific"));
                arrBill.add(new BillSelect(imageResourceId, "Dat ve may bay qua vnPay"));
                break;
            case 7: //Insurance
                arrBill.clear();
                arrBill.add(new BillSelect(imageResourceId, "BIDV insurance Corporation (BIC)"));
                break;
            case 8: //Telecommunications
                arrBill.clear();
                arrBill.add(new BillSelect(imageResourceId, "Hanoi Telecom"));
                break;
            case 9: //Credit Card/Consumer Installment Loan
                arrBill.clear();
                arrBill.add(new BillSelect(imageResourceId, "Prudential Finance"));
                arrBill.add(new BillSelect(imageResourceId, "HomeCredit PPF VN"));
                break;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.fragment_add_card, container, false);
        return viewRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sw_biller_book = (CustomSwitch) view.findViewById(R.id.sw_biller_book);
        card_number = (FontEditext) view.findViewById(R.id.card_number);
        numberPad = (NumberPad) view.findViewById(R.id.numberPad);
        tvKey1 = (FontTextView) view.findViewById(R.id.tvKey1);

        llviewBillers = (LinearLayout) view.findViewById(R.id.ll_list_biller);
        imv_arrow_down = (ImageView) view.findViewById(R.id.imv_down_arrow);
        imv_arrow_up = (ImageView) view.findViewById(R.id.imv_up_arrow);
        listBiller = (ListView) view.findViewById(R.id.lv_list_bill);
        adapter = new SelectBillAdapter(activity, android.R.layout.simple_list_item_1, arrBill, this);
        listBiller.setAdapter(adapter);
        tv_Title = (TextView) view.findViewById(R.id.tv_title);
        imv_icon = (ImageView) view.findViewById(R.id.imv_icon);

        card_number.setOnTouchListener(this);
        numberPad.setOnNumpadClickListener(this);
        if (arrBill.size() <= 0) {
            ((RelativeLayout) view.findViewById(R.id.top_layout)).setVisibility(View.GONE);
        } else {
            ((RelativeLayout) view.findViewById(R.id.top_layout)).setVisibility(View.VISIBLE);
            ((RelativeLayout) view.findViewById(R.id.top_layout)).setOnTouchListener(this);
            imv_arrow_up.setOnTouchListener(this);
        }
        try {

            switch (code) {
                case 0: //Mobile BillPay
                    setTextCard(getStr(R.string.so_thue_bao), getStr(R.string.enter_mobile_number));
                    break;
                case 1: //Homephone BillPay
                    setTextCard(getStr(R.string.so_thue_bao), getStr(R.string.enter_mobile_number));
                    break;
                case 2: //EVN BillPay
                    setTextCard(getStr(R.string.customer_id), getStr(R.string.enter_customer_id));
                    break;
                case 3: //Water BillPay
                    setTextCard(getStr(R.string.customer_id), getStr(R.string.enter_customer_id));
                    break;
                case 4: //PSTN BillPay
                    setTextCard(getStr(R.string.pstn_code), getStr(R.string.enter_pstn_code));
                    onChangeView(arrBill.get(0));
                    break;
                case 5: //ADSL BillPay
                    setTextCard(getStr(R.string.adsl_code), getStr(R.string.enter_adsl_code));
                    break;
                case 6: //Airline ticket BillPlay
                    setTextCard(getStr(R.string.reservation_code), getStr(R.string.enter_reservation_code));
                    break;
                case 7: //Insurance
                    setTextCard(getStr(R.string.customer_id), getStr(R.string.enter_customer_id));
                    onChangeView(arrBill.get(0));
                    break;
                case 8: //Telecommunications
                    setTextCard(getStr(R.string.so_thue_bao), getStr(R.string.enter_mobile_number));
                    onChangeView(arrBill.get(0));
                    break;
                case 9: //Credit Card/Consumer Installment Loan
                    setTextCard(getStr(R.string.reference_no), getStr(R.string.enter_reference_no));
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setTextCard(String title, String hint) {
        tvKey1.setText(title);
        card_number.setHint(hint);
    }

    private String getStr(int idr) {
        return getActivity().getResources().getString(idr);
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
            ((MainActivity) getActivity()).setTitle(getActivity().getResources().getString(R.string.pay_a_bill));
            activity.setRightIcon(R.drawable.btn_next_custom);
        }
    }

    public boolean isSave() {
        if (card_number.getText().toString().trim().length() >= 1) {
            return true;
        } else {
            return false;
        }
    }

    public void next() {
        if (isSave()) {
            BillSelect code = getBill();
            String phone = getPhone();
            ((MainActivity) getActivity()).replaceFragment(BillServiceCardDetail.newInstance(code, phone));
        } else {
            //TODO
        }
    }

    public BillSelect getBill() {
        BillSelect billSelect = new BillSelect(imageResourceId, title);
        return billSelect;
    }

    public String getPhone() {
        return card_number.getText().toString().trim();
    }

    @Override
    public void returnCode(int code) {
        if (code != 10 && code != 11) {
            Logger.d("namit", code + " ... code");
            for (int i = 0; i < 11; i++) {
                if (number[i] == -1) {
                    Logger.d("namit", i + "... number = " + code);
                    number[i] = code;
                    break;
                }
            }
            setChangeValue();
        } else {
            //TODO
            Logger.d("namit", code + " ... code event");
            if (code == 10) {
                for (int j = 10; j >= 0; j--) {
                    if (number[j] != -1) {
                        number[j] = -1;
                        break;
                    }
                }
                setChangeValue();
            } else if (code == 11) {
                next();
            }
        }
    }

    private void setChangeValue() {
        String str = "";
        for (int i = 0; i < number.length; i++) {
            if (number[i] != -1) {
                str += number[i];
            }
        }
        card_number.setText(str);
        if (card_number.getText().toString().trim().length() >= 1) {
            sw_biller_book.setStatus(true);
        } else {
            sw_biller_book.setStatus(false);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.card_number:
                numberPad.m14303b();
                return false;
            case R.id.top_layout:
                openListNetwork();
                return false;
            case R.id.imv_up_arrow:
                closeListNetwork();
                return false;
        }
        return false;
    }

    public boolean isShow() {
        return numberPad.getVisibility() == View.VISIBLE;
    }

    public void invi() {
        numberPad.out();
    }

    private void openListNetwork() {
        if (imv_arrow_down.getVisibility() == View.VISIBLE) {
            llviewBillers.setVisibility(View.VISIBLE);
            imv_arrow_down.setVisibility(View.INVISIBLE);
            Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_down);
            llviewBillers.startAnimation(animation);
        }
    }

    private void closeListNetwork() {
        if (imv_arrow_up.getVisibility() == View.VISIBLE) {
            llviewBillers.setVisibility(View.INVISIBLE);
            imv_arrow_down.setVisibility(View.VISIBLE);
            Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_up);
            llviewBillers.startAnimation(animation);
        }
    }

    public void onChangeView(BillSelect billSelect) {
        imv_icon.setVisibility(View.VISIBLE);
        imv_icon.setImageResource(imageResourceId);
        tv_Title.setCompoundDrawables(null, null, null, null);
        tv_Title.setText(billSelect.getTitle());
        title = billSelect.getTitle();
        closeListNetwork();
    }

    @Override
    public void onClickItem(BillSelect billSelect) {
        onChangeView(billSelect);
    }
}
