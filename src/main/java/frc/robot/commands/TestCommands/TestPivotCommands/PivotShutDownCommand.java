package frc.robot.commands.TestCommands.TestPivotCommands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.GamepadConstants;
import frc.robot.subsystems.PivotSubsystem;

public class PivotShutDownCommand extends Command {
    private PivotSubsystem m_PivotSubsystem;
    private GenericHID controller;

    public PivotShutDownCommand (PivotSubsystem pivotSubsystem) {
        m_PivotSubsystem = pivotSubsystem;

        addRequirements(m_PivotSubsystem);
    }
    @Override
    public void execute() {
        m_PivotSubsystem.setPivotPower(0);
    }

    @Override
    public boolean isFinished() {
        return controller.getRawButton(GamepadConstants.kRightTriggerPort);
    }
}
