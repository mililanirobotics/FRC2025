package frc.robot.commands.ManualCommands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class PulseCommand extends Command{
    private IntakeSubsystem m_IntakeSubsystem;
    private GenericHID m_controller;
    private boolean isFinished;
    private int counter;

    public PulseCommand(IntakeSubsystem m_IntakeSubsystem, GenericHID m_controller) {
        this.m_IntakeSubsystem  = m_IntakeSubsystem;
        this.m_controller = m_controller;

        addRequirements(m_IntakeSubsystem);
    }

    @Override
    public void initialize() {
        counter = 0;
    }

    @Override 
    public void execute() {
        counter++;
        if (counter < 50) {
            m_IntakeSubsystem.setRollerPower(-.2);
        }
        m_IntakeSubsystem.shutdown();
        isFinished = true;
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}
