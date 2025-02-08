package frc.robot.commands.TestCommands.PivotTestCommands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.GamepadConstants;
import frc.robot.subsystems.PivotSubsystem;

public class PivotTestDownSpeedCommand extends Command{
    PivotSubsystem m_pivotSubsystem;
    GenericHID m_controller;

    public PivotTestDownSpeedCommand(PivotSubsystem pivotSubsystem, GenericHID controller) {
        m_pivotSubsystem = pivotSubsystem;
        m_controller = controller;
        addRequirements(m_pivotSubsystem);
    }

    @Override
    public void initialize () {
        m_pivotSubsystem.downSpeed();
    }

    @Override
    public boolean isFinished(){
        return m_controller.getPOV() == GamepadConstants.kDpadDown;
    }
}
