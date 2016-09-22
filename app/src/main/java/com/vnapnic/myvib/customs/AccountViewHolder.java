package com.vnapnic.myvib.customs;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vnapnic.myvib.R;
import com.vnapnic.myvib.model.Account;
import com.vnapnic.myvib.model.ExchangeRate;

public class AccountViewHolder {
    private Context context;
    private FontTextView title_account, content_account, value_sodu, value_soduketoan;
    public ImageView icon_account, action_button_images_simple_balance;
    public RelativeLayout viewContent;

    public AccountViewHolder(Context context, View view) {
        this.context = context;
        title_account = (FontTextView) view.findViewById(R.id.title_account);
        content_account = (FontTextView) view.findViewById(R.id.content_account);
        value_sodu = (FontTextView) view.findViewById(R.id.value_sodu);
        value_soduketoan = (FontTextView) view.findViewById(R.id.value_soduketoan);
        icon_account = (ImageView) view.findViewById(R.id.icon_account);
        action_button_images_simple_balance = (ImageView) view.findViewById(R.id.action_button_images_simple_balance);
        viewContent = (RelativeLayout) view.findViewById(R.id.viewContent);
    }

    public void setData(Account data) {
        icon_account.setImageResource(data.icon);
        title_account.setText(data.title);
        content_account.setText(data.cardID);
        value_sodu.setText(data.soDU);
        value_soduketoan.setText(data.soduKeToan);
        if (data.isRemove) {
            action_button_images_simple_balance.setImageResource(R.drawable.icon_close_account_click);
        } else {
            action_button_images_simple_balance.setImageResource(R.drawable.icon_acc_next);
        }
    }
}
