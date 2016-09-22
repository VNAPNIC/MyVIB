package com.vnapnic.myvib.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.vnapnic.myvib.R;
import com.vnapnic.myvib.customs.AccountViewHolder;
import com.vnapnic.myvib.customs.ExchangeRateViewHolder;
import com.vnapnic.myvib.model.Account;
import com.vnapnic.myvib.model.ExchangeRate;

import java.util.List;

/**
 * Created by vnapnic on 7/6/2016.
 */
public class AccountAdapter extends BaseAdapter {
    Context context;
    List<Account> accounts;
    IActionClick actionClick;

    public interface IActionClick {
        void eventClick(Account account);

        void evnetCLickRemove(Account account);
    }

    public AccountAdapter(Context context, List<Account> list, IActionClick actionClick) {
        this.context = context;
        this.accounts = list;
        this.actionClick = actionClick;
    }

    public int getCount() {
        return this.accounts.size();
    }

    public Object getItem(int i) {
        return this.accounts.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        AccountViewHolder accountViewHolder;
        if (view == null) {
            view = LayoutInflater.from(this.context).inflate(R.layout.simplebalance_item, viewGroup, false);
            AccountViewHolder accountViewHolder2 = new AccountViewHolder(this.context, view);
            view.setTag(accountViewHolder2);
            accountViewHolder = accountViewHolder2;
        } else {
            accountViewHolder = (AccountViewHolder) view.getTag();
        }
        accountViewHolder.setData((Account) this.accounts.get(i));
        Click click = new Click((Account) this.accounts.get(i));
        accountViewHolder.viewContent.setOnClickListener(click);
        if (accounts.get(i).isRemove) {
            accountViewHolder.action_button_images_simple_balance.setOnClickListener(click);
        } else {
            accountViewHolder.action_button_images_simple_balance.setOnClickListener(null);
        }
        return view;
    }

    private class Click implements View.OnClickListener {
        Account account;

        public Click(Account account) {
            this.account = account;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.viewContent:
                    actionClick.eventClick(account);
                    break;
                case R.id.action_button_images_simple_balance:
                    actionClick.evnetCLickRemove(account);
                    break;
            }
        }
    }
}
