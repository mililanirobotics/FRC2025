package frc.robot.commands.TestCommands.TestRollerCommands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.GamepadConstants;
import frc.robot.subsystems.IntakeSubsystem;

public class TopRollerDecreaseCommand extends Command {
    private IntakeSubsystem m_IntakeSubsystem;
    private GenericHID controller;

    public TopRollerDecreaseCommand (IntakeSubsystem intakeSubsystem) {
        m_IntakeSubsystem = intakeSubsystem;

        addRequirements(m_IntakeSubsystem);
    }
    @Override
    public void initialize() {
        m_IntakeSubsystem.setTopRollerPower(m_IntakeSubsystem.getTopSpeed() - 0.1);
    }
    @Override
    public boolean isFinished() {
        return controller.getPOV() == (GamepadConstants.kDpadDown);
    }
}
