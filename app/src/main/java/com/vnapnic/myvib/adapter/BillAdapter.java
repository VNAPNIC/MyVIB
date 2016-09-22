package com.vnapnic.myvib.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.vnapnic.myvib.R;
import com.vnapnic.myvib.customs.FontTextView;
import com.vnapnic.myvib.model.Bill;

import java.util.List;

/**
 * Created by vnapnic on 7/9/2016.
 */
public class BillAdapter extends BaseAdapter {
    private List<Bill> billList;
    private Context context;
    private LayoutInflater inflater;

    public BillAdapter(List<Bill> billList, Context context) {
        this.billList = billList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return billList.size();
    }

    @Override
    public Object getItem(int position) {
        return billList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_anyone, parent, false);
            holder.icon = (ImageView) convertView.findViewById(R.id.icon);
            holder.title = (FontTextView) convertView.findViewById(R.id.title);
            holder.phone = (FontTextView) convertView.findViewById(R.id.phone);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.icon
                .setImageResource(billList.get(position).icon);
        holder.title.setText(billList.get(position).title);
        holder.phone.setText(billList.get(position).phone);
        return null;
    }

    private class ViewHolder {
        ImageView icon;
        FontTextView title;
        FontTextView phone;
    }
}
