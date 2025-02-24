// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.GamepadConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.UselessCommand;
import frc.robot.commands.AutonomousCommands.AutoIntakeCommand;
import frc.robot.commands.AutonomousCommands.AutoIntakePivotSensorCommand;
import frc.robot.commands.AutonomousCommands.AutoOuttakeCommand;
import frc.robot.commands.AutonomousCommands.AutoPivotAlgaeCommand;
import frc.robot.commands.AutonomousCommands.AutoPivotDownComand;
import frc.robot.commands.AutonomousCommands.AutoPivotMiddleCommand;
import frc.robot.commands.AutonomousCommands.AutoPivotMiddleCommand1;
import frc.robot.commands.AutonomousCommands.AutoPivotSensorCommand;
import frc.robot.commands.AutonomousCommands.AutoPivotUpCommand;
import frc.robot.commands.AutonomousCommands.AutoPivotUpCommand1;
import frc.robot.commands.AutonomousCommands.ElevatorGroundCommand;
import frc.robot.commands.AutonomousCommands.ElevatorLevel1Command;
import frc.robot.commands.AutonomousCommands.ElevatorLevel2Command;
import frc.robot.commands.AutonomousCommands.ElevatorLevel3Command;
import frc.robot.commands.ManualCommands.EjectCommand;
import frc.robot.commands.ManualCommands.ElevatorDownCommand;
import frc.robot.commands.ManualCommands.ElevatorUpCommand;
import frc.robot.commands.ManualCommands.IntakeCommand;
import frc.robot.commands.ManualCommands.IntakeShutdownCommand;
import frc.robot.commands.ManualCommands.PivotBackwardCommand;
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
import frc.robot.commands.VisionCommands.AlignLeftCommand;
import frc.robot.commands.VisionCommands.AlignRightCommand;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.PivotSubsystem;
import frc.robot.subsystems.SwerveDriveSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
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
  private final PIDController m_PidController = new PIDController(0.000001, 0, 0);

  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);




  public RobotContainer() {
    configureBindings();
    
    m_SwerveDriveSubsystem.setDefaultCommand(new SwerveControlCommand(
      m_SwerveDriveSubsystem, 
      controller0
      )
    );
  }

  
  private void configureBindings() {
    ///////////////////////////////////////////////////////////////////////////////////
    //TEST COMMANDS
    // ///////////////////////////////////////////////////////////////////////////////////

    
    //elevator test commands

    new JoystickButton(controller1, GamepadConstants.kAButtonPort)
        .onTrue(new ElevatorTestDownSpeedCommand(m_elevatorSubsystem, controller1));
    new JoystickButton(controller1, GamepadConstants.kYButtonPort)
        .onTrue(new ElevatorTestUpSpeedCommand(m_elevatorSubsystem, controller1));
    new JoystickButton(controller1, GamepadConstants.kBButtonPort)
        .onTrue(new ElevatorTestShutdownCommand(m_elevatorSubsystem, controller1));
    new JoystickButton(controller1, GamepadConstants.kXButtonPort)
        .onTrue(new ElevatorTestSetPowerCommand(m_elevatorSubsystem, controller1));
    

    new JoystickButton(controller1, GamepadConstants.kLeftTriggerPort)
        .onTrue(new AutoIntakePivotSensorCommand(m_intakeSubsystem, m_pivotSubsystem.getCurrentState(), controller1, m_pivotSubsystem));

    //pivot test commands

    new POVButton(controller1, GamepadConstants.kDpadDown)
        .onTrue(new PivotTestDownSpeedCommand(m_pivotSubsystem, controller1));
    new POVButton(controller1, GamepadConstants.kDpadUp)
        .onTrue(new PivotTestUpSpeedCommand(m_pivotSubsystem, controller1));
    new POVButton(controller1, GamepadConstants.kDpadRight)
        .onTrue(new PivotTestShutdownSpeedCommand(m_pivotSubsystem, controller1));
    new POVButton(controller1, GamepadConstants.kDpadLeft)
        .onTrue(new PivotSetPowerCommand(m_pivotSubsystem, controller1));


    //top roller test commands

    // new JoystickButton(controller0, GamepadConstants.kAButtonPort)
    //     .onTrue(new TopRollerDownSpeedCommand(m_intakeSubsystem, controller0));
    // new JoystickButton(controller0, GamepadConstants.kYButtonPort)
    //     .onTrue(new TopRollerUpSpeedCommand(m_intakeSubsystem, controller0));
    // new JoystickButton(controller0, GamepadConstants.kBButtonPort)
    //     .onTrue(new TopRollerShutdownCommand(m_intakeSubsystem, controller0));
    // new JoystickButton(controller0, GamepadConstants.kXButtonPort)
    //     .onTrue(new TopRollerSetPowerCommand(m_intakeSubsystem, controller0));
    


    
    //bottom roller test commands

    // new POVButton(controller0, GamepadConstants.kDpadDown)
    //     .onTrue(new BottomRollerDownSpeedCommand(m_intakeSubsystem, controller0));
    // new POVButton(controller0, GamepadConstants.kDpadUp)
    //     .onTrue(new BottomRolklerUpSpeedCommand(m_intakeSubsystem, controller0));
    // new POVButton(controller0, GamepadConstants.kDpadRight)
    //     .onTrue(new BottomRollerShutdownCommand(m_intakeSubsystem, controller0));
    // new POVButton(controller0, GamepadConstants.kDpadLeft)
    //     .onTrue(new BottomRollerSetPowerCommand(m_intakeSubsystem, controller0));

    // PIVOT CONTROLS

    new JoystickButton(controller0, GamepadConstants.kLeftBumperPort)
        .onTrue(new AutoPivotDownComand(m_pivotSubsystem));
    
    new JoystickButton(controller0, GamepadConstants.kRightBumperPort)
        .onTrue(new AutoPivotUpCommand(m_pivotSubsystem));

    new POVButton(controller0, GamepadConstants.kDpadUp)
        .onTrue(new AutoPivotMiddleCommand1(m_pivotSubsystem));
        
    new POVButton(controller0, GamepadConstants.kDpadRight)
        .onTrue(new AutoPivotAlgaeCommand(m_pivotSubsystem));



    // INTAKE CONTROLS

    new JoystickButton(controller0, GamepadConstants.kBButtonPort)
        .onTrue(new IntakeCommand(m_intakeSubsystem, m_pivotSubsystem.getCurrentState(), controller0, m_pivotSubsystem));
       
    new JoystickButton(controller0, GamepadConstants.kAButtonPort)
        .onTrue(new EjectCommand(m_intakeSubsystem, m_pivotSubsystem.getCurrentState(), controller0));




    // ELEVATOR AND SCORING CONTROLS    
    new POVButton(controller0, GamepadConstants.kDpadDown)
        .onTrue(new ElevatorGroundCommand(m_elevatorSubsystem, controller0));
    
    new JoystickButton(controller0, GamepadConstants.kYButtonPort)
        .onTrue(new AutoPivotUpCommand1(m_pivotSubsystem));
    new JoystickButton(controller0, GamepadConstants.kYButtonPort)
        .onTrue(new ElevatorLevel3Command(m_elevatorSubsystem, controller0));

    new JoystickButton(controller0, GamepadConstants.kXButtonPort)
        .onTrue(new AutoPivotMiddleCommand(m_pivotSubsystem));
    new JoystickButton(controller0, GamepadConstants.kXButtonPort)
        .onTrue(new ElevatorLevel2Command(m_elevatorSubsystem, controller0));
    


    ///////////////////////////////////////////////////////////////////////////////////
    //TeleOp Commands
    ///////////////////////////////////////////////////////////////////////////////////



    ////Intake

    //  new Trigger(m_intakeSubsystem::getRollerSensor)
    //      .onTrue(new AutoIntakeCommand(m_intakeSubsystem, controller0));
    // new JoystickButton(controller0, GamepadConstants.kXButtonPort)
    //    .onTrue(new AutoOuttakeCommand(m_intakeSubsystem));
    // new JoystickButton(controller0, GamepadConstants.kBButtonPort)
    //    .onTrue(new IntakeShutdownCommand(m_intakeSubsystem, controller0));

  
    ////Elevator buttons

    // new JoystickButton(controller1, GamepadConstants.kLeftBumperPort)
    //     .onTrue(new ElevatorDownCommand(m_elevatorSubsystem, controller1));
    // new JoystickButton(controller1, GamepadConstants.kRightBumperPort)
    //     .onTrue(new ElevatorUpCommand(m_elevatorSubsystem, controller1)); 


    ////Pivot buttons

    //new POVButton(controller, GamepadConstants.kDpadUp)
       // .onTrue(new PivotForwardCommand(m_pivotSubsystem, controller));
    // probably change later 
    //new JoystickButton(controller0, GamepadConstants.kRightBumperPort)
       //  .onTrue(new PivotBackwardCommand(m_pivotSubsystem, controller0));

    //Vison offset buttons














    // OFFICIAL GAMEPAD 0 CONTROLLER SCHEME

    //     // Align left on reef
    // new JoystickButton(controller0, GamepadConstants.kLeftBumperPort)
    //     .onTrue(new AlignLeftCommand(m_SwerveDriveSubsystem, m_LimelightSubsystem));
    
    //     // Align right on reef
    //     new JoystickButton(controller0, GamepadConstants.kRightBumperPort)
    //     .onTrue(new AlignRightCommand(m_SwerveDriveSubsystem, m_LimelightSubsystem));
   

  }
  
  public Command getAutonomousCommand() {
    return Autos.exampleAuto(m_exampleSubsystem);
  }

  public void zeroYaw() {
    m_SwerveDriveSubsystem.zeroOutGyro();
  }
}
