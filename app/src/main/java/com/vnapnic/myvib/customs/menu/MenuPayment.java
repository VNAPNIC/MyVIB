package com.vnapnic.myvib.customs.menu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.vnapnic.myvib.R;

public class MenuPayment extends LinearLayout {
    private RelativeLayout title;
    private LinearLayout content;
    private TextView payAnyOne;
    private TextView payABill;
    private TextView topUp;
    private TextView betweenAcount;
    private ImageView ivExpand;
    private boolean isExpand;

    class ActionPayment implements OnClickListener {
        final  MenuPayment menuPayment;

        ActionPayment(MenuPayment menuPayment) {
            this.menuPayment = menuPayment;
        }

        public void onClick(View view) {
            if (this.menuPayment.isExpand) {
                this.menuPayment.content.setVisibility(GONE);
                this.menuPayment.ivExpand.setImageResource(R.drawable.icon_expand_arrow);
                this.menuPayment.isExpand = false;
                return;
            }
            this.menuPayment.content.setVisibility(VISIBLE);
            this.menuPayment.ivExpand.setImageResource(R.drawable.icon_collapse_arrow);
            this.menuPayment.isExpand = true;
        }
    }

    public MenuPayment(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.ivExpand = (ImageView) findViewById(R.id.ivExpand);
        this.title = (RelativeLayout) findViewById(R.id.layoutTitle);
        this.content = (LinearLayout) findViewById(R.id.layoutContent);
        this.payAnyOne = (TextView) findViewById(R.id.menu_pay_any_one);
        this.payABill = (TextView) findViewById(R.id.menu_pay_a_bill);
        this.topUp = (TextView) findViewById(R.id.menu_mobile_top_up);
        this.betweenAcount = (TextView) findViewById(R.id.menu_transfer_between_account);
        this.title.setOnClickListener(new ActionPayment(this));
        setExpandState(false);
    }

    public void setExpandState(boolean z) {
        this.isExpand = z;
        if (this.isExpand) {
            this.content.setVisibility(VISIBLE);
            this.ivExpand.setImageResource(R.drawable.icon_collapse_arrow);
            return;
        }
        this.content.setVisibility(GONE);
        this.ivExpand.setImageResource(R.drawable.icon_expand_arrow);
    }
}
