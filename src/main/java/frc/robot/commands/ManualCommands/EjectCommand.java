package frc.robot.commands.ManualCommands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.GamepadConstants;
import frc.robot.Constants.IntakeConstants;
import frc.robot.Constants.pivotConstant.PivotPositions;
import frc.robot.subsystems.IntakeSubsystem;

public class EjectCommand extends Command {
    private IntakeSubsystem m_intakeSubsystem;
    private PivotPositions pivotState;
    private GenericHID controller;

    private double percentOutput;

    public EjectCommand(IntakeSubsystem m_IntakeSubsystem, PivotPositions pivotState, GenericHID controller){
        this.m_intakeSubsystem = m_IntakeSubsystem;
        this.pivotState = pivotState;
        this.controller = controller;

        addRequirements(m_IntakeSubsystem);
    }

    @Override
    public void initialize(){
        switch(pivotState) {
            default:
                percentOutput = -.2;
                break;
            case INTAKE:
                percentOutput = -IntakeConstants.IntakePercentOutput;
                break;
            case STARTCONFIG:
                percentOutput = -IntakeConstants.ScoringPercentOutput;
                break;
            case ALGAE:
                percentOutput = -IntakeConstants.AlgaePercentOutput;   
                break;
        }
    }

    @Override
    public void execute(){
        m_intakeSubsystem.setRollerPower(percentOutput);
    }

    @Override
    public void end(boolean interupted){
        m_intakeSubsystem.shutdown();
    }

    @Override
    public boolean isFinished(){
        return !controller.getRawButton(GamepadConstants.kAButtonPort);
    }
}