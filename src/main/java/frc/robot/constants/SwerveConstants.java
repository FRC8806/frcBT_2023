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

package frc.robot.constants;

import com.ctre.phoenix.Util;
import com.ctre.phoenixpro.Utils;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import frc.robot.DashBoard;

public class SwerveConstants {
  //The CAN IDs
  //Throttle
  public static final int A_THROTTLE_ID = 5;
  public static final int B_THROTTLE_ID = 2;
  public static final int C_THROTTLE_ID = 7;
  public static final int D_THROTTLE_ID = 1;
  //Rotor
  public static final int A_ROTOR_ID = 11;
  public static final int B_ROTOR_ID = 8;
  public static final int C_ROTOR_ID = 10;
  public static final int D_ROTOR_ID = 4;
  //Encoder
  public static final int A_ENCODER_ID = 0;
  public static final int B_ENCODER_ID = 1;
  public static final int C_ENCODER_ID = 2;
  public static final int D_ENCODER_ID = 3;
  //IMU
  public static final int IMU_ID = 1;
  
  //The module's constants
  public static final double A_ENCODER_OFFSET = 154 * -1;
  public static final double B_ENCODER_OFFSET = -172 * -1;
  public static final double C_ENCODER_OFFSET = -145 * -1;
  public static final double D_ENCODER_OFFSET = 91 * -1;
  public static final Translation2d A_TRANSLATION2D = new Translation2d(-0.25, 0.25);
  public static final Translation2d B_TRANSLATION2D = new Translation2d(-0.25, -0.25);
  public static final Translation2d C_TRANSLATION2D = new Translation2d(0.25, -0.25);
  public static final Translation2d D_TRANSLATION2D = new Translation2d(0.25, 0.25);
  public static final double kWheelDiameterMeters = 4 * 0.0254;
  public static final double kThrottleGearRatio = 6.12;
  public static final double kThrottlePositionConversionFactor = (kWheelDiameterMeters*Math.PI)/(kThrottleGearRatio*2048);
  public static final double kThrottleVelocityConversionFactor = (kWheelDiameterMeters*10*Math.PI)/(kThrottleGearRatio*2048);//*Math.PI
  public static final double kVoltageCompensation = 12.0;
  public static final double kMaxThrottleVelocity = 6380/60/kThrottleGearRatio*kWheelDiameterMeters*Math.PI;


  //The chassis's constants
  public static final SwerveDriveKinematics SWERVE_KINEMATICS = new SwerveDriveKinematics( A_TRANSLATION2D, B_TRANSLATION2D, C_TRANSLATION2D, D_TRANSLATION2D);
  public static final double kMaxVelocityMetersPerSecond = 4.0;
  public static final double kMaxAccelerationMetersPerSecond = 3.0;
  public static final double kPathingXP = 0.0;
  public static final double kPathingXI = 0.01;//0.0001
  public static final double kPathingXD = 0.0001;//0.001
  public static final double kPathingYP = 0.0;
  public static final double kPathingYI = 0.01;
  public static final double kPathingYD = 0.0001;
  public static final double kPathingTP = 10;
  public static final double kPathingTI = 0.0;
  public static final double kPathingTD = 0.0;

}
