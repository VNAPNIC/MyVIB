package com.vnapnic.myvib.fragments.listphone;

import java.io.Serializable;

/**
 * Created by ThoNguyenHuu on 01/08/2016.
 */
public class ContactsModel implements Serializable {
    private int style ;
    private String image ;
    private String contactsName ;
    private String contactsPhoneNumber ;

    public ContactsModel() {
    }

    public ContactsModel(int style, String image, String contactsName, String contactsPhoneNumber) {
        this.style = style;
        this.image = image;
        this.contactsName = contactsName;
        this.contactsPhoneNumber = contactsPhoneNumber;
    }

    public int getStyle() {
        return style;
    }

    public String getImage() {
        return image;
    }

    public String getContactsName() {
        return contactsName;
    }

    public String getContactsPhoneNumber() {
        return contactsPhoneNumber;
    }
}
