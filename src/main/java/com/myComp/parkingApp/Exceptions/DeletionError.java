package com.myComp.parkingApp.Exceptions;

public class DeletionError extends  RuntimeException{
    public DeletionError(String msg){
        super(msg);
    }
}
