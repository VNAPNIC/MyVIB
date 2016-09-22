package com.vnapnic.myvib.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vnapnic.myvib.R;
import com.vnapnic.myvib.model.BillSelect;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hnc on 03/08/2016.
 */
public class SelectBillAdapter extends ArrayAdapter<BillSelect> {

    private ArrayList<BillSelect> arrBillSelect;
    private Context context;
    private LayoutInflater inflater;
    private ActionItem actionItem;

    public interface ActionItem {
        void onClickItem(BillSelect billSelect);
    }

    public SelectBillAdapter(Context context, int resource, ArrayList<BillSelect> arrBillSelect, ActionItem actionItem) {
        super(context, resource, arrBillSelect);
        this.arrBillSelect = arrBillSelect;
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.actionItem = actionItem;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_bill_select, parent, false);
            holder.imageView = (ImageView) convertView.findViewById(R.id.image_item_bill_select);
            holder.textView = (TextView) convertView.findViewById(R.id.name_item_bill_select);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        BillSelect select = arrBillSelect.get(position);
        holder.imageView.setImageResource(select.getIcon());
        holder.textView.setText(select.getTitle());
        convertView.setOnClickListener(new onClickItem(select));
        return convertView;
    }

    private class onClickItem implements View.OnClickListener {
        BillSelect billSelect;

        public onClickItem(BillSelect billSelect) {
            this.billSelect = billSelect;
        }

        @Override
        public void onClick(View v) {
            actionItem.onClickItem(billSelect);
        }
    }


    private class ViewHolder {
        ImageView imageView;
        TextView textView;
    }
}
