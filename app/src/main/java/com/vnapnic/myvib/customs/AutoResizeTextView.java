package com.vnapnic.myvib.customs;

import android.content.Context;
import android.graphics.Paint;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

import com.vnapnic.myvib.BuildConfig;

public class AutoResizeTextView extends TextView {
    private OnTextResizeListener f8726a;
    private boolean f8727b;
    private float f8728c;
    private float f8729d;
    private float f8730e;
    private float f8731f;
    private float f8732g;
    private boolean f8733h;

    public interface OnTextResizeListener {
        void m14020a(TextView textView, float f, float f2);
    }

    public AutoResizeTextView(Context context) {
        this(context, null);
    }

    public AutoResizeTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AutoResizeTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f8727b = false;
        this.f8729d = 0.0f;
        this.f8730e = 10.0f;
        this.f8731f = 1.0f;
        this.f8732g = 0.0f;
        this.f8733h = true;
        this.f8728c = getTextSize();
    }

    private int m14021a(CharSequence charSequence, TextPaint textPaint, int i, float f) {
        TextPaint textPaint2 = new TextPaint(textPaint);
        textPaint2.setTextSize(f);
        return new StaticLayout(charSequence, textPaint2, i, Alignment.ALIGN_NORMAL, this.f8731f, this.f8732g, true).getHeight();
    }

    public void m14022a() {
        if (this.f8728c > 0.0f) {
            super.setTextSize(0, this.f8728c);
            this.f8729d = this.f8728c;
        }
    }

    public void m14023a(int i, int i2) {
        CharSequence text = getText();
        if (text != null && text.length() != 0 && i2 > 0 && i > 0 && this.f8728c != 0.0f) {
            Paint paint = getPaint();
            float textSize = paint.getTextSize();
            float min = this.f8729d > 0.0f ? Math.min(this.f8728c, this.f8729d) : this.f8728c;
            float f = min;
            int a = m14021a(text, (TextPaint) paint, i, min);
            while (a > i2 && f > this.f8730e) {
                float max = Math.max(f - 2.0f, this.f8730e);
                a = m14021a(text, (TextPaint) paint, i, max);
                f = max;
            }
            if (this.f8733h && f == this.f8730e && a > i2) {
                StaticLayout staticLayout = new StaticLayout(text, new TextPaint(paint), i, Alignment.ALIGN_NORMAL, this.f8731f, this.f8732g, false);
                if (staticLayout.getLineCount() > 0) {
                    int lineForVertical = staticLayout.getLineForVertical(i2) - 1;
                    if (lineForVertical < 0) {
                        setText(BuildConfig.FLAVOR);
                    } else {
                        int lineStart = staticLayout.getLineStart(lineForVertical);
                        int lineEnd = staticLayout.getLineEnd(lineForVertical);
                        min = staticLayout.getLineWidth(lineForVertical);
                        float measureText = paint.measureText("...");
                        while (((float) i) < min + measureText) {
                            lineEnd--;
                            min = paint.measureText(text.subSequence(lineStart, lineEnd + 1).toString());
                        }
                        setText(text.subSequence(0, lineEnd) + "...");
                    }
                }
            }
            setTextSize(0, f);
            setLineSpacing(this.f8732g, this.f8731f);
            if (this.f8726a != null) {
                this.f8726a.m14020a(this, textSize, f);
            }
            this.f8727b = false;
        }
    }

    public boolean getAddEllipsis() {
        return this.f8733h;
    }

    public float getMaxTextSize() {
        return this.f8729d;
    }

    public float getMinTextSize() {
        return this.f8730e;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (z || this.f8727b) {
            m14023a(((i3 - i) - getCompoundPaddingLeft()) - getCompoundPaddingRight(), ((i4 - i2) - getCompoundPaddingBottom()) - getCompoundPaddingTop());
        }
        super.onLayout(z, i, i2, i3, i4);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        if (i != i3 || i2 != i4) {
            this.f8727b = true;
        }
    }

    protected void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.f8727b = true;
        m14022a();
    }

    public void setAddEllipsis(boolean z) {
        this.f8733h = z;
    }

    public void setLineSpacing(float f, float f2) {
        super.setLineSpacing(f, f2);
        this.f8731f = f2;
        this.f8732g = f;
    }

    public void setMaxTextSize(float f) {
        this.f8729d = f;
        requestLayout();
        invalidate();
    }

    public void setMinTextSize(float f) {
        this.f8730e = f;
        requestLayout();
        invalidate();
    }

    public void setOnResizeListener(OnTextResizeListener onTextResizeListener) {
        this.f8726a = onTextResizeListener;
    }

    public void setTextSize(float f) {
        super.setTextSize(f);
        this.f8728c = getTextSize();
    }

    public void setTextSize(int i, float f) {
        super.setTextSize(i, f);
        this.f8728c = getTextSize();
    }
}
