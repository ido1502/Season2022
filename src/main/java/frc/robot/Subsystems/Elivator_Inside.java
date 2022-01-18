// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import javax.swing.text.html.HTMLDocument.HTMLReader.HiddenAction;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Elivator_Inside extends SubsystemBase {
  /** Creates a new Elivator_Inside. */

  private WPI_TalonFX telescopicMotor;
  private WPI_TalonSRX shackleOpenner;

  public Elivator_Inside() {
    this.telescopicMotor = new WPI_TalonFX(Constants.telescopicMotor);
    this.shackleOpenner = new WPI_TalonSRX(Constants.shackleOpenner);
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
