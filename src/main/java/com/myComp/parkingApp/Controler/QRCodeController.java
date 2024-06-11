package com.myComp.parkingApp.Controler;

import com.google.zxing.WriterException;
import com.myComp.parkingApp.Service.QRCodeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class QRCodeController {

    @Autowired
    private QRCodeService qrCodeService;

    @GetMapping("/user/{email}/qrCode")
    public ResponseEntity<byte[]> generateUserQRCode(@PathVariable String email) {
        try {
            byte[] imageBytes = qrCodeService.generateQRCodeFromUser(email, 250, 250);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            headers.setContentLength(imageBytes.length);

            return ResponseEntity.ok().headers(headers).body(imageBytes);

        } catch (WriterException | IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(null);
        }
    }
}

