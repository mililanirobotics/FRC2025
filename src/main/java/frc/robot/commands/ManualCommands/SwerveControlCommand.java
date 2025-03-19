package frc.robot.commands.ManualCommands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.filter.SlewRateLimiter;

import frc.robot.subsystems.SwerveDriveSubsystem;
import frc.robot.Constants.GamepadConstants;
import frc.robot.Constants.SwerveModuleConstants;
import frc.robot.Constants.DriveConstants;


public class SwerveControlCommand extends Command{
    private ChassisSpeeds chassisSpeeds;

    // Declaring the Subsystem
    private SwerveDriveSubsystem m_SwerveDriveSubsystem;

    // SlewRateLimiter limits the rate of acceleration to be gradual and linear
    private SlewRateLimiter xLimiter, yLimiter, turningLimiter;
    
    private GenericHID gamepad;
    
    private PIDController pid;
    private double currentPosition;

    public SwerveControlCommand(SwerveDriveSubsystem swerveDriveSubsystem, GenericHID gamepad) {
        m_SwerveDriveSubsystem = swerveDriveSubsystem;
        this.gamepad = gamepad;

        this.xLimiter = new SlewRateLimiter(DriveConstants.kTeleDriveMaxAcceleration);
        this.yLimiter = new SlewRateLimiter(DriveConstants.kTeleDriveMaxAcceleration);
        this.turningLimiter = new SlewRateLimiter(DriveConstants.kTeleRotationMaxAngularAcceleration);

        pid = new PIDController(0.01, 0, 0);

        addRequirements(m_SwerveDriveSubsystem);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        // Grabs Joystick Inputs as Speed Inputs
        double xSpeed = gamepad.getRawAxis(GamepadConstants.kLeftYJoystickPort);
        double ySpeed = gamepad.getRawAxis(GamepadConstants.kLeftXJoystickPort);
        double turningSpeed = gamepad.getRawAxis(GamepadConstants.kRightXJoystickPort);
        if (Math.abs(turningSpeed) > 0) {
            m_SwerveDriveSubsystem.setHeadingLimiter(false);
        }

        if(gamepad.getRawAxis(GamepadConstants.kRightTriggerPort) >= 0.5) {
            xSpeed *= 0.25;
            ySpeed *= 0.25;
            turningSpeed *= 0.25;
        }
        
        // Apply Deadband to prevent motors accidentally spinning
        xSpeed = Math.abs(xSpeed) > GamepadConstants.kDeadzone ? xSpeed : 0.0;
        ySpeed = Math.abs(ySpeed) > GamepadConstants.kDeadzone ? ySpeed : 0.0; 
        // turningSpeed = Math.abs(turningSpeed) > GamepadConstants.kDeadzone ? turningSpeed : 0.0;

        if (m_SwerveDriveSubsystem.isHeadingLimited()) {
            pid.setSetpoint(m_SwerveDriveSubsystem.getDesiredHeading());
            pid.setTolerance(1);
            
            currentPosition = m_SwerveDriveSubsystem.getDegrees();

            pid.calculate(currentPosition);
            if (Math.abs(pid.getError()) > 180 && m_SwerveDriveSubsystem.getDesiredHeading() > 180) {
                currentPosition = currentPosition + 360;    
            }
            else if (Math.abs(pid.getError()) > 180) {
                currentPosition = currentPosition -360;
            }
            turningSpeed = -pid.calculate(currentPosition);
        }
        else {
            turningSpeed = Math.abs(turningSpeed) > GamepadConstants.kDeadzone ? turningSpeed : 0.0;
        }

        //Limiting Drive Speeds Acceleration to be linear
        xSpeed = xLimiter.calculate(xSpeed) * DriveConstants.kDriveMetersPerSecondLimit;
        ySpeed = yLimiter.calculate(ySpeed) * DriveConstants.kDriveMetersPerSecondLimit;
        turningSpeed = turningLimiter.calculate(turningSpeed) * DriveConstants.kRotationMaxRadiansPerSecond;

        // Creating desired chassis speeds from joystick inputs.
        chassisSpeeds = ChassisSpeeds.discretize(ChassisSpeeds.fromFieldRelativeSpeeds(
            xSpeed, ySpeed, turningSpeed, m_SwerveDriveSubsystem.getRotation2dDegContinuous()
        ), 0.02);

        // Convert chassis speeds into swerve module states
        SwerveModuleState[] moduleStates = SwerveModuleConstants.kinematics.toSwerveModuleStates(chassisSpeeds);
        
        // Output each module state to the wheels
        m_SwerveDriveSubsystem.setModuleStates(moduleStates);

        // Temporary CANCoder print
        m_SwerveDriveSubsystem.getCANCoderReading();
        SmartDashboard.putNumber("Naxv Yaw", m_SwerveDriveSubsystem.getYawReverse());
        SmartDashboard.putNumber("Naxv Degrees", m_SwerveDriveSubsystem.getDegrees());
        SmartDashboard.putNumber("Radians", m_SwerveDriveSubsystem.getRad());
        SmartDashboard.putNumber("Theta Error", pid.getError());
        SmartDashboard.putNumber("Turning Output", pid.calculate(currentPosition));
        SmartDashboard.updateValues();
    }
    
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_SwerveDriveSubsystem.shutdown();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
        
}