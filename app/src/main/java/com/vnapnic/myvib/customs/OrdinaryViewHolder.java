package com.vnapnic.myvib.customs;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.vnapnic.myvib.R;
import com.vnapnic.myvib.model.ExchangeRate;
import com.vnapnic.myvib.model.OrdinarySavings;

public class OrdinaryViewHolder {
    private Context context;
    private FontTextView term;
    private FontTextView last_term;
    private FontTextView quarterly;
    private FontTextView monthly;

    public OrdinaryViewHolder(Context context, View view) {
        this.context = context;
        this.term = (FontTextView) view.findViewById(R.id.term);
        this.last_term = (FontTextView) view.findViewById(R.id.last_term);
        this.quarterly = (FontTextView) view.findViewById(R.id.quarterly);
        this.monthly = (FontTextView) view.findViewById(R.id.monthly);
    }

    public void setData(OrdinarySavings ordinarySavings) {
        term.setText(ordinarySavings.term);
        String last;
        if (ordinarySavings.last_term == null) {
            last = "-";
        } else {
            last = Double.parseDouble(ordinarySavings.last_term) + "";
        }

        String quarter;
        if (ordinarySavings.quarterly == null) {
            quarter = "-";
        } else {
            quarter = Double.parseDouble(ordinarySavings.quarterly) + "";
        }

        String month;
        if (ordinarySavings.monthly == null) {
            month = "-";
        } else {
            month = Double.parseDouble(ordinarySavings.monthly) + "";
        }

        last_term.setText(last);
        quarterly.setText(quarter);
        monthly.setText(month);
    }
}