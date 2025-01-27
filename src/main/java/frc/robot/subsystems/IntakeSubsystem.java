package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PortConstants;;

public class IntakeSubsystem extends SubsystemBase {
    private SparkMax topRoller;
    private SparkMax bottomRoller;
    private DigitalInput rollerSensor;
    private DigitalInput leftPathSensor;
    private DigitalInput rightPathSensor;
    

    public IntakeSubsystem () {
        topRoller = new SparkMax(PortConstants.kTopRollerPort, MotorType.kBrushless);
        bottomRoller = new SparkMax(PortConstants.kBottomRollerPort, MotorType.kBrushless);
        rollerSensor = new DigitalInput(PortConstants.kRollerSensorPort);
        leftPathSensor = new DigitalInput(PortConstants.kLeftPathSensorPort);
        rightPathSensor = new DigitalInput(PortConstants.kRightPathSensorPort);
    }
    
    public void setRollerPower (double power) {
        topRoller.set(power);
        bottomRoller.set(power);
    }
    public void setTopRollerPower (double power) {
        topRoller.set(power);
    }
    public void setBottomRollerPower (double power) {
        bottomRoller.set(power);
    }
    public void shutdown() {
        topRoller.set(0);
        bottomRoller.set(0);
    }
    public boolean getRollerSensor () {
        return rollerSensor.get();
    }
    public boolean getLeftPathSensor () {
        return leftPathSensor.get();
    }
    public boolean getRightPathSensor () {
        return rightPathSensor.get();
    }
    public double getTopSpeed () {
        return topRoller.get();
    }
    public double getBottomSpeed () {
        return bottomRoller.get();
    }

    public void periodic () {
        SmartDashboard.putNumber("Top Roller Speed: ", getTopSpeed());
        SmartDashboard.putNumber("Bottom Roller Speed: ", getBottomSpeed());
        SmartDashboard.updateValues();
    }
    // public void periodic() {
    //     SmartDashboard.putBoolean("Roller Sensor: ", getRollerSensor());
    //     SmartDashboard.putBoolean("Left Path Sensor: ", getLeftPathSensor());
    //     SmartDashboard.putBoolean("RightPathSensor: ", getRightPathSensor());
    //     SmartDashboard.updateValues();
    // }
}
