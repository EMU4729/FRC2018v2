package org.usfirst.frc.team4729.robot.subsystems;

import org.usfirst.frc.team4729.robot.Robot;
import org.usfirst.frc.team4729.robot.RobotMap;
import edu.wpi.first.wpilibj.PWMSpeedController;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/**
 *
 */
public class DriveSubsystem extends Subsystem {
    TalonSRX leftFrontDrive;
    TalonSRX leftBackDrive;
    TalonSRX rightFrontDrive;
    TalonSRX rightBackDrive;
    
    Encoder leftEncoder;
    Encoder rightEncoder;

    double leftSpeed = 0;
    double rightSpeed = 0;
    double turnSpeed = 0;
    double forwardSpeed = 0;

    double acceleration = 0.05;
    double speed = 1;
    
    MotorPID lf;
	MotorPID rf;
	MotorPID lb;
	MotorPID rb;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public DriveSubsystem() {
    	leftFrontDrive = new TalonSRX(RobotMap.MOTOR_LEFT_FRONT);
    	leftBackDrive = new TalonSRX(RobotMap.MOTOR_LEFT_BACK);
    	rightFrontDrive = new TalonSRX(RobotMap.MOTOR_RIGHT_FRONT);
    	rightBackDrive = new TalonSRX(RobotMap.MOTOR_RIGHT_BACK);
    	
    	leftEncoder = new Encoder(RobotMap.ENCODER_LEFT_A, RobotMap.ENCODER_LEFT_B, RobotMap.ENCODER_LEFT_INDEX);
    	leftEncoder.setMaxPeriod(0.1);
    	leftEncoder.setMinRate(10);
    	leftEncoder.setDistancePerPulse(5);
    	leftEncoder.setSamplesToAverage(7);
    	rightEncoder = new Encoder(RobotMap.ENCODER_RIGHT_A, RobotMap.ENCODER_RIGHT_B, RobotMap.ENCODER_RIGHT_INDEX);
    	rightEncoder.setMaxPeriod(0.1);
    	rightEncoder.setMinRate(10);
    	rightEncoder.setDistancePerPulse(5);
    	rightEncoder.setSamplesToAverage(7);
    	
    	lf = new MotorPID(leftFrontDrive, leftEncoder);
    	rf = new MotorPID(rightFrontDrive, rightEncoder);
    	lb = new MotorPID(leftBackDrive, leftEncoder);
    	rb = new MotorPID(rightBackDrive, rightEncoder);
    	
    	
//    	lf.enable();
//    	rf.enable();
//    	lb.enable();
//    	rb.enable();
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void arcade(double desiredMove, double desiredTurn) {
//    	lf.setSetpoint(100);
//    	rf.setSetpoint(100);
//    	lb.setSetpoint(100);
//    	rb.setSetpoint(100);
    	
    	SmartDashboard.putNumber("Speed", lf.getPosition());
    	
    	SmartDashboard.putNumber("Desired", lf.getSetpoint());
        if ((desiredMove < 0.1) && (desiredMove > -0.1)){
            desiredMove = 0;
            forwardSpeed = 0;
        }
        if ((desiredTurn < 0.1) && (desiredTurn > -0.1)){
            desiredTurn = 0;
            turnSpeed = 0;
        }

        if  (((desiredMove > 0) && (forwardSpeed < 0)) || ((desiredMove < 0) && (forwardSpeed > 0))){
            forwardSpeed = 0;
        }
        if (((desiredTurn > 0) && (turnSpeed < 0)) || ((desiredTurn < 0) && (turnSpeed > 0))){
            turnSpeed = 0;
        }

        if (Math.abs(desiredMove) < Math.abs(forwardSpeed)){
            forwardSpeed = desiredMove;
        }

        if (Math.abs(desiredTurn) < Math.abs(turnSpeed)) {
            turnSpeed = desiredTurn;
        }

        turnSpeed += (desiredTurn-turnSpeed)*acceleration;
        forwardSpeed += (desiredMove-forwardSpeed)*acceleration;

        power (forwardSpeed*speed - turnSpeed*speed,
        	   forwardSpeed*speed - turnSpeed*speed,
        	   forwardSpeed*speed + turnSpeed*speed,
        	   forwardSpeed*speed + turnSpeed*speed);
    }

    public void tank (double desiredLeft, double desiredRight) {
        if ((desiredLeft < 0.1) && (desiredLeft > -0.1)){
            desiredLeft = 0;
            leftSpeed = 0;
        }
        if ((desiredRight < 0.1) && (desiredRight > -0.1)){
            desiredRight = 0;
            rightSpeed = 0;
        }

        if  (((desiredLeft > 0) && (leftSpeed < 0)) || ((desiredLeft < 0) && (leftSpeed > 0))){
            leftSpeed = 0;
        }
        if (((desiredRight > 0) && (rightSpeed < 0)) || ((desiredRight < 0) && (rightSpeed > 0))){
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

        power (leftSpeed*speed, leftSpeed*speed, rightSpeed*speed, rightSpeed*speed);
    }
    
    public double getLeftEncoder() {
    	return leftEncoder.getDistance();
    }
    
    public double getRightEncoder() {
    	return rightEncoder.getDistance();
    }
    
    public void resetEncoders () {
    	leftEncoder.reset();
    	rightEncoder.reset();
    }
    
    public void power (double leftFront, double leftBack, double rightFront, double rightBack) {
    	leftFrontDrive.set(ControlMode.PercentOutput, -leftFront);
        leftBackDrive.set(ControlMode.PercentOutput, -leftBack);
        rightFrontDrive.set(ControlMode.PercentOutput, rightFront);
        rightBackDrive.set(ControlMode.PercentOutput, rightBack);
    }
}

