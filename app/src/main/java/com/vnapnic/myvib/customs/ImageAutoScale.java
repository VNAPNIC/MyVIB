package com.vnapnic.myvib.customs;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.vnapnic.myvib.R;

public class ImageAutoScale extends ImageView {
    private float f8835a;

    class OnClickListenerWrapper implements OnClickListener {
        final /* synthetic */ ImageAutoScale f8832a;
        private long f8833b;
        private final OnClickListener f8834c;

        public OnClickListenerWrapper(ImageAutoScale imageAutoScale, OnClickListener onClickListener) {
            this.f8832a = imageAutoScale;
            this.f8833b = 0;
            this.f8834c = onClickListener;
        }

        public void onClick(View view) {
            long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            if (currentAnimationTimeMillis - this.f8833b > 400) {
                this.f8834c.onClick(view);
                this.f8833b = currentAnimationTimeMillis;
            }
        }
    }

    public ImageAutoScale(Context context) {
        super(context);
    }

    public ImageAutoScale(Context context, float f) {
        super(context);
        this.f8835a = f;
    }

    public ImageAutoScale(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m14078a(context, attributeSet);
    }

    public ImageAutoScale(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m14078a(context, attributeSet);
    }

    private void m14078a(Context context, AttributeSet attributeSet) {
//        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ImageAutoScale);
//        try {
//            this.f8835a = obtainStyledAttributes.getFloat(0, 1.0f);
//        } finally {
//            obtainStyledAttributes.recycle();
//        }
    }

    protected void onMeasure(int i, int i2) {
        int size = MeasureSpec.getSize(i);
        setMeasuredDimension(size, (int) (((float) size) * this.f8835a));
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (isEnabled() && isClickable()) {
            int action = motionEvent.getAction() & 255;
            if (action == 0 || action == 2) {
                setColorFilter(null);
            } else if (action == 1) {
                setColorFilter(null);
            } else if (action == 3) {
                setColorFilter(null);
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        super.setOnClickListener(new OnClickListenerWrapper(this, onClickListener));
    }
}
