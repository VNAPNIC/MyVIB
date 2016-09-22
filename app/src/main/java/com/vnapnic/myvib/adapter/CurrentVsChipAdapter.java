package com.vnapnic.myvib.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vnapnic.myvib.R;
import com.vnapnic.myvib.model.CurrentVsChipModel;

import java.util.ArrayList;

/**
 * Created by hnc on 16/08/2016.
 */
public class CurrentVsChipAdapter extends ArrayAdapter<CurrentVsChipModel> {

    public interface IActionAdapter {
        void onClick(CurrentVsChipModel chipModel);
    }

    public static final int STYLE_DESC = 1;
    public static final int STYLE_CONT = 2;
    private IActionAdapter actionAdapter;

    private LayoutInflater inflater;
    private Context context;
    private ArrayList<CurrentVsChipModel> arrData;

    public CurrentVsChipAdapter(Context context, int resource, ArrayList<CurrentVsChipModel> objects) {
        super(context, resource, objects);
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.arrData = objects;
    }

    public void setActionAdapter(IActionAdapter actionAdapter) {
        this.actionAdapter = actionAdapter;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (arrData != null) {
            if (arrData.size() >= 1) {
                final CurrentVsChipModel chipModel = arrData.get(position);
                if (convertView == null) {
                    if (chipModel.getStyle() == STYLE_DESC) {
                        convertView = inflater.inflate(R.layout.item_listview_account_detail_content, parent, false);
                        TextView textView = (TextView) convertView.findViewById(R.id.txt_title);
                        TextView txt_current = (TextView) convertView.findViewById(R.id.txt_current);
                        textView.setText(chipModel.getTitle());
                        txt_current.setText(chipModel.getTxt_current());
                    } else {
                        convertView = inflater.inflate(R.layout.item_listview_account_detail_desc, parent, false);
                        TextView txtTitle = (TextView) convertView.findViewById(R.id.txt_title);
                        TextView txtContent = (TextView) convertView.findViewById(R.id.txt_content);
                        ImageView imageEye = (ImageView) convertView.findViewById(R.id.imv_eye);
                        txtContent.setText(chipModel.getContent());
                        txtTitle.setText(chipModel.getTitle());
                        imageEye.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (actionAdapter == null)
                                    return;
                                actionAdapter.onClick(chipModel);
                            }
                        });
                    }
                }
            }
        }
        return convertView;
    }
}
