package com.vnapnic.myvib.customs.menu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.vnapnic.myvib.R;

public class MenuTools extends LinearLayout {
    private RelativeLayout title;
    private LinearLayout content;
    private ImageView ivExpand;
    private boolean isExpand;

    /* renamed from: com.vn.vib.mobileapp.modules.menu.MenuTools.1 */
    class ActionTools implements OnClickListener {
        final /* synthetic */ MenuTools menuTools;

        ActionTools(MenuTools menuTools) {
            this.menuTools = menuTools;
        }

        public void onClick(View view) {
            if (this.menuTools.isExpand) {
                this.menuTools.content.setVisibility(GONE);
                this.menuTools.ivExpand.setImageResource(R.drawable.icon_expand_arrow);
                this.menuTools.isExpand = false;
                return;
            }
            this.menuTools.content.setVisibility(VISIBLE);
            this.menuTools.ivExpand.setImageResource(R.drawable.icon_collapse_arrow);
            this.menuTools.isExpand = true;
        }
    }

    public MenuTools(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.ivExpand = (ImageView) findViewById(R.id.ivExpand);
        this.title = (RelativeLayout) findViewById(R.id.layoutTitle);
        this.content = (LinearLayout) findViewById(R.id.layoutContent);
        this.title.setOnClickListener(new ActionTools(this));
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
