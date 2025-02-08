package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.MAXMotionConfig;
import com.revrobotics.spark.config.SparkFlexConfig;
import com.revrobotics.spark.config.MAXMotionConfig.MAXMotionPositionMode;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import frc.robot.Constants.PIDConstants;
import frc.robot.Constants.PortConstants;

public class PivotSubsystem extends SubsystemBase {
    private SparkFlex pivotMotor;
    private SparkFlexConfig pivotConfig;
    private MAXMotionConfig maxMotionConfig;
    private SparkClosedLoopController m_controller;
    private PIDController pidController;
    private Encoder encoder;
    private double testSpeed;
    private double setPoint;

    public PivotSubsystem () {
        pivotMotor = new SparkFlex(PortConstants.kPivotPort, MotorType.kBrushless);
        pivotConfig = new SparkFlexConfig();
        // maxMotionConfig = new MAXMotionConfig();

        // maxMotionConfig
        //     // .positionMode(MAXMotionPositionMode.fromInt(0))
        //     .maxAcceleration(0.01)
        //     .maxVelocity(0.001);

        pivotConfig
            .inverted(false)
            .idleMode(IdleMode.kBrake);
            // .closedLoop.apply(maxMotionConfig)
            // .pid(.0001, 0, 0);

        pivotMotor.configure(pivotConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
         pidController = new PIDController(0.02, 0.001, 0);
         // encoder = new Encoder(7, 8); //temp holder numbers4
        

        testSpeed = 0;
    }

    public void setPivotPower (double power) {
        pivotMotor.set(power);
    }

    public double getPIDError() {
        return pidController.getError();
    }

    public void setPoint(double target){
        pidController.setSetpoint(target);
        // pivotMotor.getClosedLoopController().setReference(target, SparkBase.ControlType.kMAXMotionPositionControl);
        setPoint = target;
    }

    public double getSetPoint(){
        return setPoint;
    }

    // public int getEncoder() {
    //     return encoder.get();
    // }

    public double getPivotPosition(){
        return pivotMotor.getEncoder().getPosition();
    }

    // public double getPivotEncoder(){
    //     return pivotMotor.getAbsoluteEncoder().getPosition();
    // }

    public double getSpeed() {
        return pivotMotor.get();
    }
    
    public double getOutput() {
        return pidController.calculate(getPivotPosition());
    }

    public void shutdown () {
        pivotMotor.set(0);
    }

    /*///////////////////////////
     * Test Methods
     *//////////////////////////

    // Gets the test speed
    public void setMotorTestSpeed() {
        pivotMotor.set(testSpeed);
    }
    // Increments the pivot's speed by +5%
    public void upSpeed() {
        testSpeed += 0.05;
    }
    // Decrease the pivot's speed by -5%
    public void downSpeed() {
        testSpeed -= 0.05;
    }
    // Stop the motor
    public void testSpeedShutdown() {
        testSpeed = 0;
    }

    @Override
    public void periodic(){
        SmartDashboard.putNumber("Pivot speed: ", testSpeed);
        SmartDashboard.putNumber("Pivot Encoder: " , getPivotPosition());
        SmartDashboard.putNumber("Pivot Output: ", getOutput());
        SmartDashboard.putNumber("Pivot Setpoint", getSetPoint());
        SmartDashboard.putNumber("Pivot Error: ", getPIDError());
        SmartDashboard.updateValues();
    }
}
