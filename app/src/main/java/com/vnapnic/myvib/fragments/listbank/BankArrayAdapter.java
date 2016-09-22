package com.vnapnic.myvib.fragments.listbank;

import android.content.Context;
import android.util.Log;
import android.widget.SectionIndexer;

import com.vnapnic.myvib.fragments.selectlist.SlideSelector;
import com.vnapnic.myvib.model.Bank;

import java.util.List;

/**
 * Created by vnapnic on 7/24/2016.
 */
public class BankArrayAdapter  extends BankListadapter implements SectionIndexer {
    private final String TAG = this.getClass().getName();

    public BankArrayAdapter(Context context, List<Bank> objects) {
        super(context, objects);
    }

    public Object[] getSections() {
        String[] chars = new String[SlideSelector.ALPHABET.length];
        for (int i = 0; i < SlideSelector.ALPHABET.length; i++) {
            chars[i] = String.valueOf(SlideSelector.ALPHABET[i]);
        }

        return chars;
    }

    public int getPositionForSection(int i) {

        Log.d(TAG, "getPositionForSection " + i);
        return (int) (getCount() * ((float)i/(float)getSections().length));
    }

    public int getSectionForPosition(int i) {
        return 0;
    }
}


