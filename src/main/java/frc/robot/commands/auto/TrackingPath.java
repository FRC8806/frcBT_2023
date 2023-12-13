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

package frc.robot.commands.auto;

import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.commands.PPSwerveControllerCommand;

import edu.wpi.first.math.controller.PIDController;
import frc.robot.constants.SwerveConstants;
import frc.robot.subsystems.chassis.DriveTrain;

public class TrackingPath extends PPSwerveControllerCommand{
  public TrackingPath(DriveTrain driveTrain,String pathName) {
    super(
        PathPlanner.loadPath(pathName, SwerveConstants.kMaxVelocityMetersPerSecond, SwerveConstants.kMaxAccelerationMetersPerSecond), 
        driveTrain::getPose, 
        SwerveConstants.SWERVE_KINEMATICS, 
        new PIDController(SwerveConstants.kPathingXP, SwerveConstants.kPathingXI, SwerveConstants.kPathingXD), 
        new PIDController(SwerveConstants.kPathingYP, SwerveConstants.kPathingYI, SwerveConstants.kPathingYD), 
        new PIDController(SwerveConstants.kPathingTP, SwerveConstants.kPathingTI, SwerveConstants.kPathingTD), 
        driveTrain::setModuleStates, 
        driveTrain
    );
  }
}
