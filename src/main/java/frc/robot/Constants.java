// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.List;

import com.pathplanner.lib.path.PathConstraints;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import com.pathplanner.lib.config.PIDConstants;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.numbers.N2;
import edu.wpi.first.math.system.LinearSystem;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }

  public static class GamepadConstants {
    public static final int kPrimaryGamepadPort = 0;
    public static final int kSecondaryGamepadPort = 1;
    public static final int kTestingGamepadPort = 2;

  //Gamepad Axis Ports
    public static final int kLeftXJoystickPort = 0;
    public static final int kLeftYJoystickPort = 1;
    public static final int kRightXJoystickPort = 4;
    public static final int kRightYJoystickPort = 5; 
    public final static int kLeftTriggerPort = 2;
    public final static int kRightTriggerPort = 3;

  //Gamepad Button Ports
    public final static int kAButtonPort = 1;
    public final static int kBButtonPort = 2;
    public final static int kXButtonPort = 3;
    public final static int kYButtonPort = 4;
    public final static int kLeftBumperPort = 5;
    public final static int kRightBumperPort = 6;
    public final static int kBackButtonPort = 7;
    public final static int kStartButtonPort = 8;   

  //joystick port for the gamepad
    public final static int kPrimaryLeftStickPort = 0;
    public final static int kPrimaryRightStickPort = 1;

  //Dpad values
    public final static int kDpadUp = 0;
    public final static int kDpadLeft = 270;
    public final static int kDpadRight = 90;
    public final static int kDpadDown = 180;

  //Deadzone value
    public final static double kDeadzone = 0.2;
  }

  public static class SwerveModuleConstants {
    //physical properties
    public static final double kRotationGearRatio = 1 / (165 / 4.0);
    public static final double kDriveGearRatio = 1 / 5.14;    
    public static final double kWheelDiameter = Units.inchesToMeters(3);
    public static final double kRotationToMeters = kDriveGearRatio * Math.PI * kWheelDiameter;
    public static final double kRotationToRadians = kRotationGearRatio * 2 * Math.PI;
    public static final double kMetersPerSecond = kRotationToMeters / 60.0 ;
    public static final double kRadiansPerSecond = kRotationToRadians / 60.0;

    public static final int kRightDriveCurrentLimit = 25;
    public static final int kLeftDriveCurrentLimit = 25;
    public static final int kRotationCurrentLimit = 25;

    // Drive Port Constants
    public static final int kLeftFrontWheelPort = 19;
    public static final int kLeftFrontRotationPort = 16;

    public static final int kRightFrontWheelPort = 9;
    public static final int kRightFrontRotationPort = 23;

    public static final int kLeftBackWheelPort = 21;
    public static final int kLeftBackRotationPort = 20;

    public static final int kRightBackWheelPort = 24;
    public static final int kRightBackRotationPort = 4;

    public static final int kLeftFrontCANCoderPort = 17;
    public static final int kRightFrontCANCoderPort = 11;
    public static final int kLeftBackCANCoderPort = 2;
    public static final int kRightBackCANCoderPort = 8;
    public static final double kLeftFrontCANCoderOffset = 0.203857421875;
    public static final double kRightFrontCANCoderOffset = -0.2333984375;
    public static final double kLeftBackCANCoderOffset = -0.0693359375;
    public static final double kRightBackCANCoderOffset = 0.027099609375;

    public static final int kLeftBackIndex = 2;
    public static final int kRightBackIndex = 3;
    public static final int kLeftFrontIndex = 0;
    public static final int kRightFrontIndex = 1;
    
    // Abosulte Discontinuity
    public static final double kAbsoluteSensorDiscontinuityPoint = 0.5;

    // Reverse Booleans
    public static final boolean kLeftFrontDriveReversed = false;
    public static final boolean kRightFrontDriveReversed = false;
    public static final boolean kLeftBackDriveReversed = false;
    public static final boolean kRightBackDriveReversed = false;

    public static final boolean kLeftFrontRotationReversed = false;
    public static final boolean kRightFrontRotationReversed = false;
    public static final boolean kLeftBackRotationReversed = false;
    public static final boolean kRightBackRotationReversed = false;

    public static final boolean kLeftFrontCANCoderReversed = false;
    public static final boolean kRightFrontCANCoderReversed = false;
    public static final boolean kLeftBackCANCoderReversed = false;
    public static final boolean kRightBackCANCoderReversed = false;

    // PID Constants
    public static final double kTurningP = 0.3;
    public static final double kTurningI = 0;
    public static final double kTurningD = 0;
    public static final double kTurningTolerance = 0.1;

    // The distances between each Module from the center of the robot (Meters)
    public static final double kModuleDistance = 0.259207;
    
    
    // 2d translation coordinates relative to center 
    public static final double kLeftFront2dX = kModuleDistance;
    public static final double kLeftFront2dY = kModuleDistance;

    public static final double kRightFront2dX = kModuleDistance;
    public static final double kRightFront2dY = -kModuleDistance;

    public static final double kLeftBack2dX = -kModuleDistance;
    public static final double kLeftBack2dY = kModuleDistance;

    public static final double kRightBack2dX = -kModuleDistance;
    public static final double kRightBack2dY = -kModuleDistance;

    public static final double kTrackWidth = kModuleDistance * 2;

    public static final Translation2d translationLength = new Translation2d(
      kTrackWidth,
      kTrackWidth
    );

    public static final Translation2d leftFrontLocation = new Translation2d(
      SwerveModuleConstants.kLeftFront2dX, 
      SwerveModuleConstants.kLeftFront2dY
    );

    public static final Translation2d rightFrontLocation = new Translation2d(
      SwerveModuleConstants.kRightFront2dX,
      SwerveModuleConstants.kRightFront2dY
    );

    public static final Translation2d leftBackLocation = new Translation2d(
      SwerveModuleConstants.kLeftBack2dX,
      SwerveModuleConstants.kLeftBack2dY
    );

    public static final Translation2d rightBackLocation = new Translation2d(
      SwerveModuleConstants.kRightBack2dX,
      SwerveModuleConstants.kRightBack2dY
    );

    public static final Translation2d[] moduleOffsets = {leftFrontLocation, rightFrontLocation, leftBackLocation, rightBackLocation};

    public static final SwerveDriveKinematics kinematics = new SwerveDriveKinematics(
      leftFrontLocation, 
      rightFrontLocation,
      leftBackLocation,
      rightBackLocation
    );

    //drivetrain simulation
    public static final double ksVolts = 0.22;
    public static final double kvVoltsSecondsPerMeter = 1.98;
    public static final double kaVoltSecondsSquaredPerMeter = 0.2;
    public static final double kvVoltSecondsPerRadian = 1.5;
    public static final double kaVoltSecondsSquaredPerRadian = 0.3;
    
    public static final DCMotor kDriveGearbox = DCMotor.getNEO(1);

    public static final LinearSystem<N2, N2, N2> kDrivePlant = 
      LinearSystemId.identifyDrivetrainSystem(
        kvVoltsSecondsPerMeter,
        kaVoltSecondsSquaredPerMeter,
        kvVoltSecondsPerRadian,
        kaVoltSecondsSquaredPerRadian
      );
  }

  public static class DriveConstants {
    // Drive Speed Constants
    public static final double kDriveMaxMetersPerSecond = 0.25;
    //4.95
    public static final double kRotationMaxRadiansPerSecond = 0.1 * Math.PI;
    //2 * Math.PI
    public static final double kDriveMetersPerSecondLimit = 0.1;//4.5;
    //3

    public static final double kTeleDriveMaxAcceleration = kDriveMaxMetersPerSecond * 8;
    public static final double kTeleRotationMaxAngularAcceleration = kRotationMaxRadiansPerSecond * 8;

    //======================
    // auto constants
    //======================

    public static final double ksVolts = 0;
    public static final double kvVoltSecondsPerMeter = 0;
    public static final double kaVoltSecondsSquaredPerMeter = 0;
    public static final double kPDrive = 0;

    //PID Constants for auto
    public static final double kXP = 0;
    public static final double kXI = 0;
    public static final double kXD = 0;
    
    public static final double kYP = 0;
    public static final double kYI = 0;
    public static final double kYD = 0;

    public static final double kThetaP = 0;
    public static final double kThetaI = 0;
    public static final double kThetaD = 0;

    //PID controllers
    public static final PIDController XController = new PIDController(
        DriveConstants.kXP, 
        DriveConstants.kXI, 
        DriveConstants.kXD
    );

    public static final PIDController yController = new PIDController(
        DriveConstants.kYP, 
        DriveConstants.kYI, 
        DriveConstants.kYD
    );

    public static final ProfiledPIDController thetaController = new ProfiledPIDController(
        SwerveModuleConstants.kTurningP,
        SwerveModuleConstants.kTurningI,
        SwerveModuleConstants.kTurningD,
        new TrapezoidProfile.Constraints(
            DriveConstants.kDriveMaxMetersPerSecond, 
            DriveConstants.kTeleDriveMaxAcceleration
        ) 
    );
  }
  
  public static class AutoConstants {
    //auto constraints 
    public static final double kAutoDriveMaxMetersPerSecond = 3;
    public static final double kAutoDriveMaxAcceleration = kAutoDriveMaxMetersPerSecond * 8;
    public static final double kAutoDriveMaxRadiansPerSecond = 1;
    public static final double kAutoDriveMaxAngularAcceleration = kAutoDriveMaxRadiansPerSecond * 8;

    //WPLIB constants
    public static final double kPXController = 1.5;
    public static final double kIXController = 0;
    public static final double kDXController = 0;

    public static final double kPYController = 1.5;
    public static final double kIYController = 0;
    public static final double kDYController = 0;

    public static final double kPThetaController = 3;
    public static final double kIThetaController = 0;
    public static final double kDThetaController = 0;


    //PID Constants
    public static final double kPController = 16;
    public static final double kIController = 0;
    public static final double kDController = 0;

    public static final double kPThetaControllerP = 0.08;
    public static final double kIThetaControllerP = 0.0; 
    public static final double kDThetaControllerP = 0.001;

    public static final PIDConstants translationConstants = new PIDConstants(
      kPController, 
      kIController, 
      kDController
    );
      //Does this work isntead?
    public static final PIDConstants thetaConstants = new PIDConstants(
      kPThetaControllerP,
      kIThetaControllerP, 
      kDThetaControllerP
    );


    //path planner constraint 
    public static final PathConstraints pathConstraints = new PathConstraints(
      kAutoDriveMaxMetersPerSecond, 
      kAutoDriveMaxAcceleration, 
      kAutoDriveMaxRadiansPerSecond, 
      kAutoDriveMaxAngularAcceleration
    );
    
    //max module speed
    public static final double kMaxModuleSpeed = 2;
 
    // //Path planner config
    // public static final HolonomicPathFollowerConfig pathFollowingConfig = new HolonomicPathFollowerConfig(
    //   new PIDConstants(kPController, kIController, kDController),
    //   new PIDConstants(kPThetaControllerP, kIThetaControllerP, kDThetaControllerP),
    //   kAutoDriveMaxMetersPerSecond, 
    //   SwerveModuleConstants.leftFrontLocation.getNorm(),
    //   new ReplanningConfig(true, true) 
    // );

    public static final List<Translation2d> testPath = List.of(
      new Translation2d(1, 0)
    );
  }

  public static class PortConstants {
    public final static int kRightElevatorPort = 15;
    public final static int kLeftElevatorPort = 14;

    public final static int kIntakeSensorPort = 8;

    public final static int kRollerTopPort = 35;
    public final static int kRollerBottomPort = 17;

    public final static int kPivotPort = 18;
    public final static int kPivotEncoderPort = 1;

    public final static int kLeftLinearActuatorPort = 5;
    public final static int kRightLinearActuatorPort = 6;
  }
  
  // public static class PIDConstants {
  //   public static final double kP = 0;
  //   public static final double kI = 0;
  //   public static final double kD = 0;
  // }

  public static class LimelightConstants {
    public static final double kLeftAlignOffset = 14.49; //Testing
    public static final double kRightAlignOffset = -1.55; //Testing

    public static enum VisionConstants {
      LEFTCORAL,
      RIGHTCORAL
    }

    public static final double kAlignCenterP = 0.011;
    public static final double kAlignCenterI = 0;
    public static final double kAlignCenterD = 0;
    public static final double kAlignCenterTolerance = 0.01; //Temp
  }

  public static class pivotConstant {
    public static final double kPivotZeroPosition = .665;

    /**
    Built in Encoder positons
    Down = -47
    Up = 0
    */

    public static final double kPivotDownPosition = .228; // Placeholder value, needs to be tested
    public static final double kPivotUpPosition = 0.0015; // Placeholder value, needs to be tested 102849
    public static final double kElevatedScorePosition = 0.025; //Supposed good value for Scoring while elevated
    public static final double kPivotMiddlePosition = 0.015; // Placeholder value, needs to be tested 102849
    public static final double kPivotAlgaePosition = 0.12;

    public static final double kPivotSensorPosition = 0.01; //placeholder :(

    public static final double kPivotTolerance = .0025;

    public static enum PivotPositions {
      INTAKE,
      ALGAE,
      CORALSTATION,
      STARTCONFIG,
      STORAGE
    }
  }

  public static class IntakeConstants {
    public static final double IntakePercentOutput = .6;
    public static final double AlgaePercentOutput = 0.25;
    public static final double ScoringPercentOutput = .6;
  }

  public static class ElevatorConstants {

    public static final double kGroundCounts = 0;
    public static final double kLevel1Counts = 0; //Needs Testing
    public static final double kLevel2Counts = 20; //Needs Testing
    public static final double kLevel3Counts = 97; //Needs Testing

    public static enum ElevatorPositions {
      GROUND,
      LEVEL1,
      ALGAE1,
      LEVEL2,
      ALGAE2,
      LEVEL3
    }
  }
 
  public static class LEDConstants {
    public static final int CANdleID = 7;
    public static final int LEDcount = 74; //69
}
}
