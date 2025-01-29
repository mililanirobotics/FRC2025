package frc.robot.commands.TestCommands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.GamepadConstants;
import frc.robot.subsystems.IntakeSubsystem;

public class BottomRollerUpSpeedCommand extends Command{
    IntakeSubsystem m_IntakeSubsystem;
    GenericHID m_controller;
    

    public BottomRollerUpSpeedCommand(IntakeSubsystem intakeSubsystem, GenericHID controller) {
        m_IntakeSubsystem = intakeSubsystem;
        m_controller = controller;
        addRequirements(m_IntakeSubsystem);
    }

    @Override
    public void initialize () {
        m_IntakeSubsystem.setRollerBottomPower(m_IntakeSubsystem.getBottomSpeed() + 0.1);
    }

    @Override
    public boolean isFinished(){
        return m_controller.getPOV() == GamepadConstants.kDpadUp;
    }
}
