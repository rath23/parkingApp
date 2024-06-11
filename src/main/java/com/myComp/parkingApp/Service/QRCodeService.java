package com.myComp.parkingApp.Service;

import java.io.IOException;

import com.google.zxing.WriterException;

public interface QRCodeService {
    public byte[] generateQRCodeFromUser(String userId, int width, int height)throws WriterException, IOException ;
}
