package frc.robot.commands.auto;

import java.util.Map;

import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.commands.PPSwerveControllerCommand;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.constants.SwerveConstants;
import frc.robot.subsystems.chassis.DriveTrain;

public class AutoMap {
	public static DriveTrain driveTrain = RobotContainer.driveTrain;

	public static final Command AutoTest1 = getPath("Test Path 1")
			.andThen(() -> driveTrain.drive(0, 0, 0, true));

	public static final Command AutoTest2 = getPath("Zero Path")
			.andThen(() -> driveTrain.drive(0, 0, 0, true));

	public static final Command AutoTest1001 = getPath("Test Path 1001")
			.andThen(() -> driveTrain.drive(0, 0, 0, true));

	public static Map<String, Command> autoMap = Map.of(
			"AutoTest1", AutoTest1,
			"AutoTest2", AutoTest2,
			"AutoTest1001", AutoTest1001);

	private static PPSwerveControllerCommand getPath(String pathName) {
		return new PPSwerveControllerCommand(
				PathPlanner.loadPath(pathName, SwerveConstants.kMaxVelocityMetersPerSecond,
						SwerveConstants.kMaxAccelerationMetersPerSecond),
				driveTrain::getPose,
				SwerveConstants.SWERVE_KINEMATICS,
				new PIDController(SwerveConstants.kPathingXP, SwerveConstants.kPathingXI, SwerveConstants.kPathingXD),
				new PIDController(SwerveConstants.kPathingYP, SwerveConstants.kPathingYI, SwerveConstants.kPathingYD),
				new PIDController(SwerveConstants.kPathingTP, SwerveConstants.kPathingTI, SwerveConstants.kPathingTD),
				driveTrain::setModuleStates,
				driveTrain);
	}
}
