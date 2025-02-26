package frc.robot.commands.AutonomousCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.HangSubsystem;

public class AutoHangUpCommand extends Command {
    private HangSubsystem m_HangSubsystem;
    Timer timer = new Timer();
    double initialTime;
    
    public AutoHangUpCommand(HangSubsystem m_HangSubsystem, double time) {
        this.m_HangSubsystem = m_HangSubsystem;
    
        addRequirements(m_HangSubsystem);
    }

    @Override
    public void initialize() {
        m_HangSubsystem.setPower(-1);
        initialTime = timer.get();
    }

    @Override
    public void end(boolean interrupted) {
        m_HangSubsystem.setPower(0);
    }

    @Override
    public boolean isFinished() {
        return timer.get() - initialTime >= 7;
    }
}
