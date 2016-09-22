package com.vnapnic.myvib.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vnapnic on 7/5/2016.
 */
public class ATMAddress {
    @SerializedName("Address")
    private String address;
    @SerializedName("CategoryID")
    private String categoryID;
    @SerializedName("Description")
    private String description;
    @SerializedName("Distance")
    private double distance;
    @SerializedName("Latitude")
    private String latitude;
    @SerializedName("Longitude")
    private String longitude;
    @SerializedName("Note1")
    private String note1;
    @SerializedName("Note2")
    private String note2;
    @SerializedName("Note3")
    private String note3;
    @SerializedName("Note4")
    private String note4;
    @SerializedName("Note5")
    private String note5;
    @SerializedName("PostCode")
    private String postCode;
    @SerializedName("Province")
    private String province;
    @SerializedName("TargetName")
    private String targetName;

    public ATMAddress(String str, String str2) {
        this.latitude = str;
        this.longitude = str2;
    }

    public String getAddress() {
        return this.address;
    }

    public String getCategoryID() {
        return this.categoryID;
    }

    public String getDescription() {
        return this.description;
    }

    public double getDistance() {
        return this.distance;
    }

    public String getLatitude() {
        return this.latitude;
    }

    public String getLongitude() {
        return this.longitude;
    }

    public String getNote1() {
        return this.note1;
    }

    public String getNote2() {
        return this.note2;
    }

    public String getNote3() {
        return this.note3;
    }

    public String getNote4() {
        return this.note4;
    }

    public String getNote5() {
        return this.note5;
    }

    public String getPostCode() {
        return this.postCode;
    }

    public String getProvince() {
        return this.province;
    }

    public String getTargetName() {
        return this.targetName;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public void setCategoryID(String str) {
        this.categoryID = str;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setDistance(double d) {
        this.distance = d;
    }

    public void setLatitude(String str) {
        this.latitude = str;
    }

    public void setLongitude(String str) {
        this.longitude = str;
    }

    public void setNote1(String str) {
        this.note1 = str;
    }

    public void setNote2(String str) {
        this.note2 = str;
    }

    public void setNote3(String str) {
        this.note3 = str;
    }

    public void setNote4(String str) {
        this.note4 = str;
    }

    public void setNote5(String str) {
        this.note5 = str;
    }

    public void setPostCode(String str) {
        this.postCode = str;
    }

    public void setProvince(String str) {
        this.province = str;
    }

    public void setTargetName(String str) {
        this.targetName = str;
    }
}
