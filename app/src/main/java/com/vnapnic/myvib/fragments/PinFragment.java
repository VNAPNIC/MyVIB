package com.vnapnic.myvib.fragments;

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

import com.vnapnic.myvib.R;
import com.vnapnic.myvib.MainActivity;
import com.vnapnic.myvib.common.ToolbarTyper;
import com.vnapnic.myvib.customs.NumberPad;
import com.vnapnic.myvib.fragments.welcome.MainFragment;
import com.vnapnic.myvib.model.Login;
import com.vnapnic.myvib.utils.Logger;

/**
 * Created by vnapnic on 7/2/2016.
 */
public class PinFragment extends Fragment implements NumberPad.IKeyCode {

    private View viewRoot;
    private MainActivity activity;

    private ProgressBar loading;
    private EditText edtPin1, edtPin2, edtPin3, edtPin4;
    private NumberPad numberPad;
    private Login login;
    private int[] number = {-1, -1, -1, -1};
    private Handler handlerTimer;

    public static PinFragment newInstance() {
        PinFragment fragment = new PinFragment();
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
        viewRoot = inflater.inflate(R.layout.fragment_pin, container, false);
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
        edtPin1 = (EditText) view.findViewById(R.id.edtPin1);
        edtPin2 = (EditText) view.findViewById(R.id.edtPin2);
        edtPin3 = (EditText) view.findViewById(R.id.edtPin3);
        edtPin4 = (EditText) view.findViewById(R.id.edtPin4);
        numberPad = (NumberPad) view.findViewById(R.id.layoutPad);
        loading = (ProgressBar) view.findViewById(R.id.loading);
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
            ((MainActivity)getActivity()).setUptoolBar(ToolbarTyper.VIB_ICON_PIN);
        }
    }

    public void toggleKeyboard() {
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public void returnCode(int code) {
        if (code != 10 && code != 11) {
            Logger.d("namit", code + " ... code");
            for (int i = 0; i < 4; i++) {
                if (number[i] == -1) {
                    Logger.d("namit", i + "... number = " + code);
                    number[i] = code;
                    break;
                }
            }
            setChangeValue();
            if (number[3] > -1) {
                Logger.d("namit", "request pin");
                if (login.validatePin(number, activity)) {
                    Logger.d("namit", "validate success.!");
                    loading.setVisibility(View.VISIBLE);

                    handlerTimer.postDelayed(new Runnable() {
                        public void run() {
                            loading.setVisibility(View.GONE);
                            ((MainActivity)getActivity()).replaceFragment(MainFragment.newInstance());
                        }
                    }, 700);
                } else {
                    Logger.d("namit", "validate fail.!");
                }
            }

        } else {
            //TODO
            Logger.d("namit", code + " ... code event");
            if (code == 10) {
                for (int j = 3; j >= 0; j--) {
                    if (number[j] != -1) {
                        number[j] = -1;
                        break;
                    }
                }
                setChangeValue();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handlerTimer.removeCallbacks(null);
    }

    private void setChangeValue() {
        edtPin1.setText(number[0] == -1 ? "" : number[0] + "");
        edtPin2.setText(number[1] == -1 ? "" : number[1] + "");
        edtPin3.setText(number[2] == -1 ? "" : number[2] + "");
        edtPin4.setText(number[3] == -1 ? "" : number[3] + "");
    }
}
