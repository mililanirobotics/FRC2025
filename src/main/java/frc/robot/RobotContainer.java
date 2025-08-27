// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.GamepadConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.Constants.pivotConstant.PivotPositions;
import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.LEDStatesCommand;
import frc.robot.commands.UselessCommand;
import frc.robot.commands.AutonomousCommands.AutoAlgaeEjectCommand;
import frc.robot.commands.AutonomousCommands.AutoIntakeCommand;
import frc.robot.commands.AutonomousCommands.AutoIntakePivotSensorCommand;
import frc.robot.commands.AutonomousCommands.AutoOuttakeCommand;
import frc.robot.commands.AutonomousCommands.AutoPivotAlgaeCommand;
import frc.robot.commands.AutonomousCommands.AutoPivotDownComand;
import frc.robot.commands.AutonomousCommands.AutoPivotMiddleCommand;
import frc.robot.commands.AutonomousCommands.AutoPivotSensorCommand;
import frc.robot.commands.AutonomousCommands.AutoPivotStorageCommand;
import frc.robot.commands.AutonomousCommands.AutoPivotUpCommand;
import frc.robot.commands.AutonomousCommands.ElevatorGroundCommand;
import frc.robot.commands.AutonomousCommands.ElevatorLevel1Command;
import frc.robot.commands.AutonomousCommands.ElevatorLevel2Command;
import frc.robot.commands.AutonomousCommands.ElevatorLevel3Command;
import frc.robot.commands.AutonomousCommands.ElevatorStorageCommand;
import frc.robot.commands.AutonomousCommands.LeftCoralStationAlignCommand;
import frc.robot.commands.AutonomousCommands.RightCoralStationAlignCommand;
import frc.robot.commands.ManualCommands.BlankCommand;
import frc.robot.commands.ManualCommands.EjectCommand;
import frc.robot.commands.ManualCommands.ElevatorControlCommand;
import frc.robot.commands.ManualCommands.ElevatorDownCommand;
import frc.robot.commands.ManualCommands.ElevatorUpCommand;
import frc.robot.commands.ManualCommands.HangControlCommand;
import frc.robot.commands.ManualCommands.IntakeCommand;
import frc.robot.commands.ManualCommands.IntakeControlCommand;
import frc.robot.commands.ManualCommands.IntakeShutdownCommand;
import frc.robot.commands.ManualCommands.PivotBackwardCommand;
import frc.robot.commands.ManualCommands.PivotControlCommand;
import frc.robot.commands.ManualCommands.PivotForwardCommand;
import frc.robot.commands.ManualCommands.SwerveControlCommand;
import frc.robot.commands.TestCommands.TestEncoder;
import frc.robot.commands.TestCommands.BottomRollerTestCommands.BottomRollerDownSpeedCommand;
import frc.robot.commands.TestCommands.BottomRollerTestCommands.BottomRollerSetPowerCommand;
import frc.robot.commands.TestCommands.BottomRollerTestCommands.BottomRollerShutdownCommand;
import frc.robot.commands.TestCommands.BottomRollerTestCommands.BottomRollerUpSpeedCommand;
import frc.robot.commands.TestCommands.ElevatorTestCommands.ElevatorTestDownSpeedCommand;
import frc.robot.commands.TestCommands.ElevatorTestCommands.ElevatorTestSetPowerCommand;
import frc.robot.commands.TestCommands.ElevatorTestCommands.ElevatorTestShutdownCommand;
import frc.robot.commands.TestCommands.ElevatorTestCommands.ElevatorTestUpSpeedCommand;
import frc.robot.commands.TestCommands.PivotTestCommands.PivotSetPowerCommand;
import frc.robot.commands.TestCommands.PivotTestCommands.PivotTestDownSpeedCommand;
import frc.robot.commands.TestCommands.PivotTestCommands.PivotTestShutdownSpeedCommand;
import frc.robot.commands.TestCommands.PivotTestCommands.PivotTestUpSpeedCommand;
import frc.robot.commands.TestCommands.TopRollerTestCommands.TopRollerDownSpeedCommand;
import frc.robot.commands.TestCommands.TopRollerTestCommands.TopRollerSetPowerCommand;
import frc.robot.commands.TestCommands.TopRollerTestCommands.TopRollerShutdownCommand;
import frc.robot.commands.TestCommands.TopRollerTestCommands.TopRollerUpSpeedCommand;
import frc.robot.commands.VisionCommands.AlignDistanceCommand;
import frc.robot.commands.VisionCommands.AlignLeft2Command;
import frc.robot.commands.VisionCommands.AlignLeft3Command;
import frc.robot.commands.VisionCommands.AlignRight2Command;
import frc.robot.commands.VisionCommands.AlignRight3Command;
import frc.robot.commands.VisionCommands.ThetaAlignCommand;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.HangSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LEDSubsystem;
import frc.robot.subsystems.PivotSubsystem;
import frc.robot.subsystems.SwerveDriveSubsystem;
import frc.robot.subsystems.LimelightSubsystem;

