package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PortConstants;

public class IntakeSubsystem extends SubsystemBase {
    private SparkMax rollerTop;
    private SparkMax rollerBottom;
    private DigitalInput IntakeSensor;
    private DigitalInput IntakeLeftSensor;
    private DigitalInput IntakeRightSensor;
    private SparkMaxConfig topMotorConfig;
    private SparkMaxConfig bottomMotorConfig;
    double topTestSpeed = 0;
    double bottomTestSpeed = 0;

    public IntakeSubsystem () {
        rollerTop = new SparkMax(PortConstants.kRollerTopPort, MotorType.kBrushless);
        rollerBottom = new SparkMax(PortConstants.kRollerBottomPort, MotorType.kBrushless);

        IntakeSensor = new DigitalInput(PortConstants.kIntakeSensorPort);
        IntakeLeftSensor = new DigitalInput(PortConstants.kIntakeLeftSensorPort);
        IntakeRightSensor = new DigitalInput(PortConstants.kIntakeRightSensorPort);

        bottomMotorConfig = new SparkMaxConfig();
        bottomMotorConfig
            .inverted(false)
            .idleMode(IdleMode.kBrake);
        rollerBottom.configure(bottomMotorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        topMotorConfig = new SparkMaxConfig();
        topMotorConfig
            .inverted(true)
            .idleMode(IdleMode.kBrake);
        rollerTop.configure(topMotorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        
    }
    public void setRollerPower (double power, double multiplier) {
        rollerTop.set(power);
        rollerBottom.set(power*multiplier);
    }
    public void setRollerTopPower (double power) {
        rollerTop.set(power);
    }
    public void setRollerBottomPower (double power) {
        rollerBottom.set(power);
    }
    /*
     * Returns true if the intake sensor is broken to detect if the coral is inside the payload.
     */
    public boolean isCoralIn(){
        return !IntakeSensor.get();
    }
    /*
     * Returns true if the left slot's sensor is broken.
     */
    public boolean isCoralInLeftSlot() {
        return !IntakeLeftSensor.get();
    } 
    /*
     * Returns true if the right slot's sensor is broken.
     */
    public boolean isCoralInRightSlot() {
        return !IntakeRightSensor.get();
    }

    public double getBottomSpeed(){
        return rollerBottom.get();
    }
    public double getTopSpeed() {
        return rollerTop.get();
    }

    public void topUpTestSpeed(){
        topTestSpeed += 0.05;
    }
    public void topDownTestSpeed(){
        topTestSpeed -= 0.05;
    }
    public void topTestSpeedShutdown(){
        topTestSpeed = 0;
    }
    public double getTopTestSpeed(){
        return topTestSpeed;
    }
    public void setTopPowerTestSpeed(){
        rollerTop.set(topTestSpeed);
    }
    public void bottomUpTestSpeed(){
        bottomTestSpeed += 0.05;
    }
    public void bottomDownTestSpeed(){
        bottomTestSpeed -= 0.05;
    }
    public double getBottomTestSpeed(){
        return bottomTestSpeed;
    }
    public void setBottomPowerTestSpeed(){
        rollerBottom.set(bottomTestSpeed);
    }
    public void bottomTestSpeedShutdown(){
        bottomTestSpeed = 0;
    }

    public void shutdown() {
        rollerTop.set(0);
        rollerBottom.set(0);
    } 
    @Override
    public void periodic() {
        SmartDashboard.putBoolean("IntakeSensor: ", isCoralIn());
        SmartDashboard.putBoolean("LeftSensor: ", isCoralInLeftSlot());
        SmartDashboard.putBoolean("RightSensor: ", isCoralInRightSlot());
        SmartDashboard.putNumber("Bottom Roller Speed", getBottomSpeed());
        SmartDashboard.putNumber("Top Roller Speed", getTopSpeed());
        SmartDashboard.putNumber("Top Roller Test Speed", getTopTestSpeed());
        SmartDashboard.putNumber("Bottom Roller Test Speed", getBottomTestSpeed());
        SmartDashboard.updateValues();
    }   

}
