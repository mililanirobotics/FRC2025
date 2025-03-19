package frc.robot.commands.AutonomousCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class AutoCoralCorrectionCommand extends Command {
    private IntakeSubsystem m_intakeSubsystem;

    public AutoCoralCorrectionCommand(IntakeSubsystem intakeSubsystem){
        m_intakeSubsystem = intakeSubsystem;
        
        addRequirements(m_intakeSubsystem);
    }
    @Override
    public void initialize(){
    }
    @Override
    public void execute(){
        m_intakeSubsystem.setRollerPower(0.6, 1);
    }
    @Override
    public void end(boolean interupted){
        m_intakeSubsystem.shutdown();
    }
    @Override
    public boolean isFinished(){
        return m_intakeSubsystem.isCoralIn();
    }
}
