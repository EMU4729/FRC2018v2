package org.usfirst.frc.team4729.robot.subsystems;

import org.usfirst.frc.team4729.robot.Robot;
import org.usfirst.frc.team4729.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveSubsystem extends Subsystem {
	RobotDrive driveTrain;
	Encoder leftEncoder;
	Encoder rightEncoder;
	Talon leftMotor;
	Talon rightMotor;
	MotorPID leftPID;
	MotorPID rightPID;
	
	double leftSpeed = 0;
	double rightSpeed = 0;
	double turnSpeed = 0;
	double forwardSpeed = 0;
	
	double acceleration = 0.05;
	double speed = 1;
	
	public DriveSubsystem () {
		//driveTrain = new RobotDrive(0, 1);
		leftEncoder = new Encoder (RobotMap.leftEncoderA, RobotMap.leftEncoderB);
		leftEncoder.reset ();
		rightEncoder = new Encoder (RobotMap.rightEncoderA, RobotMap.rightEncoderB);
		rightEncoder.reset ();
		leftMotor = new Talon (RobotMap.leftMotor);
		rightMotor = new Talon (RobotMap.rightMotor);
		leftPID = new MotorPID (leftMotor, leftEncoder);
		leftPID.enable ();
		//rightPID = new MotorPID (rightMotor, rightEncoder);
		//rightPID.enable ();
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand () {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
    
    public void arcade (double desiredMove, double desiredTurn) {
    	if ((desiredMove < 0.1) && (desiredMove > -0.1)){
    		desiredMove = 0;
    		forwardSpeed = 0;
    	}
    	if ((desiredTurn < 0.1) && (desiredTurn > -0.1)){
    		desiredTurn = 0;
    		turnSpeed = 0;
    	}
    	
    	if (((desiredMove > 0) && (forwardSpeed < 0)) || ((desiredMove < 0) && (forwardSpeed > 0))){
    		forwardSpeed = 0;
    	}
    	if (((desiredTurn > 0) && (turnSpeed < 0)) || ((desiredTurn < 0) && (turnSpeed > 0))){
    		turnSpeed = 0;
    	}
    	
    	if (Math.abs(desiredMove) < Math.abs(turnSpeed)){
    		forwardSpeed = desiredMove;
    	}
    	
    	if (Math.abs(desiredTurn) < Math.abs(turnSpeed)) {
    		turnSpeed = desiredTurn;
    	}
    	
    	turnSpeed += (desiredTurn-turnSpeed)*acceleration;
    	forwardSpeed += (desiredMove-forwardSpeed)*acceleration;
    	//leftMotor.setSpeed (forwardSpeed);
    	leftPID.setSetpoint (forwardSpeed);
    	//rightPID.setSetpoint (forwardSpeed);
    	// driveTrain.arcadeDrive(-forwardSpeed*speed, -turnSpeed*speed);
    }
    
    public void tank (double desiredLeft, double desiredRight) {
    	if ((desiredLeft < 0.1) && (desiredLeft > -0.1)) {
    		desiredLeft = 0;
    		leftSpeed = 0;
    	}
    	
    	if ((desiredRight < 0.1) && (desiredRight > -0.1)) {
    		desiredRight = 0;
    		rightSpeed = 0;
    	}
    	
    	if (((desiredLeft > 0) && (leftSpeed < 0)) || ((desiredLeft < 0) && (leftSpeed > 0))) {
    		leftSpeed = 0;
    	}
    	
    	if (((desiredRight > 0) && (rightSpeed < 0)) || ((desiredRight < 0) && (rightSpeed > 0))) {
    		rightSpeed = 0;
    	}
    	
    	if (Math.abs(desiredLeft) < Math.abs(leftSpeed)){
    		leftSpeed = desiredLeft;
    	}
    	
    	if (Math.abs(desiredRight) < Math.abs(rightSpeed)) {
    		rightSpeed = desiredRight;
    	}
    	
    	rightSpeed += (desiredRight-rightSpeed)*acceleration;
    	leftSpeed += (desiredLeft-leftSpeed)*acceleration;
	}
    
    public void resetEncoders () {
    	leftEncoder.reset ();
    }
    
    public double getDistance () {
    	return leftEncoder.getDistance ();
    }
    
    public double getRate () {
    	return leftEncoder.getRate ();
    }
}

