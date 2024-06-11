package com.myComp.parkingApp.Controler;

import org.springframework.web.bind.annotation.*;

import com.myComp.parkingApp.Models.UserModel;
import com.myComp.parkingApp.Service.UserService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("parking")
public class UserControler {

    @Autowired
    UserService userService;
    
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllUser() {
        return userService.getAllUser();
    }
    
    @GetMapping
    public ResponseEntity<?> getUserBYId(@RequestParam String id) {
        return userService.getUser(id);
    }
    
    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@Valid @RequestBody UserModel userModel) {
        return userService.addUser(userModel);
    }

    @PutMapping
    public  ResponseEntity<?> updateUserInfo(@RequestParam String id , @RequestBody UserModel userModel){
        return userService.updateUser(id,userModel);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestParam String id){
        return userService.deleteUser(id);
    }
    
}
