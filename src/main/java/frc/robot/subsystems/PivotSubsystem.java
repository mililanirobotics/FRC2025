package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PortConstants;

public class PivotSubsystem extends SubsystemBase {
    private SparkFlex pivotMotor;
    private PIDController m_PIDController;
    private Encoder encoder;

    public PivotSubsystem () {
        pivotMotor = new SparkFlex(PortConstants.kPivotMotorPort, MotorType.kBrushless);
        m_PIDController = new PIDController(0, 0, 0);
    }
    public void setPivotPower (double power) {
        pivotMotor.set(power);
    }
    public double getOutput(int setpoint){
        return m_PIDController.calculate(encoder.get(), setpoint);
    }
    public double getError () {
        return m_PIDController.getError();
    }

    public double getSpeed () {
        return pivotMotor.get();
    }
    public void shutdown () {
        pivotMotor.set(0);
    }

    public void periodic () {
        SmartDashboard.putNumber("Pivot Motor Speed: ", getSpeed());
        SmartDashboard.updateValues();
    }
}