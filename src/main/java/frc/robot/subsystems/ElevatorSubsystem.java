package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ElevatorSubsystem extends SubsystemBase{
    private TalonFX rightElevatorMotor;
    private TalonFX leftElevatorMotor;
    private PIDController pidController;
    private Encoder encoder;
    
    public ElevatorSubsystem(){
        rightElevatorMotor = new TalonFX(0);
        leftElevatorMotor = new TalonFX(18);
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

    // public double getRPM() {
    //     return leftElevatorMotor.();
    // }


    @Override
    public void periodic() {
        // SmartDashboard.putNumber("Motor input: ", getRPM());
        // SmartDashboard.updateValues();
    }
}
