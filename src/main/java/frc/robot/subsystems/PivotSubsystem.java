package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PortConstants;

public class PivotSubsystem extends SubsystemBase {
    private SparkFlex pivotMotor;

    public PivotSubsystem () {
        pivotMotor = new SparkFlex(PortConstants.kPivotMotorPort, MotorType.kBrushless);
    }
    public void setPivotPower (double power) {
        pivotMotor.set(power);
    }
    public void shutdown () {
        pivotMotor.set(0);
    }

    
}
