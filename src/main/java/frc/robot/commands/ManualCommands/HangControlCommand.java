package frc.robot.commands.ManualCommands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
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
        m_HangSubsystem.setPower(gamepad.getRawAxis(1));
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
