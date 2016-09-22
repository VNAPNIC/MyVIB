package com.vnapnic.myvib.fragments.listphone;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by ThoNguyenHuu on 01/08/2016.
 */
public class GetContacts {

    private static final String TAG = "GetContacts";


    private ArrayList<ContactsModel> arrContacts;

    public GetContacts(Context context) {
        arrContacts = new ArrayList<>();


        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projection = new String[]{
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.Contacts.PHOTO_THUMBNAIL_URI,
                ContactsContract.Contacts.Photo.PHOTO};


        Cursor cursor = context.getContentResolver().query(uri, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phone = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            String imageUri = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI));
            Log.d(TAG, "\n" + name);
            arrContacts.add(new ContactsModel(1, imageUri, name, phone));
            cursor.moveToNext();
        }
        cursor.close();
    }

    public ArrayList<ContactsModel> getArrContacts() {
        return arrContacts;
    }
}
