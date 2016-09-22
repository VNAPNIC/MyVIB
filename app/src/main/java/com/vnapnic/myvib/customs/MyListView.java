package com.vnapnic.myvib.customs;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vnapnic.myvib.R;

public class MyListView extends RelativeLayout {
    private OnLoadMore f9134a;
    private OnItemClick f9135b;
    private ListView listView;
    private TextView txtData;
    private RelativeLayout f9138e;
    private int f9139f;
    private int f9140g;
    private boolean f9141h;

    public interface OnItemClick {
        void m13069a(int i);
    }

    /* renamed from: com.vn.vib.mobileapp.customviews.MyListView.1 */
    class C13401 implements OnScrollListener {
        final /* synthetic */ MyListView f9131a;
        private int f9132b;

        C13401(MyListView myListView) {
            this.f9131a = myListView;
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            Object obj = i + i2 >= i3 ? 1 : null;
            if (!(!this.f9131a.f9141h || obj == null || i3 == this.f9132b || this.f9131a.f9138e.getVisibility() == 0)) {
                this.f9131a.f9134a.m14286a();
            }
            this.f9132b = i + i2;
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
        }
    }

    /* renamed from: com.vn.vib.mobileapp.customviews.MyListView.2 */
    class C13412 implements OnItemClickListener {
        final /* synthetic */ MyListView f9133a;

        C13412(MyListView myListView) {
            this.f9133a = myListView;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            this.f9133a.f9135b.m13069a(i);
        }
    }

    public interface OnLoadMore {
        void m14286a();
    }

    public MyListView(Context context) {
        super(context);
        this.f9139f = 1;
        this.f9140g = 10;
        View.inflate(context, R.layout.layout_my_listview, this);
        m14291f();
    }

    public MyListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f9139f = 1;
        this.f9140g = 10;
        View.inflate(context, R.layout.layout_my_listview, this);
        m14291f();
    }

    private void m14291f() {
        this.listView = (ListView) findViewById(R.id.lv);
        this.txtData = (TextView) findViewById(R.id.tvNoData);
        this.f9138e = (RelativeLayout) findViewById(R.id.pb);
    }

    public void m14292a() {
        if (this.listView.getAdapter() != null) {
            if (this.listView.getAdapter().getCount() == 0) {
                this.listView.setVisibility(8);
                this.txtData.setVisibility(0);
                return;
            }
            this.listView.setVisibility(0);
            this.txtData.setVisibility(8);
        }
    }

    public void m14293a(int i, int i2) {
        this.listView.setDivider(new ColorDrawable(getResources().getColor(i)));
        this.listView.setDividerHeight(getResources().getDimensionPixelSize(i2));
    }

    public void m14294b() {
        this.f9139f = 1;
        this.f9141h = false;
    }

    public void m14295c() {
        this.f9139f++;
    }

    public void m14296d() {
        if (this.f9138e.getVisibility() != 0) {
            this.f9138e.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.pb_slide_in_down));
            this.f9138e.setVisibility(0);
        }
    }

    public void m14297e() {
        if (this.f9138e.getVisibility() != 8) {
            this.f9138e.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.pb_slide_out_up));
            this.f9138e.setVisibility(8);
            m14292a();
        }
    }

    public ListView getLv() {
        return this.listView;
    }

    public OnItemClick getOnItemClick() {
        return this.f9135b;
    }

    public OnLoadMore getOnLoadMore() {
        return this.f9134a;
    }

    public int getPageIndex() {
        return this.f9139f;
    }

    public int getPageSize() {
        return this.f9140g;
    }

    public void setAdapter(ListAdapter listAdapter) {
        this.listView.setAdapter(listAdapter);
    }

    public void setCanLoadMore(OnLoadMore onLoadMore) {
        this.f9134a = onLoadMore;
        this.listView.setOnScrollListener(new C13401(this));
    }

    public void setCanLoadMore(boolean z) {
        this.f9141h = z;
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.f9135b = onItemClick;
        this.listView.setOnItemClickListener(new C13412(this));
    }

    public void setPageIndex(int i) {
        this.f9139f = i;
    }

    public void setPageSize(int i) {
        this.f9140g = i;
    }

    public void setTextNoData(int i) {
        this.txtData.setText(getContext().getString(i));
    }

    public void setTextNoData(String str) {
        this.txtData.setText(str);
    }
}
