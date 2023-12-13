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

package frc.robot.subsystems.chassis;

import com.ctre.phoenix.sensors.Pigeon2;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.SwerveConstants;

public class DriveTrain extends SubsystemBase {
  private Pigeon2 imu = new Pigeon2(SwerveConstants.IMU_ID);
  private SwerveModule moduleA = new SwerveModule(SwerveConstants.A_THROTTLE_ID, SwerveConstants.A_ROTOR_ID, SwerveConstants.A_ENCODER_ID, SwerveConstants.A_ENCODER_OFFSET);
  private SwerveModule moduleB = new SwerveModule(SwerveConstants.B_THROTTLE_ID, SwerveConstants.B_ROTOR_ID, SwerveConstants.B_ENCODER_ID, SwerveConstants.B_ENCODER_OFFSET);
  private SwerveModule moduleC = new SwerveModule(SwerveConstants.C_THROTTLE_ID, SwerveConstants.C_ROTOR_ID, SwerveConstants.C_ENCODER_ID, SwerveConstants.C_ENCODER_OFFSET);
  private SwerveModule moduleD = new SwerveModule(SwerveConstants.D_THROTTLE_ID, SwerveConstants.D_ROTOR_ID, SwerveConstants.D_ENCODER_ID, SwerveConstants.D_ENCODER_OFFSET);
  private SwerveDriveOdometry odometry;
  public DriveTrain() {
    imu.configFactoryDefault();
    odometry = new SwerveDriveOdometry(SwerveConstants.SWERVE_KINEMATICS, getRotation2d(), getModulePositions());
  }

  @Override
  public void periodic() {
    odometry.update(getRotation2d(), getModulePositions());
    SmartDashboard.putNumber("imu", getRotation2d().getDegrees());
    SmartDashboard.putNumber("ae", moduleA.getState().angle.getDegrees());
    SmartDashboard.putNumber("be", moduleB.getState().angle.getDegrees());
    SmartDashboard.putNumber("ce", moduleC.getState().angle.getDegrees());
    SmartDashboard.putNumber("de", moduleD.getState().angle.getDegrees());
    SmartDashboard.putNumber("speed", moduleA.getState().speedMetersPerSecond);
    SmartDashboard.putNumber("positionA", moduleA.getPosition().distanceMeters);
    SmartDashboard.putNumber("positionB", moduleB.getPosition().distanceMeters);
    SmartDashboard.putNumber("positionC", moduleC.getPosition().distanceMeters);
    SmartDashboard.putNumber("positionD", moduleD.getPosition().distanceMeters);
  }

  public void drive(double xSpeed, double ySpeed, double zSpeed, boolean fieldOriented) {
    SwerveModuleState [] states = fieldOriented ? 
        SwerveConstants.SWERVE_KINEMATICS.toSwerveModuleStates(ChassisSpeeds.fromFieldRelativeSpeeds(
          xSpeed * SwerveConstants.kMaxThrottleVelocity, 
          ySpeed * SwerveConstants.kMaxThrottleVelocity, 
          zSpeed * 15.64 * 0.6, getRotation2d())):
        SwerveConstants.SWERVE_KINEMATICS.toSwerveModuleStates(new ChassisSpeeds(xSpeed, ySpeed, zSpeed));
    SwerveModuleState [] zeroStates = {
        new SwerveModuleState(0, Rotation2d.fromDegrees(135)), 
        new SwerveModuleState(0, Rotation2d.fromDegrees(45)), 
        new SwerveModuleState(0, Rotation2d.fromDegrees(135)), 
        new SwerveModuleState(0, Rotation2d.fromDegrees(45))};
    states = Math.abs(xSpeed) < 0.07 
    
    && Math.abs(ySpeed) <0.07 && Math.abs(zSpeed) < 0.07 ? 
        zeroStates : 
        states;
    setModuleStates(states);
  }

  public SwerveModuleState [] getModuleStates() {
    return new SwerveModuleState [] {
      moduleA.getState(),
      moduleB.getState(),
      moduleC.getState(),
      moduleD.getState()
    };
  }

  public SwerveModulePosition [] getModulePositions() {
    return new SwerveModulePosition [] {
      moduleA.getPosition(),
      moduleB.getPosition(),
      moduleC.getPosition(),
      moduleD.getPosition()
    };
  }

  public void setModuleStates(SwerveModuleState [] desiredStates) {
    SwerveDriveKinematics.desaturateWheelSpeeds(desiredStates, SwerveConstants.kMaxThrottleVelocity);
    moduleA.setState(desiredStates[0]);
    moduleB.setState(desiredStates[1]);
    moduleC.setState(desiredStates[2]);
    moduleD.setState(desiredStates[3]);
  }

  public Pose2d getPose() {
    return odometry.getPoseMeters();
  }

  public void resetPose(Pose2d pose) {
    odometry.resetPosition(getRotation2d(), getModulePositions(), pose);
  }

  private Rotation2d getRotation2d() {
    return Rotation2d.fromDegrees(imu.getYaw() - 90);
  }
}
