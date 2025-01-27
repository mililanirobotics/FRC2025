package frc.robot.commands.ManualCommands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.GamepadConstants;
import frc.robot.subsystems.PivotSubsystem;

public class ManualPivotUpCommand extends Command {
    private PivotSubsystem m_PivotSubsystem;
    private GenericHID controller;
    private double pivotSpeed;

    public ManualPivotUpCommand (PivotSubsystem pivotSubsystem, GenericHID controller) {
        m_PivotSubsystem = pivotSubsystem;
        this.controller = controller;
        pivotSpeed = 0;

        addRequirements(m_PivotSubsystem);
    }
    @Override
    public void initialize() {
    }
    @Override
    public void execute() {
        // if (controller.getRawButtonPressed(GamepadConstants.kXButtonPort)) {
        //     pivotSpeed += 0.1;
        // }
        // else if (controller.getRawButtonPressed(GamepadConstants.kYButtonPort)) {
        //     pivotSpeed -= 0.1;
        // }
        // else if (controller.getRawButtonPressed(GamepadConstants.kLeftTriggerPort)) {
        //     pivotSpeed = 0;
        // }

        // m_PivotSubsystem.setPivotPower(pivotSpeed);

        // SmartDashboard.putNumber("Pivot Speed: ", pivotSpeed);
        // SmartDashboard.updateValues();
        m_PivotSubsystem.setPivotPower(pivotSpeed);
    }
    @Override
    public void end(boolean interrupted) {
        m_PivotSubsystem.shutdown();
    }
    @Override
    public boolean isFinished() {
        // return controller.getRawButton(GamepadConstants.kDpadDown);
        return !controller.getRawButton(GamepadConstants.kDpadUp);
    }
}