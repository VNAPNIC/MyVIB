package com.vnapnic.myvib.fragments.topup;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.vnapnic.myvib.MainActivity;
import com.vnapnic.myvib.R;
import com.vnapnic.myvib.common.ToolbarTyper;
import com.vnapnic.myvib.common.ViewDialog;
import com.vnapnic.myvib.customs.FontTextView;
import com.vnapnic.myvib.customs.NumberPad;
import com.vnapnic.myvib.fragments.getsecuritycode.MobiePhoneGetSecurityCodeFragment;
import com.vnapnic.myvib.fragments.listphone.ContactsModel;
import com.vnapnic.myvib.fragments.listphone.PhoneSelectListFragment;
import com.vnapnic.myvib.model.SecurityCode;
import com.vnapnic.myvib.utils.Logger;

/**
 * Created by vnapnic on 7/10/2016.
 */
public class TopUpAddNewFragment extends Fragment implements View.OnClickListener, View.OnTouchListener, NumberPad.IKeyCode {
    private View viewRoot;
    private MainActivity activity;
    private LinearLayout content_view;
    private LinearLayout btn_phone_list;
    private LinearLayout btn_instant;
    private FontTextView tvKey3;
    private EditText card_number;
    private EditText nameDisplay;
    private ViewDialog viewDialog;
    private ImageView contacts;
    private static final String DATA = "key.data";
    private ContactsModel contact;

    private NumberPad numberPad;

    private int[] number = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};

    public static TopUpAddNewFragment newInstance(ContactsModel contact) {
        TopUpAddNewFragment fragment = new TopUpAddNewFragment();
        fragment.setArguments(newBundle(contact));
        return fragment;
    }

    private static Bundle newBundle(ContactsModel contact) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(DATA, contact);
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
        contact = (ContactsModel) savedInstanceState.getSerializable(DATA);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.fragment_top_up_add_new, container, false);
        return viewRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewDialog = new ViewDialog();
        numberPad = (NumberPad) view.findViewById(R.id.numberPad);


        content_view = (LinearLayout) view.findViewById(R.id.content_view);
        btn_phone_list = (LinearLayout) view.findViewById(R.id.btn_phone_list);
        btn_instant = (LinearLayout) view.findViewById(R.id.btn_instant);

        View contentView = LayoutInflater.from(activity).inflate(R.layout.item_email_phone, null);
        content_view.removeAllViews();
        tvKey3 = (FontTextView) contentView.findViewById(R.id.tvKey3);
        tvKey3.setText(getActivity().getResources().getString(R.string.save_to_address_book));
        card_number = (EditText) contentView.findViewById(R.id.card_number);
        nameDisplay = (EditText) contentView.findViewById(R.id.nameDisplay);
        contacts = (ImageView) contentView.findViewById(R.id.contacts);
        contacts.setVisibility(View.GONE);
        ((FontTextView) contentView.findViewById(R.id.tvKey1)).setText(getActivity().getResources().getString(R.string.mobile));
        ((FontTextView) contentView.findViewById(R.id.tvKey2)).setText(getActivity().getResources().getString(R.string.name));

        card_number.setHint(getActivity().getResources().getString(R.string.enter_number));
        nameDisplay.setHint(getActivity().getResources().getString(R.string.optional));
        nameDisplay.setEnabled(true);
        content_view.addView(contentView);

        if (contact != null) {
            if (!TextUtils.isEmpty(contact.getContactsName())) {
                nameDisplay.setText(contact.getContactsName());
            }
            if (!TextUtils.isEmpty(contact.getContactsPhoneNumber())) {
                card_number.setText(contact.getContactsPhoneNumber());
            }
        }
//        contacts.setOnClickListener(this);
        btn_phone_list.setOnClickListener(this);
        btn_instant.setOnClickListener(this);
        numberPad.setOnNumpadClickListener(this);

        card_number.setOnTouchListener(this);
        nameDisplay.setOnTouchListener(this);


    }

    public String getCode() {
        return card_number.getText().toString().trim();
    }

    public String getName() {
        if (TextUtils.isEmpty(nameDisplay.getText().toString().trim())) {
            return "";
        } else {
            return nameDisplay.getText().toString().trim();
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
            ((MainActivity) getActivity()).setUptoolBar(ToolbarTyper.NONE_RIGHT_BACK);
            ((MainActivity) getActivity()).setTitle(getActivity().getResources().getString(R.string.top_up_title));
            ((MainActivity) getActivity()).setRightIcon(R.drawable.btn_next_custom);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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

            case R.id.contacts:
//                ((MainActivity) getActivity()).replaceFragment(PhoneSelectListFragment.newInstance(1,0));
                break;
        }
    }

    public void setContactsModel(ContactsModel contactsModel) throws Exception {
        nameDisplay.setText(contactsModel.getContactsName());
        card_number.setText(contactsModel.getContactsPhoneNumber());
    }

    @Override
    public void returnCode(int code) {
        if (code != 10 && code != 11) {
            Logger.d("namit", code + " ... code");
            for (int i = 0; i < 12; i++) {
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
                for (int j = 11; j >= 0; j--) {
                    if (number[j] != -1) {
                        number[j] = -1;
                        break;
                    }
                }
                setChangeValue();
            } else if (code == 11) {
                try {
                    String name = getName();
                    String c = getCode();

//                    if (c.length() <= 12) {
                    ((MainActivity) getActivity()).replaceFragment(TopUpDetailFragment.newInstance(name, c));
//                    } else {
//                        String title = getResources().getString(R.string.mobile_field_invalid_title);
//                        String content = getResources().getString(R.string.mobile_field_invalid);
//                        viewDialog.showDialog1Buton(activity, title, content);
//                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.card_number:
                InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(nameDisplay.getWindowToken(), 0);
                nameDisplay.clearFocus();
                numberPad.m14303b();
                return true;
            case R.id.nameDisplay:
                if (isShow()) {
                    invi();
                    return false;
                } else {
                    return false;
                }
        }
        return false;
    }

    public boolean isShow() {
        return numberPad.getVisibility() == View.VISIBLE;
    }

    public void invi() {
        numberPad.out();
    }
}
