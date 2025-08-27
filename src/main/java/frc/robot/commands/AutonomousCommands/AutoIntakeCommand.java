package frc.robot.commands.AutonomousCommands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.GamepadConstants;
import frc.robot.subsystems.IntakeSubsystem;

public class AutoIntakeCommand extends Command{
    private IntakeSubsystem m_intakeSubsystem;
    private GenericHID m_controller;

    public AutoIntakeCommand(IntakeSubsystem intakeSubsystem, GenericHID m_controller){
        m_intakeSubsystem = intakeSubsystem;
        this.m_controller = m_controller;
        
        addRequirements(m_intakeSubsystem);
    }
    @Override
    public void initialize(){
    }
    @Override
    public void execute(){
        m_intakeSubsystem.setRollerPower(-1, 1);
    }
    @Override
    public void end(boolean interupted){
        // m_intakeSubsystem.shutdown();
    }
    @Override
    public boolean isFinished(){
        return m_intakeSubsystem.isCoralIn() || m_controller.getRawAxis(GamepadConstants.kRightTriggerPort) > GamepadConstants.kDeadzone;
    }
}
