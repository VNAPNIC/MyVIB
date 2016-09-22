package com.vnapnic.myvib.customs.menu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.vnapnic.myvib.R;

public class MenuPromotionOffer extends LinearLayout {
    private RelativeLayout title;
    private LinearLayout content;
    private TextView promotion;
    private TextView location;
    private ImageView ivExpand;
    private boolean isExpand;

    class ActionPromotionOffer implements OnClickListener {
        final /* synthetic */ MenuPromotionOffer menuPromotionOffer;

        ActionPromotionOffer(MenuPromotionOffer menuPromotionOffer) {
            this.menuPromotionOffer = menuPromotionOffer;
        }

        public void onClick(View view) {
            if (this.menuPromotionOffer.isExpand) {
                this.menuPromotionOffer.content.setVisibility(GONE);
                this.menuPromotionOffer.ivExpand.setImageResource(R.drawable.icon_expand_arrow);
                this.menuPromotionOffer.isExpand = false;
                return;
            }
            this.menuPromotionOffer.content.setVisibility(VISIBLE);
            this.menuPromotionOffer.ivExpand.setImageResource(R.drawable.icon_collapse_arrow);
            this.menuPromotionOffer.isExpand = true;
        }
    }

    public MenuPromotionOffer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.ivExpand = (ImageView) findViewById(R.id.ivExpand);
        this.title = (RelativeLayout) findViewById(R.id.layoutTitle);
        this.content = (LinearLayout) findViewById(R.id.layoutContent);
        this.location = (TextView) findViewById(R.id.menu_promotion_location);
        this.title.setOnClickListener(new ActionPromotionOffer(this));
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
