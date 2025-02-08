package frc.robot.commands.TestCommands.ElevatorTestCommands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.GamepadConstants;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorTestDownSpeedCommand extends Command{
    ElevatorSubsystem m_ElevatorSubsystem;
    GenericHID m_controller;
    

    public ElevatorTestDownSpeedCommand(ElevatorSubsystem elevatorSubsystem, GenericHID controller) {
        m_ElevatorSubsystem = elevatorSubsystem;
        m_controller = controller;
        addRequirements(m_ElevatorSubsystem);
    }

    @Override
    public void initialize () {
        m_ElevatorSubsystem.downSpeed();
    }

    @Override
    public boolean isFinished(){
        return m_controller.getRawButton(GamepadConstants.kAButtonPort);
    }
}
