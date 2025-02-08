package frc.robot.commands.AutonomousCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.pivotConstant;
import frc.robot.subsystems.PivotSubsystem;

public class AutoPivotMiddleCommand extends Command{
    private PivotSubsystem m_PivotSubsystem;
    public AutoPivotMiddleCommand(PivotSubsystem pivotSubsystem){
        m_PivotSubsystem = pivotSubsystem;
        addRequirements(m_PivotSubsystem);
    }
    @Override
    public void initialize(){
        m_PivotSubsystem.setPoint(pivotConstant.kPivotMiddleCounts);
    }
    @Override
    public void execute(){
        m_PivotSubsystem.setPivotPower(m_PivotSubsystem.getOutput());
        
    }
    @Override
    public void end(boolean interupted){
        m_PivotSubsystem.shutdown();
    }
    @Override
    public boolean isFinished(){
        return Math.abs(m_PivotSubsystem.getPIDError()) <= pivotConstant.kPivotTolerance;    }
}
