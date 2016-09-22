package com.vnapnic.myvib.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vnapnic on 7/10/2016.
 */
public class DailySavings {
    @SerializedName("deposit_amount")
    public String deposit_amount;
    @SerializedName("term")
    public String term;
    @SerializedName("value")
    public String value;
    @SerializedName("id")
    public String id;
}
