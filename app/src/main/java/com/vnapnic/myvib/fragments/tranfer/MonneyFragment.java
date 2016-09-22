package com.vnapnic.myvib.fragments.tranfer;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.vnapnic.myvib.MainActivity;
import com.vnapnic.myvib.R;
import com.vnapnic.myvib.common.ToolbarTyper;
import com.vnapnic.myvib.customs.NumberPad;
import com.vnapnic.myvib.model.Login;
import com.vnapnic.myvib.utils.Logger;

/**
 * Created by vnapnic on 7/20/2016.
 */
public class MonneyFragment extends Fragment implements NumberPad.IKeyCode {

    private View viewRoot;
    private MainActivity activity;

    private EditText edittext;
    private NumberPad numberPad;
    private Login login;
    private int[] number = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
    private Handler handlerTimer;

    public static MonneyFragment newInstance() {
        MonneyFragment fragment = new MonneyFragment();
        return fragment;
    }

    //  lifecycle
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handlerTimer = new Handler();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.fragment_money, container, false);
        return viewRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toggleKeyboard();
        initView(view);
    }

    private void initView(View view) {
        login = new Login();
        edittext = (EditText) view.findViewById(R.id.edittext);
        numberPad = (NumberPad) view.findViewById(R.id.layoutPad);
        numberPad.setOnNumpadClickListener(this);
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
            ((MainActivity)getActivity()).setTitle(getActivity().getResources().getString(R.string.enter_amount_title));
            ((MainActivity)getActivity()).setRightText(getActivity().getResources().getString(R.string.done));
        }
    }

    public void toggleKeyboard() {
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public String getAmount() {
        return edittext.getText().toString().trim();
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
                //TODO DONE
                activity.setMonneyTransfer();
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
        edittext.setText(str + " VND");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handlerTimer.removeCallbacks(null);
    }
}
