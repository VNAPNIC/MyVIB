package com.vnapnic.myvib.fragments.listphone;

import android.content.Context;
import android.util.Log;
import android.widget.SectionIndexer;

import com.vnapnic.myvib.fragments.selectlist.SlideSelector;
import com.vnapnic.myvib.model.PhoneContact;

import java.util.List;

/**
 * Created by CaNaWan on 7/14/2016.
 */
public class IndexingArrayAdapter extends PhoneSelectListAdapter implements SectionIndexer {
    private final String TAG = this.getClass().getName();

    public IndexingArrayAdapter(Context context, List<PhoneContact> objects) {
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


