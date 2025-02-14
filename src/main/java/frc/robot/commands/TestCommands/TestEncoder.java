package frc.robot.commands.TestCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.PivotSubsystem;

public class TestEncoder extends Command{
    private PivotSubsystem m_PivotSubsystem;
    public TestEncoder(PivotSubsystem pivotSubsystem){
        pivotSubsystem = m_PivotSubsystem;
    }
    @Override
    public void initialize () {
        m_PivotSubsystem.setPoint(0);
    }

    @Override
    public void execute() {
        // m_PivotSubsystem.setPivotPower(m_PivotSubsystem.getOutput());
    }

    @Override
    public void end(boolean interrupted) {
        m_PivotSubsystem.shutdown();
    }

    @Override
    public boolean isFinished() {
        // return (m_PivotSubsystem.getPIDError() == 0);
        return true;
    }
}
