package frc.robot.commands.AutonomousCommands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ElevatorConstants;
import frc.robot.Constants.pivotConstant;
import frc.robot.Constants.pivotConstant.PivotPositions;
import frc.robot.subsystems.PivotSubsystem;


public class AutoPivotAlgaeCommand extends Command{
    private PivotSubsystem m_PivotSubsystem;
    private GenericHID controller;

    public AutoPivotAlgaeCommand (PivotSubsystem pivotSubsystem, GenericHID controller){
        m_PivotSubsystem = pivotSubsystem;
        this.controller = controller;
        
        addRequirements(m_PivotSubsystem);
    }
    @Override
    public void initialize(){
        m_PivotSubsystem.setPoint(pivotConstant.kPivotAlgaePosition);
        m_PivotSubsystem.setCurrentState(PivotPositions.INTAKE);
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
        System.out.println("Pivot Algae finished");
    }
    @Override
    public boolean isFinished(){
        return Math.abs(m_PivotSubsystem.getPIDError()) <= pivotConstant.kPivotTolerance;

    }
}
