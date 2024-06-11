package com.myComp.parkingApp.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.myComp.parkingApp.Entity.UserEntity;
import com.myComp.parkingApp.Repository.UserRepository;

@Service
public class QRCodeServiceImp  implements QRCodeService{

    
     @Autowired
    private UserRepository userRepository;

    @Override
    public byte[] generateQRCodeFromUser(String email, int width, int height) throws WriterException, IOException {
        Optional<UserEntity> userOptional = userRepository.findById(email);
        if (!userOptional.isPresent()) {
            throw new IllegalArgumentException("User not found");
        }

        UserEntity user = userOptional.get();
        String userInfo = "Name: " + user.getOwnerName() + "\nEmail: " + user.getEmail() + "\nPhone Number: "+user.getPhoneNumber()+"\nAddress: "+user.getAddress();

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        BitMatrix bitMatrix = qrCodeWriter.encode(userInfo, BarcodeFormat.QR_CODE, width, height, hints);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        return pngOutputStream.toByteArray();
    }
    
}
