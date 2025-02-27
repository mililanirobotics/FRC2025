package frc.robot.commands.VisionCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.SwerveDriveSubsystem;

public class ThetaAlignCommand extends Command {
    private LimelightSubsystem m_LimelightSubsystem;
    private SwerveDriveSubsystem m_SwerveDriveSubsystem;

    public ThetaAlignCommand(LimelightSubsystem m_LimelightSubsystem, SwerveDriveSubsystem m_SwerveDriveSubsystem) {
        this.m_LimelightSubsystem = m_LimelightSubsystem;
        this.m_SwerveDriveSubsystem = m_SwerveDriveSubsystem;

        addRequirements(m_LimelightSubsystem);
    }

    @Override
    public void initialize() {
        if (!m_LimelightSubsystem.isReefTargetFound()) {
            return;
        }
        if (m_LimelightSubsystem.getTagID() == 7 || m_LimelightSubsystem.getTagID() == 18) {
            m_SwerveDriveSubsystem.setDesiredHeading(0);
        }
        if (m_LimelightSubsystem.getTagID() == 8 || m_LimelightSubsystem.getTagID() == 17) {
            m_SwerveDriveSubsystem.setDesiredHeading(60);
        }
        if (m_LimelightSubsystem.getTagID() == 9 || m_LimelightSubsystem.getTagID() == 22) {
            m_SwerveDriveSubsystem.setDesiredHeading(120);
        }
        if (m_LimelightSubsystem.getTagID() == 10 || m_LimelightSubsystem.getTagID() == 21) {
            m_SwerveDriveSubsystem.setDesiredHeading(180);
        }
        if (m_LimelightSubsystem.getTagID() == 11 || m_LimelightSubsystem.getTagID() == 20) {
            m_SwerveDriveSubsystem.setDesiredHeading(240);
        }
        if (m_LimelightSubsystem.getTagID() == 6 || m_LimelightSubsystem.getTagID() == 19) {
            m_SwerveDriveSubsystem.setDesiredHeading(300);
        }
        m_SwerveDriveSubsystem.setHeadingLimiter(true);    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
