package frc.robot.commands.TestCommands;
// package frc.robot.commands.TestCommands;

// import edu.wpi.first.wpilibj.GenericHID;
// import edu.wpi.first.wpilibj2.command.Command;
// import frc.robot.Constants.GamepadConstants;
// import frc.robot.subsystems.ElevatorSubsystem;
// import frc.robot.subsystems.IntakeSubsystem;
// import frc.robot.subsystems.PivotSubsystem;

// public class TestIncreaseSpeedCommand extends Command {
//     private ElevatorSubsystem m_ElevatorSubsystem;
//     private IntakeSubsystem m_IntakeSubsystem;
//     private PivotSubsystem m_PivotSubsystem;
//     private GenericHID m_controller;

//     public TestIncreaseSpeedCommand(ElevatorSubsystem elevatorSubsystem, IntakeSubsystem intakeSubsystem, PivotSubsystem pivotSubsystem, GenericHID controller){
//         m_ElevatorSubsystem = elevatorSubsystem;
//         m_IntakeSubsystem = intakeSubsystem;
//         m_PivotSubsystem = pivotSubsystem;
//         m_controller = controller;
//     }
//     @Override
//     public void initialize(){
//         m_ElevatorSubsystem.increaseSpeedCommand();
//     }
//     @Override
//     public void end(boolean interupted){

//     }
//     @Override
//     public boolean isFinished(){
//         return m_controller.getRawButton(GamepadConstants.kRightBumperPort);
//     }
// }
