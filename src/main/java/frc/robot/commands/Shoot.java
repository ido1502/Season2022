// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Shooting;

public class Shoot extends CommandBase {
  
  private final Shooting shooting;
  private final double velocity;
  private final DoubleSupplier getAngleDiff;

  public Shoot(Shooting shooting, DoubleSupplier getAngleDiff, double velocity) {
    this.shooting = shooting;
    this.velocity = velocity;
    this.getAngleDiff = getAngleDiff;

    addRequirements(shooting);
  }

  @Override
  public void initialize() {
    shooting.setShooterVelocity(velocity);
  }

  @Override
  public void execute() {
    if (Math.abs(shooting.getShooterVelocity() - velocity) <= Constants.MAX_SHOOT_VELOCITY_ERROR && 
        getAngleDiff.getAsDouble() <= Constants.MAX_SHOOT_ANGLE_ERROR){
          
      shooting.openShooterInput();
    }
  }

  @Override
  public void end(boolean interrupted) {
    shooting.closeShooterInput();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
