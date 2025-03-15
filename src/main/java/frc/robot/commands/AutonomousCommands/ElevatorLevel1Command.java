package frc.robot.commands.AutonomousCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ElevatorConstants;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorLevel1Command extends Command {
    
    private ElevatorSubsystem m_elevatorSubsystem;

    public ElevatorLevel1Command(ElevatorSubsystem elevatorSubsystem){
        m_elevatorSubsystem = elevatorSubsystem;

        addRequirements(m_elevatorSubsystem);
    }

    @Override
    public void initialize(){
        m_elevatorSubsystem.setPoint(ElevatorConstants.kLevel1Counts);

    }

    @Override
    public void execute(){
        m_elevatorSubsystem.setPower(m_elevatorSubsystem.getOutput());
    }
    @Override
    public void end(boolean interupted){
        m_elevatorSubsystem.shutdown();
    }
    @Override
    public boolean isFinished(){
        return m_elevatorSubsystem.getCurrentError() <= 0.5;
    }
}
