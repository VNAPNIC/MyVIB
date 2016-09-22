package com.vnapnic.myvib.customs;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.vnapnic.myvib.R;

/**
 * Created by vnapnic on 7/3/2016.
 */
public class NumberPad extends RelativeLayout implements OnClickListener {

    LinearLayout code1;
    LinearLayout code2;
    LinearLayout code3;
    LinearLayout code4;
    LinearLayout code5;
    LinearLayout code6;
    LinearLayout code7;
    LinearLayout code8;
    LinearLayout code9;
    LinearLayout code0;
    LinearLayout backspace;

    IKeyCode keyCode;
    private LinearLayout done;

    public interface IKeyCode {
        void returnCode(int i);
    }

    public NumberPad(Context context) {
        super(context);
        initView();
    }

    public NumberPad(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView();
    }

    public NumberPad(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initView();
    }

    private void sendCode(int i) {
        if (this.keyCode != null) {
            this.keyCode.returnCode(i);
        }
    }

    private void initView() {
        inflate(getContext(), R.layout.layout_number_pad, this);
        this.code1 = (LinearLayout) findViewById(R.id.num_pad_1_layout);
        this.code2 = (LinearLayout) findViewById(R.id.num_pad_2_layout);
        this.code3 = (LinearLayout) findViewById(R.id.num_pad_3_layout);
        this.code4 = (LinearLayout) findViewById(R.id.num_pad_4_layout);
        this.code5 = (LinearLayout) findViewById(R.id.num_pad_5_layout);
        this.code6 = (LinearLayout) findViewById(R.id.num_pad_6_layout);
        this.code7 = (LinearLayout) findViewById(R.id.num_pad_7_layout);
        this.code8 = (LinearLayout) findViewById(R.id.num_pad_8_layout);
        this.code9 = (LinearLayout) findViewById(R.id.num_pad_9_layout);
        this.code0 = (LinearLayout) findViewById(R.id.num_pad_0_layout);
        this.done = (LinearLayout) findViewById(R.id.num_pad_done);
        this.backspace = (LinearLayout) findViewById(R.id.num_pad_backspace_layout);

        this.code1.setOnClickListener(this);
        this.code2.setOnClickListener(this);
        this.code3.setOnClickListener(this);
        this.code4.setOnClickListener(this);
        this.code5.setOnClickListener(this);
        this.code6.setOnClickListener(this);
        this.code7.setOnClickListener(this);
        this.code8.setOnClickListener(this);
        this.code9.setOnClickListener(this);
        this.code0.setOnClickListener(this);
        this.done.setOnClickListener(this);
        this.backspace.setOnClickListener(this);
    }

    public boolean out() {
        if (getVisibility() != VISIBLE) {
            return false;
        }
        startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.view_slide_out_down));
        setVisibility(GONE);
        return true;
    }

    public boolean m14303b() {
        if (getVisibility() == VISIBLE) {
            return false;
        }
        startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.view_slide_in_up));
        setVisibility(VISIBLE);
        return true;
    }

    public IKeyCode getOnNumpadClickListener() {
        return this.keyCode;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.num_pad_1_layout:
                sendCode(1);
                break;
            case R.id.num_pad_2_layout:
                sendCode(2);
                break;
            case R.id.num_pad_3_layout:
                sendCode(3);
                break;
            case R.id.num_pad_4_layout:
                sendCode(4);
                break;
            case R.id.num_pad_5_layout:
                sendCode(5);
                break;
            case R.id.num_pad_6_layout:
                sendCode(6);
                break;
            case R.id.num_pad_7_layout:
                sendCode(7);
                break;
            case R.id.num_pad_8_layout:
                sendCode(8);
                break;
            case R.id.num_pad_9_layout:
                sendCode(9);
                break;
            case R.id.num_pad_0_layout:
                sendCode(0);
                break;
            case R.id.num_pad_done:
                sendCode(11);
                break;
            case R.id.num_pad_backspace_layout:
                sendCode(10);
                break;
            default:
                break;
        }
    }

    public void setDecimalNumPadEnable(boolean z) {
    }

    public void setOnNumpadClickListener(IKeyCode iKeyCode) {
        this.keyCode = iKeyCode;
    }
}
