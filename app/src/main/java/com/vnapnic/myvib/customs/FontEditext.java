package com.vnapnic.myvib.customs;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by vnapnic on 7/3/2016.
 */
public class FontEditext extends EditText {

    public FontEditext(Context context) {
        super(context);
        initView(context, null, 0);
    }

    public FontEditext(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs, 0);
    }

    public FontEditext(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context, attrs, defStyleAttr);
    }

    private void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        Typeface tf = Typeface.createFromAsset(context.getAssets(), "MyriadPro-Regular.otf");
        setTypeface(tf, Typeface.NORMAL);
    }
}
