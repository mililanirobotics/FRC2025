package frc.robot.commands.AutonomousCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SwerveDriveSubsystem;

public class LeftCoralStationAlignCommand extends Command {
    private SwerveDriveSubsystem m_SwerveDriveSubsystem;

    public LeftCoralStationAlignCommand(SwerveDriveSubsystem m_SwerveDriveSubsystem) {
        this.m_SwerveDriveSubsystem = m_SwerveDriveSubsystem;
    }

    @Override
    public void initialize() {
        m_SwerveDriveSubsystem.setDesiredHeading(225);
        m_SwerveDriveSubsystem.setHeadingLimiter(true);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
