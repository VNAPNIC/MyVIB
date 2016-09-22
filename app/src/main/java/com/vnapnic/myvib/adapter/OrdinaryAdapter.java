package com.vnapnic.myvib.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.vnapnic.myvib.R;
import com.vnapnic.myvib.customs.ExchangeRateViewHolder;
import com.vnapnic.myvib.customs.OrdinaryViewHolder;
import com.vnapnic.myvib.model.ExchangeRate;
import com.vnapnic.myvib.model.OrdinarySavings;

import java.util.List;

/**
 * Created by vnapnic on 7/10/2016.
 */
public class OrdinaryAdapter extends BaseAdapter {
    Context context;
    List<OrdinarySavings> ordinarySavingses;

    public OrdinaryAdapter(Context context, List<OrdinarySavings> list) {
        this.context = context;
        this.ordinarySavingses = list;
    }

    public int getCount() {
        return this.ordinarySavingses.size();
    }

    public Object getItem(int i) {
        return this.ordinarySavingses.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        OrdinaryViewHolder ordinaryViewHolder;
        if (view == null) {
            view = LayoutInflater.from(this.context).inflate(R.layout.item_ordinary, viewGroup, false);
            OrdinaryViewHolder ordinaryViewHolder2 = new OrdinaryViewHolder(this.context, view);
            view.setTag(ordinaryViewHolder2);
            ordinaryViewHolder = ordinaryViewHolder2;
        } else {
            ordinaryViewHolder = (OrdinaryViewHolder) view.getTag();
        }
        ordinaryViewHolder.setData((OrdinarySavings) this.ordinarySavingses.get(i));
        if (i % 2 == 0) {
            view.setBackgroundColor(this.context.getResources().getColor(R.color.white));
        } else {
            view.setBackgroundColor(this.context.getResources().getColor(R.color.silver4));
        }
        return view;
    }
}
