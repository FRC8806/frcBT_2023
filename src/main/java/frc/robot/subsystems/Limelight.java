// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableValue;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Limelight extends SubsystemBase {
  private NetworkTable limelight;
  /** Creates a new LimelightTest1109. */
  public Limelight(String name) {
    limelight = NetworkTableInstance.getDefault().getTable(name);
  }

  @Override
  public void periodic() {
  }

  public void setPipeLine(int pipeLine) {
    limelight.getEntry("pipeline").setNumber(pipeLine);
  }

  public double getPipeLine() {
    return limelight.getEntry("getpipe").getDouble(0.0);
  }

  public double getX() {
    return limelight.getEntry("tx").getDouble(0.0);
  }

  public double getY() {
    return limelight.getEntry("ty").getDouble(0.0);
  }

  public double getArea() {
    return limelight.getEntry("ta").getDouble(0.0);
  }

  public double [] getTargetposeInRobotspace() {
    return limelight.getEntry("targetpose_robotspace").getDoubleArray(new double[6]);
  }	
}
