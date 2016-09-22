package com.vnapnic.myvib.fragments.choice;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vnapnic.myvib.MainActivity;
import com.vnapnic.myvib.R;
import com.vnapnic.myvib.common.ToolbarTyper;
import com.vnapnic.myvib.customs.FontButton;
import com.vnapnic.myvib.customs.FontEditext;

/**
 * Created by vnapnic on 7/10/2016.
 */
public class SelectDescriptionFragment extends Fragment implements View.OnClickListener {
    private View viewRoot;
    private MainActivity activity;
    private FontEditext edt_desc;
    private boolean is1, is2, is3, is4, is5, is6, is7, is8, is9;
    private FontButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    public String[] values = {"", "", "", "", "", "", "", "", ""};

    public static SelectDescriptionFragment newInstance() {
        SelectDescriptionFragment fragment = new SelectDescriptionFragment();
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
        viewRoot = inflater.inflate(R.layout.fragment_select_description, container, false);
        return viewRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edt_desc = (FontEditext) view.findViewById(R.id.edt_desc);

        btn1 = (FontButton) viewRoot.findViewById(R.id.btn1);
        btn1.setOnClickListener(this);

        btn2 = (FontButton) viewRoot.findViewById(R.id.btn2);
        btn2.setOnClickListener(this);

        btn3 = (FontButton) viewRoot.findViewById(R.id.btn3);
        btn3.setOnClickListener(this);

        btn4 = (FontButton) viewRoot.findViewById(R.id.btn4);
        btn4.setOnClickListener(this);

        btn5 = (FontButton) viewRoot.findViewById(R.id.btn5);
        btn5.setOnClickListener(this);

        btn6 = (FontButton) viewRoot.findViewById(R.id.btn6);
        btn6.setOnClickListener(this);

        btn7 = (FontButton) viewRoot.findViewById(R.id.btn7);
        btn7.setOnClickListener(this);

        btn8 = (FontButton) viewRoot.findViewById(R.id.btn8);
        btn8.setOnClickListener(this);

        btn9 = (FontButton) viewRoot.findViewById(R.id.btn9);
        btn9.setOnClickListener(this);
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
            ((MainActivity) getActivity()).setTitle(getActivity().getResources().getString(R.string.description));
            ((MainActivity) getActivity()).setRightText(getActivity().getResources().getString(R.string.done));
        }
    }

    private String data = "";

    public String Data() {
        return data;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                try {
                    is1 = !is1;
                    setData(v, is1, 0);
                    if (is1) {
                        btn1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.icon_choose_v_yellow, 0);
                    } else {
                        btn1.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn2:
                try {
                    is2 = !is2;
                    setData(v, is2, 1);
                    if (is2) {
                        btn2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.icon_choose_v_yellow, 0);
                    } else {
                        btn2.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn3:
                try {
                    is3 = !is3;
                    setData(v, is3, 2);
                    if (is3) {
                        btn3.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.icon_choose_v_yellow, 0);
                    } else {
                        btn3.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn4:
                try {
                    is4 = !is4;
                    setData(v, is4, 3);
                    if (is4) {
                        btn4.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.icon_choose_v_yellow, 0);
                    } else {
                        btn4.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn5:
                try {
                    is5 = !is5;
                    setData(v, is5, 4);
                    if (is5) {
                        btn5.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.icon_choose_v_yellow, 0);
                    } else {
                        btn5.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn6:
                try {
                    is6 = !is6;
                    setData(v, is6, 5);
                    if (is6) {
                        btn6.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.icon_choose_v_yellow, 0);
                    } else {
                        btn6.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn7:
                try {
                    is7 = !is7;
                    setData(v, is7, 6);
                    if (is7) {
                        btn7.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.icon_choose_v_yellow, 0);
                    } else {
                        btn7.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn8:
                try {
                    is8 = !is8;
                    setData(v, is8, 7);
                    if (is8) {
                        btn8.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.icon_choose_v_yellow, 0);
                    } else {
                        btn8.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn9:
                try {
                    is9 = !is9;
                    setData(v, is9, 8);
                    if (is9) {
                        btn9.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.icon_choose_v_yellow, 0);
                    } else {
                        btn9.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void setData(View v, boolean isAdd, int index) {
        if (isAdd) {
            if (TextUtils.isEmpty(data.trim())) {
                values[index] = ((FontButton) viewRoot.findViewById(v.getId())).getText().toString().trim();
            } else {
                values[index] = "," + ((FontButton) viewRoot.findViewById(v.getId())).getText().toString().trim();
            }
        } else {
            values[index] = "";
        }
        data = "";
        for (int i = 0; i < values.length; i++) {
            data += values[i];
        }
        data.substring(1, 1).replace(",", "");
        edt_desc.setText(data);
    }
}
