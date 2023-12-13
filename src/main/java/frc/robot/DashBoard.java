package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.auto.AutoMap;
import frc.robot.subsystems.Limelight;

/**
 * Put the needed in-game data here.
 * Take care that don't put null object on table.
 */
public class DashBoard {
  private SendableChooser<String> autoChooser;
  public static String autoString = "nonAuto";
  public static double testXP = 0;
  public static double testXI = 0;
  public static double testXD = 0;
  public static double testZP = 0;
  public DashBoard() {
    autoChooserSetUp();
  }

  public void autoChooserSetUp() {
    autoChooser = new SendableChooser<>();
    autoChooser.setDefaultOption("nonAuto", "nonAuto");
    AutoMap.AutoTest1.setName("AutoTest1");
    AutoMap.AutoTest2.setName("AutoTest2");
    AutoMap.AutoTest1001.setName("AutoTest1001");
    autoChooser.addOption(AutoMap.AutoTest1.getName(), AutoMap.AutoTest1.getName());
    autoChooser.addOption(AutoMap.AutoTest2.getName(), AutoMap.AutoTest2.getName());
    autoChooser.addOption(AutoMap.AutoTest1001.getName(), AutoMap.AutoTest1001.getName());
    SmartDashboard.putData(autoChooser);

    SmartDashboard.putNumber("XP",0);
    SmartDashboard.putNumber("XI",0);
    SmartDashboard.putNumber("XD",0);
    SmartDashboard.putNumber("ZP",0);
    
  }

  public void onLoop() {
    autoString = autoChooser.getSelected();
    SmartDashboard.putString("autoCommand", autoString != "nonAuto" ? AutoMap.autoMap.get(autoString).getName(): "nonAuto");
    double x = RobotContainer.driveTrain.getPose().getX();
    double y = RobotContainer.driveTrain.getPose().getY();
    double rotation = RobotContainer.driveTrain.getPose().getRotation().getDegrees();
    SmartDashboard.putNumber("Pose-x", x);
    SmartDashboard.putNumber("Pose-y", y);
    SmartDashboard.putNumber("Pose-r", rotation);
    

    testXP = SmartDashboard.getNumber("XP", 0);
    testXI = SmartDashboard.getNumber("XI", 0);
    testXD = SmartDashboard.getNumber("XD", 0);
    testZP = SmartDashboard.getNumber("ZP", 0);

    SmartDashboard.putNumber("targetX", RobotContainer.limelight.getX());
    SmartDashboard.putNumber("targetY", RobotContainer.limelight.getArea());
    SmartDashboard.putNumber("targetR", RobotContainer.limelight.getTargetposeInRobotspace()[5]);

    // double test1001 = RobotContainer.driveTrain.moduleA.getPosition().distanceMeters;
    // SmartDashboard.putNumber("test1001", test1001);

    // System.out.println(AutoMap.autoMap.get(autoString).getName());
    // System.out.println(autoString);
  }

  public void teleopOnLoop() {

  }

  public void autoOnLoop() {

  }
}
