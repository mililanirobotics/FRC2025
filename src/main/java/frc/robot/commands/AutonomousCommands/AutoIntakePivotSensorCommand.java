package frc.robot.commands.AutonomousCommands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.GamepadConstants;
import frc.robot.Constants.IntakeConstants;
import frc.robot.Constants.pivotConstant.PivotPositions;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.PivotSubsystem;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Constants.pivotConstant;

public class AutoIntakePivotSensorCommand extends SequentialCommandGroup {
    private IntakeSubsystem m_intakeSubsystem;
    private PivotPositions pivotState;
    private GenericHID controller;
    private PivotSubsystem m_pivotSubsystem;
    private double initialTime;

    private double percentOutput;

    public AutoIntakePivotSensorCommand(IntakeSubsystem m_IntakeSubsystem, PivotPositions pivotState, GenericHID controller, PivotSubsystem m_pivotSubsystem){
        // addCommands(
        //     new 
        // );
    }
}
