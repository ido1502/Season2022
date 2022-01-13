// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.vision;

import org.opencv.core.Mat;

import edu.wpi.first.util.sendable.SendableBuilder;
import frc.robot.utils.VisionProcessor;

/** Add your docs here. */
public class Processor extends VisionProcessor{

    public Processor(int cameraWidth, int cameraHeight){
        super(cameraWidth, cameraHeight);
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        builder.addDoubleProperty("Vision Distance", () -> {return get("distance");}, null);
        builder.addDoubleProperty("Vision Angle", () -> {return get("angle");}, null);
    }

    @Override
    public void calculate(Mat frame) {
        //TODO : vision
    }
}
