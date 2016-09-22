package com.vnapnic.myvib.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by vnapnic on 7/10/2016.
 */
public class InterestRatesList {
//    @SerializedName("daily_savings")
//    public List<DailySavings> dailySavingses;
    @SerializedName("e_savings")
    public List<ESavings> eSavingses;
    @SerializedName("ordinary_savings")
    public List<OrdinarySavings> ordinarySavingses;
    @SerializedName("progressive_savings")
    public List<ProgressiveSavings> progressiveSavingses;
}
