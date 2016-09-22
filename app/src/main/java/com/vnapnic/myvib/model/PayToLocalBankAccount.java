package com.vnapnic.myvib.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vnapnic on 7/15/2016.
 */
public class PayToLocalBankAccount {
    @SerializedName("BankId")
    public String BankId;
    @SerializedName("Code")
    public String Code;
    @SerializedName("Name")
    public String Name;
}
