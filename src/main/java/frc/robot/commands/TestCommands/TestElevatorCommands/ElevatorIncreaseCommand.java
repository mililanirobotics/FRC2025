package frc.robot.commands.TestCommands.TestElevatorCommands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.GamepadConstants;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorIncreaseCommand extends Command {
    private ElevatorSubsystem m_ElevatorSubsystem;
    private GenericHID controller;

    public ElevatorIncreaseCommand (ElevatorSubsystem elevatorSubsystem) {
        m_ElevatorSubsystem = elevatorSubsystem;

        addRequirements(m_ElevatorSubsystem);
    }

    @Override
    public void initialize() {
        m_ElevatorSubsystem.setPower(m_ElevatorSubsystem.getSpeed() + 0.1);
    }
    @Override
    public boolean isFinished() {
        return controller.getRawButton(GamepadConstants.kYButtonPort);
    }
}
