package frc.robot.commands.VisionCommands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.GamepadConstants;
import frc.robot.Constants.LimelightConstants;
import frc.robot.Constants.LimelightConstants.VisionConstants;
import frc.robot.Constants.SwerveModuleConstants;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.SwerveDriveSubsystem;

public class AlignDistanceCommand extends Command {
    private SwerveDriveSubsystem m_SwerveDriveSubsystem;
    private LimelightSubsystem m_LimelightSubsystem;
    //private VisionConstants m_VisionState;
    private PIDController alignCenterPID;
    private double offset;

    private double currentAngle;
    private double translationalSpeed;

    private GenericHID controller;

    public AlignDistanceCommand(SwerveDriveSubsystem swerveDriveSubsystem, LimelightSubsystem limelightSubsystem) {
        m_SwerveDriveSubsystem = swerveDriveSubsystem;
        m_LimelightSubsystem = limelightSubsystem;
        //m_VisionState = visionConstants;

        alignCenterPID = new PIDController(LimelightConstants.kAlignCenterP, LimelightConstants.kAlignCenterI, LimelightConstants.kAlignCenterD);
        alignCenterPID.enableContinuousInput(-Math.PI, Math.PI);
        alignCenterPID.setTolerance(LimelightConstants.kAlignCenterTolerance);

        addRequirements(swerveDriveSubsystem, limelightSubsystem);
    }

    @Override
    public void initialize() {
        // switch(m_VisionState) {
        //     case LEFTCORAL:
        //         offset = LimelightConstants.kLeftAlignOffset;
        //         break;
        //     case RIGHTCORAL: 
        //         offset = LimelightConstants.kRightAlignOffset;
        //         break;
        // }
        offset = Math.toRadians(LimelightConstants.kVertical3AlignOffset);
    }

    @Override
    public void execute() {
        currentAngle = Math.toRadians(m_LimelightSubsystem.getVerticalOffset());
        translationalSpeed = alignCenterPID.calculate(currentAngle, offset) * (-3);

        /*
         * Minimal speed buffer for turning
         */
        if (Math.abs(translationalSpeed) < 0.007) {
            translationalSpeed = Math.copySign(0.007, translationalSpeed);
        }

        ChassisSpeeds targetSpeed = ChassisSpeeds.fromRobotRelativeSpeeds(0, translationalSpeed, 0, Rotation2d.fromDegrees(0));

        SwerveModuleState[] moduleStates = SwerveModuleConstants.kinematics.toSwerveModuleStates(targetSpeed);
        m_SwerveDriveSubsystem.setModuleStates(moduleStates);
    }

    @Override 
    public void end (boolean isFinished) {
        System.out.println("Command end");
        m_SwerveDriveSubsystem.shutdown();
    }

    public boolean isFinished() {
        return Math.abs(offset-currentAngle) < LimelightConstants.kAlignCenterTolerance || controller.getRawButton(GamepadConstants.kLeftTriggerPort);
    }
}