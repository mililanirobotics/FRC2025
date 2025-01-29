package frc.robot.commands.TestCommands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.GamepadConstants;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorTestUpSpeedCommand extends Command{
    ElevatorSubsystem m_ElevatorSubsystem;
    GenericHID m_controller;

    public ElevatorTestUpSpeedCommand(ElevatorSubsystem elevatorSubsystem, GenericHID controller) {
        m_ElevatorSubsystem = elevatorSubsystem;
        m_controller = controller;
        addRequirements(m_ElevatorSubsystem);
    }

    @Override
    public void initialize () {
        m_ElevatorSubsystem.setPower(m_ElevatorSubsystem.getSpeed() + 0.1);
    }

    @Override
    public boolean isFinished(){
        return m_controller.getRawButton(GamepadConstants.kYButtonPort);
    }
}
