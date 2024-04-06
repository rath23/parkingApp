package com.myComp.parkingApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myComp.parkingApp.Entity.VehicalEntity;

@Repository
public interface VehicalRepository extends JpaRepository<VehicalEntity,String> {
    
}
