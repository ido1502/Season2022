// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    //motors
    public static final int TELESCOPIC_MOTOR = -1;
    public static final int SHACKLE_OPENNER = -1;
    
    //power
    public static final double SHACKLE_OPENNING_MAX_POWER = -1;

    //controller
    public static final int TRIGER_FOR_SHACKLE = -1;
    public static final int XBOX_CONTROLLER_PORT = 0;
    public static final int STEP_1_BUTTON = -1;
    public static final int STEP_2_BUTTON = -1;
    public static final double JOYSTICK_DEADBAND = -1;

    //elivator
    public static final double PULSES_PER_METER = -1;

    //distane to move
    public static final double DISTANCE_STEP_1 = -1;
    public static final double DISTANCE_STEP_2 = -  1;

    //PID for climbing
    public static final double KP = -1;
    public static final double KI = -1;
    public static final double KD = -1;

    //FeedForward for climbing
    public static final double KS = -1;
    public static final double KV = -1;
}
