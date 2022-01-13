// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.drive.Vector2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooting;
import frc.robot.subsystems.Chassis;
import frc.robot.Constants;

public class AutoShoot extends CommandBase {
  
  private final Chassis chassis;
  private final Shooting shooting;
  private double angle;
  private double distance;
  private Command command;

  public AutoShoot(Shooting shooting, Chassis chassis) {
    this.chassis = chassis;
    this.shooting = shooting;
  }

  @Override
  public void initialize() {
    Vector2d temp = shooting.getVision();
    distance = temp.x;
    angle = temp.y + chassis.getAngle();

    command = new Turn(angle).alongWith(new Shoot(shooting, this::getAngleDiff, calculateVelocity()));
  }

  /**
   * calculates the velocity needed to shoot to the target
   * @return the wanted velocity in meter/sec if out of range, returns Double.NaN
   */
  private double calculateVelocity(){
    if (distance < Constants.MIN_SHOOTING_DISTANCE){
      end(false);
      return Double.NaN;
    }

    for (int i = 0; i < Constants.SHOOTING_VELOCITIES.length; i++) {
      double currentDistance = Constants.MIN_SHOOTING_DISTANCE + Constants.SHOOTING_VELOCITIES_DIFF * i;
      if (currentDistance > distance){
        double slope = (Constants.SHOOTING_VELOCITIES[i] - Constants.SHOOTING_VELOCITIES[i - 1]) /
            Constants.SHOOTING_VELOCITIES_DIFF;
        return slope * (distance - currentDistance) + Constants.SHOOTING_VELOCITIES[i];
      }
    }

    end(false);
    return Double.NaN;
  }

  /**
   * calculates the difference between your angle and the desired angle
   * @return the difference in absolute
   */
  private double getAngleDiff(){
    return Math.abs(angle - chassis.getAngle());
  }

  @Override
  public void end(boolean interrupted) {
    command.end(interrupted);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
