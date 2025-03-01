package frc.robot.commands.ManualCommands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.GamepadConstants;
import frc.robot.Constants.pivotConstant;
import frc.robot.subsystems.PivotSubsystem;


public class PivotControlCommand extends Command{
    private PivotSubsystem m_PivotSubsystem;
    private GenericHID m_controller;
    private double percentOutput;

    public PivotControlCommand(PivotSubsystem pivotSubsystem, GenericHID controller){
        m_PivotSubsystem = pivotSubsystem;
        m_controller = controller;
        addRequirements(m_PivotSubsystem);
    }

    @Override
    public void initialize(){
        percentOutput = .6;
    }

    @Override
    public void execute() {
        if (m_controller.getRawButton(GamepadConstants.kBButtonPort) && !m_controller.getRawButton(GamepadConstants.kRightBumperPort)) {
            m_PivotSubsystem.setPivotPower(m_controller.getRawAxis(GamepadConstants.kRightTriggerPort) >= .5 ? .3 : .6);
        }
        else if (m_controller.getRawButton(GamepadConstants.kBButtonPort) && m_controller.getRawButton(GamepadConstants.kRightBumperPort)) {
            m_PivotSubsystem.setPivotPower(m_controller.getRawAxis(GamepadConstants.kRightTriggerPort) >= .5 ? -.3 : -.6);
        }
        else {
            m_PivotSubsystem.setPivotPower(0);
        }
    }

    @Override
    public void end(boolean interupted){
        m_PivotSubsystem.shutdown();
    }

    @Override
    public boolean isFinished(){
        return false;
    }
}
