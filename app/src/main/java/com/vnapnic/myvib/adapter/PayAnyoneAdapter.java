package com.vnapnic.myvib.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vnapnic.myvib.R;
import com.vnapnic.myvib.model.Bill;
import com.vnapnic.myvib.model.CurrentVsChipModel;
import com.vnapnic.myvib.model.PayAnyone;

import java.util.ArrayList;

/**
 * Created by hnc on 16/08/2016.
 */
public class PayAnyoneAdapter extends ArrayAdapter<Bill> {

    public interface IActionPayAnyoneAdapter {
        void onClick(Bill bill);
    }

    public static final int STYLE_TITLE = 1;
    public static final int STYLE_DESC = 2;

    private IActionPayAnyoneAdapter actionAdapter;

    private LayoutInflater inflater;
    private Context context;
    private ArrayList<Bill> arrData;

    public PayAnyoneAdapter(Context context, int resource, ArrayList<Bill> objects) {
        super(context, resource, objects);
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.arrData = objects;
    }

    public void setActionAdapter(IActionPayAnyoneAdapter actionAdapter) {
        this.actionAdapter = actionAdapter;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (arrData != null) {
            if (arrData.size() >= 1) {
                final Bill bill = arrData.get(position);
                if (convertView == null) {
                    if (bill.style == STYLE_TITLE) {
                        convertView = inflater.inflate(R.layout.item_heder_list, parent, false);
                        TextView textView = (TextView) convertView.findViewById(R.id.header_list);
                        textView.setText(bill.title);
                    } else {
                        convertView = inflater.inflate(R.layout.item_anyone, parent, false);
                        ((ImageView) convertView.findViewById(R.id.icon)).setImageResource(bill.icon);
                        ((TextView) convertView.findViewById(R.id.title)).setText(bill.title);
                        ((TextView) convertView.findViewById(R.id.phone)).setText(bill.phone);
                        if(!TextUtils.isEmpty(bill.address)) {
                            ((TextView) convertView.findViewById(R.id.address)).setVisibility(View.VISIBLE);
                            ((TextView) convertView.findViewById(R.id.address)).setText(bill.address);
                        }
                        convertView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (actionAdapter == null)
                                    return;
                                actionAdapter.onClick(bill);
                            }
                        });
                    }
                }
            }
        }
        return convertView;
    }
}
