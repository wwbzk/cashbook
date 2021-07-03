package com.example.my0510.entity;

import java.io.Serializable;

public class Account implements Serializable {
    private int id;
    //记账金额
    private double accountMoney;
    //账目分类 衣食住行其他
    private String accountType;
    //账目类型  支出、收入
    private String payType;
    //所属账户  微信、支付宝
    private String assetsName;
    //创建时间
    private String time;
    //备注
    private String Remarks;

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountMoney=" + accountMoney +
                ", accountType='" + accountType + '\'' +
                ", payType='" + payType + '\'' +
                ", assetsName='" + assetsName + '\'' +
                ", time='" + time + '\'' +
                ", Remarks='" + Remarks + '\'' +
                '}';
    }

    public Account(int id, double accountMoney, String accountType, String payType, String assetsName, String time, String remarks) {
        this.id = id;
        this.accountMoney = accountMoney;
        this.accountType = accountType;
        this.payType = payType;
        this.assetsName = assetsName;
        this.time = time;
        Remarks = remarks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAccountMoney() {
        return accountMoney;
    }

    public void setAccountMoney(double accountMoney) {
        this.accountMoney = accountMoney;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getAssetsName() {
        return assetsName;
    }

    public void setAssetsName(String assetsName) {
        this.assetsName = assetsName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }
}
