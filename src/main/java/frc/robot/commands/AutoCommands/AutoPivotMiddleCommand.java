package frc.robot.commands.AutoCommands;


import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.PIDConstants;
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
    }
    @Override
    public void execute(){
        m_PivotSubsystem.setPivotPower(0.1);
    }
    @Override
    public void end(boolean interupted){
        m_PivotSubsystem.shutdown();
    }
    // @Override
    // public boolean isFinished(){
    //     return;
    // }
}