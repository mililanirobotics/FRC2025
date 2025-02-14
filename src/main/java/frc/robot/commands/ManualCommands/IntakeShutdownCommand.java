package frc.robot.commands.ManualCommands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.GamepadConstants;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeShutdownCommand extends Command{
    private IntakeSubsystem m_intakeSubsystem;
    private GenericHID m_controller;
    public IntakeShutdownCommand(IntakeSubsystem intakeSubsystem, GenericHID controller){
        m_intakeSubsystem = intakeSubsystem;
        m_controller = controller;
        addRequirements(m_intakeSubsystem);
    }
    @Override
    public void initialize(){

    }
    @Override
    public void execute(){
    m_intakeSubsystem.shutdown();
    }
    @Override
    public void end(boolean interupted){
    m_intakeSubsystem.shutdown();
    }
    @Override
    public boolean isFinished(){
    return !m_controller.getRawButton(GamepadConstants.kBButtonPort);
    }
}
