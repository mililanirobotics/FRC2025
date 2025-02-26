package frc.robot.commands.AutonomousCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SwerveDriveSubsystem;

public class AutoReefAlignCommand extends Command {
    private SwerveDriveSubsystem m_SwerveDriveSubsystem;

    public AutoReefAlignCommand(SwerveDriveSubsystem m_SwerveDriveSubsystem) {
        this.m_SwerveDriveSubsystem = m_SwerveDriveSubsystem;
    }
}