import java.time.InstantSource;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.commands.PathPlannerAuto;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class RobotContainer { 
  //Shuffleboard Tabs
  private final ShuffleboardTab teleopTab = Shuffleboard.getTab("Teleop");
  private final ShuffleboardTab testTranPos = Shuffleboard.getTab("Test_Tran_Pos");
  private final ShuffleboardTab testTranVel = Shuffleboard.getTab("Test_Tran_Vel");
  private final ShuffleboardTab testRotPos = Shuffleboard.getTab("Test_Rot_Pos");
  private final ShuffleboardTab testRotVel = Shuffleboard.getTab("Test_Rot_Vel");
  private final ShuffleboardTab testPos = Shuffleboard.getTab("Test_Pos");
  private final ShuffleboardTab testGyroData = Shuffleboard.getTab("Test_Gyro_Data");

  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final SwerveDriveSubsystem m_SwerveDriveSubsystem = new SwerveDriveSubsystem(    
    testTranPos,
    testTranVel,
    testRotPos,
    testRotVel, 
    testPos,
    testGyroData
  );
  private final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();
  private final PivotSubsystem m_pivotSubsystem = new PivotSubsystem();
  private final GenericHID controller0 = new GenericHID(0);
  private final GenericHID controller1 = new GenericHID(1);
  private final ElevatorSubsystem m_elevatorSubsystem = new ElevatorSubsystem();
  private final LimelightSubsystem m_LimelightSubsystem = new LimelightSubsystem();
  private final HangSubsystem m_HangSubsystem = new HangSubsystem();
  private final LEDSubsystem m_LEDSubsystem = new LEDSubsystem();
  private final PIDController m_PidController = new PIDController(0.000001, 0, 0);

  private final CommandXboxController m_driverController = new CommandXboxController(OperatorConstants.kDriverControllerPort);
  private final SendableChooser<Command> autoChooser;

  public RobotContainer() {
    // CameraServer.startAutomaticCapture();

    //Registering Commands for PathPlanner
    NamedCommands.registerCommand("Move Elevator to Ground", new ElevatorGroundCommand(m_elevatorSubsystem));
    NamedCommands.registerCommand("Move Elevator to Level 2", new ElevatorLevel2Command(m_elevatorSubsystem));
    NamedCommands.registerCommand("Move Elevator to Level 3", new ElevatorLevel3Command(m_elevatorSubsystem));
    NamedCommands.registerCommand("Move Pivot to Level 2", new AutoPivotMiddleCommand(m_pivotSubsystem));
    NamedCommands.registerCommand("Move Pivot to Level 3", new AutoPivotUpCommand(m_pivotSubsystem));
    NamedCommands.registerCommand("Intake", new AutoIntakeCommand(m_intakeSubsystem, controller1));
    NamedCommands.registerCommand("Eject", new AutoOuttakeCommand(m_intakeSubsystem));
    NamedCommands.registerCommand("Remove Algae", new AutoAlgaeEjectCommand(m_intakeSubsystem));
  
  
    //Binding Default Commands
    m_SwerveDriveSubsystem.setDefaultCommand(new SwerveControlCommand(
      m_SwerveDriveSubsystem, 
      m_LimelightSubsystem,
      controller0
      )
    );
    m_intakeSubsystem.setDefaultCommand(new IntakeControlCommand(m_intakeSubsystem, m_pivotSubsystem, controller1));
    m_pivotSubsystem.setDefaultCommand(new PivotControlCommand(m_pivotSubsystem, controller1));
    m_elevatorSubsystem.setDefaultCommand(new ElevatorControlCommand(m_elevatorSubsystem, controller1));
    m_HangSubsystem.setDefaultCommand(new HangControlCommand(m_HangSubsystem, controller1));
    m_LEDSubsystem.setDefaultCommand(
      new LEDStatesCommand(m_LEDSubsystem, m_pivotSubsystem, m_intakeSubsystem)
    );

    autoChooser = AutoBuilder.buildAutoChooser();
    SmartDashboard.putData("Auto Path", autoChooser);

    configureBindings();
  }

  private void configureBindings() {
   
    ///////////////////////////////////////////////////////////////////////////////////
    // OFFICIAL GAMEPAD 0 CONTROLLER SCHEME
    ///////////////////////////////////////////////////////////////////////////////////

    //Align with AprilTag
    // new Trigger(
    //     () -> controller0.getRawAxis(GamepadConstants.kLeftTriggerPort) >= 0.5
    //   ).onTrue(
    //     new ThetaAlignCommand(m_LimelightSubsystem, m_SwerveDriveSubsystem)
    //   );

    // //Reef Alignment Level 3
    // new JoystickButton(controller0, GamepadConstants.kLeftBumperPort)
    //   .onTrue(
    //     new ConditionalCommand(
    //       new AlignLeft3Command(m_SwerveDriveSubsystem, m_LimelightSubsystem, m_intakeSubsystem, controller0), 
    //       new ConditionalCommand(
    //         new AlignRight3Command(m_SwerveDriveSubsystem, m_LimelightSubsystem, m_intakeSubsystem, controller0), 
    //         new BlankCommand(), 
    //         m_intakeSubsystem::isCoralInRightSlot), 
    //       m_intakeSubsystem::isCoralInLeftSlot)
    //   );

    // //Reef Alignment Level 2
    // new JoystickButton(controller0, GamepadConstants.ktBumperPort)
    //   .onTrue(
    //     new ConditionalCommand(
    //       new AlignLeft2Command(m_SwerveDriveSubsystem, m_LimelightSubsystem, m_intakeSubsystem, controller0), 
    //       new ConditionalCommand(
    //         new AlignRight2Command(m_SwerveDriveSubsystem, m_LimelightSubsystem, m_intakeSubsystem, controller0), 
    //         new BlankCommand(), 
    //         m_intakeSubsystem::isCoralInRightSlot), 
    //       m_intakeSubsystem::isCoralInLeftSlot)
    //   );

    //Align Left on Reef
    // new JoystickButton(controller0, GamepadConstants.kLeftBumperPort)
    //     .onTrue(
    //       new AlignLeft3Command(m_SwerveDriveSubsystem, m_LimelightSubsystem, m_intakeSubsystem, controller0)
    //       .unless(() -> 
    //       controller0.getRawAxis(GamepadConstants.kLeftXJoystickPort) > GamepadConstants.kDeadzone
    //       || controller0.getRawAxis(GamepadConstants.kLeftYJoystickPort) > GamepadConstants.kDeadzone
    //       || controller0.getRawAxis(GamepadConstants.kRightXJoystickPort) > GamepadConstants.kDeadzone
    //       || !m_LimelightSubsystem.isReefTargetFound())
    //       );

    // //Align Right on Reef
    // new JoystickButton(controller0, GamepadConstants.kRightBumperPort)
    // .onTrue(
    //       new AlignRight3Command(m_SwerveDriveSubsystem, m_LimelightSubsystem, m_intakeSubsystem, controller0)
    //       .unless(() -> 
    //       controller0.getRawAxis(GamepadConstants.kLeftXJoystickPort) > GamepadConstants.kDeadzone
    //       || controller0.getRawAxis(GamepadConstants.kLeftYJoystickPort) > GamepadConstants.kDeadzone
    //       || controller0.getRawAxis(GamepadConstants.kRightXJoystickPort) > GamepadConstants.kDeadzone
    //       || !m_LimelightSubsystem.isReefTargetFound())
    //   );

    //Reset NAVX2 Yaw
    new JoystickButton(controller0, GamepadConstants.kXButtonPort)
      .onTrue(new InstantCommand(()-> m_SwerveDriveSubsystem.zeroOutGyro()));

    ///////////////////////////////////////////////////////////////////////////////////
    //OFFICIAL GAMEPAD 1 CONTROLLER SCHEME
    ///////////////////////////////////////////////////////////////////////////////////
    
    //Coral Auto Correct
    new JoystickButton(controller1, GamepadConstants.kXButtonPort)
      .onTrue(
        new EjectCommand(m_intakeSubsystem, m_pivotSubsystem.getCurrentState())
        .andThen(new WaitCommand(.02))
        .andThen(new IntakeCommand(m_intakeSubsystem,  m_pivotSubsystem.getCurrentState()))
        .andThen(new WaitCommand(.04))
        .andThen(new InstantCommand(
            ()-> {m_intakeSubsystem.setAutoIntake(false);},
             m_intakeSubsystem)
        )
      );
    
    //Coral Auto Ground Intake
    new Trigger(
        () -> controller1.getRawAxis(GamepadConstants.kLeftTriggerPort) >= 0.5
      ).onTrue(
        new ElevatorDownCommand(m_elevatorSubsystem, controller0)
        .andThen(new AutoPivotDownComand(m_pivotSubsystem))
        .andThen(new AutoIntakeCommand(m_intakeSubsystem, controller1))
        .andThen(new WaitCommand(.04))
        .andThen(
          new ParallelCommandGroup(
            new AutoPivotAlgaeCommand(m_pivotSubsystem),
            new SequentialCommandGroup(
              new WaitCommand(.1),
              new InstantCommand(
                ()-> {
                  m_intakeSubsystem.setAutoIntake(false);
                },
                m_intakeSubsystem
              )
            )
          )
        )
      );

    // Presets

    //Storage Preset
    new JoystickButton(controller1, GamepadConstants.kLeftBumperPort)
      .onTrue(
        new AutoPivotStorageCommand(m_pivotSubsystem)
          .alongWith(new ElevatorStorageCommand(m_elevatorSubsystem))
      );

    new JoystickButton(controller1, GamepadConstants.kRightBumperPort)
      .onTrue(
        new InstantCommand(() -> m_LEDSubsystem.setHPSignal(true))
      )
      .onFalse(
        new InstantCommand(() -> m_LEDSubsystem.setHPSignal(false))
      );
    

    //Reef Level 3 Preset
    new POVButton(controller1, GamepadConstants.kDpadUp)
      .onTrue(
        new AutoPivotUpCommand(m_pivotSubsystem)
          .alongWith(
            new ElevatorLevel3Command(m_elevatorSubsystem)
          )
      );

    //Reef Level 2 Preset
    new POVButton(controller1, GamepadConstants.kDpadRight)
      .onTrue(
        new AutoPivotMiddleCommand(m_pivotSubsystem)
          .alongWith(
            new ElevatorLevel2Command(m_elevatorSubsystem)
          )
      );

    //Algae Intake Preset
    new POVButton(controller1, GamepadConstants.kDpadLeft)
      .onTrue(
        new AutoPivotAlgaeCommand(m_pivotSubsystem)
        .alongWith(
          new ElevatorGroundCommand(m_elevatorSubsystem)                
        )
      );
    
    //Ground Intake Preset
    new POVButton(controller1, GamepadConstants.kDpadDown)
      .onTrue(
        new AutoPivotDownComand(m_pivotSubsystem)
          .alongWith(
            new ElevatorGroundCommand(m_elevatorSubsystem)
          )
      );
  }
  
  public Command getAutonomousCommand() {
    // return Autos.exampleAuto(m_exampleSubsystem);
    // return new PathPlannerAuto("LEAVE-BLUE");
    return autoChooser.getSelected();
    // return null;
  }

  public void autonomousInit() {
    zeroYaw();
    // m_LEDSubsystem.clear();
    m_LEDSubsystem.autonomous();
  }

  public void disabledInit() {
    m_LEDSubsystem.clear();
    m_LEDSubsystem.disabled();
    // zeroYaw();
  }

  public void zeroYaw() {
    m_SwerveDriveSubsystem.zeroOutGyro();
  }
}
