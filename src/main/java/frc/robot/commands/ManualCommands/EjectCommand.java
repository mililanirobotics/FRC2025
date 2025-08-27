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
    private double scoringMultiplier;

    public EjectCommand(IntakeSubsystem m_IntakeSubsystem, PivotPositions pivotState){
        this.m_intakeSubsystem = m_IntakeSubsystem;
        this.pivotState = pivotState;
        addRequirements(m_IntakeSubsystem);
        scoringMultiplier = 1;
    }

    @Override
    public void initialize(){
        m_intakeSubsystem.setAutoIntake(true);
        switch(pivotState) {
            default:
                percentOutput = -.2;
                break;
            case INTAKE:
                percentOutput = -IntakeConstants.IntakePercentOutput;
                break;
            case SCORING:
                percentOutput = -IntakeConstants.ScoringPercentOutput;
                scoringMultiplier = 0.85;
                break;
            case ALGAE:
                percentOutput = -IntakeConstants.AlgaePercentOutput;   
                break;
        }
        m_intakeSubsystem.setRollerPower(.75, scoringMultiplier);

    }

    @Override
    public void end(boolean interupted){
        // m_intakeSubsystem.shutdown();
        // m_intakeSubsystem.setAutoIntake(false);
    }

    @Override
    public boolean isFinished(){
        return true;
    }
}
