package frc.robot.commands.ManualCommands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.GamepadConstants;
import frc.robot.subsystems.IntakeSubsystem;

public class ManualOuttakeCommand extends Command {
    private IntakeSubsystem m_IntakeSubsystem;
    private GenericHID controller;

    public ManualOuttakeCommand (IntakeSubsystem intakeSubsystem, GenericHID controller) {
        m_IntakeSubsystem = intakeSubsystem;
        this.controller = controller;

        addRequirements(m_IntakeSubsystem);
    }
    @Override
    public void initialize() {
    }
    @Override
    public void execute () {
        m_IntakeSubsystem.setRollerPower(-0.5);
    }
    @Override
    public void end(boolean interrupted) {
        m_IntakeSubsystem.shutdown();
    }
    @Override
    public boolean isFinished() {
        return !controller.getRawButton(GamepadConstants.kBButtonPort);
    }
}