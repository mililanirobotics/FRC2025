package frc.robot.commands.TestCommands.BottomRollerTestCommands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.GamepadConstants;
import frc.robot.subsystems.IntakeSubsystem;

public class BottomRollerSetPowerCommand extends Command{
    IntakeSubsystem m_IntakeSubsystem;
    GenericHID m_controller;
    

    public BottomRollerSetPowerCommand(IntakeSubsystem intakeSubsystem, GenericHID controller) {
        m_IntakeSubsystem = intakeSubsystem;
        m_controller = controller;
        addRequirements(m_IntakeSubsystem);
    }

    @Override
    public void initialize () {
        m_IntakeSubsystem.setBottomPowerTestSpeed();
    }

    @Override
    public boolean isFinished(){
        return !(m_controller.getPOV() == GamepadConstants.kDpadLeft);
    }
}
