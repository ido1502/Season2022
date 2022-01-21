// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants;
import frc.robot.subsystems.ElivatorInside;

public class OpenShackle extends CommandBase {
  /** Creates a new OpenShackle. */
  
  private final ElivatorInside elivator;

  public OpenShackle(ElivatorInside elivator) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.elivator = elivator;
    addRequirements(elivator);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    elivator.SetPowerShackleOpenner(Constants.shackleOpenningMaxPower);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    elivator.SetPowerShackleOpenner(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
