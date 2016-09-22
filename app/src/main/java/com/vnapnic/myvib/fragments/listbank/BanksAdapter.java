package com.vnapnic.myvib.fragments.listbank;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.vnapnic.myvib.R;
import com.vnapnic.myvib.fragments.listphone.ContactsModel;

import java.util.ArrayList;

/**
 * Created by ThoNguyenHuu on 01/08/2016.
 */
public class BanksAdapter extends ArrayAdapter<BanksModel> {

    public static final int TITLE = 0;
    public static final int CONTENT = 1;

    private LayoutInflater inflater;
    private Context context;
    private ArrayList<BanksModel> arrContacts;
    BankClick bankClick;

    public BanksAdapter(Context context, int resource, ArrayList<BanksModel> arrBanks, BankClick bankClick) {
        super(context, resource, arrBanks);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.arrContacts = arrBanks;
        this.bankClick = bankClick;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (arrContacts.get(position).getStyle() == TITLE) {
            convertView = inflater.inflate(R.layout.item_title_contacs, parent, false);
            TextView textView = (TextView) convertView.findViewById(R.id.tv_title_contacts);
            textView.setText(arrContacts.get(position).getCode());
        } else {
            convertView = inflater.inflate(R.layout.item_banks, parent, false);
            TextView tvName = (TextView) convertView.findViewById(R.id.tv_code);
            TextView tvPhoneNum = (TextView) convertView.findViewById(R.id.tv_name);
            tvName.setText(arrContacts.get(position).getCode());
            tvPhoneNum.setText(arrContacts.get(position).getName());

            convertView.setOnClickListener(new Click(arrContacts.get(position)));
        }

        return convertView;
    }

    private class Click implements View.OnClickListener {
        private BanksModel bank;

        public Click(BanksModel bank) {
            this.bank = bank;
        }

        @Override
        public void onClick(View v) {
            bankClick.onItemListViewClickListener(bank);
        }
    }
}
