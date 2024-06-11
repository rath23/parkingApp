package com.myComp.parkingApp.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class CarNumberPlate{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String numberPlate;
    private LocalDateTime timestamp;
    
}
