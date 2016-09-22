package com.vnapnic.myvib.fragments.listbank;

/**
 * Created by hnc on 04/08/2016.
 */
public class BanksModel
{
    private String id ;
    private String bankId;
    private String code ;
    private String name ;
    private String city ;
    private int style ;

    public BanksModel(String id, String bankId, String code, String name, String city, int style) {
        this.id = id;
        this.bankId = bankId;
        this.code = code;
        this.name = name;
        this.city = city;
        this.style = style;
    }

    public String getId() {
        return id;
    }

    public String getBankId() {
        return bankId;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public int getStyle() {
        return style;
    }
}
