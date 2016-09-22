package com.vnapnic.myvib.fragments.listphone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.vnapnic.myvib.R;
import com.vnapnic.myvib.customs.FontTextView;
import com.vnapnic.myvib.model.Account;
import com.vnapnic.myvib.model.PhoneContact;

import java.util.List;

/**
 * Created by vnapnic on 7/6/2016.
 */
public class PhoneSelectListAdapter extends BaseAdapter {
    Context context;
    List<PhoneContact> objects;
    private IOnAction actionClick;

    public interface IOnAction {
        void onEventClick(PhoneContact contact);
    }

    public PhoneSelectListAdapter(Context context, List<PhoneContact> list) {
        this.context = context;
        this.objects = list;
    }

    public void setActionClick(IOnAction actionClick) {
        this.actionClick = actionClick;
    }

    public int getCount() {
        return this.objects.size();
    }

    public Object getItem(int i) {
        return this.objects.get(i);
    }

    public long getItemId(int i) {
        return 0;
    }

    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(this.context).inflate(R.layout.select_list_item, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.name.setText(objects.get(position).name);
        viewHolder.number.setText(objects.get(position).phone);
        Click click = new Click(objects.get(position));
        view.setOnClickListener(click);
        return view;
    }

    private class Click implements View.OnClickListener {
        private PhoneContact contact;

        public Click(PhoneContact contact) {
            this.contact = contact;
        }

        @Override
        public void onClick(View v) {
            actionClick.onEventClick(contact);
        }
    }

    private class ViewHolder {
        private FontTextView name, number;

        public ViewHolder(View view) {
            name = (FontTextView) view.findViewById(R.id.name);
            number = (FontTextView) view.findViewById(R.id.phone_number);
        }

    }
}
