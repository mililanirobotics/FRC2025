// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.pathplanner.lib.commands.PathPlannerAuto;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.Commands.SwerveControlCommand;
import frc.robot.Constants.JoystickConstants;
import frc.robot.Subsystems.SwerveDriveSubsystem;

public class RobotContainer {
  //shuffleboard tabs
  private final ShuffleboardTab teleopTab = Shuffleboard.getTab("Teleop");
  private final ShuffleboardTab testTranPos = Shuffleboard.getTab("Test_Tran_Pos");
  private final ShuffleboardTab testTranVel = Shuffleboard.getTab("Test_Tran_Vel");
  private final ShuffleboardTab testRotPos = Shuffleboard.getTab("Test_Rot_Pos");
  private final ShuffleboardTab testRotVel = Shuffleboard.getTab("Test_Rot_Vel");
  private final ShuffleboardTab testPos = Shuffleboard.getTab("Test_Pos");
  private final ShuffleboardTab testGyroData = Shuffleboard.getTab("Test_Gyro_Data");

  //Subsystem intinitialized
  private final SwerveDriveSubsystem swerveDriveSubsystem = new SwerveDriveSubsystem(
    testTranPos,
    testTranVel,
    testRotPos,
    testRotVel,
    testPos,
    testGyroData
  );

  private final GenericHID primaryGamepad = new GenericHID(JoystickConstants.kPrimaryGamepadPort);
  private final Field2d field = new Field2d();
  private final PathPlannerAuto auto = new PathPlannerAuto("TEST1");


  public RobotContainer() {
    configureBindings();

    swerveDriveSubsystem.setDefaultCommand(new SwerveControlCommand(
      swerveDriveSubsystem, 
      primaryGamepad
      )
    );
  }

  private void configureBindings() {}

  public Command getAutonomousCommand() {
    // This method loads the auto when it is called, however, it is recommended
    // to first load your paths/autos when code starts, then return the
    // pre-loaded auto/path
    return auto;
  }

  public void zeroOutGyro() {
    swerveDriveSubsystem.zeroOutGyro();
  }
}
