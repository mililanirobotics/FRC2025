package frc.robot.commands.AutonomousCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.pivotConstant;
import frc.robot.Constants.pivotConstant.PivotPositions;
import frc.robot.subsystems.PivotSubsystem;

public class AutoPivotMiddleCommand extends Command{
    private PivotSubsystem m_PivotSubsystem;
    public AutoPivotMiddleCommand(PivotSubsystem pivotSubsystem){
        m_PivotSubsystem = pivotSubsystem;
        addRequirements(m_PivotSubsystem);
    }
    @Override
    public void initialize(){
        m_PivotSubsystem.setPoint(pivotConstant.kPivotMiddlePosition);
        m_PivotSubsystem.setCurrentState(PivotPositions.ALGAE);
    }
    @Override
    public void execute(){
        m_PivotSubsystem.setPivotPower(
            Math.abs(m_PivotSubsystem.getOutput()) > pivotConstant.kMaximumOutput ? 
            Math.copySign(pivotConstant.kMaximumOutput, m_PivotSubsystem.getOutput()) 
            : m_PivotSubsystem.getOutput());
        
    }
    @Override
    public void end(boolean interupted){
        m_PivotSubsystem.shutdown();
        System.out.println("Pivot Middle finished");
    }
    @Override
    public boolean isFinished(){
        return Math.abs(m_PivotSubsystem.getPIDError()) <= pivotConstant.kPivotTolerance;    }
}
