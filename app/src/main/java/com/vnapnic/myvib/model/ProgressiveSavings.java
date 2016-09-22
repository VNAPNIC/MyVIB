package com.vnapnic.myvib.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vnapnic on 7/10/2016.
 */
public class ProgressiveSavings {
    @SerializedName("id")
    public String id;
    @SerializedName("term")
    public String term;
    @SerializedName("type")
    public String type;
    @SerializedName("value")
    public String value;
}
