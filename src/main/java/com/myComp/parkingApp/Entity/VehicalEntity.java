package com.myComp.parkingApp.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class VehicalEntity {

    private String ownerName;
    private String vehicalType;
    private String vehicleModel;
    @Id
    private String vehicalNumber;
    
}
