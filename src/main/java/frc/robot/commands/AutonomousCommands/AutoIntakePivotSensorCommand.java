package frc.robot.commands.AutonomousCommands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.GamepadConstants;
import frc.robot.Constants.IntakeConstants;
import frc.robot.Constants.pivotConstant.PivotPositions;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.PivotSubsystem;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Constants.pivotConstant;

public class AutoIntakePivotSensorCommand extends Command {
    private IntakeSubsystem m_intakeSubsystem;
    private PivotPositions pivotState;
    private GenericHID controller;
    private PivotSubsystem m_pivotSubsystem;
    private double initialTime;

    private double percentOutput;

    public AutoIntakePivotSensorCommand(IntakeSubsystem m_IntakeSubsystem, PivotPositions pivotState, GenericHID controller, PivotSubsystem m_pivotSubsystem){
        this.m_intakeSubsystem = m_IntakeSubsystem;
        this.m_pivotSubsystem = m_pivotSubsystem;

        this.pivotState = pivotState;
        this.controller = controller;

        addRequirements(m_IntakeSubsystem);
    }

    @Override
    public void initialize(){
        initialTime = Timer.getTimestamp();

        switch(pivotState) {
            default:
                percentOutput = .2;
                break;
            case INTAKE:
                percentOutput = IntakeConstants.IntakePercentOutput;
                break;
            case STARTCONFIG:
                percentOutput = IntakeConstants.IntakePercentOutput;
                break;
            case ALGAE:
                percentOutput = IntakeConstants.AlgaePercentOutput;
                break;
        }
        m_pivotSubsystem.setPoint(pivotConstant.kPivotAlgaePosition);
        m_pivotSubsystem.setCurrentState(PivotPositions.ALGAE);
    }

    @Override
    public void execute(){
        if(!m_intakeSubsystem.getIntakeSensor()){
        m_intakeSubsystem.setRollerPower(percentOutput, 1);
        }
        else if(m_intakeSubsystem.getIntakeSensor() && Math.abs(m_pivotSubsystem.getPIDError()) > pivotConstant.kPivotTolerance && Timer.getTimestamp() > initialTime + 0.2){
        m_intakeSubsystem.setRollerPower(0.3, 1);
        m_pivotSubsystem.setPivotPower(m_pivotSubsystem.getOutput());
        }
        else if(m_intakeSubsystem.getIntakeSensor() && Math.abs(m_pivotSubsystem.getPIDError()) <= pivotConstant.kPivotTolerance){
            m_intakeSubsystem.setRollerPower(0.4, 1);
            m_pivotSubsystem.setPivotPower(0);
        }
      

    }

    @Override
    public void end(boolean interupted){
        m_intakeSubsystem.shutdown();
        m_pivotSubsystem.shutdown();
    }

    @Override
    public boolean isFinished(){
        return !controller.getRawButton(GamepadConstants.kBButtonPort);
    }
}
