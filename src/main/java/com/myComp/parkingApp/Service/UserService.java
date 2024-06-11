package com.myComp.parkingApp.Service;

import org.springframework.http.ResponseEntity;

import com.myComp.parkingApp.Models.UserModel;

public interface UserService {

    public ResponseEntity<?> getAllUser();
    public ResponseEntity<?> getUser(String email);
    public ResponseEntity<?> addUser(UserModel userModel);
    public ResponseEntity<?> updateUser(String email , UserModel userModel);
    public ResponseEntity<?> deleteUser(String email);
    
}