package com.vnapnic.myvib.model;

import java.io.Serializable;

/**
 * Created by hnc on 03/08/2016.
 */
public class BillSelect implements Serializable{
    private int icon ;
    private String title ;

    public BillSelect() {
    }

    public BillSelect(int icon, String title) {
        this.icon = icon;
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public String getTitle() {
        return title;
    }
}
