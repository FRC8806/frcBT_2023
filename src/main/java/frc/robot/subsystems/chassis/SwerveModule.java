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

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.sensors.AbsoluteSensorRange;
import com.ctre.phoenix.sensors.CANCoder;
import com.ctre.phoenix.sensors.SensorInitializationStrategy;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.constants.SwerveConstants;

public class SwerveModule {
  private TalonFX throttle;
  private TalonFX rotor;
  private CANCoder encoder;
  private PIDController rotorPID;
  private double encoderOffset;

  public SwerveModule(int throttleID, int rotorID, int encoderID, double encoderOffsetDeg) {
    throttle = new TalonFX(throttleID);
    rotor = new TalonFX(rotorID);
    encoder = new CANCoder(encoderID);
    encoderOffset = encoderOffsetDeg;
    rotorPID = new PIDController(0.012, 0, 0);//0.008
    setup();
  }

  public SwerveModuleState getState() {
    double throttleVelocity = 
        throttle.getSelectedSensorVelocity() * SwerveConstants.kThrottleVelocityConversionFactor;
    return new SwerveModuleState(throttleVelocity, Rotation2d.fromDegrees(encoder.getAbsolutePosition()));
  }

  public SwerveModulePosition getPosition() {
    double throttlePosition = throttle.getSelectedSensorPosition() * SwerveConstants.kThrottlePositionConversionFactor;
    return new SwerveModulePosition(throttlePosition, Rotation2d.fromDegrees(encoder.getAbsolutePosition()));
  }

  public void setState(SwerveModuleState state) {
    SwerveModuleState optimizedState = SwerveModuleState.optimize(state, getState().angle);
    double throttleOutput = optimizedState.speedMetersPerSecond/SwerveConstants.kMaxThrottleVelocity;
    SmartDashboard.putNumber("speedMetersPerSecond", throttleOutput);
    double rotorOutput = rotorPID.calculate(getState().angle.getDegrees(), optimizedState.angle.getDegrees());

    rotor.set(TalonFXControlMode.PercentOutput, -rotorOutput);
    throttle.set(TalonFXControlMode.PercentOutput, throttleOutput*1.2);
  }

  private void setup() {
    //throttle
    throttle.configFactoryDefault();
    throttle.setNeutralMode(NeutralMode.Brake);
    //rotor
    rotor.configFactoryDefault();
    rotor.setNeutralMode(NeutralMode.Brake);
    rotor.configVoltageCompSaturation(SwerveConstants.kVoltageCompensation);
    rotor.enableVoltageCompensation(true);
    //encoder
    encoder.configFactoryDefault();
    encoder.configAbsoluteSensorRange(AbsoluteSensorRange.Signed_PlusMinus180);
    encoder.configMagnetOffset(encoderOffset);
    encoder.configSensorInitializationStrategy(SensorInitializationStrategy.BootToAbsolutePosition);
    //PID controller
    rotorPID.enableContinuousInput(-180, 180);
  }
}