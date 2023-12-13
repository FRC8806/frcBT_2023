
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.chassis.DriveTrain;

public class AprilTagTracking extends CommandBase {
  private DriveTrain driveTrain;
  private Limelight limelight;
  private double xPosition; 
  private double yPosition;
  // private int counter;
  /** Creates a new AprilTagTracking. */
  public AprilTagTracking(DriveTrain driveTrain, Limelight limelight) {
    this.driveTrain = driveTrain;
    this.limelight = limelight;
    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    driveTrain.drive(0, 0, 0, false);
    limelight.setPipeLine(0);
    xPosition = 0;
    yPosition = 0;
    // counter = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(limelight.getTargetposeInRobotspace()[2] != 0) xPosition = limelight.getTargetposeInRobotspace()[2];
    if(limelight.getTargetposeInRobotspace()[0] != 0) yPosition = limelight.getTargetposeInRobotspace()[0];
    // double xSpeed = limelight.getTargetposeInRobotspace()[2] == 0 ? 0 : -(1.5-limelight.getTargetposeInRobotspace()[2])*2;
    // double ySpeed = limelight.getTargetposeInRobotspace()[0] == 0 ? 0 : -limelight.getTargetposeInRobotspace()[0]*2;
    double xSpeed = -(1.5-xPosition)*2;
    double ySpeed = -yPosition*2;
    double zSpeed = (0-driveTrain.getPose().getRotation().getDegrees())*0.05;
    xSpeed = xSpeed > 1 ? 1 : xSpeed;
    ySpeed = ySpeed > 1 ? 1 : ySpeed;
    driveTrain.drive(xSpeed, ySpeed, zSpeed, false);
    SmartDashboard.putNumber("xP", limelight.getTargetposeInRobotspace()[0]);
    SmartDashboard.putNumber("yP", limelight.getTargetposeInRobotspace()[2]);
    SmartDashboard.putNumber("rP", driveTrain.getPose().getRotation().getDegrees());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveTrain.drive(0, 0, 0, false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
