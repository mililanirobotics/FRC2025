// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.GamepadConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.AutonomousCommands.AutoIntakeCommand;
import frc.robot.commands.AutonomousCommands.AutoOuttakeCommand;
import frc.robot.commands.AutonomousCommands.ElevatorGroundCommand;
import frc.robot.commands.ManualCommands.ElevatorDownCommand;
import frc.robot.commands.ManualCommands.ElevatorUpCommand;
import frc.robot.commands.ManualCommands.IntakeShutdownCommand;
import frc.robot.commands.ManualCommands.PivotBackwardCommand;
import frc.robot.commands.ManualCommands.PivotForwardCommand;
import frc.robot.commands.TestCommands.BottomRollerDownSpeedCommand;
import frc.robot.commands.TestCommands.BottomRollerShutdownCommand;
import frc.robot.commands.TestCommands.BottomRollerUpSpeedCommand;
import frc.robot.commands.TestCommands.ElevatorTestDownSpeedCommand;
import frc.robot.commands.TestCommands.ElevatorTestShutdownCommand;
import frc.robot.commands.TestCommands.ElevatorTestUpSpeedCommand;
import frc.robot.commands.TestCommands.PivotTestDownSpeedCommand;
import frc.robot.commands.TestCommands.PivotTestShutdownSpeedCommand;
import frc.robot.commands.TestCommands.PivotTestUpSpeedCommand;
import frc.robot.commands.TestCommands.TopRollerDownSpeedCommand;
import frc.robot.commands.TestCommands.TopRollerShutdownCommand;
import frc.robot.commands.TestCommands.TopRollerUpSpeedCommand;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.PivotSubsystem;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class RobotContainer {
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();
  private final PivotSubsystem m_pivotSubsystem = new PivotSubsystem();
  private final GenericHID controller1 = new GenericHID(0);
  private final GenericHID controller2 = new GenericHID(1);
  private final ElevatorSubsystem m_elevatorSubsystem = new ElevatorSubsystem();

  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  public RobotContainer() {
    configureBindings();

  }

  
  private void configureBindings() {
    // new Trigger(m_exampleSubsystem::exampleCondition)
    //     .onTrue(new ExampleCommand(m_exampleSubsystem));
    // m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());

    

    //TEST COMMANDS 
    //new POVButton(controller1, GamepadConstants.kDpadLeft).onTrue(new ElevatorTestDownSpeedCommand(m_elevatorSubsystem, controller1));
    new JoystickButton(controller1, GamepadConstants.kAButtonPort)
        .onTrue(new ElevatorTestDownSpeedCommand(m_elevatorSubsystem, controller1));
    new JoystickButton(controller1, GamepadConstants.kYButtonPort)
        .onTrue(new ElevatorTestUpSpeedCommand(m_elevatorSubsystem, controller1));
    new JoystickButton(controller1, GamepadConstants.kBButtonPort)
        .onTrue(new ElevatorTestShutdownCommand(m_elevatorSubsystem, controller1));

    new POVButton(controller1, GamepadConstants.kDpadDown)
        .onTrue(new PivotTestDownSpeedCommand(m_pivotSubsystem, controller1));
    new POVButton(controller1, GamepadConstants.kDpadUp)
        .onTrue(new PivotTestUpSpeedCommand(m_pivotSubsystem, controller1));
    new POVButton(controller1, GamepadConstants.kDpadRight)
        .onTrue(new PivotTestShutdownSpeedCommand(m_pivotSubsystem, controller1));

    new JoystickButton(controller2, GamepadConstants.kAButtonPort)
        .onTrue(new TopRollerDownSpeedCommand(m_intakeSubsystem, controller2));
    new JoystickButton(controller2, GamepadConstants.kYButtonPort)
        .onTrue(new TopRollerUpSpeedCommand(m_intakeSubsystem, controller2));
    new JoystickButton(controller2, GamepadConstants.kBButtonPort)
        .onTrue(new TopRollerShutdownCommand(m_intakeSubsystem, controller2));

    new POVButton(controller2, GamepadConstants.kDpadDown)
        .onTrue(new BottomRollerDownSpeedCommand(m_intakeSubsystem, controller2));
    new POVButton(controller2, GamepadConstants.kDpadUp)
        .onTrue(new BottomRollerUpSpeedCommand(m_intakeSubsystem, controller2));
    new POVButton(controller2, GamepadConstants.kDpadRight)
        .onTrue(new BottomRollerShutdownCommand(m_intakeSubsystem, controller2));
    
    //Intake
    //  new Trigger(m_intakeSubsystem::getRollerSensor)
    //      .onTrue(new AutoIntakeCommand(m_intakeSubsystem, controller1));
    // new JoystickButton(controller1, GamepadConstants.kXButtonPort)
    //    .onTrue(new AutoOuttakeCommand(m_intakeSubsystem));
    // new JoystickButton(controller1, GamepadConstants.kBButtonPort)
    //    .onTrue(new IntakeShutdownCommand(m_intakeSubsystem, controller1));

       
    //Elevator buttons
    // new JoystickButton(controller2, GamepadConstants.kLeftBumperPort)
    //     .onTrue(new ElevatorDownCommand(m_elevatorSubsystem, controller2));
    //new JoystickButton(controller, GamepadConstants.kRightBumperPort)
        //.onTrue(new ElevatorUpCommand(m_elevatorSubsystem, controller)); 


    //Pivot buttons
    //new JoystickButton(controller, GamepadConstants.kDpadUp)
       // .onTrue(new PivotForwardCommand(m_pivotSubsystem, controller));
    // new JoystickButton(controller1, GamepadConstants.kRightBumperPort)
    //     .onTrue(new PivotBackwardCommand(m_pivotSubsystem, controller1));


  }

  
  public Command getAutonomousCommand() {
    return Autos.exampleAuto(m_exampleSubsystem);
  }
}
