package com.vnapnic.myvib.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.vnapnic.myvib.R;
import com.vnapnic.myvib.customs.EsavingViewHolder;
import com.vnapnic.myvib.model.ESavings;

import java.util.List;

/**
 * Created by vnapnic on 7/10/2016.
 */
public class EsavingAdapter extends BaseAdapter {
    Context context;
    List<ESavings> eSavingses;

    public EsavingAdapter(Context context, List<ESavings> list) {
        this.context = context;
        this.eSavingses = list;
    }

    public int getCount() {
        return this.eSavingses.size();
    }

    public Object getItem(int i) {
        return this.eSavingses.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        EsavingViewHolder ordinaryViewHolder;
        if (view == null) {
            view = LayoutInflater.from(this.context).inflate(R.layout.item_esaving, viewGroup, false);
            EsavingViewHolder ordinaryViewHolder2 = new EsavingViewHolder(this.context, view);
            view.setTag(ordinaryViewHolder2);
            ordinaryViewHolder = ordinaryViewHolder2;
        } else {
            ordinaryViewHolder = (EsavingViewHolder) view.getTag();
        }
        ordinaryViewHolder.setData((ESavings) this.eSavingses.get(i));
        if (i % 2 == 0) {
            view.setBackgroundColor(this.context.getResources().getColor(R.color.white));
        } else {
            view.setBackgroundColor(this.context.getResources().getColor(R.color.silver4));
        }
        return view;
    }
}
