package com.vnapnic.myvib.customs.menu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.vnapnic.myvib.R;

public class MenuSetting extends LinearLayout {
    private RelativeLayout title;
    private LinearLayout content;
    private ImageView ivExpand;
    private boolean isExpand;

    class ActionSetting implements OnClickListener {
        final /* synthetic */ MenuSetting menuSetting;

        ActionSetting(MenuSetting menuSetting) {
            this.menuSetting = menuSetting;
        }

        public void onClick(View view) {
            if (this.menuSetting.isExpand) {
                this.menuSetting.content.setVisibility(GONE);
                this.menuSetting.ivExpand.setImageResource(R.drawable.icon_expand_arrow);
                this.menuSetting.isExpand = false;
                return;
            }
            this.menuSetting.content.setVisibility(VISIBLE);
            this.menuSetting.ivExpand.setImageResource(R.drawable.icon_collapse_arrow);
            this.menuSetting.isExpand = true;
        }
    }

    public MenuSetting(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.ivExpand = (ImageView) findViewById(R.id.ivExpand);
        this.title = (RelativeLayout) findViewById(R.id.layoutTitle);
        this.content = (LinearLayout) findViewById(R.id.layoutContent);
        this.title.setOnClickListener(new ActionSetting(this));
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
