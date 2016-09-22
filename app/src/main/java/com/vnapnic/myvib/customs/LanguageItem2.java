package com.vnapnic.myvib.customs;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vnapnic.myvib.R;

public class LanguageItem2 extends LinearLayout implements OnClickListener {
    private int typer;
    private boolean status;

    public LanguageItem2(Context context) {
        super(context);
        this.typer = 1;
        this.status = false;
        View.inflate(context, R.layout.item_language2, this);
        initView();
    }

    public LanguageItem2(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.typer = 1;
        this.status = false;
        View.inflate(context, R.layout.item_language2, this);
        initView();
    }

    private void initView() {
        this.typer = 1;
        this.status = false;
        setOnClickListener(this);
    }

    public void onSelect(boolean z) {
        if (z) {
            this.status = true;
            ((ImageView) findViewById(R.id.status)).setImageResource(R.drawable.language_screen_selected2);
            return;
        }
        this.status = false;
        ((ImageView) findViewById(R.id.status)).setImageResource(R.drawable.language_screen_not_select);
    }

    public boolean getStatus() {
        return this.status;
    }

    public int getType() {
        return this.typer;
    }

    @Override
    public void onClick(View view) {

    }

    public void setStatus(boolean z) {
        if (this.status != z) {
            this.status = z;
            onSelect(z);
        }
    }

    public void setType(int i) {
        switch (i) {
            case 1:
                this.typer = 1;
                ((ImageView) findViewById(R.id.icon)).setImageResource(R.drawable.icon_english);
                ((ImageView) findViewById(R.id.status)).setImageResource(R.drawable.language_screen_not_select);
                ((TextView) findViewById(R.id.text)).setText(R.string.english);
                break;
            case 2:
                this.typer = 2;
                ((ImageView) findViewById(R.id.icon)).setImageResource(R.drawable.icon_vn);
                ((ImageView) findViewById(R.id.status)).setImageResource(R.drawable.language_screen_not_select);
                ((TextView) findViewById(R.id.text)).setText(R.string.tieng_viet);
                break;
            default:
                break;
        }
    }
}
