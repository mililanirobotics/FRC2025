package frc.robot.commands.ManualCommands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.GamepadConstants;
import frc.robot.Subsystems.ElevatorSubsystem;

public class ElevatorUpCommand extends Command{

    private ElevatorSubsystem m_elevatorSubsystem;
    private GenericHID controller;
    private double elevatorSpeed;

    public ElevatorUpCommand(ElevatorSubsystem elevatorSubsystem, GenericHID controller){
        m_elevatorSubsystem = elevatorSubsystem;
        this.controller = controller;

        addRequirements(m_elevatorSubsystem);
    }

    @Override
    public void initialize(){
    }
    @Override
    public void execute(){
        m_elevatorSubsystem.setPower(0.3);
    }
    @Override
    public void end(boolean interupted){
        m_elevatorSubsystem.shutdown();
    }
    @Override
    public boolean isFinished(){
        return !controller.getRawButton(GamepadConstants.kRightBumperPort);
    }
}
