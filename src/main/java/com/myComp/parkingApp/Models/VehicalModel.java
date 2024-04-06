package com.myComp.parkingApp.Models;

public class VehicalModel {
    private String ownerName;
    private String vehicalType;
    private String vehicalNumber;
    
    public String getOwnerName() {
        return ownerName;
    }

    public String getVehicalNumber() {
        return vehicalNumber;
    }

    public String getVehicalType() {
        return vehicalType;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setVehicalNumber(String vehicalNumber) {
        this.vehicalNumber = vehicalNumber;
    }

    public void setVehicalType(String vehicalType) {
        this.vehicalType = vehicalType;
    }
}
