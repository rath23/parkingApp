package com.myComp.parkingApp.components;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.myComp.parkingApp.Entity.CarNumberPlate;
import com.myComp.parkingApp.Repository.CarNumberPlateRepository;

import io.micrometer.common.lang.NonNull;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.util.List;


@Component
public class CarNumberPlateWebSocketHandler extends TextWebSocketHandler {

  
     @Autowired
    private CarNumberPlateRepository carNumberPlateRepository;

    static {
        // Load the native OpenCV library
        nu.pattern.OpenCV.loadShared();
    }

    @SuppressWarnings("null")
    @Override
    protected void handleTextMessage(@NonNull WebSocketSession session,@NonNull TextMessage message) throws Exception {
        // Capture the image from the webcam
        Mat frame = captureImageFromCamera();

        // Detect number plate
        String numberPlate = detectNumberPlate(frame);

        LocalDateTime timestamp = LocalDateTime.now();

        CarNumberPlate carNumberPlate = new CarNumberPlate();
        carNumberPlate.setNumberPlate(numberPlate);
        carNumberPlate.setTimestamp(timestamp);

        carNumberPlateRepository.save(carNumberPlate);

        session.sendMessage(new TextMessage("Number plate captured and stored: " + numberPlate));
    }

    private Mat captureImageFromCamera() {
        VideoCapture camera = new VideoCapture(0); // 0 is usually the default camera
        Mat frame = new Mat();
        if (camera.isOpened()) {
            camera.read(frame);
            camera.release();
        } else {
            System.out.println("Error: Camera not detected");
        }
        return frame;
    }

    private String detectNumberPlate(Mat frame) {
        CascadeClassifier numberPlateCascade = new CascadeClassifier("src/main/resources/haarcascade_russian_plate_number.xml");

        Mat grayFrame = new Mat();
        Imgproc.cvtColor(frame, grayFrame, Imgproc.COLOR_BGR2GRAY);

        MatOfRect numberPlates = new MatOfRect();
        numberPlateCascade.detectMultiScale(grayFrame, numberPlates);

        List<Rect> numberPlateList = numberPlates.toList();

        for (Rect rect : numberPlateList) {
            Mat numberPlate = new Mat(frame, rect);

            // Perform OCR using Tesseract
            String result = performOCR(numberPlate);

            if (!result.isEmpty()) {
                return result;
            }
        }

        return "No number plate detected";
    }

    private String performOCR(Mat image) {
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("src/main/resources/tessdata");
        tesseract.setLanguage("eng");

        String result = "";
        try {
            BufferedImage bufferedImage = matToBufferedImage(image);
            result = tesseract.doOCR(bufferedImage);
        } catch (TesseractException e) {
            e.printStackTrace();
        }
        return result.trim();
    }

    private BufferedImage matToBufferedImage(Mat mat) {
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if (mat.channels() > 1) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        int bufferSize = mat.channels() * mat.cols() * mat.rows();
        byte[] buffer = new byte[bufferSize];
        mat.get(0, 0, buffer);
        BufferedImage image = new BufferedImage(mat.cols(), mat.rows(), type);
        final byte[] targetPixels = ((java.awt.image.DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(buffer, 0, targetPixels, 0, buffer.length);
        return image;
    }
}
