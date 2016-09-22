package com.vnapnic.myvib.model;


import com.google.gson.annotations.SerializedName;

public class ExchangeRate {
    @SerializedName("code")
    public String code;
    @SerializedName("type")
    public String type;
    @SerializedName("buy")
    public String buy;
    @SerializedName("sell")
    public String sell;
    @SerializedName("id")
    public String id;

}
