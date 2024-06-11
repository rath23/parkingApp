package com.myComp.parkingApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myComp.parkingApp.Entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,String> {
    
}
