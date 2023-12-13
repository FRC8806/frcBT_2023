//                          ___________
//       8888888888888888  8888888888888888  0000000000000000  6666666666666666  ___________
//       88            88  88            88  00            00  66
//      88            88  88            88  00            00  66                          ________________________
//      88            88  88            88  00            00  66
//     8888888888888888  8888888888888888  00            00  6666666666666666                 _____________
//     88            88  88            88  00            00  66            66         
//    88            88  88            88  00            00  66            66                         _____________________
//    88            88  88            88  00            00  66            66   ________________________
//   8888888888888888  8888888888888888  0000000000000000  6666666666666666                  ____________________
//                         __________________________                    __________

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.chassis.DriveTrain;

public class TeleSwerveControl extends CommandBase{
  private DriveTrain driveTrain;
  private XboxController controller;
  public TeleSwerveControl(DriveTrain driveTrain, XboxController xboxController) {
    this.driveTrain = driveTrain;
    this.controller = xboxController;
    addRequirements(driveTrain);
  }

  @Override
  public void execute() {
    double speedModify = 1 - controller.getRightTriggerAxis();
    driveTrain.drive(controller.getLeftY() * speedModify, controller.getLeftX() * speedModify, -controller.getRightX() * speedModify, true);
  }

  @Override
  public void end(boolean interrupted) {
    driveTrain.drive(0, 0, 0, true);
  }
}