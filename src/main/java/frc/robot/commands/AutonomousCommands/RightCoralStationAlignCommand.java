package frc.robot.commands.AutonomousCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SwerveDriveSubsystem;

public class RightCoralStationAlignCommand extends Command {
    private SwerveDriveSubsystem m_SwerveDriveSubsystem;

    public RightCoralStationAlignCommand(SwerveDriveSubsystem m_SwerveDriveSubsystem) {
        this.m_SwerveDriveSubsystem = m_SwerveDriveSubsystem;
    }

    @Override
    public void initialize() {
        m_SwerveDriveSubsystem.setDesiredHeading(135);
        m_SwerveDriveSubsystem.setHeadingLimiter(true);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
