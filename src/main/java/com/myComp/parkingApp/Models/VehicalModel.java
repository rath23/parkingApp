package com.myComp.parkingApp.Models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class VehicalModel {
    
    @NotBlank(message = "Vehical owner name is required.")
    private String ownerName;
    private String vehicleModel;
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

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String carModel) {
        this.vehicleModel = carModel;
    }
}
