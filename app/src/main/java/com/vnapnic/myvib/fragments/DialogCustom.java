package com.vnapnic.myvib.fragments;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.vnapnic.myvib.R;
import com.vnapnic.myvib.utils.Logger;

/**
 * Created by vnapn on 8/11/2016.
 */
public class DialogCustom extends Dialog {
    private String[] strings;
    private static final String DATA = "key.data";
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private onActionDialog actionDialog;
    private int index;

    public interface onActionDialog {
        void onAction(String item, int index);
    }

    public DialogCustom(Context context, String[] strings, onActionDialog actionDialog, int index) {
        super(context);
        this.strings = strings;
        this.actionDialog = actionDialog;
        this.index = index;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        setContentView(R.layout.item_dialog_select);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, strings);
        listView = (ListView) findViewById(R.id.list_item);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // ListView Clicked item index
                int itemPosition = position;
                // ListView Clicked item value
                String itemValue = (String) listView.getItemAtPosition(position);
                Logger.d("namit", "Position :" + itemPosition + "  ListItem : " + itemValue);
                actionDialog.onAction(itemValue, index);
                DialogCustom.this.dismiss();
            }
        });
    }


    @Override
    public void dismiss() {
        super.dismiss();
    }
}
