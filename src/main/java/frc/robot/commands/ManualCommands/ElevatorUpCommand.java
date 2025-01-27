package frc.robot.commands.ManualCommands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.GamepadConstants;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorUpCommand extends Command{

    private ElevatorSubsystem m_elevatorSubsystem;
    private GenericHID controller;
    private double elevatorSpeed;

    public ElevatorUpCommand(ElevatorSubsystem elevatorSubsystem, GenericHID controller){
        m_elevatorSubsystem = elevatorSubsystem;
        this.controller = controller;

        elevatorSpeed = 0;
    }

    @Override
    public void initialize(){
    }
    @Override
    public void execute(){
        // if (controller.getRawButtonPressed(GamepadConstants.kAButtonPort)) {
        //     elevatorSpeed += 0.1;
        // }
        // else if (controller.getRawButtonPressed(GamepadConstants.kBButtonPort)) {
        //     elevatorSpeed -= 0.1;
        // }
        // else if (controller.getRawButtonPressed(GamepadConstants.kRightTriggerPort)) {
        //     elevatorSpeed = 0;
        // }

        // m_elevatorSubsystem.setPower(elevatorSpeed);

        // SmartDashboard.putNumber("ElevatorSpeed: ", elevatorSpeed);
        // SmartDashboard.updateValues();
        m_elevatorSubsystem.setPower(0.5);

    }
    @Override
    public void end(boolean interupted){
        m_elevatorSubsystem.shutdown();
    }
    @Override
    public boolean isFinished(){
        // return controller.getRawButton(GamepadConstants.kDpadUp);
        return !controller.getRawButton(GamepadConstants.kRightBumperPort);
    }
}
