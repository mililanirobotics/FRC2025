package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ElevatorConstants;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorLevel3Command extends Command {
    
    private ElevatorSubsystem m_elevatorSubsystem;

    public ElevatorLevel3Command(ElevatorSubsystem elevatorSubsystem){
        m_elevatorSubsystem = elevatorSubsystem;

        addRequirements(m_elevatorSubsystem);
    }

    @Override
    public void initialize(){
    }

    @Override
    public void execute(){
        m_elevatorSubsystem.setPower(m_elevatorSubsystem.getOutput(ElevatorConstants.kLevel3SetPoint));
    }
    @Override
    public void end(boolean interupted){
        m_elevatorSubsystem.shutdown();
    }
    @Override
    public boolean isFinished(){
        return m_elevatorSubsystem.getCurrentError() == 0;
    }
}
