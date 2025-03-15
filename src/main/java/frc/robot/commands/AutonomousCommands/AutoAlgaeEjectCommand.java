package frc.robot.commands.AutonomousCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class AutoAlgaeEjectCommand extends Command {
    private IntakeSubsystem m_IntakeSubsystem; 

    public AutoAlgaeEjectCommand (IntakeSubsystem intakeSubsystem) {
        m_IntakeSubsystem = intakeSubsystem;
        
        addRequirements(m_IntakeSubsystem);
    }

    @Override
    public void execute() {
        m_IntakeSubsystem.setRollerPower(-0.6, 0);
    }

    @Override
    public boolean isFinished() {
        return true;
    }


    
}
