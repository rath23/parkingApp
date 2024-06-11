package com.myComp.parkingApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myComp.parkingApp.Entity.CarNumberPlate;

public interface CarNumberPlateRepository extends JpaRepository<CarNumberPlate, Long> {
}
