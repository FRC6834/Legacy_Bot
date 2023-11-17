package frc.robot;

//Use link below to install vendor library for TalonSRX motor controllers
//https://maven.ctr-electronics.com/release/com/ctre/phoenixpro/PhoenixProAnd5-frc2023-latest.json

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

//Imports
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

//The goal of this class is to create methods that can be used to operate the robot's drivetrain.
//This will clean up the code in Robot.java and will allow for easier future fixes.
public class RobotDrivetrain {
  
  //Creates SPARK MAX objects for each speed controller in the drivetrain.
  //The first parameter refers to the CAN ID - Use Rev Tool to determine CAN IDs
  //MotorType.kBrushless MUST be used when using NEO brushless motors
  //Use REV Tool to ensure motor controllers are flashed for brushless
  private WPI_TalonSRX leftFront = new WPI_TalonSRX(Constants.frontLeftID);
  private WPI_TalonSRX leftRear = new WPI_TalonSRX(Constants.rearLeftID);
  private WPI_TalonSRX rightFront = new WPI_TalonSRX(Constants.frontRightID);
  private WPI_TalonSRX rightRear = new WPI_TalonSRX(Constants.rearRightID);
  
  //Groups left side speed controllers together and right side speed controllers together
  //This is cleaner than setting up two different differential drives
  private MotorControllerGroup driveLeft = new MotorControllerGroup(leftFront, leftRear);
  private MotorControllerGroup  driveRight = new MotorControllerGroup(rightFront, rightRear);
  
  //The DifferentialDrive class gives us access to the arcade drive, tank drive, and curvature drive methods.
  private DifferentialDrive robotDrive = new DifferentialDrive(driveLeft, driveRight);

  //Constructor is called in Robot.java to create RobotDrivetrain objects.
  //Nothing needs to happen in the constructor for our purposes
  public RobotDrivetrain(){
    rightFront.setInverted(true);
    rightRear.setInverted(true);
  }
  
  //used in Dpad controls and autonomous mode
  //leftSpeed (-1.0 to 1.0) - comes from left stick
  //rightSpeed (-1.0 to 1.0) - comes from right stick
  public void tankDrive(double leftSpeed, double rightSpeed){
    robotDrive.tankDrive(leftSpeed, rightSpeed);
  }

  //Method for curvature drive - used for main drivetrain controls on robot
  //xSpeed (0 to 1.0) to go forward and (-1.0 to 0) to move backward
  //zRotation (-1.0 to 1.0) controls direction
  public void curvatureDrive(double xSpeed, double zRotation){
    robotDrive.curvatureDrive(xSpeed, zRotation, false);
  }

  /*
  * D-Pad setup - these are used for small movements
  * 0 = up arrow (forward)
  * 90 = right arrow (right)
  * 180 = down arrow (backward)
  * 270 = left arrow (left)
  * speeds can be adjusted
  * Setting equal to 0 for left/right ensures it stays in place
  */
  public void dPadGetter(int dPad){
    if (dPad==0){
      robotDrive.tankDrive(0.4, 0.4); //forward
    }
    if (dPad==90){
      robotDrive.tankDrive(0.4, -0.4); //right
    }
    if (dPad==180){
      robotDrive.tankDrive(-0.4, -0.4); //reverse
    }
    if (dPad==270){
      robotDrive.tankDrive(-0.4, 0.4); //left
    }
  } 
}