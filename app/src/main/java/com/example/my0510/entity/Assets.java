package com.example.my0510.entity;

import com.example.my0510.dao.AssetsDao;

import java.io.Serializable;

public class Assets implements Serializable {
    private int id;
    private String assetsName;
    private String assetsType;
    private Double assetsMoney;
    private String Remarks;

    @Override
    public String toString() {
        return "Assets{" +
                "id=" + id +
                ", assetsName='" + assetsName + '\'' +
                ", assetsType='" + assetsType + '\'' +
                ", assetsMoney=" + assetsMoney +
                ", Remarks='" + Remarks + '\'' +
                '}';
    }

    public Assets(){

    }

    public Assets(int id, String assetsName, String assetsType, double assetsMoney, String remarks) {
        this.id = id;
        this.assetsName = assetsName;
        this.assetsType = assetsType;
        this.assetsMoney = assetsMoney;
        Remarks = remarks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAssetsName() {
        return assetsName;
    }

    public void setAssetsName(String assetsName) {
        this.assetsName = assetsName;
    }

    public String getAssetsType() {
        return assetsType;
    }

    public void setAssetsType(String assetsType) {
        this.assetsType = assetsType;
    }

    public Double getAssetsMoney() {
        return assetsMoney;
    }

    public void setAssetsMoney(Double assetsMoney) {
        this.assetsMoney = assetsMoney;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }


}
