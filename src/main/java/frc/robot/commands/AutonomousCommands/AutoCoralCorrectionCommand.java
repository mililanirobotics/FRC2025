package frc.robot.commands.AutonomousCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class AutoCoralCorrectionCommand extends Command {
    private IntakeSubsystem m_intakeSubsystem;
    private double startingIntakePosition;
    private boolean isFinished;
    // private Timer time = new Timer();

    public AutoCoralCorrectionCommand(IntakeSubsystem intakeSubsystem){
        m_intakeSubsystem = intakeSubsystem;
        
        addRequirements(m_intakeSubsystem);
    }
    @Override
    public void initialize(){
        startingIntakePosition = m_intakeSubsystem.getTopRollerPosition();
        isFinished = false;
        
    }

    @Override
    public void execute(){
        if (m_intakeSubsystem.getTopRollerPosition() - startingIntakePosition < 0.25) {
            m_intakeSubsystem.setRollerPower(0.6, 1);
        }


    }
    @Override
    public void end(boolean interupted){
        m_intakeSubsystem.shutdown();
    }
    @Override
    public boolean isFinished(){
        return isFinished;
    }
}
