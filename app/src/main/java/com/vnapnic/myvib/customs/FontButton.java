package com.vnapnic.myvib.customs;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by vnapnic on 7/3/2016.
 */
public class FontButton extends Button {

    public FontButton(Context context) {
        super(context);
        initView(context, null, 0);
    }

    public FontButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs, 0);
    }

    public FontButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context, attrs, defStyleAttr);
    }

    private void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        Typeface tf = Typeface.createFromAsset(context.getAssets(), "MyriadPro-Regular.otf");
        setTypeface(tf, Typeface.NORMAL);
    }
}
