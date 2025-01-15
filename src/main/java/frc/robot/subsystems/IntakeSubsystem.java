package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PortConstants;;

public class IntakeSubsystem extends SubsystemBase {
    private SparkMax roller1;
    private SparkMax roller2;
    private DigitalInput rollerSensor;
    private DigitalInput leftPathSensor;
    private DigitalInput rightPathSensor;
    

    public IntakeSubsystem () {
        roller1 = new SparkMax(PortConstants.kRoller1Port, MotorType.kBrushless);
        roller2 = new SparkMax(PortConstants.kRoller2Port, MotorType.kBrushless);
        rollerSensor = new DigitalInput(PortConstants.kRollerSensorPort);
        leftPathSensor = new DigitalInput(PortConstants.kLeftPathSensorPort);
        rightPathSensor = new DigitalInput(PortConstants.kRightPathSensorPort);
    }
    
    public void setRollerPower (double power) {
        roller1.set(power);
        roller2.set(power);
    }
    public void setRoller1Power (double power) {
        roller1.set(power);
    }
    public void setRoller2Power (double power) {
        roller2.set(power);
    }
    public void shutdown() {
        roller1.set(0);
        roller2.set(0);
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
}
