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
//    TalonSRX leftFrontDrive;
//    TalonSRX leftBackDrive;
//    TalonSRX rightFrontDrive;
//    TalonSRX rightBackDrive;
	
	Talon leftFrontDrive;
	Talon leftBackDrive;
	Talon rightFrontDrive;
	Talon rightBackDrive;
    
    Encoder leftEncoder;
    Encoder rightEncoder;
    
    double circumferenceOfWheels = 0.1016;
    double pulsesPerRevolution = 2048;

    double leftSpeed = 0;
    double rightSpeed = 0;
    double turnSpeed = 0;
    double forwardSpeed = 0;
    
    double DRIVETHRESHOLD = 0.05;

    double acceleration = 0.05;
    
    PIDMotorController leftFrontPIDMotor;
	PIDMotorController rightFrontPIDMotor;
	PIDMotorController leftBackPIDMotor;
	PIDMotorController rightBackPIDMotor;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public DriveSubsystem() {
//    	leftFrontDrive = new TalonSRX(RobotMap.MOTOR_LEFT_FRONT);
//    	leftBackDrive = new TalonSRX(RobotMap.MOTOR_LEFT_BACK);
//    	rightFrontDrive = new TalonSRX(RobotMap.MOTOR_RIGHT_FRONT);
//    	rightBackDrive = new TalonSRX(RobotMap.MOTOR_RIGHT_BACK);
    	
    	leftFrontDrive = new Talon(RobotMap.MOTOR_LEFT_FRONT);
    	leftBackDrive = new Talon(RobotMap.MOTOR_LEFT_BACK);
    	rightFrontDrive = new Talon(RobotMap.MOTOR_RIGHT_FRONT);
    	rightBackDrive = new Talon(RobotMap.MOTOR_RIGHT_BACK);
    	
//    	leftEncoder = new Encoder(RobotMap.ENCODER_LEFT_A, RobotMap.ENCODER_LEFT_B, En);
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
    	
    	leftFrontPIDMotor = new PIDMotorController(leftFrontDrive, leftEncoder, Robot.gyroSubsystem.getGyro(), false);
    	rightFrontPIDMotor = new PIDMotorController(rightFrontDrive, rightEncoder, Robot.gyroSubsystem.getGyro(), true);
    	leftBackPIDMotor = new PIDMotorController(leftBackDrive, leftEncoder, Robot.gyroSubsystem.getGyro(), false);
    	rightBackPIDMotor = new PIDMotorController(rightBackDrive, rightEncoder, Robot.gyroSubsystem.getGyro(), true);
    	
    	leftFrontPIDMotor.enable();
    	leftBackPIDMotor.enable();
    	rightFrontPIDMotor.enable();
    	rightBackPIDMotor.enable();
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    public void arcade(double forwardSpeed, double turnSpeed) { 
    	if (Math.abs(forwardSpeed) < DRIVETHRESHOLD && Math.abs(turnSpeed) < DRIVETHRESHOLD) {
    		reset();
    	}
    	
    	
    	SmartDashboard.putNumber("LeftPidMotorSetpoint", leftFrontPIDMotor.motor.getSetpoint());
    	SmartDashboard.putNumber("ForwardSpeed", forwardSpeed);
    	SmartDashboard.putNumber("TurnSpeed", turnSpeed);
    	
    	
    	
    	
        power(forwardSpeed - turnSpeed,
        	   forwardSpeed - turnSpeed,
        	   forwardSpeed + turnSpeed,
        	   forwardSpeed + turnSpeed);
        
    }

    public void tank (double leftSpeed, double rightSpeed) {
    	if (Math.abs(forwardSpeed) < DRIVETHRESHOLD && Math.abs(turnSpeed) < DRIVETHRESHOLD) {
    		reset();
    	}
        power(leftSpeed, leftSpeed, rightSpeed, rightSpeed);
    }
    
    public double getLeftEncoder() {
    	return leftFrontPIDMotor.getDistance();
    }
    
    public double getRightEncoder() {
    	return rightFrontPIDMotor.getDistance();
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
    	stopMotors();
    	startMotors();
    }
    
    public void resetEncoders() {
    	leftEncoder.reset();
    	rightEncoder.reset();
    }
    
    int counter = 0;
    
    public void power(double leftFront, double leftBack, double rightFront, double rightBack) {
    	SmartDashboard.putNumber("counter", counter);
    	counter++;
    	SmartDashboard.putNumber("leftFront", leftFront);
    	leftFrontPIDMotor.setSetpoint(-leftFront);
    	rightFrontPIDMotor.setSetpoint(rightFront);
    	leftBackPIDMotor.setSetpoint(-leftBack);
    	rightBackPIDMotor.setSetpoint(rightBack);
    }
    
    public Encoder[] getEncoders() {
		return new Encoder[] {leftEncoder, rightEncoder};
    }
    
    public void enable() {
    	leftFrontPIDMotor.enable();
    	rightFrontPIDMotor.enable();
    	leftBackPIDMotor.enable();
    	rightBackPIDMotor.enable();
    }
    
    public void disable() {
    	leftFrontPIDMotor.disable();
    	rightFrontPIDMotor.disable();
    	leftBackPIDMotor.disable();
    	rightBackPIDMotor.disable();
    }
    
    public void setMotorsToSpeed() {
    	leftFrontPIDMotor.useSpeed();
    	rightFrontPIDMotor.useSpeed();
    	leftBackPIDMotor.useSpeed();
    	rightBackPIDMotor.useSpeed();
    }
    
    public void setMotorsToDistance() {
    	leftFrontPIDMotor.useDistance();
    	rightFrontPIDMotor.useDistance();
    	leftBackPIDMotor.useDistance();
    	rightBackPIDMotor.useDistance();
    }
    
    public void setMotorsToAngle() {
    	leftFrontPIDMotor.useAngle();
    	rightFrontPIDMotor.useAngle();
    	leftBackPIDMotor.useAngle();
    	rightBackPIDMotor.useAngle();
    }
    
    public void setMotorAngle(double angle) {
    	setMotorsToAngle();
    	leftFrontPIDMotor.setSetpoint(angle);
    	rightFrontPIDMotor.setSetpoint(angle);
    	leftBackPIDMotor.setSetpoint(angle);
    	rightBackPIDMotor.setSetpoint(angle);
    }
    
    public void setMotorDistance(double distance) {
    	setMotorsToDistance();
    	leftFrontPIDMotor.setSetpoint(distance);
    	rightFrontPIDMotor.setSetpoint(-distance);
    	leftBackPIDMotor.setSetpoint(distance);
    	rightBackPIDMotor.setSetpoint(-distance);
    }
    
    public void setMotorSpeed(double speed) {
    	setMotorsToSpeed();
    	leftFrontPIDMotor.setSetpoint(speed);
    	rightFrontPIDMotor.setSetpoint(speed);
    	leftBackPIDMotor.setSetpoint(speed);
    	rightBackPIDMotor.setSetpoint(speed);
    }
    
    public boolean isAllMotorsOnTarget() {
    	return leftFrontPIDMotor.onTarget() && rightFrontPIDMotor.onTarget() && leftBackPIDMotor.onTarget() && rightBackPIDMotor.onTarget();
    }
    
    public void startMotors() {
    	leftFrontPIDMotor.start();
    	rightFrontPIDMotor.start();
    	leftBackPIDMotor.start();
    	rightBackPIDMotor.start();
    }
    
    public void stopMotors() {
    	leftFrontPIDMotor.stop();
    	rightFrontPIDMotor.stop();
    	leftBackPIDMotor.stop();
    	rightBackPIDMotor.stop();
    }
}

