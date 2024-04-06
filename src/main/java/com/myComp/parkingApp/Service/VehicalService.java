package com.myComp.parkingApp.Service;

import org.springframework.http.ResponseEntity;

import com.myComp.parkingApp.Models.VehicalModel;

public interface VehicalService {

    public ResponseEntity<?> getAllVehical();
    public ResponseEntity<?> getVehecial(String vehicalNumber);
    public ResponseEntity<?> addVehical(VehicalModel vehicalModel);
    public ResponseEntity<?> updateVehical(String vehicalNumber , VehicalModel vehicalModel);
    public ResponseEntity<?> deleteVehical(String vehicalNumber);
    
} 
    

