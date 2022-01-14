// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooting;
import frc.robot.subsystems.Chassis;
import frc.robot.Constants;

public class ShootingCalibration extends CommandBase {
  
  private final Chassis chassis;
  private final Shooting shooting;
  private double velocity;
  private Shoot shootCommand;
  private double[] velocities;
  private final MoveCommand moveCommand;

  public ShootingCalibration(Shooting shooting, Chassis chassis) {
    this.chassis = chassis;
    this.shooting = shooting;
    moveCommand = new MoveCommand(chassis, Constants.SHOOTING_VELOCITIES_DIFF);
    velocity = 0;
  }

  @Override
  public void initialize() {
    
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {
    shootCommand.cancel();
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void initSendable(SendableBuilder builder) {
    builder.addBooleanProperty("Shooting Calibration", null, (bool) -> {
      if (bool){
        schedule();
      }
      else {
        cancel();
      }
    });

    builder.addDoubleProperty("Calibration Velocity", null, (vel) -> {velocity = vel;});

    builder.addBooleanProperty("Calibration Shoot", null, (bool) -> {
      if (bool){
        shootCommand = new Shoot(shooting, () -> {return 0;}, velocity);
        shootCommand.schedule();
      }
      else {
        shootCommand.cancel();
      }
    });

    builder.addBooleanProperty("Calibration Save", () -> {return false;}, (bool) -> {
      if (bool){
        double[] temp = new double[velocities.length + 1];

        for (int i = 0; i < velocities.length; i++){
          temp[i] = velocities[i];
        }
        temp[velocities.length] = velocity;

        moveCommand.schedule();
      }
    });

    builder.addDoubleArrayProperty("Calibration Velocities", () -> {return velocities;}, null);
  }
}
