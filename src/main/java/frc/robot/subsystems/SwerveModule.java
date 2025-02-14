package frc.robot.subsystems;

import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.signals.SensorDirectionValue;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkFlexConfig;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.SwerveModuleConstants;

public class SwerveModule {
    //Spark max motor controllers
 private final SparkFlex driveMotor;
 private final SparkFlexConfig driveMotorConfig;
 private final SparkMax rotationMotor;
 private final SparkMaxConfig rotationMotorConfig;
 //encoders
 private final RelativeEncoder driveEncoder;
 private final RelativeEncoder rotationEncoder;
 //PID controller
 private final PIDController rotationPID;
 //angle offsets
 private final CANcoder angleCANCoder;
 private final boolean CANCoderReversed;

 //constructor
 public SwerveModule(int drivePort, int rotationPort, boolean driveReversed, boolean rotationReversed,
    int CANCoderPort, double CANCoderOffset, boolean CANCoderReversed) {
     //initializing absolute encoder parameters 
     this.CANCoderReversed = CANCoderReversed;
     angleCANCoder = new CANcoder(CANCoderPort);
   
     // Configure the CANcoder for basic use
     CANcoderConfiguration configs = new CANcoderConfiguration();
     
     // This CANcoder should report absolute position from [-0.5, 0.5) rotations,
     // with a 0.26 rotation offset, with clockwise being positive
     configs.MagnetSensor
      .withAbsoluteSensorDiscontinuityPoint(SwerveModuleConstants.kAbsoluteSensorDiscontinuityPoint)
      .withMagnetOffset(CANCoderOffset)
      .withSensorDirection(SensorDirectionValue.CounterClockwise_Positive);
     
     // Write these configs to the CANcoder
     angleCANCoder.getConfigurator().apply(configs);
     
     //initializing SparkMax
     driveMotor = new SparkFlex(drivePort, MotorType.kBrushless);
     driveMotorConfig = new SparkFlexConfig();
     
     driveMotorConfig
      .inverted(driveReversed)
      .idleMode(IdleMode.kBrake);
     //converting native units to measurements
      driveMotorConfig.encoder
      .positionConversionFactor(SwerveModuleConstants.kRotationToMeters)
      .velocityConversionFactor(SwerveModuleConstants.kMetersPerSecond);

     rotationMotor = new SparkMax(rotationPort, MotorType.kBrushless);
     rotationMotorConfig = new SparkMaxConfig();

     rotationMotorConfig
      .inverted(rotationReversed)
      .idleMode(IdleMode.kBrake)
      .smartCurrentLimit(SwerveModuleConstants.kRotationCurrentLimit);
     //converting native units to measurements
     rotationMotorConfig.encoder
      .positionConversionFactor(SwerveModuleConstants.kRotationToRadians)
      .velocityConversionFactor(SwerveModuleConstants.kRadiansPerSecond);

     //setting motor configurations
     driveMotor.configure(driveMotorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
     rotationMotor.configure(rotationMotorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
     //reseting faults 
     driveMotor.clearFaults();
     rotationMotor.clearFaults();

     //initializing encoders
     driveEncoder = driveMotor.getEncoder();
     rotationEncoder = rotationMotor.getEncoder();   

     //initializing PID controller
     rotationPID = new PIDController(
      SwerveModuleConstants.kTurningP, 
      SwerveModuleConstants.kTurningI, 
      SwerveModuleConstants.kTurningD
     );
     //calculates the least turning degrees to setpoint
     rotationPID.enableContinuousInput(-Math.PI, Math.PI);

     resetEncoders();
 }

 //=========================================================================== 
 // helper methods
 //===========================================================================

 /**
  * Returns the current position of the swerve module's drive motor
  * @return The current displacement of the drive motor in meters
  */
 public double getDrivePosition() {
  return driveEncoder.getPosition();
 }

 /**
  * Returns the current position of the swerve module's rotation motor
  * @return The current rotation of the rotation motor in radians
  */
 public double getRotationPosition() {
  return rotationEncoder.getPosition();
 }

 /**
  * Returns the current velocity of the swerve module's drive motor
  * @return The current velocity of the drive motor in meters per second
  */
 public double getDriveVelocity() {
  return driveEncoder.getVelocity();
 }

 /**
  * Returns the current going into the drive motors
  * @return The current going into the drive motors in amps
  */
 public double getCurrentDrive() {
  return driveMotor.getAppliedOutput();
 }

 /**
  * Returns the current going into the rotation motors
  * @return The current going into the rotation motor in amps
  */
 public double getCurrentRotation() {
  return rotationMotor.getAppliedOutput();
 }

 /**
  * Returns the current velocity of the swerve module's rotation motor
  * @return The current velocity of the rotation motor in radians per second
  */
 public double getRotationVelocity() {
  return rotationEncoder.getVelocity();
 }

 /**
  * Returns the current reading of the absolute encoder
  * Indicates which direction the motor should turn based on absoluteEncoderReversed 
  * @return The current reading of the absolute encoder in radians
  */
 public double getCANCoderReading() {
  double angle = (angleCANCoder.getAbsolutePosition().getValueAsDouble());
  return (angle * 2.0 * Math.PI) * (CANCoderReversed ? -1 : 1);   
 }

 public double getAbsoluteRotations() {
  return angleCANCoder.getAbsolutePosition().getValueAsDouble();
 }

 /**
  * Resets the encoders to their default position
  */
 public void resetEncoders() {
  driveEncoder.setPosition(0);
  rotationEncoder.setPosition(getCANCoderReading());
 }

 public SwerveModuleState getModuleState() {
  return new SwerveModuleState(getDriveVelocity(), Rotation2d.fromRadians(getRotationPosition()));
 }
 /**
  * Stops the movement of the drive and rotation motor 
  */
 public void shutdown() {
  driveMotor.set(0);
  rotationMotor.set(0);
 }

 public void test(double power) {
  driveMotor.set(power);
 }

 /**
  * Sets the optimal swerve module state to a given setpoint 
  * Changes the target drive and rotation speed of the module
  * @param currentState The swerve module state of the motor
  */
 public void setSwerveState(SwerveModuleState currentState) {
  if(Math.abs(currentState.speedMetersPerSecond) < 0.001) {
    shutdown();
    return;
  }
  
  // currentState = getModuleState();
  currentState.optimize(getModuleState().angle);
  double currentSpeed = currentState.speedMetersPerSecond;
  double limit = DriveConstants.kDriveMetersPerSecondLimit;
  double maximumSpeed = DriveConstants.kDriveMaxMetersPerSecond;

  driveMotor.set(Math.abs(currentSpeed) > limit
    ? Math.copySign(limit, currentSpeed) / maximumSpeed
    : currentSpeed / maximumSpeed);

  // driveMotor.set(1);
  rotationMotor.set(rotationPID.calculate(getRotationPosition(), currentState.angle.getRadians()));
 }
}