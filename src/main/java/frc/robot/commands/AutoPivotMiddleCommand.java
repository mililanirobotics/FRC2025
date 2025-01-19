package frc.robot.commands;


import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.PIDConstants;
import frc.robot.Constants.pivotConstant;
import frc.robot.subsystems.PivotSubsystem;

public class AutoPivotMiddleCommand extends Command{
    private PivotSubsystem m_PivotSubsystem;
    private PIDController m_pidController;
    public AutoPivotMiddleCommand(PivotSubsystem pivotSubsystem){
        pivotSubsystem = m_PivotSubsystem;
        m_pidController = new PIDController(PIDConstants.kP, PIDConstants.kI, PIDConstants.kD); 
    }
    @Override
    public void initialize(){
        m_PivotSubsystem.setPoint(pivotConstant.kPivotMiddleCounts);
    }
    @Override
    public void execute(){
        m_PivotSubsystem.setPivotPower(m_PivotSubsystem.getOutput(pivotConstant.kPivotMiddleCounts));
        
    }
    @Override
    public void end(boolean interupted){
        m_PivotSubsystem.shutdown();
    }
    @Override
    public boolean isFinished(){
        return m_PivotSubsystem.getPIDError() == 0;
    }
}
