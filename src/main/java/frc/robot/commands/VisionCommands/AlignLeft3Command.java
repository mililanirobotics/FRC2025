package frc.robot.commands.VisionCommands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.GamepadConstants;
import frc.robot.Constants.LimelightConstants;
import frc.robot.Constants.LimelightConstants.VisionConstants;
import frc.robot.Constants.SwerveModuleConstants;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.SwerveDriveSubsystem;

public class AlignLeft3Command extends Command {
    private SwerveDriveSubsystem m_SwerveDriveSubsystem;
    private LimelightSubsystem m_LimelightSubsystem;
    private IntakeSubsystem m_IntakeSubsystem;
    private GenericHID m_controller;


    //private VisionConstants m_VisionState;
    private PIDController yAxisPID;
    private PIDController xAxisPID;
    private double yoffset;
    private double xoffset;

    private double currentYAngle;
    private double currentXAngle;
    private double translationalYSpeed;
    private double translationalXSpeed;

    private boolean validConditions = true;

    public AlignLeft3Command(SwerveDriveSubsystem swerveDriveSubsystem, LimelightSubsystem limelightSubsystem, IntakeSubsystem m_IntakeSubsystem, GenericHID m_controller) {
        this.m_SwerveDriveSubsystem = swerveDriveSubsystem;
        this.m_LimelightSubsystem = limelightSubsystem;
        this.m_IntakeSubsystem = m_IntakeSubsystem;
        this.m_controller = m_controller;
        
        //m_VisionState = visionConstants;
        
        yAxisPID = new PIDController(.2, LimelightConstants.kAlignCenterI, LimelightConstants.kAlignCenterD);
        yAxisPID.enableContinuousInput(-Math.PI, Math.PI);
        yAxisPID.setTolerance(LimelightConstants.kAlignCenterTolerance);

        xAxisPID = new PIDController(LimelightConstants.kAlignCenterP, LimelightConstants.kAlignCenterI, LimelightConstants.kAlignCenterD);
        xAxisPID.enableContinuousInput(-Math.PI, Math.PI);
        xAxisPID.setTolerance(LimelightConstants.kAlignCenterTolerance);

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
        if (m_IntakeSubsystem.isCoralInLeftSlot()) {
            yoffset = Math.toRadians(LimelightConstants.kLeftSlotLAlignOffset);
        }
        // else if (m_IntakeSubsystem.isCoralInRightSlot()) {
        //     yoffset = Math.toRadians(LimelightConstants.kLeftSlotRAlignOffset);
        // }
        else {
            validConditions = false;
        }
        xoffset = Math.toRadians(LimelightConstants.kVertical3AlignOffset);

        yAxisPID.setSetpoint(yoffset);
        xAxisPID.setSetpoint(xoffset);

        translationalYSpeed = yAxisPID.calculate(currentYAngle) * (-3);
        translationalXSpeed = xAxisPID.calculate(currentXAngle) * (3);
    }

    @Override
    public void execute() {
        if (m_controller.getRawAxis(GamepadConstants.kLeftXJoystickPort) > GamepadConstants.kDeadzone || m_controller.getRawAxis(GamepadConstants.kLeftYJoystickPort) > GamepadConstants.kDeadzone) {
            validConditions = false;
            return;
        }

        currentYAngle = Math.toRadians(m_LimelightSubsystem.getHorizontalOffset());
        translationalYSpeed = yAxisPID.calculate(currentYAngle) * (-3);

        currentXAngle = Math.toRadians(m_LimelightSubsystem.getVerticalOffset());
        translationalXSpeed = xAxisPID.calculate(currentXAngle) * (3);
        
        /*
         * Minimal speed buffer for moving
         */
        if (Math.abs(translationalYSpeed) < 0.007) {
            translationalYSpeed = Math.copySign(0.007, translationalYSpeed);
        }
        if (Math.abs(translationalXSpeed) < 0.007) {
            translationalXSpeed = Math.copySign(0.007, translationalXSpeed);
        }

        ChassisSpeeds targetSpeed = ChassisSpeeds.fromRobotRelativeSpeeds(translationalXSpeed, translationalYSpeed, 0, m_SwerveDriveSubsystem.getRotation2dDegContinuous());

        SwerveModuleState[] moduleStates = SwerveModuleConstants.kinematics.toSwerveModuleStates(targetSpeed);
        m_SwerveDriveSubsystem.setModuleStates(moduleStates);

        SmartDashboard.putNumber("xError", xAxisPID.getError());
        SmartDashboard.putNumber("yError", yAxisPID.getError());
        SmartDashboard.putNumber("xOutput", translationalXSpeed);
        SmartDashboard.putNumber("yOutput", translationalYSpeed);
        SmartDashboard.updateValues();
    }

    @Override 
    public void end (boolean isFinished) {
        System.out.println("Command end");
        m_SwerveDriveSubsystem.shutdown();
    }

    public boolean isFinished() {
        return !validConditions || !m_LimelightSubsystem.isReefTargetFound() || ((Math.abs(yAxisPID.getError()) < LimelightConstants.kAlignCenterTolerance) && (Math.abs(xAxisPID.getError()) < LimelightConstants.kAlignCenterTolerance));
    }
}