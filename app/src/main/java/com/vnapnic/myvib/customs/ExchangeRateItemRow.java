package com.vnapnic.myvib.customs;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vnapnic.myvib.R;
import com.vnapnic.myvib.utils.StringUtil;


public class ExchangeRateItemRow extends LinearLayout {
    private TextView title;
    private TextView sell;
    private TextView buy;

    public ExchangeRateItemRow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void m14066a(String str, double d, double d2) {
        this.title.setText(str);
        this.sell.setText(d == 0.0d ? "-" : StringUtil.m16041a().m16049b(Double.valueOf(d)));
        this.buy.setText(d2 == 0.0d ? "-" : StringUtil.m16041a().m16049b(Double.valueOf(d2)));
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.title = (TextView) findViewById(R.id.title);
        this.sell = (TextView) findViewById(R.id.sell);
        this.buy = (TextView) findViewById(R.id.buy);
    }
}
