package frc.robot.subsystems;

import java.io.ObjectInputFilter.Config;

import javax.security.auth.login.Configuration;

import com.ctre.phoenix6.configs.jni.ConfigJNI;
import com.fasterxml.jackson.databind.cfg.ConfigOverride;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkMaxConfigAccessor;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PortConstants;;

public class IntakeSubsystem extends SubsystemBase {
    private SparkMax rollerTop;
    private SparkMax rollerBottom;
    private DigitalInput rollerSensor;
    private DigitalInput rightPathSensor;
    private DigitalInput leftPathSensor;
    private SparkMaxConfig reverse;

    public IntakeSubsystem () {
        rollerTop = new SparkMax(PortConstants.kRollerTopPort, MotorType.kBrushless);
        rollerBottom = new SparkMax(PortConstants.kRollerBottomPort, MotorType.kBrushless);
        
        rollerSensor = new DigitalInput(PortConstants.kRollerSensorPort);
        rightPathSensor = new DigitalInput(PortConstants.kRightPathSensor);
        leftPathSensor = new DigitalInput(PortConstants.kLeftPathSensor);
        
    }
    public void setRollerPower (double power) {
        rollerTop.set(power);
        rollerBottom.set(power);
    }
    public void setRollerTopPower (double power) {
        rollerTop.set(power);
    }
    public void setRollerBottomPower (double power) {
        rollerBottom.set(power);
    }
    public boolean getRollerSensor(){
        return rollerSensor.get();
    }
    public boolean getLeftPathSensor(){
        return leftPathSensor.get();
    }
    public boolean getRightPathSensor(){
        return rightPathSensor.get();
    }
    public double getBottomSpeed(){
        return rollerBottom.get();
    }
    public double getTopSpeed() {
        return rollerTop.get();
    }
    public void shutdown() {
        rollerTop.set(0);
        rollerBottom.set(0);
    } 
    @Override
    public void periodic() {
        SmartDashboard.putBoolean("Roller Sensor: ", getRollerSensor());
        SmartDashboard.putBoolean("Left Path Sensor: ", getLeftPathSensor());
        SmartDashboard.putBoolean("Right Path Sensor:", getRightPathSensor());

        SmartDashboard.putNumber("Bottom Roller Speed", getBottomSpeed());
        SmartDashboard.putNumber("Top Roller Speed", getTopSpeed());
        SmartDashboard.updateValues();
    }   

}
