package com.vnapnic.myvib.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.vnapnic.myvib.R;
import com.vnapnic.myvib.customs.ProgressiveViewHolder;
import com.vnapnic.myvib.model.OrdinarySavings;
import com.vnapnic.myvib.model.ProgressiveSavings;

import java.util.List;

/**
 * Created by vnapnic on 7/10/2016.
 */
public class ProgressiveAdapter extends BaseAdapter {
    Context context;
    List<ProgressiveSavings> progressiveSavingses;

    public ProgressiveAdapter(Context context, List<ProgressiveSavings> list) {
        this.context = context;
        this.progressiveSavingses = list;
    }

    public int getCount() {
        return this.progressiveSavingses.size();
    }

    public Object getItem(int i) {
        return this.progressiveSavingses.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ProgressiveViewHolder  progressiveViewHolder;
        if (view == null) {
            view = LayoutInflater.from(this.context).inflate(R.layout.item_progressive, viewGroup, false);
            ProgressiveViewHolder  progressiveViewHolder2 = new ProgressiveViewHolder(this.context, view);
            view.setTag( progressiveViewHolder2);
             progressiveViewHolder =  progressiveViewHolder2;
        } else {
             progressiveViewHolder = (ProgressiveViewHolder) view.getTag();
        }
         progressiveViewHolder.setData((ProgressiveSavings) this.progressiveSavingses.get(i));
        if (i % 2 == 0) {
            view.setBackgroundColor(this.context.getResources().getColor(R.color.white));
        } else {
            view.setBackgroundColor(this.context.getResources().getColor(R.color.silver4));
        }
        return view;
    }
}
