package com.vnapnic.myvib.customs.menu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.vnapnic.myvib.R;

public class MenuProduct extends LinearLayout {
    private RelativeLayout title;
    private LinearLayout content;
    private TextView applyProduct;
    private ImageView ivExpand;
    private boolean isExpand;
    
    class ActionProduct implements OnClickListener {
        final /* synthetic */ MenuProduct menuProduct;

        ActionProduct(MenuProduct menuProduct) {
            this.menuProduct = menuProduct;
        }

        public void onClick(View view) {
            if (this.menuProduct.isExpand) {
                this.menuProduct.content.setVisibility(GONE);
                this.menuProduct.ivExpand.setImageResource(R.drawable.icon_expand_arrow);
                this.menuProduct.isExpand = false;
                return;
            }
            this.menuProduct.content.setVisibility(VISIBLE);
            this.menuProduct.ivExpand.setImageResource(R.drawable.icon_collapse_arrow);
            this.menuProduct.isExpand = true;
        }
    }

    public MenuProduct(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.ivExpand = (ImageView) findViewById(R.id.ivExpand);
        this.title = (RelativeLayout) findViewById(R.id.layoutTitle);
        this.content = (LinearLayout) findViewById(R.id.layoutContent);
        this.applyProduct = (TextView) findViewById(R.id.menu_apply_product);
        this.title.setOnClickListener(new ActionProduct(this));
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
