package com.myComp.parkingApp.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.myComp.parkingApp.Exceptions.VehicalCannotUpdat;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.myComp.parkingApp.Entity.UserEntity;
import com.myComp.parkingApp.Exceptions.DataBaseEmpty;
import com.myComp.parkingApp.Exceptions.DeletionError;
import com.myComp.parkingApp.Exceptions.VehicalExist;
import com.myComp.parkingApp.Exceptions.VehicalNotFound;
import com.myComp.parkingApp.Models.UserModel;
import com.myComp.parkingApp.Repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    } 

    @Override
    public ResponseEntity<?> getAllUser() {
        List<UserModel> vehicalListToRtn = new ArrayList<>();
        List<UserEntity> vehicalListToExcess = userRepository.findAll();
        for(UserEntity vehicalEntity:vehicalListToExcess){
            UserModel vehicalModel = new UserModel();
            BeanUtils.copyProperties(vehicalEntity, vehicalModel);
            vehicalListToRtn.add(vehicalModel);
        }
        if(vehicalListToRtn.isEmpty()){
        throw new DataBaseEmpty("Data Base is Empty");
        }
        return ResponseEntity.ok().body(vehicalListToRtn);
    }

    @Override
    public ResponseEntity<?> getUser(String email) {
        Optional<UserEntity> checkEntity = userRepository.findById(email);
        if(checkEntity.isEmpty()){
            throw new VehicalNotFound("There is no vehicle with this vehicle number.");
        }
        UserModel userModel = new UserModel();
        UserEntity userEntity = userRepository.findById(email).get();
        BeanUtils.copyProperties(userEntity,userModel);
        return ResponseEntity.ok().body(userModel);
    }

    @Override
    public ResponseEntity<?> addUser(UserModel userModel) {
        Optional<UserEntity> existingVehicle = userRepository.findById(userModel.getEmail());
        if(existingVehicle.isPresent()) {
            throw new VehicalExist("Vehicle Number already exists.");
        }
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userModel, userEntity);
        userRepository.save(userEntity);
        return ResponseEntity.ok().body(userModel);
    }

    @Override
    public ResponseEntity<?> updateUser(String email, UserModel userModel) {
        Optional<UserEntity> checkEntity = userRepository.findById(email);
        if (checkEntity.isEmpty()) {
            throw new VehicalCannotUpdat("No vehicle with this vehicle number was found.");
        }
        UserEntity userEntity = userRepository.findById(email).get();
        userEntity.setOwnerName(userModel.getOwnerName());
        userEntity.setPhoneNumber(userModel.getPhoneNumber());
        userEntity.setAddress(userModel.getAddress());
        userRepository.save(userEntity);
        return  ResponseEntity.ok().body(userModel);
    }

    @Override
    public ResponseEntity<?> deleteUser(String email) {
        Optional<UserEntity> checkEntity = userRepository.findById(email);
        if (checkEntity.isEmpty()) {
            throw new DeletionError("No Vehicle found to be deleted please check vehicle number");
        }
        UserEntity vehicalEntity = userRepository.findById(email).get();
        userRepository.delete(vehicalEntity);
        return ResponseEntity.ok().body("Deleted successfully.");
    }
}
