package frc.robot.commands.TestCommands.TestPivotCommands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.GamepadConstants;
import frc.robot.subsystems.PivotSubsystem;

public class PivotDecreaseCommand extends Command {
    private PivotSubsystem m_PivotSubsystem;
    private GenericHID controller;

    public PivotDecreaseCommand (PivotSubsystem pivotSubsystem) {
        m_PivotSubsystem = pivotSubsystem;

        addRequirements(m_PivotSubsystem);
    }
    
    @Override
    public void initialize() {
        m_PivotSubsystem.setPivotPower(m_PivotSubsystem.getSpeed() - 0.1);
    }

    @Override
    public boolean isFinished() {
        return controller.getRawButton(GamepadConstants.kAButtonPort);
    }
}
