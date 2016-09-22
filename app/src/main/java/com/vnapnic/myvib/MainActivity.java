package com.vnapnic.myvib;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.vnapnic.myvib.common.ToolbarTyper;
import com.vnapnic.myvib.common.ToolbarTyperLeft;
import com.vnapnic.myvib.common.ViewDialog;
import com.vnapnic.myvib.customs.FontButton;
import com.vnapnic.myvib.customs.FontTextView;
import com.vnapnic.myvib.customs.slidingmenu.SlidingMenu;
import com.vnapnic.myvib.fragments.ExchangeRateFragment;
import com.vnapnic.myvib.fragments.InterestRatesFragment;
import com.vnapnic.myvib.fragments.LoginFragment;
import com.vnapnic.myvib.fragments.MapsFragment;
import com.vnapnic.myvib.fragments.PinFragment;
import com.vnapnic.myvib.fragments.SettingLanguageFragment;
import com.vnapnic.myvib.fragments.transactionmanagement.TransactionManagementFragment;
import com.vnapnic.myvib.fragments.account.AccountFragment;
import com.vnapnic.myvib.fragments.account.CloseAccountFragment;
import com.vnapnic.myvib.fragments.account.PayOffCritFragment;
import com.vnapnic.myvib.fragments.account.PaymentDetailFragment;
import com.vnapnic.myvib.fragments.account.PaymentEditDetailFragment;
import com.vnapnic.myvib.fragments.account.PaymentoldDetailFragment;
import com.vnapnic.myvib.fragments.bill.BillDetailMobileFragment;
import com.vnapnic.myvib.fragments.bill.BillFragment;
import com.vnapnic.myvib.fragments.bill.BillServiceAddCard;
import com.vnapnic.myvib.fragments.bill.BillServiceCardDetail;
import com.vnapnic.myvib.fragments.bill.PayOffCreditDetailFragment;
import com.vnapnic.myvib.fragments.choice.SelectAccountFragment;
import com.vnapnic.myvib.fragments.choice.SelectDescriptionFragment;
import com.vnapnic.myvib.fragments.choice.SelectMonneyFragment;
import com.vnapnic.myvib.fragments.getsecuritycode.GetSecurityCodeFragment;
import com.vnapnic.myvib.fragments.getsecuritycode.MobiePhoneGetSecurityCodeFragment;
import com.vnapnic.myvib.fragments.getsecuritycode.PayABillGetSecurityCodeFragment;
import com.vnapnic.myvib.fragments.getsecuritycode.PayAnyoneGetSecurityCodeFragment;
import com.vnapnic.myvib.fragments.getsecuritycode.TranferGetSecurityCodeFragment;
import com.vnapnic.myvib.fragments.listphone.ContactsModel;
import com.vnapnic.myvib.fragments.listphone.PhoneSelectListFragment;
import com.vnapnic.myvib.fragments.payanyone.EmailMobileDetailFragment;
import com.vnapnic.myvib.fragments.payanyone.FastAnyoneFragment;
import com.vnapnic.myvib.fragments.payanyone.NormalAnyoneFragment;
import com.vnapnic.myvib.fragments.payanyone.PayAnyoneDetailFragment;
import com.vnapnic.myvib.fragments.payanyone.PayAnyoneEndDetailFragment;
import com.vnapnic.myvib.fragments.payanyone.PayAnyoneFragment;
import com.vnapnic.myvib.fragments.payanyone.PayNewCardFragment;
import com.vnapnic.myvib.fragments.receipt.MobiePhoneReceiptBillFragment;
import com.vnapnic.myvib.fragments.receipt.PayABillReceiptBillFragment;
import com.vnapnic.myvib.fragments.receipt.PayAnyoneReceiptBillFragment;
import com.vnapnic.myvib.fragments.receipt.ReceiptBillFragment;
import com.vnapnic.myvib.fragments.receipt.ReceiptFragment;
import com.vnapnic.myvib.fragments.receipt.TranferReceiptBillFragment;
import com.vnapnic.myvib.fragments.topup.TopUpAddNewFragment;
import com.vnapnic.myvib.fragments.topup.TopUpDetailFragment;
import com.vnapnic.myvib.fragments.topup.TopUpFragment;
import com.vnapnic.myvib.fragments.tranfer.MonneyFragment;
import com.vnapnic.myvib.fragments.tranfer.TransferBetweenYourAccountFragment;
import com.vnapnic.myvib.fragments.transactionmanagement.TransactionScheduleFragment;
import com.vnapnic.myvib.fragments.welcome.MainFragment;
import com.vnapnic.myvib.model.Account;
import com.vnapnic.myvib.model.SecurityCode;
import com.vnapnic.myvib.utils.Logger;
import com.vnapnic.myvib.utils.Util;

