// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.MoveElivator;

public class ElivatorInside extends SubsystemBase {
  /** Creates a new Elivator_Inside. */

  private final WPI_TalonFX telescopicMotor;
  private final WPI_TalonSRX shackleOpenner;

  public ElivatorInside(XboxController controller) {
    this.telescopicMotor = new WPI_TalonFX(Constants.TELESCOPIC_MOTOR);
    this.shackleOpenner = new WPI_TalonSRX(Constants.SHACKLE_OPENNER);
    setDefaultCommand(new MoveElivator(this, controller));
  }

  /**
   * sets the power of telescopic motor 
   */
  public void SetPowerTelescopicMotor(double power){ 
    this.telescopicMotor.set(ControlMode.PercentOutput, power);
  }

  /**
   * sets the power of shackleOpenner motor 
   */
  public void SetPowerShackleOpenner(double power){ 
    this.shackleOpenner.set(ControlMode.PercentOutput, power);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
