package com.vnapnic.myvib.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by vnapnic on 7/24/2016.
 */
public class Bank implements Serializable {
    @SerializedName("id")
    public String id;
    @SerializedName("BankId")
    public String bankId;
    @SerializedName("Code")
    public String code;
    @SerializedName("Name")
    public String name;
    @SerializedName("city")
    public String city;
    @SerializedName("Branch")
    public String branch;
}
