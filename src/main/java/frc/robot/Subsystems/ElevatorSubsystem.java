package frc.robot.Subsystems;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElevatorConstants;

public class ElevatorSubsystem extends SubsystemBase{
    
    private TalonFX leftLiftMotor;
    private TalonFX rightLiftMotor;

    private TalonFXConfiguration leftLiftMotorConfig;
    public ElevatorSubsystem() {
        leftLiftMotor = new TalonFX(ElevatorConstants.leftLiftID);
        rightLiftMotor = new TalonFX(ElevatorConstants.rightLiftID);

        leftLiftMotorConfig = new TalonFXConfiguration();
    }


    public void setLiftPower(double power) {
        leftLiftMotor.set(power);
        rightLiftMotor.set(power);
    }

    // public StatusSignal[] getLiftEncoderValues() {
    //     StatusSignal<Angle>[] encoderValues = new StatusSignal<Angle>[];

    //     encoderValues[0] = leftLiftMotor.getPosition();
    //     encoderValues[1] = rightLiftMotor.getPosition();

    //     return encoderValues;
    // }
 }