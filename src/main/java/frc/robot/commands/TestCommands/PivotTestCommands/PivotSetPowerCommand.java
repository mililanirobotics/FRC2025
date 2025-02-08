package frc.robot.commands.TestCommands.PivotTestCommands;


import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.GamepadConstants;
import frc.robot.subsystems.PivotSubsystem;

public class PivotSetPowerCommand extends Command{
    PivotSubsystem m_pivotSubsystem;
    GenericHID m_controller;

    public PivotSetPowerCommand(PivotSubsystem pivotSubsystem, GenericHID controller) {
        m_pivotSubsystem = pivotSubsystem;
        m_controller = controller;
        addRequirements(m_pivotSubsystem);
    }
    @Override
    public void initialize () {
        m_pivotSubsystem.setMotorTestSpeed();
    }
    @Override
    public void end(boolean interupted){
        m_pivotSubsystem.shutdown();
    }
    @Override
    public boolean isFinished(){
        return !(m_controller.getPOV() == GamepadConstants.kDpadLeft);
    }
}
