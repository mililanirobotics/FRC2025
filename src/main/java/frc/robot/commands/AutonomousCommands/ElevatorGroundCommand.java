package frc.robot.commands.AutonomousCommands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ElevatorConstants;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorGroundCommand extends Command{
    
    private ElevatorSubsystem m_elevatorSubsystem;

    public ElevatorGroundCommand(ElevatorSubsystem elevatorSubsystem){
        m_elevatorSubsystem = elevatorSubsystem;

        addRequirements(m_elevatorSubsystem);
    }

    @Override
    public void initialize(){
        m_elevatorSubsystem.setPoint(ElevatorConstants.kGroundCounts);
    }

    @Override
    public void execute(){
        m_elevatorSubsystem.setPower(
            Math.abs(m_elevatorSubsystem.getOutput()) > ElevatorConstants.kMaximumOutput ? 
            Math.copySign(ElevatorConstants.kMaximumOutput, m_elevatorSubsystem.getOutput()) 
            : m_elevatorSubsystem.getOutput());   
     }
    @Override
    public void end(boolean interupted){
        m_elevatorSubsystem.shutdown();
        System.out.println("Command end");
    }
    @Override
    public boolean isFinished(){
        return Math.abs(m_elevatorSubsystem.getCurrentError()) <= 0.5;
    }
}
