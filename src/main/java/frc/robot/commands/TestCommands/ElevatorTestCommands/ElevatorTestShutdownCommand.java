package frc.robot.commands.TestCommands.ElevatorTestCommands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.GamepadConstants;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorTestShutdownCommand extends Command{
    ElevatorSubsystem m_ElevatorSubsystem;
    GenericHID m_controller;

    public ElevatorTestShutdownCommand(ElevatorSubsystem elevatorSubsystem, GenericHID controller) {
        m_ElevatorSubsystem = elevatorSubsystem;
        m_controller = controller;
        addRequirements(m_ElevatorSubsystem);
    }

    @Override
    public void initialize () {
        m_ElevatorSubsystem.shutdownTestSpeed();
    }

    @Override
    public boolean isFinished(){
        return m_controller.getRawButton(GamepadConstants.kBButtonPort);
    }
}
