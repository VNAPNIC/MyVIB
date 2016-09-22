package com.vnapnic.myvib.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by vnapnic on 7/5/2016.
 */
public class AtmLocator {

    @SerializedName("List")
    public List<ATMAddress> List;
}
