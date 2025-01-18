package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorUpCommand extends Command{

    private ElevatorSubsystem m_elevatorSubsystem;
    private GenericHID controller;

    public ElevatorUpCommand(ElevatorSubsystem elevatorSubsystem, GenericHID controller){
        m_elevatorSubsystem = elevatorSubsystem;
        this.controller = controller;
    }

    @Override
    public void initialize(){
    }
    @Override
    public void execute(){
        m_elevatorSubsystem.setPower(0.5);
    }
    @Override
    public void end(boolean interupted){
        m_elevatorSubsystem.shutdown();
    }
    @Override
    public boolean isFinished(){
        return !controller.getRawButton(0);
    }
}
