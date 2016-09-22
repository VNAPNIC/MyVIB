package com.vnapnic.myvib.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vnapnic on 7/10/2016.
 */
public class ESavings {
    @SerializedName("deposit_amount")
    public String deposit_amount;
    @SerializedName("percent_year")
    public String percent_year;
    @SerializedName("id")
    public String id;
}
