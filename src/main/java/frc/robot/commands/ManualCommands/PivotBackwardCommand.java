package frc.robot.commands.ManualCommands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.GamepadConstants;
import frc.robot.Constants.pivotConstant;
import frc.robot.subsystems.PivotSubsystem;


public class PivotBackwardCommand extends Command{
    private PivotSubsystem m_PivotSubsystem;
    private GenericHID m_controller;
    private double pivotSpeed;
    public PivotBackwardCommand(PivotSubsystem pivotSubsystem, GenericHID controller){
        m_PivotSubsystem = pivotSubsystem;
        m_controller = controller;
        addRequirements(m_PivotSubsystem);
    }
    @Override
    public void initialize(){
        m_PivotSubsystem.setPivotPower(-0.001); 
    }
    @Override
    public void end(boolean interupted){
        m_PivotSubsystem.shutdown();
    }
    @Override
    public boolean isFinished(){
        return !m_controller.getRawButton(GamepadConstants.kBButtonPort) || !m_controller.getRawButton(GamepadConstants.kRightBumperPort);
    }
}
