package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.GamepadConstants;
import frc.robot.subsystems.PivotSubsystem;

public class ManualPivotDownCommand extends Command {
    private PivotSubsystem m_PivotSubsystem;
    private GenericHID controller;
    
    public ManualPivotDownCommand (PivotSubsystem pivotSubsystem, GenericHID controller) {
        m_PivotSubsystem = pivotSubsystem;
        this.controller = controller;
    }
    @Override
    public void initialize() {
    }
    @Override
    public void execute() {
        m_PivotSubsystem.setPivotPower(-0.1);
    }
    @Override
    public void end(boolean interrupted) {
        m_PivotSubsystem.shutdown();
    }
    @Override
    public boolean isFinished() {
        return !controller.getRawButton(GamepadConstants.kRightBumperPort);
    }
}