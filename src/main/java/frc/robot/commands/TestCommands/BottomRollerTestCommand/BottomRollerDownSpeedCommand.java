package frc.robot.commands.TestCommands.BottomRollerTestCommand;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.GamepadConstants;
import frc.robot.subsystems.IntakeSubsystem;

public class BottomRollerDownSpeedCommand extends Command{
    IntakeSubsystem m_IntakeSubsystem;
    GenericHID m_controller;
    

    public BottomRollerDownSpeedCommand(IntakeSubsystem intakeSubsystem, GenericHID controller) {
        m_IntakeSubsystem = intakeSubsystem;
        m_controller = controller;
        addRequirements(m_IntakeSubsystem);
    }

    @Override
    public void initialize () {
        m_IntakeSubsystem.bottomDownTestSpeed();
    }

    @Override
    public boolean isFinished(){
        return m_controller.getPOV() == GamepadConstants.kDpadDown;
    }
}
