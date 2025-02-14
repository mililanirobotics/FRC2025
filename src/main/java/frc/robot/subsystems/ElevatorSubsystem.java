
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



public class ElevatorSubsystem extends SubsystemBase{
    private SparkFlex rightElevatorMotor;
    private SparkFlex leftElevatorMotor;

    private SparkFlexConfig rightSparkFlexConfig;
    private SparkFlexConfig leftSparkFlexConfig;
    
    private double testSpeed;

    private PIDController pidController;
    private Encoder encoder;
    
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
        pidController = new PIDController(0, 0, 0);
        // encoder = new Encoder(0, 1);

        testSpeed = 0;
        
    }

    //Set powers
    public void setPower(double power){ 
        rightElevatorMotor.set(power);
        leftElevatorMotor.set(power);
    }
    public void setLeftPower(double power) {
        leftElevatorMotor.set(power);
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


    //Get information
    public double getSpeed() {
        return testSpeed;
    }

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
    public double getOutput(){
        return pidController.calculate(encoder.get());
    }
    public double getEnoder(){
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
        SmartDashboard.putNumber("Elevator encoder: ", getEnoder());
        SmartDashboard.putNumber("Elevator SetPoint", getSetpoint());
        SmartDashboard.updateValues();
    }
}
