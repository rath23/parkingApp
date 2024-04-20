package com.myComp.parkingApp.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.myComp.parkingApp.Exceptions.VehicalCannotUpdat;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.myComp.parkingApp.Entity.VehicalEntity;
import com.myComp.parkingApp.Exceptions.DataBaseEmpty;
import com.myComp.parkingApp.Exceptions.DeletionError;
import com.myComp.parkingApp.Exceptions.VehicalExist;
import com.myComp.parkingApp.Exceptions.VehicalNotFound;
import com.myComp.parkingApp.Models.VehicalModel;
import com.myComp.parkingApp.Repository.VehicalRepository;

@Service
public class VehicalServiceImpl implements VehicalService{

    VehicalRepository vehicalRepository;

    public VehicalServiceImpl(VehicalRepository vehicalRepository){
        this.vehicalRepository=vehicalRepository;
    } 

    @Override
    public ResponseEntity<?> getAllVehical() {
        List<VehicalModel> vehicalListToRtn = new ArrayList<>();
        List<VehicalEntity> vehicalListToExcess = vehicalRepository.findAll();
        for(VehicalEntity vehicalEntity:vehicalListToExcess){
            VehicalModel vehicalModel = new VehicalModel();
            BeanUtils.copyProperties(vehicalEntity, vehicalModel);
            vehicalListToRtn.add(vehicalModel);
        }
        if(vehicalListToRtn.isEmpty()){
        throw new DataBaseEmpty("Data Base is Empty");
        }
        return ResponseEntity.ok().body(vehicalListToRtn);
    }

    @Override
    public ResponseEntity<?> getVehecial(String vehicalNumber) {
        Optional<VehicalEntity> checkEntity = vehicalRepository.findById(vehicalNumber);
        if(checkEntity.isEmpty()){
            throw new VehicalNotFound("There is no vehicle with this vehicle number.");
        }
        VehicalModel vehicalModel = new VehicalModel();
        VehicalEntity vehicalEntity = vehicalRepository.findById(vehicalNumber).get();
        BeanUtils.copyProperties(vehicalEntity,vehicalModel);
        return ResponseEntity.ok().body(vehicalModel);
    }

    @Override
    public ResponseEntity<?> addVehical(VehicalModel vehicalModel) {
        Optional<VehicalEntity> existingVehicle = vehicalRepository.findById(vehicalModel.getVehicalNumber());
        if(existingVehicle.isPresent()) {
            throw new VehicalExist("Vehicle Number already exists.");
        }
        VehicalEntity vehicalEntity = new VehicalEntity();
        BeanUtils.copyProperties(vehicalModel, vehicalEntity);
        vehicalRepository.save(vehicalEntity);
        return ResponseEntity.ok().body(vehicalModel);
    }

    @Override
    public ResponseEntity<?> updateVehical(String vehicalNumber, VehicalModel vehicalModel) {
        Optional<VehicalEntity> checkEntity = vehicalRepository.findById(vehicalNumber);
        if (checkEntity.isEmpty()) {
            throw new VehicalCannotUpdat("No vehicle with this vehicle number was found.");
        }
        VehicalEntity vehicalEntity = vehicalRepository.findById(vehicalNumber).get();
        vehicalEntity.setOwnerName(vehicalModel.getOwnerName());
        vehicalEntity.setVehicalType(vehicalModel.getVehicalType());
        vehicalEntity.setVehicleModel(vehicalModel.getVehicleModel());
        vehicalRepository.save(vehicalEntity);
        return  ResponseEntity.ok().body(vehicalModel);
    }

    @Override
    public ResponseEntity<?> deleteVehical(String vehicalNumber) {
        Optional<VehicalEntity> checkEntity = vehicalRepository.findById(vehicalNumber);
        if (checkEntity.isEmpty()) {
            throw new DeletionError("No Vehicle found to be deleted please check vehicle number");
        }
        VehicalEntity vehicalEntity = vehicalRepository.findById(vehicalNumber).get();
        vehicalRepository.delete(vehicalEntity);
        return ResponseEntity.ok().body("Deleted successfully.");
    }
    
}
