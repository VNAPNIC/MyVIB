package com.vnapnic.myvib.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by vnapnic on 7/24/2016.
 */
public class BankList {
    @SerializedName("localbank")
    public List<Bank> bankList;
}
