package com.vnapnic.myvib.customs;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.vnapnic.myvib.R;

public class LayoutCardStatus extends LinearLayout {
    boolean f8944a;
    boolean f8945b;
    boolean f8946c;
    private TextView f8947d;
    private TextView f8948e;
    private TextView f8949f;
    private CustomSwitch2 f8950g;
    private CustomSwitch2 f8951h;
    private CustomSwitch2 f8952i;
    private OnSwitchClick f8953j;
    private boolean f8954k;

    /* renamed from: com.vn.vib.mobileapp.customviews.LayoutCardStatus.1 */
    class C13121 implements CustomSwitch.OnStateChange {
        final /* synthetic */ OnSwitchClick f8938a;
        final /* synthetic */ LayoutCardStatus f8939b;

        C13121(LayoutCardStatus layoutCardStatus, OnSwitchClick onSwitchClick) {
            this.f8939b = layoutCardStatus;
            this.f8938a = onSwitchClick;
        }

        @Override
        public void m14048a(boolean z) {
            if (!z && this.f8939b.f8952i.getStatus()) {
                this.f8939b.f8952i.setStatus(false);
            }
            this.f8939b.m14176c();
            this.f8938a.m14173a(1);
        }
    }

    /* renamed from: com.vn.vib.mobileapp.customviews.LayoutCardStatus.2 */
    class C13132 implements CustomSwitch.OnStateChange {
        final /* synthetic */ OnSwitchClick f8940a;
        final /* synthetic */ LayoutCardStatus f8941b;

        C13132(LayoutCardStatus layoutCardStatus, OnSwitchClick onSwitchClick) {
            this.f8941b = layoutCardStatus;
            this.f8940a = onSwitchClick;
        }

        @Override
        public void m14048a(boolean z) {
            if (this.f8941b.f8954k && z) {
                this.f8941b.f8952i.setStatus(this.f8941b.f8946c);
            }
            this.f8941b.m14176c();
            this.f8940a.m14173a(2);
        }
    }

    /* renamed from: com.vn.vib.mobileapp.customviews.LayoutCardStatus.3 */
    class C13143 implements CustomSwitch.OnStateChange {
        final /* synthetic */ OnSwitchClick f8942a;
        final /* synthetic */ LayoutCardStatus f8943b;

        C13143(LayoutCardStatus layoutCardStatus, OnSwitchClick onSwitchClick) {
            this.f8943b = layoutCardStatus;
            this.f8942a = onSwitchClick;
        }

        @Override
        public void m14048a(boolean z) {
            if (z && !this.f8943b.f8950g.getStatus()) {
                this.f8943b.f8952i.setStatus(false);
            }
            if (z && this.f8943b.f8951h.getStatus() && !this.f8943b.f8946c) {
                this.f8943b.f8952i.setStatus(false);
            }
            this.f8943b.m14176c();
            this.f8942a.m14173a(3);
        }
    }

    public interface OnSwitchClick {
        void m14173a(int i);
    }

    public LayoutCardStatus(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f8954k = true;
    }

    private void m14176c() {
        if (this.f8950g.getStatus()) {
            this.f8947d.setText(R.string.card_activated);
            if (!this.f8950g.isEnabled()) {
                this.f8950g.setVisibility(INVISIBLE);
            }
        } else {
            this.f8947d.setText(R.string.activate_card);
            this.f8950g.setVisibility(VISIBLE);
        }
        if (this.f8951h.getStatus()) {
            this.f8948e.setText(R.string.card_locked);
        } else {
            this.f8948e.setText(R.string.card_unlocked);
        }
        if (this.f8952i.getStatus()) {
            this.f8949f.setText(R.string.e_active);
        } else {
            this.f8949f.setText(R.string.e_inactive);
        }
    }

    public void m14180a() {
        findViewById(R.id.layout3).setVisibility(GONE);
        findViewById(R.id.line3).setVisibility(GONE);
    }

    public void m14181a(boolean z, boolean z2, boolean z3) {
        this.f8944a = z;
        this.f8945b = z2;
        this.f8946c = z3;
        this.f8950g.setStatus(z);
        this.f8951h.setStatus(z2);
        this.f8952i.setStatus(z3);
        m14176c();
    }

    public void m14182b() {
        findViewById(R.id.layout3).setVisibility(VISIBLE);
        findViewById(R.id.line3).setVisibility(VISIBLE);
    }

    public void m14183b(boolean z, boolean z2, boolean z3) {
        this.f8950g.setCanEdit(z);
        this.f8951h.setCanEdit(z2);
        this.f8952i.setCanEdit(z3);
    }

    public boolean getStatusSwitch1() {
        return this.f8950g.getStatus();
    }

    public String getStatusSwitch1String() {
        return this.f8950g.getStatus() ? "Y" : "N";
    }

    public boolean getStatusSwitch2() {
        return this.f8951h.getStatus();
    }

    public String getStatusSwitch2String() {
        return this.f8951h.getStatus() ? "Y" : "N";
    }

    public Boolean getStatusSwitch3() {
        return findViewById(R.id.layout3).getVisibility() == GONE ? null : Boolean.valueOf(this.f8952i.getStatus());
    }

    public String getStatusSwitch3String() {
        return findViewById(R.id.layout3).getVisibility() == GONE ? "NONE" : this.f8952i.getStatus() ? "Y" : "N";
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.f8950g = (CustomSwitch2) findViewById(R.id.sw1);
        this.f8951h = (CustomSwitch2) findViewById(R.id.sw2);
        this.f8952i = (CustomSwitch2) findViewById(R.id.sw3);
        this.f8947d = (TextView) findViewById(R.id.tvKey1);
        this.f8948e = (TextView) findViewById(R.id.tvKey2);
        this.f8949f = (TextView) findViewById(R.id.tvKey3);
    }

    public void setCanEdit1(boolean z) {
        this.f8950g.setCanEdit(z);
    }

    public void setCanEdit2(boolean z) {
        this.f8951h.setCanEdit(z);
    }

    public void setCanEdit3(boolean z) {
        this.f8954k = z;
        this.f8952i.setCanEdit(z);
    }

    public void setOnSwitchClick(OnSwitchClick onSwitchClick) {
        this.f8953j = onSwitchClick;
        this.f8950g.setOnStateChange(new C13121(this, onSwitchClick));
        this.f8951h.setOnStateChange(new C13132(this, onSwitchClick));
        this.f8952i.setOnStateChange(new C13143(this, onSwitchClick));
    }
}
