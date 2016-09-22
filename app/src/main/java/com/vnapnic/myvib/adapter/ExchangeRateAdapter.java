package com.vnapnic.myvib.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.vnapnic.myvib.R;
import com.vnapnic.myvib.customs.ExchangeRateViewHolder;
import com.vnapnic.myvib.model.ExchangeRate;

import java.util.List;

/**
 * Created by vnapnic on 7/6/2016.
 */
public class ExchangeRateAdapter extends BaseAdapter {
    Context context;
    List<ExchangeRate> exChanges;

    public ExchangeRateAdapter(Context context, List<ExchangeRate> list) {
        this.context = context;
        this.exChanges = list;
    }

    public int getCount() {
        return this.exChanges.size();
    }

    public Object getItem(int i) {
        return this.exChanges.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ExchangeRateViewHolder exchangeRateViewHolder;
        if (view == null) {
            view = LayoutInflater.from(this.context).inflate(R.layout.exchangerate_item, viewGroup, false);
            ExchangeRateViewHolder exchangeRateViewHolder2 = new ExchangeRateViewHolder(this.context, view);
            view.setTag(exchangeRateViewHolder2);
            exchangeRateViewHolder = exchangeRateViewHolder2;
        } else {
            exchangeRateViewHolder = (ExchangeRateViewHolder) view.getTag();
        }
        exchangeRateViewHolder.setData((ExchangeRate) this.exChanges.get(i));
        if (i % 2 == 0) {
            view.setBackgroundColor(this.context.getResources().getColor(R.color.white));
        } else {
            view.setBackgroundColor(this.context.getResources().getColor(R.color.silver4));
        }
        return view;
    }
}
