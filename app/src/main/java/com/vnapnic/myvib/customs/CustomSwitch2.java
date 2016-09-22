package com.vnapnic.myvib.customs;

import android.content.Context;
import android.util.AttributeSet;

import com.vnapnic.myvib.R;

public class CustomSwitch2 extends CustomSwitch {

    private boolean isCheck = false;
    public CustomSwitch2(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setCanEdit(boolean z) {
        setEnabled(z);
    }

    public void setStatus(boolean z) {

        if (z) {
            setBackgroundResource(R.drawable.toggle_on);
        } else {
            setBackgroundResource(R.drawable.toggle_off);
        }
    }
}