import java.util.Locale;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, ViewDialog.IAction2Button, ViewDialog.IAction2ButtonV2 {
    private SlidingMenu slidingMenu;
    private RelativeLayout toolbar;
    private ImageButton toolbarBack;
    private boolean isLock = true;
    private FragmentManager mFragmentManager;
    private ViewDialog viewDialog;

    private SharedPreferences sharedPreferences;
    public static final String LANGUAGE = "language";
    public static final String KEY_LANGUAGE = "key.language";
    private SharedPreferences.Editor editor;
    // Request code for READ_CONTACTS. It can be any number > 0.
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        setContentView(R.layout.activity_welcome_screen);
        sharedPreferences = getSharedPreferences(LANGUAGE, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString(KEY_LANGUAGE, "en");
        editor.commit();

        viewDialog = new ViewDialog();
        initView();

        getSoftButtonsBarSizePort(this);
    }

    public void addPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                checkSelfPermission(android.Manifest.permission.READ_CONTACTS)
                        != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{android.Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {
                Toast.makeText(MainActivity.this, "Bạn chưa cho quyền chuy cập vào danh bạ", Toast.LENGTH_LONG).show();
            }
        }
    }

    public static int getSoftButtonsBarSizePort(Activity activity) {
        // getRealMetrics is only available with API 17 and +
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            DisplayMetrics metrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
            int usableHeight = metrics.heightPixels;
            activity.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
            int realHeight = metrics.heightPixels;
            if (realHeight > usableHeight)
                return realHeight - usableHeight;
            else
                return 0;
        }
        return 0;
    }

    public void onCloseSliding() {
        slidingMenu.setSlidingEnabled(false);
    }

    public void onOpenSliding() {
        slidingMenu.setSlidingEnabled(true);
    }

    private void initView() {
        initSliding();
        toolbar = (RelativeLayout) findViewById(R.id.toolbar);
        addFragment(MainFragment.newInstance());
        //Thay BackstackChanged
        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {

            @Override
            public void onBackStackChanged() {
                Fragment f = getSupportFragmentManager().findFragmentById(R.id.content);
                if (f != null) {
                    updateTitleAndDrawer(f);
                }
            }
        });
    }

    private View initViewSliding() {
        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.sliding_menu, null);
        view.findViewById(R.id.menu_option10).setOnClickListener(this);
        view.findViewById(R.id.menu_view_account_balance).setOnClickListener(this);
        view.findViewById(R.id.menu_option6_layout).setOnClickListener(this);
        view.findViewById(R.id.menu_promotion_location).setOnClickListener(this);
        view.findViewById(R.id.menu_apply_product).setOnClickListener(this);
        view.findViewById(R.id.menu_option2).setOnClickListener(this);
        view.findViewById(R.id.menu_option3).setOnClickListener(this);
        view.findViewById(R.id.menu_option8).setOnClickListener(this);
        view.findViewById(R.id.menu_option9).setOnClickListener(this);
        view.findViewById(R.id.menu_pay_any_one).setOnClickListener(this);
        view.findViewById(R.id.menu_pay_a_bill).setOnClickListener(this);
        view.findViewById(R.id.menu_mobile_top_up).setOnClickListener(this);
        view.findViewById(R.id.menu_transfer_between_account).setOnClickListener(this);
        view.findViewById(R.id.menu_option_schedule).setOnClickListener(this);
        return view;
    }

    public void initSliding() {
        slidingMenu = new SlidingMenu(this);
        slidingMenu.setMode(SlidingMenu.LEFT);
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        slidingMenu.setShadowWidthRes(R.dimen.shadow_width);
        slidingMenu.setShadowDrawable(R.drawable.shadow);
        slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);
//        slidingMenu.setMenu(initViewSliding());
    }

    private void updateTitleAndDrawer(Fragment fragment) {
        String fragClassName = fragment.getClass().getName();

        if (fragClassName.equals(MainActivity.class.getName())) {
            Logger.d("namit", MainActivity.class.getName());
            finish();
        } else if (fragClassName.equals(LoginFragment.class.getName())) {
            Logger.d("namit", LoginFragment.class.getName());
            //set selected item position, etc
        }
    }

