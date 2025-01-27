package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.PivotConstants;
import frc.robot.subsystems.PivotSubsystem;


public class AutoPivotDownCommand extends Command{
    private PivotSubsystem m_PivotSubsystem;
    public AutoPivotDownCommand(PivotSubsystem pivotSubsystem) {
        pivotSubsystem = m_PivotSubsystem;

        addRequirements(m_PivotSubsystem);
    }
    @Override
    public void initialize(){
    }
    @Override
    public void execute(){
        m_PivotSubsystem.setPivotPower(m_PivotSubsystem.getOutput(PivotConstants.kDownSetPoint));
    }
    @Override
    public void end(boolean interupted){
        m_PivotSubsystem.shutdown();
    }
    @Override
    public boolean isFinished(){
        return m_PivotSubsystem.getError() == 0;
    }
}