package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class AutoIntakeCommand extends Command{
    private IntakeSubsystem m_intakeSubsystem;
    public AutoIntakeCommand(IntakeSubsystem intakeSubsystem){
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
    return !m_intakeSubsystem.getLeftPathSensor() || !m_intakeSubsystem.getRightPathSensor();
    }
}