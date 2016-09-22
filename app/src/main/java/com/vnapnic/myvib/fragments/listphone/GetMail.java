package com.vnapnic.myvib.fragments.listphone;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import com.vnapnic.myvib.utils.Logger;

import java.util.ArrayList;

/**
 * Created by vnapn on 8/7/2016.
 */
public class GetMail {

    private static final String TAG = "GetContacts";


    private ArrayList<ContactsModel> arrContacts;

    public GetMail(Context context) {
        arrContacts = new ArrayList<>();
        ContentResolver cr = context.getContentResolver();
        Cursor cursor = cr.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, PROJECTION, null, null, null);
        if (cursor != null) {
            try {
                final int contactIdIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.CONTACT_ID);
                final int displayNameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
                final int emailIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA);
                long contactId;
                String displayName, email;
                while (cursor.moveToNext()) {
                    contactId = cursor.getLong(contactIdIndex);
                    displayName = cursor.getString(displayNameIndex);
                    email = cursor.getString(emailIndex);
                    Logger.d("namit", "\ncontactId: " + contactId + "\ndisplayName: " + displayName + "\naddress: " + email);
                    arrContacts.add(new ContactsModel(1, null, displayName, email));
                }
            } finally {
                cursor.close();
            }
        }
    }

    private static final String[] PROJECTION = new String[]{
            ContactsContract.CommonDataKinds.Email.CONTACT_ID,
            ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Email.DATA
    };

    public ArrayList<ContactsModel> getArrContacts() {
        return arrContacts;
    }
}
