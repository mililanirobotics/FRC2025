package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PortConstants;

public class ElevatorSubsystem extends SubsystemBase{
    private TalonFX rightElevatorMotor;
    private TalonFX leftElevatorMotor;

    private PIDController pidController;
    private Encoder encoder;
    
    private ShuffleboardTab tab = Shuffleboard.getTab("SmartDashboard");
    private GenericEntry rightElevatorSpeed = tab.add("RightElevatorSpeed", 0).getEntry();


    public ElevatorSubsystem(){
        rightElevatorMotor = new TalonFX(PortConstants.kRightElevatorPort);
        leftElevatorMotor = new TalonFX(PortConstants.kLeftElevatorPort);

        rightElevatorMotor.setNeutralMode(NeutralModeValue.Brake);

        pidController = new PIDController(0, 0, 0);
        encoder = new Encoder(0, 1);
        
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

    public double getSpeed() {
        return rightElevatorMotor.get();
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

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Elevator Motor Speed: ", getSpeed());
        SmartDashboard.putNumber("FUHSDKNSHF", rightElevatorSpeed.getDouble(0));
        SmartDashboard.updateValues();
    }
}
