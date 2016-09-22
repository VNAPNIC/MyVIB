package com.vnapnic.myvib.customs;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.vnapnic.myvib.R;
import com.vnapnic.myvib.model.ExchangeRate;

public class ExchangeRateViewHolder {
    private Context context;
    private TextView code;
    private ExchangeRateItemRow cashover;
    private ExchangeRateItemRow cash;
    private ExchangeRateItemRow transfer;

    public ExchangeRateViewHolder(Context context, View view) {
        this.context = context;
        this.code = (TextView) view.findViewById(R.id.code);
        this.cashover = (ExchangeRateItemRow) view.findViewById(R.id.cashover);
        this.cash = (ExchangeRateItemRow) view.findViewById(R.id.cash);
        this.transfer = (ExchangeRateItemRow) view.findViewById(R.id.transfer);
    }

    public void setData(ExchangeRate exchangeRate) {
        this.code.setText(exchangeRate.code);
        double buyCash = Double.parseDouble(exchangeRate.buy);

        if (buyCash == 0.0d) {
            this.cashover.setVisibility(View.GONE);
            this.cash.setVisibility(View.GONE);
        } else if (buyCash == 0.0d) {
            this.cash.setVisibility(View.GONE);
            this.cashover.setVisibility(View.VISIBLE);
        } else {
            this.cash.setVisibility(View.VISIBLE);
            this.cashover.setVisibility(View.VISIBLE);
        }
        this.cash.m14066a(this.context.getString(R.string.cash_over), Double.parseDouble(exchangeRate.sell), buyCash);
        this.cashover.m14066a(this.context.getString(R.string.cash_less), Double.parseDouble(exchangeRate.sell), buyCash);
        this.transfer.m14066a(this.context.getString(R.string.transfer2), Double.parseDouble(exchangeRate.sell), Double.parseDouble(exchangeRate.sell));
    }
}