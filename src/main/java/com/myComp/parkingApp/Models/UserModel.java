package com.myComp.parkingApp.Models;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserModel {
    
    @NotBlank(message = "Vehical owner name is required.")
    private String ownerName;
    @NotBlank(message = "Vehical Model is required.")
    private String email;
    private String phoneNumber;
    private String address;
    
}
