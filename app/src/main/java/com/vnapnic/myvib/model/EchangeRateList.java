package com.vnapnic.myvib.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by vnapnic on 7/10/2016.
 */
public class EchangeRateList {
    @SerializedName("foreign_exchange_rates")
    public List<ExchangeRate> exchangeRates;
}
