package com.myComp.parkingApp.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class UserEntity {

    private String ownerName;
    @Id
    private String email;
    private String phoneNumber;
    private String address;
    
}
