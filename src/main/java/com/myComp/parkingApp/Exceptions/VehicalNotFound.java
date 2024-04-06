package com.myComp.parkingApp.Exceptions;

public class VehicalNotFound extends RuntimeException{
    public VehicalNotFound(String msg){
        super(msg);
    }
}
