package com.vnapnic.myvib.customs;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.vnapnic.myvib.R;

public class CustomSwitch extends ImageView {
    protected boolean f8791a;
    private OnStateChange f8792b;

    /* renamed from: com.vn.vib.mobileapp.customviews.CustomSwitch.1 */
    class C12861 implements OnClickListener {
        final /* synthetic */ CustomSwitch f8790a;

        C12861(CustomSwitch customSwitch) {
            this.f8790a = customSwitch;
        }

        public void onClick(View view) {
            this.f8790a.f8791a = !this.f8790a.f8791a;
            this.f8790a.setStatus(this.f8790a.f8791a);
            if (this.f8790a.f8792b != null) {
                this.f8790a.f8792b.m14048a(this.f8790a.f8791a);
            }
        }
    }

    public interface OnStateChange {
        void m14048a(boolean z);
    }

    public CustomSwitch(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f8791a = false;
    }

    public boolean getStatus() {
        return this.f8791a;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        setStatus(getStatus());
        setOnClickListener(new C12861(this));
    }

    protected void setOnStateChange(OnStateChange onStateChange) {
        this.f8792b = onStateChange;
    }

    public void setStatus(boolean z) {
        this.f8791a = z;
        if (z) {
            setBackgroundResource(R.drawable.switch_on);
        } else {
            setBackgroundResource(R.drawable.switch_off);
        }
    }
}
