package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ElevatorSubsystem extends SubsystemBase{
    private SparkMax rightElevatorMotor;
    private SparkMax leftElevatorMotor;
    private PIDController pidController;
    private Encoder encoder;
    public ElevatorSubsystem(){
        rightElevatorMotor = new SparkMax(0, MotorType.kBrushless);
        leftElevatorMotor = new SparkMax(0, MotorType.kBrushless);
        pidController = new PIDController(0, 0, 0);
        encoder = new Encoder(0, 0);
    }
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
    public double getCurrentError(){
        return pidController.getError();
    }

    public double getOutput(int setpoint){
        return pidController.calculate(encoder.get(), setpoint);
    }
    public void shutdown(){
        rightElevatorMotor.set(0);
        leftElevatorMotor.set(0);
    }

}
