package frc.robot.commands.TestCommands.TestRollerCommands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.GamepadConstants;
import frc.robot.subsystems.IntakeSubsystem;

public class BottomRollerIncreaseCommand extends Command {
    private IntakeSubsystem m_IntakeSubsystem;
    private GenericHID controller;

    public BottomRollerIncreaseCommand (IntakeSubsystem intakeSubsystem) {
        m_IntakeSubsystem = intakeSubsystem;

        addRequirements(m_IntakeSubsystem);
    }

    @Override
    public void initialize() {
        m_IntakeSubsystem.setBottomRollerPower(m_IntakeSubsystem.getBottomSpeed() + 1);
    }

    @Override
    public boolean isFinished() {
        return controller.getPOV() == (GamepadConstants.kDpadRight);
    }
}
