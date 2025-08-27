package frc.robot.commands;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LEDSubsystem;
import frc.robot.subsystems.PivotSubsystem;
import frc.robot.Constants.pivotConstant.PivotPositions;

public class LEDStatesCommand extends Command {
    private LEDSubsystem m_LedSubsystem;
    private PivotSubsystem m_PivotSubsystem;
    private IntakeSubsystem m_IntakeSubsystem;

    public LEDStatesCommand(LEDSubsystem m_LedSubsystem, PivotSubsystem m_PivotSubsystem, IntakeSubsystem m_IntakeSubsystem) {
        this.m_LedSubsystem = m_LedSubsystem;
        this.m_PivotSubsystem = m_PivotSubsystem;
        this.m_IntakeSubsystem = m_IntakeSubsystem;

        addRequirements(m_LedSubsystem);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        if(m_LedSubsystem.getHPSignal()) {
            m_LedSubsystem.coralInDisplay();
            m_LedSubsystem.setAnimSpeed(.25);
            m_LedSubsystem.strobeAnimation();
            return;
        }

        if(m_IntakeSubsystem.isCoralInLeftSlot()) {
            m_LedSubsystem.coralLeftDisplay();
        }
        else if (m_IntakeSubsystem.isCoralInRightSlot()) {
            m_LedSubsystem.coralRightDisplay();
        }
        else if (m_IntakeSubsystem.isCoralIn()) {
            m_LedSubsystem.setColor(new Color(128, 0, 128));
        }
        else {
            m_LedSubsystem.neutralDisplay();
        }

        switch(m_PivotSubsystem.getCurrentState()) {
            default:
                m_LedSubsystem.clear();
                break;
            case STORAGE:
                // m_LedSubsystem.clear();
                if(m_IntakeSubsystem.isCoralInLeftSlot()) {
                    m_LedSubsystem.coralLeftDisplay();
                }
                else if (m_IntakeSubsystem.isCoralInRightSlot()) {
                    m_LedSubsystem.coralRightDisplay();
                }
                else if (m_IntakeSubsystem.isCoralIn()) {
                    m_LedSubsystem.coralInDisplay();
                }
                else {
                    m_LedSubsystem.neutralDisplay();
                }
                m_LedSubsystem.setAnimSpeed(0);
                m_LedSubsystem.singleFadeAnimation();
                break;
            case INTAKE:
                m_LedSubsystem.setAnimSpeed(.9);
                m_LedSubsystem.singleFadeAnimation();
                break; 
            case SCORING:
                m_LedSubsystem.setAnimSpeed(.5);
                m_LedSubsystem.larsonAnimation();
                break;
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
