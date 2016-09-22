package com.vnapnic.myvib.fragments;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.vnapnic.myvib.R;
import com.vnapnic.myvib.MainActivity;
import com.vnapnic.myvib.common.ToolbarTyper;
import com.vnapnic.myvib.common.ViewDialog;
import com.vnapnic.myvib.customs.FontEditext;
import com.vnapnic.myvib.customs.FontTextView;
import com.vnapnic.myvib.model.Login;
import com.vnapnic.myvib.utils.Logger;

/**
 * Created by vnapnic on 7/2/2016.
 */
public class LoginFragment extends Fragment implements View.OnClickListener, FontEditext.OnEditorActionListener, ViewDialog.IAction2Button {

    private View viewRoot;
    private MainActivity activity;

    private ProgressBar loading;

    private FontEditext username;
    private FontEditext password;
    private Login login;
    private FontTextView forgtten, reset, register;
    private ViewDialog viewDialog;
    private Handler handlerTimer;

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
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
        viewRoot = inflater.inflate(R.layout.fragment_login, container, false);
        return viewRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iniView(view);
    }

    private void iniView(View view) {
        viewDialog = new ViewDialog();
        RelativeLayout btnLogin = (RelativeLayout) view.findViewById(R.id.fragment_login_action);
        username = (FontEditext) view.findViewById(R.id.fragment_login_username);
        password = (FontEditext) view.findViewById(R.id.fragment_login_password);
        password.setOnEditorActionListener(this);
        btnLogin.setOnClickListener(this);
        login = new Login();

        forgtten = (FontTextView) view.findViewById(R.id.forgtten_login);
        forgtten.setText(Html.fromHtml(getActivity().getResources().getString(R.string.forgotten_your) + " <u>" + getActivity().getResources().getString(R.string._username) + "</u>"));

        reset = (FontTextView) view.findViewById(R.id.reset_login);
        reset.setText(Html.fromHtml(getActivity().getResources().getString(R.string.reset_your) + " <u>" + getActivity().getResources().getString(R.string.password) + "</u>"));

        register = (FontTextView) view.findViewById(R.id.register_login);
        register.setText(Html.fromHtml(getActivity().getResources().getString(R.string.sign_up) + " <u>online banking</u>"));

        loading = (ProgressBar) view.findViewById(R.id.loading);

        forgtten.setOnClickListener(this);
        reset.setOnClickListener(this);
        register.setOnClickListener(this);
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
            ((MainActivity)getActivity()).setUptoolBar(ToolbarTyper.VIB_ICON_LOGIN);
            username.post(new Runnable() {
                public void run() {
                    username.requestFocusFromTouch();
                }
            });
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_login_action:
                Logger.d("namit", "login click");
                boolean isLoginSuccess = login.validateLogin(username.getText().toString().trim().toLowerCase(), password.getText().toString().trim().toLowerCase(), activity);
                password.setText("");
                if (isLoginSuccess) {
                    username.setText("");
                    Logger.d("namit", "login Success.!");
                    InputMethodManager imm = (InputMethodManager) getActivity()
                            .getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(password.getWindowToken(), 0);
                    imm.hideSoftInputFromWindow(username.getWindowToken(), 0);
                    loading.setVisibility(View.VISIBLE);

                    handlerTimer.postDelayed(new Runnable() {
                        public void run() {
                            loading.setVisibility(View.GONE);
                            ((MainActivity)getActivity()).replaceFragment(PinFragment.newInstance());
                        }
                    }, 700);
                } else {
                    //TODO
                }
                break;
            case R.id.forgtten_login:
                Logger.d("namit", "forgtten_login click");
                viewDialog.showDialog1Buton(activity,
                        getActivity().getResources().getString(R.string.forget_username_title),
                        getActivity().getResources().getString(R.string.forget_username_message));
                break;
            case R.id.reset_login:
                try {
                    Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://ib.vib.com.vn/en-us/forgotpassword.aspx"));
                    startActivity(myIntent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(activity, "No application can handle this request."
                            + " Please install a webbrowser", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
                break;
            case R.id.register_login:
                Logger.d("namit", "register_login click");
                viewDialog.showDialog2Buton(activity,
                        getActivity().getResources().getString(R.string.do_you_have_current_account),
                        "", this);
                break;
        }
    }


    @Override
    public void button1() {
        Logger.d("namit", "button1 click");
        ((MainActivity)getActivity()).replaceFragment(RegisterFragment.newInstance());
    }

    @Override
    public void button2() {
        Toast.makeText(activity, "no function.!", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                actionId == EditorInfo.IME_ACTION_DONE ||
                event.getAction() == KeyEvent.ACTION_DOWN &&
                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
            Logger.d("namit", "login click");
            boolean isLoginSuccess = login.validateLogin(username.getText().toString().trim().toLowerCase(), password.getText().toString().trim().toLowerCase(), activity);
            password.setText("");

            if (isLoginSuccess) {
                username.setText("");
                Logger.d("namit", "login Success.!");
                InputMethodManager imm = (InputMethodManager) getActivity()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(password.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(username.getWindowToken(), 0);
                loading.setVisibility(View.VISIBLE);

                handlerTimer.postDelayed(new Runnable() {
                    public void run() {
                        loading.setVisibility(View.GONE);
                        ((MainActivity)getActivity()).replaceFragment(PinFragment.newInstance());
                    }
                }, 1000);
            } else {
                //TODO
            }
            return true;
        }
        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handlerTimer.removeCallbacks(null);
    }
}
