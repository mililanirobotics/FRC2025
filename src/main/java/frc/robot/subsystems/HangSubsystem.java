package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PortConstants;

public class HangSubsystem extends SubsystemBase {
    private TalonSRX leftActuator;
    private TalonSRX rightActuator;

    public HangSubsystem() {
        leftActuator = new TalonSRX(PortConstants.kLeftLinearActuatorPort);
        rightActuator = new TalonSRX(PortConstants.kRightLinearActuatorPort);

        leftActuator.setNeutralMode(NeutralMode.Brake);
        rightActuator.setNeutralMode(NeutralMode.Brake);
    }

    public void setPower(double PercentOutput) {
        leftActuator.set(ControlMode.PercentOutput, PercentOutput);
        rightActuator.set(ControlMode.PercentOutput, PercentOutput);
    }

    @Override
    public void periodic() {}
}

