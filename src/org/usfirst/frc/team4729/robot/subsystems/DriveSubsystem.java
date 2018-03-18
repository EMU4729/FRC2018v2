package org.usfirst.frc.team4729.robot.subsystems;

import org.usfirst.frc.team4729.robot.Robot;
import org.usfirst.frc.team4729.robot.RobotMap;
import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
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
	
//	Talon leftFrontDrive;
//	Talon leftBackDrive;
//	Talon rightFrontDrive;
//	Talon rightBackDrive;
    
    Encoder leftEncoder;
    Encoder rightEncoder;
    
    double circumferenceOfWheels = 0.1016;
    double pulsesPerRevolution = 2048;
    
    double DRIVE_THRESHOLD = 0.05;
    double POWER_MAX = 1;
    double speedMultiplier = 0.75;
    double POWER_MIN = 0.15;
    double TURN_FACTOR = 0.85;

    double acceleration = 0.05;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public DriveSubsystem() {
    	leftFrontDrive = new TalonSRX(RobotMap.MOTOR_LEFT_FRONT);
    	leftBackDrive = new TalonSRX(RobotMap.MOTOR_LEFT_BACK);
    	rightFrontDrive = new TalonSRX(RobotMap.MOTOR_RIGHT_FRONT);
    	rightBackDrive = new TalonSRX(RobotMap.MOTOR_RIGHT_BACK);
    	
//    	leftFrontDrive = new Talon(RobotMap.MOTOR_LEFT_FRONT);
//    	leftBackDrive = new Talon(RobotMap.MOTOR_LEFT_BACK);
//    	rightFrontDrive = new Talon(RobotMap.MOTOR_RIGHT_FRONT);
//    	rightBackDrive = new Talon(RobotMap.MOTOR_RIGHT_BACK);
    	
//    	leftEncoder = new Encoder(RobotMap.ENCODER_LEFT_A, RobotMap.ENCODER_LEFT_B, En);
    	createEncoders();
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    public void arcade(double forwardSpeed, double turnSpeed) {
    	if (Math.abs(forwardSpeed) < DRIVE_THRESHOLD) {
    		forwardSpeed = 0;
    	}
    	if (Math.abs(turnSpeed) < DRIVE_THRESHOLD) {
    		turnSpeed = 0;
    	}
    	turnSpeed *= TURN_FACTOR;
    	power(forwardSpeed - turnSpeed,
    	      forwardSpeed - turnSpeed,
    	      forwardSpeed + turnSpeed,
    	      forwardSpeed + turnSpeed);
    	
//    	SmartDashboard.putNumber("LeftPidMotorSetpoint", leftFrontPIDMotor.motor.getSetpoint());
//    	SmartDashboard.putNumber("RightPidMotorSetpoint", rightFrontPIDMotor.motor.getSetpoint());
//    	SmartDashboard.putNumber("ForwardSpeed", forwardSpeed);
//    	SmartDashboard.putNumber("TurnSpeed", turnSpeed);
//    	SmartDashboard.putNumber("Left output", leftFrontPIDMotor.motor.output);
//    	SmartDashboard.putNumber("Right output", rightFrontPIDMotor.motor.output);
//    	SmartDashboard.putNumber("Left Encoder", getLeftEncoder());
//    	SmartDashboard.putNumber("Right Encoder", getRightEncoder());
        
    }

    public void tank (double leftSpeed, double rightSpeed) {
    	if (Math.abs(leftSpeed) < DRIVE_THRESHOLD) {
    		leftSpeed = 0;
    	}
    	if (Math.abs(rightSpeed) < DRIVE_THRESHOLD) {
    		rightSpeed = 0;
    	}
    	power(leftSpeed, leftSpeed, rightSpeed, rightSpeed);
    }
    
    public double getLeftEncoder() {
    	return leftEncoder.getDistance();
    }
    
    public double getRightEncoder() {
    	return rightEncoder.getDistance();
    }
    
    public double getLeftEncoderRate() {
    	return leftEncoder.getRate();
    }
    
    public double getRightEncoderRate() {
    	return rightEncoder.getRate();
    }
    
    public void reset() {
    	leftEncoder.reset();
    	rightEncoder.reset();
//    	resetEncoders();
    }
    
    public void resetEncoders() {
    	leftEncoder.reset();
    	rightEncoder.reset();
    }
    
    public void createEncoders() {
    	leftEncoder = new Encoder(RobotMap.ENCODER_LEFT_A, RobotMap.ENCODER_LEFT_B, false, Encoder.EncodingType.k4X);
    	leftEncoder.setMaxPeriod(0.1);
    	leftEncoder.setMinRate(10);
    	leftEncoder.setDistancePerPulse(Math.PI * circumferenceOfWheels/pulsesPerRevolution);
    	leftEncoder.setSamplesToAverage(7);
    	leftEncoder.setReverseDirection(true);
    	
    	rightEncoder = new Encoder(RobotMap.ENCODER_RIGHT_A, RobotMap.ENCODER_RIGHT_B, false, Encoder.EncodingType.k4X);
    	rightEncoder.setMaxPeriod(0.1);
    	rightEncoder.setMinRate(10);
    	rightEncoder.setDistancePerPulse(Math.PI * circumferenceOfWheels/pulsesPerRevolution);
    	rightEncoder.setSamplesToAverage(7);
    	rightEncoder.setReverseDirection(false);
    }
    
    public void freeEncoders() {
    	if (leftEncoder != null) {
    		leftEncoder.free();
    	}
    	
    	if (rightEncoder != null) {
    		rightEncoder.free();
    	}
    	
    	createEncoders();
    }
    
    int counter = 0;
    
    public void power(double leftFront, double leftBack, double rightFront, double rightBack) {
    	leftFront *= speedMultiplier;
    	leftBack *= speedMultiplier;
    	rightFront *= speedMultiplier;
    	rightBack *= speedMultiplier;
//    	SmartDashboard.putNumber("counter", counter);
//    	counter++;
//    	SmartDashboard.putNumber("leftFront", leftFront);
		leftFrontDrive.set(ControlMode.PercentOutput, leftFront == 0 ? 0 : -leftFront * (POWER_MAX - POWER_MIN) - (Math.abs(leftFront) / leftFront) * POWER_MIN);
		rightFrontDrive.set(ControlMode.PercentOutput, rightFront == 0 ? 0 : rightFront * (POWER_MAX - POWER_MIN) + (Math.abs(rightFront) / rightFront) * POWER_MIN);
		leftBackDrive.set(ControlMode.PercentOutput, leftBack == 0 ? 0 : -leftBack * (POWER_MAX - POWER_MIN) - (Math.abs(leftBack) / leftBack) * POWER_MIN);
		rightBackDrive.set(ControlMode.PercentOutput, rightBack == 0 ? 0 : rightBack * (POWER_MAX - POWER_MIN) + (Math.abs(rightBack) / rightBack) * POWER_MIN);
    }
    
    public Encoder[] getEncoders() {
		return new Encoder[] {leftEncoder, rightEncoder};
    }
    
    public void highSpeed() {
    	speedMultiplier = 0.75;
    }
    
    public void lowSpeed() {
    	speedMultiplier = 0.5;
    }
}

