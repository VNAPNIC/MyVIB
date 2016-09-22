package com.vnapnic.myvib.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vnapnic on 7/10/2016.
 */
public class OrdinarySavings {
    @SerializedName("id")
    public String id;
    @SerializedName("term")
    public String term;
    @SerializedName("last_term")
    public String last_term;
    @SerializedName("quarterly")
    public String quarterly;
    @SerializedName("monthly")
    public String monthly;
    @SerializedName("type")
    public String type;
}

