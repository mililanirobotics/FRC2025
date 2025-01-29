package frc.robot.subsystems;

import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PIDConstants;
import frc.robot.Constants.PortConstants;

public class PivotSubsystem extends SubsystemBase {
    private SparkFlex pivotMotor;
    private PIDController pidController;
    private Encoder encoder;

    public PivotSubsystem () {
        pivotMotor = new SparkFlex(PortConstants.kPivotPort, MotorType.kBrushless);
        pidController = new PIDController(PIDConstants.kP, PIDConstants.kI, PIDConstants.kD);
        encoder = new Encoder(7, 8); //temp holder numbers
    }
    public void setPivotPower (double power) {
        pivotMotor.set(power);
    }
    public double getPIDError() {
        return pidController.getError();
    }
    public void setPoint(double target){
        pidController.setSetpoint(target);
    }
    public int getEncoder() {
        return encoder.get();
    }
    public double getSpeed() {
        return pivotMotor.get();
    }
    public double getOutput(double point) {
        return pidController.calculate(getEncoder(), point);
    }
    public void shutdown () {
        pivotMotor.set(0);
    }

    @Override
    public void periodic(){
        SmartDashboard.putNumber("Pivot speed: ", getSpeed());
        SmartDashboard.updateValues();
    }
}
