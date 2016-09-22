package com.vnapnic.myvib.fragments.listbank;

/**
 * Created by CaNaWan on 7/14/2016.
 */

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.vnapnic.myvib.R;
import com.vnapnic.myvib.customs.FontTextView;
import com.vnapnic.myvib.model.Bank;

public class BankListadapter extends BaseAdapter {
    Context context;
    List<Bank> objects;
    private IOnAction actionClick;

    public interface IOnAction {
        void onEventClick(Bank bank);
    }

    public BankListadapter(Context context, List<Bank> list) {
        this.context = context;
        this.objects = list;
    }

    public void setActionClick(IOnAction actionClick) {
        this.actionClick = actionClick;
    }

    public int getCount() {
        return this.objects.size();
    }

    public Object getItem(int i) {
        return this.objects.get(i);
    }

    public long getItemId(int i) {
        return 0;
    }

    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(this.context).inflate(R.layout.select_item_bank, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.name.setText(objects.get(position).code);
        viewHolder.address.setText(" - " + objects.get(position).name);
        Click click = new Click(objects.get(position));
        view.setOnClickListener(click);
        return view;
    }

    private class Click implements View.OnClickListener {
        private Bank bank;

        public Click(Bank bank) {
            this.bank = bank;
        }

        @Override
        public void onClick(View v) {
            actionClick.onEventClick(bank);
        }
    }

    private class ViewHolder {
        private FontTextView name, address;

        public ViewHolder(View view) {
            name = (FontTextView) view.findViewById(R.id.name_bank);
            address = (FontTextView) view.findViewById(R.id.address_bank);
        }

    }
}