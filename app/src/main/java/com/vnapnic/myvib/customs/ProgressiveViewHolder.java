package com.vnapnic.myvib.customs;

import android.content.Context;
import android.view.View;

import com.vnapnic.myvib.R;
import com.vnapnic.myvib.model.ESavings;
import com.vnapnic.myvib.model.ProgressiveSavings;

public class ProgressiveViewHolder {
    private Context context;
    private FontTextView term;
    private FontTextView vnd;
    private FontTextView usd;
    private FontTextView eur;

    public ProgressiveViewHolder(Context context, View view) {
        this.context = context;
        this.term = (FontTextView) view.findViewById(R.id.term);
        this.vnd = (FontTextView) view.findViewById(R.id.vnd);
        this.usd = (FontTextView) view.findViewById(R.id.usd);
        this.eur = (FontTextView) view.findViewById(R.id.eur);
    }

    public void setData(ProgressiveSavings progressiveSavings) {
        term.setText(progressiveSavings.term);
        switch (progressiveSavings.type) {
            case "VND":
                vnd.setText(Double.parseDouble(progressiveSavings.value) + "");
                usd.setText("0");
                eur.setText("0");
                break;
            case "USD":
                vnd.setText("0");
                usd.setText(Double.parseDouble(progressiveSavings.value) + "");
                eur.setText("0");
                break;
            case "EUR":
                vnd.setText("0");
                usd.setText("0");
                eur.setText(Double.parseDouble(progressiveSavings.value) + "");
                break;
        }
    }
}