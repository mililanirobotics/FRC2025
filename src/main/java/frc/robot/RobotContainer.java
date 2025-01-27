// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.GamepadConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

// SUBSYSTEMS
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.PivotSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;


// MANUAL COMMANDS
import frc.robot.commands.ManualCommands.ElevatorUpCommand;
import frc.robot.commands.ManualCommands.ElevatorDownCommand;
import frc.robot.commands.ManualCommands.ManualIntakeCommand;
import frc.robot.commands.ManualCommands.ManualOuttakeCommand;
import frc.robot.commands.ManualCommands.ManualIntakeShutDownCommand;
import frc.robot.commands.ManualCommands.ManualPivotDownCommand;
import frc.robot.commands.ManualCommands.ManualPivotUpCommand;

// AUTO COMMANDS
import frc.robot.commands.AutoCommands.AutoIntakeCommand;
import frc.robot.commands.AutoCommands.AutoOuttakeCommand;


// TEST COMMANDS
import frc.robot.commands.TestCommands.TestPivotCommands.PivotDecreaseCommand;
import frc.robot.commands.TestCommands.TestPivotCommands.PivotIncreaseCommand;
import frc.robot.commands.TestCommands.TestPivotCommands.PivotShutDownCommand;

import frc.robot.commands.TestCommands.TestElevatorCommands.ElevatorIncreaseCommand;
import frc.robot.commands.TestCommands.TestElevatorCommands.ElevatorDecreaseCommand;
import frc.robot.commands.TestCommands.TestElevatorCommands.ElevatorShutDownCommand;

import frc.robot.commands.TestCommands.TestRollerCommands.TopRollerIncreaseCommand;
import frc.robot.commands.TestCommands.TestRollerCommands.TopRollerDecreaseCommand;
import frc.robot.commands.TestCommands.TestRollerCommands.TopRollerShutDownCommand;
import frc.robot.commands.TestCommands.TestRollerCommands.BottomRollerIncreaseCommand;
import frc.robot.commands.TestCommands.TestRollerCommands.BottomRollerDecreaseCommand;
import frc.robot.commands.TestCommands.TestRollerCommands.BottomRollerShutDownCommand;

public class RobotContainer {
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();
  private final PivotSubsystem m_pivotSubsystem = new PivotSubsystem();
  private final ElevatorSubsystem m_elevatorSubsystem = new ElevatorSubsystem();

  private final GenericHID controller = new GenericHID(0);
  private final GenericHID controller2 = new GenericHID(1);

  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  public RobotContainer() {
    configureBindings();
  }

  
  private void configureBindings() {
    new Trigger(m_exampleSubsystem::exampleCondition)
        .onTrue(new ExampleCommand(m_exampleSubsystem));
    m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());


    // GAMEPAD ONE // 

    // MANUAL INTAKE
    new JoystickButton(controller, GamepadConstants.kAButtonPort)
    .onTrue(new ManualIntakeCommand(m_intakeSubsystem, controller));

    // MANUAL OUTTAKE
    new JoystickButton(controller, GamepadConstants.kBButtonPort)
    .onTrue(new ManualOuttakeCommand(m_intakeSubsystem, controller));

    // MANUAL PIVOT UP
    new JoystickButton(controller, GamepadConstants.kDpadUp)
    .onTrue(new ManualPivotUpCommand(m_pivotSubsystem, controller));
    // m_pivotSubsystem.setDefaultCommand(new ManualPivotUpCommand(m_pivotSubsystem, controller2));

    // MANUAL PIVOT DOWN
    new JoystickButton(controller, GamepadConstants.kDpadDown)
    .onTrue(new ManualPivotDownCommand(m_pivotSubsystem, controller));

    // MANUAL INTAKE SHUT DOWN
    new JoystickButton(controller, GamepadConstants.kRightTriggerPort)
    .onTrue(new ManualIntakeShutDownCommand(m_intakeSubsystem, controller));

    // MANUAL ELEVATOR DOWN
    new JoystickButton(controller, GamepadConstants.kLeftBumperPort)
    .onTrue(new ElevatorDownCommand(m_elevatorSubsystem, controller));

    // MANUAL ELEVATOR UP
    new JoystickButton(controller, GamepadConstants.kRightBumperPort)
    .onTrue(new ElevatorUpCommand(m_elevatorSubsystem, controller));
    // m_elevatorSubsystem.setDefaultCommand(new ElevatorUpCommand(m_elevatorSubsystem, controller2));





    // GAMEPAD TWO //

    // Pivot Power Adjustment
    
    new JoystickButton(controller2, GamepadConstants.kAButtonPort) 
    .onTrue(new PivotIncreaseCommand(m_pivotSubsystem));

    new JoystickButton(controller2, GamepadConstants.kBButtonPort) 
    .onTrue(new PivotDecreaseCommand(m_pivotSubsystem));

    new JoystickButton(controller2, GamepadConstants.kRightTriggerPort) 
    .onTrue(new PivotShutDownCommand(m_pivotSubsystem));

    // Elevator Power Adjustment

    new JoystickButton(controller2, GamepadConstants.kYButtonPort)
    .onTrue(new ElevatorIncreaseCommand(m_elevatorSubsystem));

    new JoystickButton(controller2, GamepadConstants.kXButtonPort)
    .onTrue(new ElevatorDecreaseCommand(m_elevatorSubsystem));

    new JoystickButton(controller2, GamepadConstants.kLeftTriggerPort)
    .onTrue(new ElevatorShutDownCommand(m_elevatorSubsystem));

    // Top Roller Power Adjustment

    new POVButton(controller2, GamepadConstants.kDpadUp)
    .onTrue(new TopRollerIncreaseCommand(m_intakeSubsystem));

    new POVButton(controller2, GamepadConstants.kDpadDown)
    .onTrue(new TopRollerDecreaseCommand(m_intakeSubsystem));

    new JoystickButton(controller2, GamepadConstants.kLeftBumperPort)
    .onTrue(new TopRollerShutDownCommand(m_intakeSubsystem));

    // Bottom Roller Power Adjustment

    new POVButton(controller2, GamepadConstants.kDpadRight)
    .onTrue(new BottomRollerIncreaseCommand(m_intakeSubsystem));

    new POVButton(controller2, GamepadConstants.kDpadLeft)
    .onTrue(new BottomRollerDecreaseCommand(m_intakeSubsystem));

    new JoystickButton(controller2, GamepadConstants.kRightBumperPort)
    .onTrue(new BottomRollerShutDownCommand(m_intakeSubsystem));





    // AUTONOMOUS COMMANDS

    new Trigger(m_intakeSubsystem::getRollerSensor)
    .onTrue(new AutoIntakeCommand(m_intakeSubsystem));
    
    new Trigger(m_intakeSubsystem::getRollerSensor)
    .onTrue(new AutoOuttakeCommand(m_intakeSubsystem));
  }

  
  public Command getAutonomousCommand() {
    return Autos.exampleAuto(m_exampleSubsystem);
  }
}
