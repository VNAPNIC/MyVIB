package com.vnapnic.myvib.model;

import java.util.ArrayList;
import java.util.List;

public class AdminAcctAssociateCard {
    private String CardAccount;
    private String CardNo;
    private String CardSerNo;
    private String CardType;
    private Account MasterCardInfo;
    private String PrimaryCard;
    private String STGeneral;
    private List<AdminAcctAssociateCard> listSubCard;

    public void addSubCard(AdminAcctAssociateCard adminAcctAssociateCard) {
        if (this.listSubCard == null) {
            this.listSubCard = new ArrayList();
        }
        this.listSubCard.add(adminAcctAssociateCard);
    }

    public String getCardAccount() {
        return this.CardAccount;
    }

    public String getCardNo() {
        return this.CardNo;
    }

    public String getCardSerNo() {
        return this.CardSerNo;
    }

    public String getCardType() {
        return this.CardType;
    }

    public List<AdminAcctAssociateCard> getListSubCard() {
        if (this.listSubCard == null) {
            this.listSubCard = new ArrayList();
        }
        return this.listSubCard;
    }

    public Account getMasterCardInfo() {
        return this.MasterCardInfo;
    }

    public String getPrimaryCard() {
        return this.PrimaryCard;
    }

    public String getSTGeneral() {
        return this.STGeneral;
    }

    public void setCardAccount(String str) {
        this.CardAccount = str;
    }

    public void setCardNo(String str) {
        this.CardNo = str;
    }

    public void setCardSerNo(String str) {
        this.CardSerNo = str;
    }

    public void setCardType(String str) {
        this.CardType = str;
    }

    public void setListSubCard(List<AdminAcctAssociateCard> list) {
        this.listSubCard = list;
    }

    public void setMasterCardInfo(Account account) {
        this.MasterCardInfo = account;
    }

    public void setPrimaryCard(String str) {
        this.PrimaryCard = str;
    }

    public void setSTGeneral(String str) {
        this.STGeneral = str;
    }
}