//    public void addSlidingFragment(final Fragment fragment) {
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                mFragmentManager = getSupportFragmentManager();
//                FragmentTransaction transaction = mFragmentManager.beginTransaction();
//                transaction.replace(R.id.content_sliding, fragment);
//                transaction.addToBackStack(null);
//                transaction.commit();
//            }
//        });
//    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Logger.d("Nankai", "onPostResume activity");
    }


    public void addFragment(Fragment fragment) {
        String backStateName = fragment.getClass().getName();
        String fragmentTag = backStateName;
        mFragmentManager = getSupportFragmentManager();
        boolean fragmentPopped = mFragmentManager.popBackStackImmediate(backStateName, 0);
        if (!fragmentPopped && mFragmentManager.findFragmentByTag(fragmentTag) == null) { //fragment not in back stack, create it.
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            transaction.add(R.id.content, fragment, fragmentTag);
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            transaction.addToBackStack(backStateName);
            transaction.commit();
        }
    }

    public void addAndRemoveFragment(Fragment fragment, Fragment pop) {
        String backStateName = fragment.getClass().getName();
        String fragmentTag = backStateName;
        mFragmentManager = getSupportFragmentManager();
        boolean fragmentPopped = mFragmentManager.popBackStackImmediate(backStateName, 0);
        if (!fragmentPopped && mFragmentManager.findFragmentByTag(fragmentTag) == null) { //fragment not in back stack, create it.
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            transaction.add(R.id.content, fragment, fragmentTag);
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            transaction.addToBackStack(backStateName);
            transaction.commit();
        }
        String popName = pop.getClass().getName();
        mFragmentManager.popBackStack(popName, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    public void replaceNotAnimFragment(final Fragment fragment) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mFragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = mFragmentManager.beginTransaction();
                transaction.replace(R.id.content, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

    public void replaceFragment(final Fragment fragment) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mFragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = mFragmentManager.beginTransaction();
                transaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
                transaction.replace(R.id.content, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

    public void replaceFragmentDelay(final Fragment fragment) {
        slidingMenu.toggle();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mFragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = mFragmentManager.beginTransaction();
                transaction.replace(R.id.content, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        }, 300);
    }

    /**
     * Navigate to new fragment, clear all old fragment to stack
     *
     * @param fragment
     */
    public void switchPage(Fragment fragment) {
        mFragmentManager = getSupportFragmentManager();
        for (int i = 0; i < mFragmentManager.getBackStackEntryCount(); ++i) {
            /*0=main ... 1=sliding*/
            if (i == 0 || i == 1) {
                //TODO
            } else {
                mFragmentManager.popBackStack();
            }
        }
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.replace(R.id.content, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void switchPageHome() {
        mFragmentManager = getSupportFragmentManager();
        for (int i = 0; i < mFragmentManager.getBackStackEntryCount(); ++i) {
            /*0=main ... 1=sliding*/
            if (i == 0) {
                //TODO
            } else {
                mFragmentManager.popBackStack();
            }
        }
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.replace(R.id.content, MainFragment.newInstance());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void setUptoolBar(ToolbarTyper typer) {
        toolbar.removeAllViews();
        switch (typer) {
            case DEFAULT:
                break;
            case VIB_ICON_WELCOME:
                toolbarVIB(ToolbarTyper.VIB_ICON_WELCOME);
                break;
            case VIB_ICON_LOGIN:
                toolbarVIB(ToolbarTyper.VIB_ICON_LOGIN);
                break;
            case VIB_ICON_PIN:
                toolbarVIB(ToolbarTyper.VIB_ICON_PIN);
                break;
            case VIB_ICON_MAIN:
                toolbarVIB(ToolbarTyper.VIB_ICON_MAIN);
                break;
            case NONE_BACK:
                toolbarVIB(ToolbarTyper.NONE_BACK);
                break;
            case NONE_RIGHT_BACK:
                toolbarVIB(ToolbarTyper.NONE_RIGHT_BACK);
                break;
            default:
                break;
        }
    }

    private void toolbarVIB(ToolbarTyper typer) {
        Logger.d("namit", "ToolbarTyper: " + typer);
        View layout = setLayout(typer);
        //action back on toolbar
        toolbarBack = (ImageButton) layout.findViewById(R.id.toolbar_back);
        toolbar.addView(layout);
        FontButton login = (FontButton) findViewById(R.id.btn_login);
        FontTextView customer = (FontTextView) findViewById(R.id.txt_customer);
        ImageView lock = (ImageView) findViewById(R.id.img_lock);

        switch (typer) {
            case VIB_ICON_WELCOME:
                Logger.d("namit", "VIB_ICON_WELCOME");
                customer.setVisibility(View.GONE);
                login.setVisibility(View.VISIBLE);
                lock.setVisibility(View.GONE);
                login.setOnClickListener(this);
                updateToolbar(ToolbarTyperLeft.SLIDING);

                break;
            case VIB_ICON_LOGIN:
                customer.setVisibility(View.VISIBLE);
                login.setVisibility(View.GONE);
                lock.setVisibility(View.GONE);
                customer.setOnClickListener(this);
                updateToolbar(ToolbarTyperLeft.NONE);
                break;
            case VIB_ICON_PIN:
                customer.setVisibility(View.GONE);
                login.setVisibility(View.GONE);
                lock.setVisibility(View.GONE);
                updateToolbar(ToolbarTyperLeft.BACKSPACE);
                break;
            case VIB_ICON_MAIN:
                customer.setVisibility(View.GONE);
                login.setVisibility(View.GONE);
                lock.setVisibility(View.VISIBLE);
                lock.setOnClickListener(this);
                isLock = true;
                updateToolbar(ToolbarTyperLeft.SLIDING);
                break;
            case NONE_BACK:
                updateToolbar(ToolbarTyperLeft.BACKSPACE);
                break;
            case NONE_RIGHT_BACK:
                updateToolbar(ToolbarTyperLeft.BACKSPACE);
                break;
            default:
                break;
        }
    }

    public void setTitle(String title) {
        ((FontTextView) findViewById(R.id.title)).setText(title);
    }

    public void setRightText(String rightText) {
        ((FontTextView) findViewById(R.id.txt_start)).setVisibility(View.VISIBLE);
        ((ImageView) findViewById(R.id.icon_right)).setVisibility(View.GONE);
        ((FontTextView) findViewById(R.id.txt_start)).setText(rightText);
        ((FontTextView) findViewById(R.id.txt_start)).setOnClickListener(this);
    }

    public void setRightIcon(int icon) {
        ((FontTextView) findViewById(R.id.txt_start)).setVisibility(View.GONE);
        ((ImageView) findViewById(R.id.icon_right)).setVisibility(View.VISIBLE);
//        ((ImageView) findViewById(R.id.icon_right)).setBackgroundResource(icon);
        ((ImageView) findViewById(R.id.icon_right)).setOnClickListener(this);
    }

    public View setLayout(ToolbarTyper typer) {
//        toolbar.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
        if (typer == ToolbarTyper.VIB_ICON_WELCOME) {
            return inflater.inflate(R.layout.toolbar_icon_vib, null, false);
        } else if (typer == ToolbarTyper.NONE_BACK) {
            return inflater.inflate(R.layout.toolbar_none_back, null, false);
        } else if (typer == ToolbarTyper.NONE_RIGHT_BACK) {
            return inflater.inflate(R.layout.toolbar_button_right_back, null, false);
        } else {
            return inflater.inflate(R.layout.toolbar_icon_vib, null, false);
        }
    }

    private void updateToolbar(ToolbarTyperLeft typer) {
        Logger.d("namit", "ToolbarTyperLeft: " + typer);
        toolbarBack.setOnClickListener(null);
        switch (typer) {
            case SLIDING:
                slidingMenu.setMenu(initViewSliding());
                slidingMenu.requestLayout();
                slidingMenu.setSlidingEnabled(true);

                sharedPreferences = getSharedPreferences(LANGUAGE, Context.MODE_PRIVATE);
                String lg = sharedPreferences.getString(KEY_LANGUAGE, "en");
                Logger.d("Nankai", "lg: " + lg);
                Locale locale = new Locale(lg);
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getApplicationContext().getResources().updateConfiguration(config, null);

                if (toolbarBack != null) {
                    toolbarBack.setBackgroundResource(R.drawable.icon_menu_toggle);
                    toolbarBack.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //TODO open siding
                            slidingMenu.showMenu();
                        }
                    });
                }
                break;
            case BACKSPACE:
                slidingMenu.setSlidingEnabled(false);
                toolbarBack.setBackgroundResource(R.drawable.btn_back_custom);
                toolbarBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO open back
                        MainActivity.this.onBackPressed();
                    }
                });
                break;
            case NONE:
                slidingMenu.setSlidingEnabled(false);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logger.d("Nankai", "onResume activity");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                //TODO
                Logger.d("namit", "login click");
                replaceFragment(LoginFragment.newInstance());
                break;
            case R.id.txt_customer:
                //TODO
                Logger.d("namit", "page customer click");
//                switchPage(WelcomeFragment.newInstance());
                onBackPressed();
                break;
            case R.id.img_lock:
                Logger.d("namit", "lock click");
                ImageView lock = (ImageView) findViewById(R.id.img_lock);

                if (isLock) {
                    viewDialog.showDialog2Buton(MainActivity.this,
                            getResources().getString(R.string.exit_title), getResources().getString(R.string.exit_mes), this);
                } else {
                    isLock = true;
                    lock.setBackgroundResource(R.drawable.icon_lock);
                }
                break;
            case R.id.txt_start:
            case R.id.icon_right:
                hideSoftKeyboard(MainActivity.this);
                Fragment f = getSupportFragmentManager().findFragmentById(R.id.content);
                if (f instanceof ReceiptFragment) {
                    if (((ReceiptFragment) f).getNo() == 1) {
                        switchPage(BillFragment.newInstance());
                    } else {
                        onBackPressed();
                    }
                } else if (f instanceof EmailMobileDetailFragment) {
                    try {
                        ((EmailMobileDetailFragment) f).next();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (f instanceof PaymentDetailFragment) {
                    replaceFragment(PaymentEditDetailFragment.newInstance());
                } else if (f instanceof PaymentEditDetailFragment) {
                    try {
                        ((PaymentEditDetailFragment) f).next();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (f instanceof PaymentoldDetailFragment) {
                    try {
                        ((PaymentoldDetailFragment) f).next();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (f instanceof PaymentoldDetailFragment) {
                    try {
                        ((PaymentoldDetailFragment) f).next();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (f instanceof TransactionScheduleFragment) {
                    try {
                        ((TransactionScheduleFragment) f).next();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (f instanceof CloseAccountFragment) {
                    Account account = ((CloseAccountFragment) f).getData();
                    SecurityCode content = new SecurityCode();
                    content.type = 2;
                    content.from = account.title;
                    content.title = getResources().getString(R.string.pay);
                    content.monney = account.soduKeToan;
                    content.name = "Nguyen van thach";
                    content.phone = account.cardID;
                    content.msTyper = account.title;
                    replaceFragment(GetSecurityCodeFragment.newInstance(content));
                } else if (f instanceof ReceiptBillFragment) {
                    switchPageHome();
                }
//---------------------------------------------------------------------------------
                else if (f instanceof TranferReceiptBillFragment) {
                    switchPageHome();
                } else if (f instanceof MobiePhoneReceiptBillFragment) {
                    switchPageHome();
                } else if (f instanceof PayAnyoneReceiptBillFragment) {
                    switchPageHome();
                } else if (f instanceof PayABillReceiptBillFragment) {
                    switchPageHome();
                }
//---------------------------------------------------------------------------------
                else if (f instanceof PayOffCreditDetailFragment) {
                    if (((PayOffCreditDetailFragment) f).isSave()) {
                        onBackPressed();
                    } else {
                        //TODO
                    }
                } else if (f instanceof BillServiceAddCard) {
                    ((BillServiceAddCard) f).next();
                } else if (f instanceof NormalAnyoneFragment) {
                    ((NormalAnyoneFragment) f).next();
                } else if (f instanceof FastAnyoneFragment) {
                    try {
                        ((FastAnyoneFragment) f).next();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (f instanceof PayNewCardFragment) {
                    try {
                        ((PayNewCardFragment) f).next();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (f instanceof GetSecurityCodeFragment) {
                    if (((GetSecurityCodeFragment) f).isCheckCode()) {
                        int typer = ((GetSecurityCodeFragment) f).getData().type;
                        if (typer == 1) {
                            SecurityCode content = ((GetSecurityCodeFragment) f).getData();
                            String btn1 = getResources().getString(R.string.confirm_transaction);
                            String btn2 = getResources().getString(R.string.cancel);
                            int icon = R.drawable.icon_confirm;
                            viewDialog.showDialog2Buton(this, content, btn1, btn2, icon, this);
                        } else if (typer == 2) {
                            SecurityCode content = ((GetSecurityCodeFragment) f).getData();
                            String btn1 = getResources().getString(R.string.confirm_transaction);
                            String btn2 = getResources().getString(R.string.cancel);
                            int icon = R.drawable.icon_confirm;
                            String[] action = {btn1, btn2};
                            viewDialog.showDialog2ButonBill(this, content, icon, action, this);
                        } else {
                            switchPageHome();
                        }
                    } else {
                        //TODO
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle(getResources().getString(R.string.title_get_code_dialog))
                                .setMessage(getResources().getString(R.string.get_code_dialog))
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // continue with delete
                                        dialog.dismiss();
                                    }
                                })
                                .show();
                    }
                }
//------------------------------------------------------------------------------------------------------
                else if (f instanceof TranferGetSecurityCodeFragment) {
                    if (((TranferGetSecurityCodeFragment) f).isCheckCode()) {
                        int typer = ((TranferGetSecurityCodeFragment) f).getData().type;
                        if (typer == 1) {
                            SecurityCode content = ((TranferGetSecurityCodeFragment) f).getData();
                            String btn1 = getResources().getString(R.string.confirm_transaction);
                            String btn2 = getResources().getString(R.string.cancel);
                            int icon = R.drawable.icon_confirm;

                            viewDialog.showDialog2Buton(this, content, btn1, btn2, icon, this);
                            Log.i("tho", "onClick: type 1");
                        } else if (typer == 2) {
                            SecurityCode content = ((TranferGetSecurityCodeFragment) f).getData();
                            String btn1 = getResources().getString(R.string.confirm_transaction);
                            String btn2 = getResources().getString(R.string.cancel);
                            int icon = R.drawable.icon_confirm;
                            String[] action = {btn1, btn2};
                            viewDialog.myShowDialog2ButonBill(this, content, icon, action, this, f);
                            Log.i("tho", "onClick: type 2");
                        } else {
                            switchPageHome();
                        }
                    } else {
                        //TODO
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle(getResources().getString(R.string.title_get_code_dialog))
                                .setMessage(getResources().getString(R.string.get_code_dialog))
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // continue with delete
                                        dialog.dismiss();
                                    }
                                })
                                .show();
                    }
                } else if (f instanceof MobiePhoneGetSecurityCodeFragment) {
                    if (((MobiePhoneGetSecurityCodeFragment) f).isCheckCode()) {
                        int typer = ((MobiePhoneGetSecurityCodeFragment) f).getData().type;
                        if (typer == 1) {
                            SecurityCode content = ((MobiePhoneGetSecurityCodeFragment) f).getData();
                            String btn1 = getResources().getString(R.string.confirm_transaction);
                            String btn2 = getResources().getString(R.string.cancel);
                            int icon = R.drawable.icon_confirm;

                            viewDialog.showDialog2Buton(this, content, btn1, btn2, icon, this);
                            Log.i("tho", "onClick: type 1");
                        } else if (typer == 2) {
                            SecurityCode content = ((MobiePhoneGetSecurityCodeFragment) f).getData();
                            String btn1 = getResources().getString(R.string.confirm_transaction);
                            String btn2 = getResources().getString(R.string.cancel);
                            int icon = R.drawable.icon_confirm;
                            String[] action = {btn1, btn2};
                            viewDialog.myShowDialog2ButonBill(this, content, icon, action, this, f);
                            Log.i("tho", "onClick: type 2");
                        } else {
                            switchPageHome();
                        }
                    } else {
                        //TODO
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle(getResources().getString(R.string.title_get_code_dialog))
                                .setMessage(getResources().getString(R.string.get_code_dialog))
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // continue with delete
                                        dialog.dismiss();
                                    }
                                })
                                .show();
                    }
                } else if (f instanceof PayAnyoneGetSecurityCodeFragment) {
                    if (((PayAnyoneGetSecurityCodeFragment) f).isCheckCode()) {
                        int typer = ((PayAnyoneGetSecurityCodeFragment) f).getData().type;
                        if (typer == 1) {
                            SecurityCode content = ((PayAnyoneGetSecurityCodeFragment) f).getData();
                            String btn1 = getResources().getString(R.string.confirm_transaction);
                            String btn2 = getResources().getString(R.string.cancel);
                            int icon = R.drawable.icon_confirm;

                            viewDialog.showDialog2Buton(this, content, btn1, btn2, icon, this);
                            Log.i("tho", "onClick: type 1");
                        } else if (typer == 2) {
                            SecurityCode content = ((PayAnyoneGetSecurityCodeFragment) f).getData();
                            String btn1 = getResources().getString(R.string.confirm_transaction);
                            String btn2 = getResources().getString(R.string.cancel);
                            int icon = R.drawable.icon_confirm;
                            String[] action = {btn1, btn2};
                            viewDialog.myShowDialog2ButonBill(this, content, icon, action, this, f);
                            Log.i("tho", "onClick: type 2");
                        } else {
                            switchPageHome();
                        }
                    } else {
                        //TODO
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle(getResources().getString(R.string.title_get_code_dialog))
                                .setMessage(getResources().getString(R.string.get_code_dialog))
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // continue with delete
                                        dialog.dismiss();
                                    }
                                })
                                .show();
                    }
                } else if (f instanceof PayABillGetSecurityCodeFragment) {
                    if (((PayABillGetSecurityCodeFragment) f).isCheckCode()) {
                        int typer = ((PayABillGetSecurityCodeFragment) f).getData().type;
                        if (typer == 1) {
                            SecurityCode content = ((PayABillGetSecurityCodeFragment) f).getData();
                            String btn1 = getResources().getString(R.string.confirm_transaction);
                            String btn2 = getResources().getString(R.string.cancel);
                            int icon = R.drawable.icon_confirm;
                            viewDialog.myShowDialog2Buton(this, content, btn1, btn2, icon, this, f);

                            Log.i("tho", "onClick: type 1");
                        } else if (typer == 2) {
                            SecurityCode content = ((PayABillGetSecurityCodeFragment) f).getData();
                            String btn1 = getResources().getString(R.string.confirm_transaction);
                            String btn2 = getResources().getString(R.string.cancel);
                            int icon = R.drawable.icon_confirm;
                            String[] action = {btn1, btn2};
                            viewDialog.myShowDialog2ButonBill(this, content, icon, action, this, f);
                            Log.i("tho", "onClick: type 2");
                        } else {
                            switchPageHome();
                        }
                    } else {
                        //TODO
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle(getResources().getString(R.string.title_get_code_dialog))
                                .setMessage(getResources().getString(R.string.get_code_dialog))
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // continue with delete
                                        dialog.dismiss();
                                    }
                                })
                                .show();
                    }
//-----------------------------------------------------------------------------------------------------------------------

                } else if (f instanceof BillServiceCardDetail) {
                    try {
                        ((BillServiceCardDetail) f).next();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else if (f instanceof BillDetailMobileFragment) {
                    try {
                        ((BillDetailMobileFragment) f).next();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (f instanceof TopUpAddNewFragment) {
                    try {
                        String name = ((TopUpAddNewFragment) f).getName();
                        String code = ((TopUpAddNewFragment) f).getCode();
                        //                        if (code.length() <= 12) {
                        replaceFragment(TopUpDetailFragment.newInstance(name, code));
                        //                        } else {
                        //                            String title = getResources().getString(R.string.mobile_field_invalid_title);
                        //                            String content = getResources().getString(R.string.mobile_field_invalid);
                        //                            viewDialog.showDialog1Buton(this, title, content);
                        //                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (f instanceof TopUpDetailFragment) {
                    try {
                        ((TopUpDetailFragment) f).next();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (f instanceof PayAnyoneEndDetailFragment) {
                    try {
                        ((PayAnyoneEndDetailFragment) f).next();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (f instanceof PayOffCritFragment) {
                    ((PayOffCritFragment) f).next();
                } else if (f instanceof SelectDescriptionFragment) {
                    String dataDescriptionSelect = "";
                    dataDescriptionSelect = ((SelectDescriptionFragment) f).Data();
                    super.onBackPressed();
                    Fragment f2 = getSupportFragmentManager().findFragmentById(R.id.content);
                    if (f2 instanceof TopUpDetailFragment) {
                        ((TopUpDetailFragment) f2).setDataDesc(dataDescriptionSelect);
                    } else if (f2 instanceof PayOffCritFragment) {
                        ((PayOffCritFragment) f2).setDataDesc(dataDescriptionSelect);
                    } else if (f2 instanceof TransferBetweenYourAccountFragment) {
                        ((TransferBetweenYourAccountFragment) f2).setDataDesc(dataDescriptionSelect);
                    } else if (f2 instanceof BillServiceCardDetail) {
                        ((BillServiceCardDetail) f2).setDataDesc(dataDescriptionSelect);
                    } else if (f2 instanceof PayAnyoneEndDetailFragment) {
                        ((PayAnyoneEndDetailFragment) f2).setDataDesc(dataDescriptionSelect);
                    } else if (f2 instanceof PayAnyoneDetailFragment) {
                        ((PayAnyoneDetailFragment) f2).setDataDesc(dataDescriptionSelect);
                    }
                } else if (f instanceof MonneyFragment) {
                    setMonneyTransfer();
                } else if (f instanceof TransferBetweenYourAccountFragment) {
                    try {
                        ((TransferBetweenYourAccountFragment) f).next();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                break;


            //Menu sliding
            case R.id.menu_option10:
                replaceFragmentDelay(PinFragment.newInstance());
                break;
            case R.id.menu_option6_layout:
                Util.openURLVIB(this);
                break;
            case R.id.menu_view_account_balance:
                replaceFragmentDelay(AccountFragment.newInstance());
                break;
            case R.id.menu_promotion_location:
                replaceFragmentDelay(MapsFragment.newInstance(1));
                break;
            case R.id.menu_apply_product:
                Util.openAppVIB(this);
                break;
            case R.id.menu_option2:
                replaceFragmentDelay(InterestRatesFragment.newInstance());
                break;
            case R.id.menu_option3:
                replaceFragmentDelay(ExchangeRateFragment.newInstance());
                break;
            case R.id.menu_option8:
                replaceFragmentDelay(SettingLanguageFragment.newInstance());
                break;
            case R.id.menu_option9:
                replaceFragmentDelay(PinFragment.newInstance());
                break;
            case R.id.menu_pay_any_one:
                replaceFragmentDelay(PayAnyoneFragment.newInstance());
                break;
            case R.id.menu_pay_a_bill:
                replaceFragmentDelay(BillFragment.newInstance());
                break;
            case R.id.menu_mobile_top_up:
                replaceFragmentDelay(TopUpFragment.newInstance());
                break;
            case R.id.menu_transfer_between_account:
                replaceFragmentDelay(TransferBetweenYourAccountFragment.newInstance());
                break;
            case R.id.menu_option_schedule:
                replaceFragmentDelay(TransactionManagementFragment.newInstance());
                break;
        }
    }

    public void setMonneyTransfer() {
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.content);
        if (f instanceof MonneyFragment) {
            String amount = "";
            amount = ((MonneyFragment) f).getAmount();
            onBackPressed();
            Fragment f2 = getSupportFragmentManager().findFragmentById(R.id.content);
            if (f2 instanceof TransferBetweenYourAccountFragment) {
                ((TransferBetweenYourAccountFragment) f2).setAmount(amount);
            }
        }
    }

    @Override
    public void button1() {
        ImageView lock = (ImageView) findViewById(R.id.img_lock);
        if (lock != null) {
            isLock = false;
            lock.setBackgroundResource(R.drawable.icon_unlock);
            replaceFragment(PinFragment.newInstance());
        }
    }

    @Override
    public void button2() {
        //TODO
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(INPUT_METHOD_SERVICE);
        if (activity.getCurrentFocus() != null)
            imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    @Override
    public void onBackPressed() {


        //fragment welcome & sliding
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else {
            hideSoftKeyboard(MainActivity.this);
            Fragment f = getSupportFragmentManager().findFragmentById(R.id.content);
            if (f instanceof GetSecurityCodeFragment) {
                if (((GetSecurityCodeFragment) f).isShow()) {
                    ((GetSecurityCodeFragment) f).invi();
                } else {
                    super.onBackPressed();
                }
            } else if (f instanceof PhoneSelectListFragment) {
                ContactsModel contactsModel = ((PhoneSelectListFragment) f).getContactsModel();
                int type = ((PhoneSelectListFragment) f).getType();
                super.onBackPressed();
                if (type == 1) {
                    Fragment f2 = getSupportFragmentManager().findFragmentById(R.id.content);
                    if (f2 instanceof EmailMobileDetailFragment) {
                        try {
                            ((EmailMobileDetailFragment) f2).setContactsModel(contactsModel);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (f2 instanceof TopUpAddNewFragment) {
                        try {
                            ((TopUpAddNewFragment) f2).setContactsModel(contactsModel);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else if (f instanceof PayOffCreditDetailFragment) {
                if (((PayOffCreditDetailFragment) f).isShow()) {
                    ((PayOffCreditDetailFragment) f).invi();
                } else {
//                    String[] data = ((PayOffCreditDetailFragment) f).getData();
                    super.onBackPressed();
//                    Fragment f2 = getSupportFragmentManager().findFragmentById(R.id.content);
//                    if (f2 instanceof PayOffCreditFragment) {
//                        Bill bill = new Bill();
//                        bill.id = 1 + "";
//                        bill.title = data[0];
//                        bill.phone = data[1];
//                        ((PayOffCreditFragment) f2).setData(bill);
//                    }
                }
            } else if (f instanceof BillServiceAddCard) {
                if (((BillServiceAddCard) f).isShow()) {
                    ((BillServiceAddCard) f).invi();
                } else {
                    super.onBackPressed();
                }
            } else if (f instanceof TopUpAddNewFragment) {
                if (((TopUpAddNewFragment) f).isShow()) {
                    ((TopUpAddNewFragment) f).invi();
                } else {
                    super.onBackPressed();
                }
            } else if (f instanceof SelectMonneyFragment) {
                String dataMonneySelect = "";
                dataMonneySelect = ((SelectMonneyFragment) f).Data();
                super.onBackPressed();
                Fragment f2 = getSupportFragmentManager().findFragmentById(R.id.content);
                if (f2 instanceof TopUpDetailFragment) {
                    ((TopUpDetailFragment) f2).setDataMonney(dataMonneySelect);
                } else if (f2 instanceof PayOffCritFragment) {
                    ((PayOffCritFragment) f2).setDataMonney(dataMonneySelect);
                }
            } else if (f instanceof SelectAccountFragment) {
                Account account = ((SelectAccountFragment) f).getData();
                super.onBackPressed();
                Fragment f2 = getSupportFragmentManager().findFragmentById(R.id.content);
                if (f2 instanceof TopUpDetailFragment) {
                    try {
                        ((TopUpDetailFragment) f2).setAccount(account);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (f2 instanceof PayOffCritFragment) {
                    try {
                        ((PayOffCritFragment) f2).setAccount(account);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (f2 instanceof BillServiceCardDetail) {
                    ((BillServiceCardDetail) f2).setAccount(account);
                } else if (f2 instanceof PayAnyoneEndDetailFragment) {
                    try {
                        ((PayAnyoneEndDetailFragment) f2).setAccount(account);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (f2 instanceof PayAnyoneDetailFragment) {
                    try {
                        ((PayAnyoneDetailFragment) f2).setAccount(account);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else if (f instanceof PayNewCardFragment) {
                if (((PayNewCardFragment) f).isShow()) {
                    ((PayNewCardFragment) f).invi();
                } else {
                    super.onBackPressed();
                }
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public void button1V2(SecurityCode securityCode) {
        //TODO
        replaceFragment(ReceiptFragment.newInstance(securityCode.monney + " VND", securityCode.name, securityCode.phone, securityCode.desc, true, 1));
    }

    @Override
    public void button1V3(SecurityCode securityCode) {
        Logger.d("namit", "button 1V3");
        replaceFragment(ReceiptBillFragment.newInstance(securityCode));
    }

    @Override
    public void button2V2() {
        //TODO
    }
}
