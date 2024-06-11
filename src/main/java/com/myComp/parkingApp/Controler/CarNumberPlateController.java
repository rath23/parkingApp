package com.myComp.parkingApp.Controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CarNumberPlateController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
}