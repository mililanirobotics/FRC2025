package frc.robot.Subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class CameraSubsystem extends SubsystemBase {
    private NetworkTable networkTable;

    public enum Pipeline {
        //Set ID numbers to pipelines configured on limelight
        DRIVER_VIEW(0), APRILTAG(1);

        private int PipelineID;

        //Constructor for the enum
        private Pipeline(int PipelineID){
            this.PipelineID = PipelineID;
        }  
    }
    
    public CameraSubsystem() {
        networkTable = NetworkTableInstance.getDefault().getTable("limelight");
        setPipeline(Pipeline.DRIVER_VIEW);
    }

    //Method to change the pipeline on the limelight
    public void setPipeline(Pipeline pipeline) {
        networkTable.getEntry("pipeline").setValue(pipeline.PipelineID);
    }

    //Returns the horizontal rotational offset from the center of camera in degrees
    public double horizontalOffset() {
        return networkTable.getEntry("tx").getDouble(0.0);
    }

    //Returns the vertical rotational offset from the center of camera in degrees
    public double verticalOffset() {
        return networkTable.getEntry("ty").getDouble(0.0);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Horizontal Offset:", horizontalOffset());
        SmartDashboard.putNumber("Vertical Offset:", verticalOffset());
    }
}
