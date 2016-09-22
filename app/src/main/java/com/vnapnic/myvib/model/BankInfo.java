package com.vnapnic.myvib.model;

public class BankInfo {
    private String Address;
    private String AddressCity;
    private String AddressCountry;
    private String BankId;
    private String BranchId;
    private String BranchName;
    private String Code;
    private String Name;

    public String getAddress() {
        return this.Address;
    }

    public String getAddressCity() {
        return this.AddressCity;
    }

    public String getAddressCountry() {
        return this.AddressCountry;
    }

    public String getBankId() {
        return this.BankId;
    }

    public String getBranchId() {
        return this.BranchId;
    }

    public String getBranchName() {
        return this.BranchName;
    }

    public String getCode() {
        return this.Code;
    }

    public String getName() {
        return this.Name;
    }

    public void setAddress(String str) {
        this.Address = str;
    }

    public void setAddressCity(String str) {
        this.AddressCity = str;
    }

    public void setAddressCountry(String str) {
        this.AddressCountry = str;
    }

    public void setBankId(String str) {
        this.BankId = str;
    }

    public void setBranchId(String str) {
        this.BranchId = str;
    }

    public void setBranchName(String str) {
        this.BranchName = str;
    }

    public void setCode(String str) {
        this.Code = str;
    }

    public void setName(String str) {
        this.Name = str;
    }
}
