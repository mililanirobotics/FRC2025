package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.GamepadConstants;
import frc.robot.subsystems.IntakeSubsystem;


public class AutoOuttakeCommand extends Command {
    private IntakeSubsystem m_intakeSubsystem;
    private GenericHID controller;
    public AutoOuttakeCommand(IntakeSubsystem intakeSubsystem){
        m_intakeSubsystem = intakeSubsystem;

        addRequirements(m_intakeSubsystem);
    }
    @Override
    public void initialize(){

    }
    @Override
    public void execute(){
        m_intakeSubsystem.setRollerPower(0.6);
    }
    @Override
    public void end(boolean interupted){
        m_intakeSubsystem.shutdown();
    }
    @Override
    public boolean isFinished(){
        return m_intakeSubsystem.getRollerSensor() && m_intakeSubsystem.getLeftPathSensor() && m_intakeSubsystem.getRightPathSensor()
        || controller.getRawButton(GamepadConstants.kLeftTriggerPort);
    }
}