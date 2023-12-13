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

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.ChangPipeLine;
import frc.robot.commands.TeleSwerveControl;
import frc.robot.commands.auto.AprilTagTracking;
import frc.robot.commands.auto.AutoMap;
import frc.robot.constants.ControllerConstants;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.chassis.DriveTrain;

public class RobotContainer {
  // instantiate controller here
  public static XboxController driverController = new XboxController(ControllerConstants.DRIVER_CONTROLLER_PORT);
  public static XboxController operaterController = new XboxController(ControllerConstants.OPERATER_CONTROLLER_PORT);

  // instantiate subsystem here
  public static DriveTrain driveTrain = new DriveTrain();
  public static Limelight limelight = new Limelight("limelight-front");

  // instantiate command here
  private TeleSwerveControl driveCommand = new TeleSwerveControl(driveTrain, driverController);

  // public static DashBoard dashBoard = new DashBoard();

  public RobotContainer() {
    // Binding commands on subsystems
    driveTrain.setDefaultCommand(driveCommand);

    // Binding commands on triggers
    new Trigger(driverController::getAButton).whileTrue(new AprilTagTracking(driveTrain, limelight));
    new Trigger(driverController::getBButton).toggleOnTrue(new ChangPipeLine(limelight, 1));
    new Trigger(driverController::getXButton).toggleOnTrue(new ChangPipeLine(limelight, 2));
  }

  public Command getAutoCommand() {
    return AutoMap.autoMap.get(DashBoard.autoString);
  }
}
