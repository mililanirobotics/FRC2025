package frc.robot.subsystems;

import javax.print.attribute.standard.PagesPerMinute;

import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PortConstants;

public class PivotSubsystem extends SubsystemBase {
    private SparkFlex pivotMotor;
    private PIDController pidController;
    private Encoder encoder;

    public PivotSubsystem () {
        pivotMotor = new SparkFlex(PortConstants.kLeftPivotPort, MotorType.kBrushless);
        pidController = new PIDController(0, 0, 0);
        encoder = new Encoder(0, 0);


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
    public double getOutput(double point) {
        return pidController.calculate(getEncoder(), point);
    }
    public void shutdown () {
        pivotMotor.set(0);
    }
}
