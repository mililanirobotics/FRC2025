package frc.robot.commands.AutonomousCommands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorLevel1Command extends Command {
    
    private ElevatorSubsystem m_elevatorSubsystem;
    private GenericHID controller;

    public ElevatorLevel1Command(ElevatorSubsystem elevatorSubsystem, GenericHID controller){
        m_elevatorSubsystem = elevatorSubsystem;
        this.controller = controller;
    }

    @Override
    public void initialize(){
    }

    @Override
    public void execute(){
        m_elevatorSubsystem.setPower(m_elevatorSubsystem.getOutput(?????));
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
