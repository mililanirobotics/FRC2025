package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;

public class LiftSubsystem {
    private SparkMax bottomBlue;
    private SparkMax topBlue;
    private SparkMax bottomGreen;
    private SparkMax topGreen;
    private DigitalInput bottomSensor;
    private DigitalInput middleSensor;
    private DigitalInput topSensor;

    public LiftSubsystem(){
        bottomBlue = new SparkMax(0, MotorType.kBrushless);
        topBlue = new SparkMax(0, MotorType.kBrushless);
        bottomGreen = new SparkMax(0, MotorType.kBrushless);
        topGreen = new SparkMax(0, MotorType.kBrushless);
        bottomSensor = new DigitalInput(0);
        middleSensor = new DigitalInput(0);
        topSensor = new DigitalInput(0);
    }
    public void setTopPower(double power){
        topBlue.set(power);
        topGreen.set(power);
    }
    public void setBottomPower(double power){
        bottomBlue.set(power);
        bottomGreen.set(power);
    }
    public void shutdown(){
        topBlue.set(0);
        bottomBlue.set(0);
        topGreen.set(0);
        bottomGreen.set(0);
    }
    public void getBottomSensor(){
        bottomSensor.get();
    }
    public void getMiddleSensor(){
        middleSensor.get();
    }
    public void getTopSensor(){
        topSensor.get();
    }

}
