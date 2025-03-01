package frc.robot.commands.ManualCommands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.GamepadConstants;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorControlCommand extends Command{

    private ElevatorSubsystem m_elevatorSubsystem;
    private GenericHID controller;
    private double percentOutput;

    public ElevatorControlCommand(ElevatorSubsystem elevatorSubsystem, GenericHID controller){
        m_elevatorSubsystem = elevatorSubsystem;
        this.controller = controller;

        addRequirements(m_elevatorSubsystem);
    }

    @Override
    public void initialize(){
        percentOutput = .75;
    }

    @Override
    public void execute() {
        if (controller.getRawButton(GamepadConstants.kYButtonPort)&& !controller.getRawButton(GamepadConstants.kRightBumperPort)) {
            m_elevatorSubsystem.setPower(controller.getRawAxis(GamepadConstants.kRightTriggerPort) >= .5 ? .3 : percentOutput);
        }
        else if (controller.getRawButton(GamepadConstants.kYButtonPort) && controller.getRawButton(GamepadConstants.kRightBumperPort)) {
            m_elevatorSubsystem.setPower(controller.getRawAxis(GamepadConstants.kRightTriggerPort) >= .5 ? -.3 : -percentOutput);
        }
        else {
            m_elevatorSubsystem.setPower(0);
        }
    }

    @Override
    public void end(boolean interupted){
        m_elevatorSubsystem.shutdown();
    }
    @Override
    public boolean isFinished(){
        return false;
    }
}
