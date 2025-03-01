package frc.robot.commands.ManualCommands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.GamepadConstants;
import frc.robot.subsystems.HangSubsystem;

public class HangControlCommand extends Command {
    private HangSubsystem m_HangSubsystem;
    private GenericHID gamepad;

    public HangControlCommand(HangSubsystem m_HangSubsystem, GenericHID gamepad) {
        this.m_HangSubsystem = m_HangSubsystem;
        this.gamepad = gamepad;

        addRequirements(m_HangSubsystem);
    }

    @Override
    public void execute() {
        if (gamepad.getRawButton(GamepadConstants.kBackButtonPort)) {
            m_HangSubsystem.setPower(-1);
        }
        else if (gamepad.getRawButton(GamepadConstants.kStartButtonPort)) {
            m_HangSubsystem.setPower(1);
        }
        else {
            m_HangSubsystem.setPower(0);
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
