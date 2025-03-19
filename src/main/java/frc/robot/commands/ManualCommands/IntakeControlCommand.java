package frc.robot.commands.ManualCommands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.GamepadConstants;
import frc.robot.Constants.IntakeConstants;
import frc.robot.Constants.pivotConstant.PivotPositions;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.PivotSubsystem;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Constants.pivotConstant;

public class IntakeControlCommand extends Command {
    private IntakeSubsystem m_intakeSubsystem;
    private PivotSubsystem m_PivotSubsystem;
    private GenericHID controller;

    private double percentOutput;

    public IntakeControlCommand(IntakeSubsystem m_IntakeSubsystem, PivotSubsystem m_PivotSubsystem, GenericHID controller){
        this.m_intakeSubsystem = m_IntakeSubsystem;

        this.m_PivotSubsystem = m_PivotSubsystem;
        this.controller = controller;

        addRequirements(m_IntakeSubsystem);
    }

    @Override
    public void initialize(){
        
    }

    @Override
    public void execute(){
        // if(!m_intakeSubsystem.getIntakeSensor() && Timer.getTimestamp() > initialTime + 0.1){
        // m_intakeSubsystem.setRollerPower(percentOutput, 1);
        // }
        // else if(m_intakeSubsystem.getIntakeSensor() && Math.abs(m_pivotSubsystem.getPIDError()) > pivotConstant.kPivotTolerance){
        // m_intakeSubsystem.setRollerPower(0.3, 1);
        // m_pivotSubsystem.setPivotPower(m_pivotSubsystem.getOutput());
        // }
        // else if(m_intakeSubsystem.getIntakeSensor() )

        if (m_intakeSubsystem.getAutoIntake()) {
            return;
        }

        switch(m_PivotSubsystem.getCurrentState()) {
            default:
                percentOutput = .2;
                break;
            case INTAKE:
                percentOutput = IntakeConstants.IntakePercentOutput;
                break;
            case SCORING:
                percentOutput = IntakeConstants.IntakePercentOutput;
                break;
            case ALGAE:
                percentOutput = IntakeConstants.AlgaePercentOutput;
                break;
        }
        // percentOutput = .5;
        if (controller.getRawButton(GamepadConstants.kAButtonPort) && !controller.getRawButton(GamepadConstants.kRightBumperPort)) {
            m_intakeSubsystem.setRollerPower(-percentOutput, 1);
        }
        else if (controller.getRawButton(GamepadConstants.kAButtonPort) && controller.getRawButton(GamepadConstants.kRightBumperPort)) {
            m_intakeSubsystem.setRollerPower(percentOutput, 1);
        }
        else {
            m_intakeSubsystem.setRollerPower(0, 1);
        }

    }

    @Override
    public void end(boolean interupted){
        m_intakeSubsystem.shutdown();
        // m_pivotSubsystem.shutdown();
    }

    @Override
    public boolean isFinished(){
        return false;
    }
}
