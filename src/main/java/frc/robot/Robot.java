//General Imports
package frc.robot;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.math.MathUtil;

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public class Robot extends TimedRobot {
  private RobotDrivetrain drivetrain = new RobotDrivetrain();
  private XboxController controller0 = new XboxController(0);
  
  //This function is run when the robot is first started up and should be used for any initialization code.
  @Override
  public void robotInit() {}

  //This function is called every 20 ms, no matter the mode.
  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic(){}

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {

    //Curvature Drive  
    double forwardSpeed = controller0.getRightTriggerAxis();
    double reverseSpeed = controller0.getLeftTriggerAxis();
    double turn = controller0.getLeftX();
    
    if (forwardSpeed > 0){
      drivetrain.curvatureDrive(speedCalc(forwardSpeed, true), turnCalc(turn, true));
    }
    else if (reverseSpeed > 0){
      drivetrain.curvatureDrive(speedCalc(reverseSpeed, false), turnCalc(turn, false));
    }
    else{
      drivetrain.curvatureDrive(0,0);
    }

    //D-Pad controls for fine movements
    int dPad = controller0.getPOV(); //scans to see which directional arrow is being pushed
    drivetrain.dPadGetter(dPad);
  }

  //Applies deadband to drive speeds
  public double speedCalc(double speed, boolean forward){
    if(forward){
      return MathUtil.applyDeadband(Constants.fMaxSpeed*speed,Constants.driveDeadband, 0.6);
    }
    return MathUtil.applyDeadband(Constants.rMaxSpeed*speed,Constants.driveDeadband, 0.6);
  }
  
  //Applies deadband to turn speeds
  public double turnCalc (double turn, boolean forward){
    if(forward){
      return MathUtil.applyDeadband(Constants.fMaxTurn*turn, Constants.driveDeadband, 0.6);
    }
    return MathUtil.applyDeadband(Constants.rMaxTurn*turn, Constants.driveDeadband, 0.6);
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}  
}