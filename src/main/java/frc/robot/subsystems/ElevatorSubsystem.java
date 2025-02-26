package frc.robot.subsystems;

import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkFlexConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PortConstants;
import frc.robot.Constants.ElevatorConstants.ElevatorPositions;



public class ElevatorSubsystem extends SubsystemBase{
    private SparkFlex rightElevatorMotor;
    private SparkFlex leftElevatorMotor;

    private SparkFlexConfig rightSparkFlexConfig;
    private SparkFlexConfig leftSparkFlexConfig;
    
    private double testSpeed;
    private double setPoint;

    private PIDController pidController;
    

    private ElevatorPositions currentState;
    
    //private ShuffleboardTab tab = Shuffleboard.getTab("SmartDashboard");
    //private GenericEntry rightElevatorSpeed = tab.add("RightElevatorSpeed", 0).getEntry();


    public ElevatorSubsystem(){
        rightElevatorMotor = new SparkFlex(PortConstants.kRightElevatorPort, MotorType.kBrushless);
        leftElevatorMotor = new SparkFlex(PortConstants.kLeftElevatorPort, MotorType.kBrushless);

        rightSparkFlexConfig = new SparkFlexConfig();
        rightSparkFlexConfig
            .inverted(false)
            .idleMode(IdleMode.kBrake);
       rightElevatorMotor.configure(rightSparkFlexConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        leftSparkFlexConfig = new SparkFlexConfig();
        leftSparkFlexConfig
            .inverted(true)
            .idleMode(IdleMode.kBrake);
       leftElevatorMotor.configure(leftSparkFlexConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        pidController = new PIDController(0.03, 0.01, 0);
        pidController.setIntegratorRange(-0.05, 0.045);
        testSpeed = 0;
        
    }

    //Set powers
    public void setPower(double power){ 
        rightElevatorMotor.set(power);
        leftElevatorMotor.set(power);
    }
    public void setLeftPower(double power) {
        leftElevatorMotor.set(power);
        leftElevatorMotor.get();
    }
    public void setRightPower(double power){
        rightElevatorMotor.set(power);
    }
    // public int getInscreaseSpeedVar(){
    //     return increaseSpeed;
    // }
    // public void increaseSpeedCommand(){
    //     if(increaseSpeed < 4 ){
    //         increaseSpeed += 1;
    //     }
    //     else{
    //         increaseSpeed = 0;
    //     }
    // }


    // public double getOutput() {
    //     return -pidController.calculate(getShaftEncoder());
    // }

    // public double getShaftEncoder() {
    //     return pivotEncoder.get();
    // }

    //Get information
    public double getSpeed() {
        return testSpeed;
    }

    //PID Methods
    public double getOutput() {
        return pidController.calculate(getElevatorPosition());
    }
    public double getSetPoint() {
        return setPoint;
    }
    public double getPIDError() {
        return pidController.getError();
    }
    public void setPoint(double target) {
        pidController.setSetpoint(target);
        setPoint = target;
    }

    /*
     * Methods for using enum Elevator States
     */



    public double getRightSpeed() {
        return rightElevatorMotor.get();
    }
    public double getLeftSpeed(){
        return leftElevatorMotor.get();
    }
    public double getCurrentError(){
        return pidController.getError();
    }
    public void motorSetPoint(double target){
        pidController.setSetpoint(target);
    }
    public double getSetpoint(){
        return pidController.getSetpoint();
    }
    public double getElevatorPosition(){
        return rightElevatorMotor.getEncoder().getPosition();
    }

    public void shutdown(){
        rightElevatorMotor.set(0);
        leftElevatorMotor.set(0);
    }

    //Test methods
    public void setPowerTestSpeed(){
        rightElevatorMotor.set(testSpeed);
        leftElevatorMotor.set(testSpeed);
    }
    public void upSpeed(){
        testSpeed += 0.1;
    }
    public void downSpeed(){
        testSpeed -= 0.1;
    }
    public void shutdownTestSpeed(){
        testSpeed = 0;
    }
    @Override
    public void periodic() {
        SmartDashboard.putNumber("Right Elevator Motor Speed: ", getRightSpeed());
        SmartDashboard.putNumber("Left Elevator Motor Speed: ", getLeftSpeed());
        SmartDashboard.putNumber("Elevator test speed: ", testSpeed);
        SmartDashboard.putNumber("Elevator encoder: ", getElevatorPosition());
        SmartDashboard.putNumber("Elevator SetPoint", getSetpoint());
        SmartDashboard.updateValues();
    }
}
