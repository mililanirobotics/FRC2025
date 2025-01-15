// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.GamepadConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.AutoCommands.ManualPivotDownCommand;
import frc.robot.commands.ManualCommands.ManualIntakeCommand;
import frc.robot.commands.ManualCommands.ManualOuttakeCommand;
import frc.robot.commands.ManualCommands.ManualPivotUpCommand;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.PivotSubsystem;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import frc.robot.commands.AutoCommands.AutoIntakeCommand;
import frc.robot.commands.AutoCommands.AutoOuttakeCommand;

public class RobotContainer {
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();
  private final PivotSubsystem m_pivotSubsystem = new PivotSubsystem();

  private final GenericHID controller = new GenericHID(0);

  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  public RobotContainer() {
    configureBindings();
  }

  
  private void configureBindings() {
    new Trigger(m_exampleSubsystem::exampleCondition)
        .onTrue(new ExampleCommand(m_exampleSubsystem));
    m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());

    new JoystickButton(controller, GamepadConstants.kAButtonPort)
    .onTrue(new ManualIntakeCommand(m_intakeSubsystem, controller));

    new JoystickButton(controller, GamepadConstants.kBButtonPort)
    .onTrue(new ManualOuttakeCommand(m_intakeSubsystem, controller));

    new JoystickButton(controller, GamepadConstants.kLeftBumperPort)
    .onTrue(new ManualPivotUpCommand(m_pivotSubsystem, controller));

    new JoystickButton(controller, GamepadConstants.kRightBumperPort)
    .onTrue(new ManualPivotDownCommand(m_pivotSubsystem, controller));

    new Trigger(m_intakeSubsystem::getRollerSensor)
    .onTrue(new AutoIntakeCommand(m_intakeSubsystem));
    
    new JoystickButton(controller, GamepadConstants.kXButtonPort)
    .onTrue(new AutoOuttakeCommand(m_intakeSubsystem));
  }

  
  public Command getAutonomousCommand() {
    return Autos.exampleAuto(m_exampleSubsystem);
  }
}
