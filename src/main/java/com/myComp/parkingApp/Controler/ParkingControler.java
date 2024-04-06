package com.myComp.parkingApp.Controler;

import org.springframework.web.bind.annotation.*;

import com.myComp.parkingApp.Models.VehicalModel;
import com.myComp.parkingApp.Service.VehicalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("parking")
public class ParkingControler {

    @Autowired
    VehicalService vehicalService;
    
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllVehicle() {
        return vehicalService.getAllVehical();
    }
    
    @GetMapping
    public ResponseEntity<?> getVehicalBYId(@RequestParam String id) {
        return vehicalService.getVehecial(id);
    }
    
    @PostMapping("/addVehical")
    public ResponseEntity<?> addVehicle(@RequestBody VehicalModel vehicalModel) {
        return vehicalService.addVehical(vehicalModel);
    }

    @PutMapping
    public  ResponseEntity<?> updateVehicleInfo(@RequestParam String id , @RequestBody VehicalModel vehicalModel){
        return vehicalService.updateVehical(id,vehicalModel);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteVehicle(@RequestParam String id){
        return vehicalService.deleteVehical(id);
    }
    
}
