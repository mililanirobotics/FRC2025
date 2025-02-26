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
        // if (!m_LimelightSubsystem.isReefTargetFound()) {
        //     return;
        // }
        // switch(m_LimelightSubsystem.getTagID()) {
        //     case ""
        // }
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
