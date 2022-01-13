// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.utils;

import java.util.HashMap;

import org.opencv.core.Mat;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.CvSink;
import edu.wpi.first.cscore.CvSource;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.util.sendable.Sendable;


public abstract class VisionProcessor implements Sendable{

    private final UsbCamera camera;
    private final CvSink video;
    private final CvSource outputStream;
    private final HashMap<String,Double> values;

    public VisionProcessor(int cameraWidth, int cameraHeight){
        camera = CameraServer.startAutomaticCapture();
        camera.setResolution(cameraWidth, cameraHeight);
        camera.setFPS(30);
        video = CameraServer.getVideo();
        outputStream = CameraServer.putVideo("output", cameraWidth, cameraHeight);
        values = new HashMap<String,Double>();
    }

    /**
     * return your calculation with {@link #put}
     */
    public abstract void calculate(Mat frame);

    public void put(String key, double value) {
        values.put(key, value);
    }

    public double get(String key) {
        return values.get(key);
    }

    public void run() {

        Mat frame = new Mat();
        
        if (video.grabFrame(frame) == 0) return;
        
        outputStream.putFrame(frame);

        calculate(frame);
    }
}