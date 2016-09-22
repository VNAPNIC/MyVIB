package com.vnapnic.myvib.customs;

import android.content.Context;
import android.view.View;

import com.vnapnic.myvib.R;
import com.vnapnic.myvib.model.ESavings;
import com.vnapnic.myvib.model.OrdinarySavings;

public class EsavingViewHolder {
    private Context context;
    private FontTextView depositeRate;
    private FontTextView rate;

    public EsavingViewHolder(Context context, View view) {
        this.context = context;
        this.depositeRate = (FontTextView) view.findViewById(R.id.depositeRate);
        this.rate = (FontTextView) view.findViewById(R.id.rate);
    }

    public void setData(ESavings ordinarySavings) {
        depositeRate.setText(ordinarySavings.deposit_amount);
        String last;
        if (ordinarySavings.percent_year == null) {
            last = "-";
        } else {
            last = Double.parseDouble(ordinarySavings.percent_year) + "";
        }
        rate.setText(last);
    }
}