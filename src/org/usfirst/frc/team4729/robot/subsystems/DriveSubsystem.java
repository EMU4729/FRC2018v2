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
    
    double circumferenceOfWheels = 0.1016;
    double pulsesPerRevolution = 2048;

    double leftSpeed = 0;
    double rightSpeed = 0;
    double turnSpeed = 0;
    double forwardSpeed = 0;

    double acceleration = 0.05;
    double speed = 1;
    
    PIDController leftFrontPIDMotor;
	PIDController rightFrontPIDMotor;
	PIDController leftBackPIDMotor;
	PIDController rightBackPIDMotor;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public DriveSubsystem() {
    	leftFrontDrive = new TalonSRX(RobotMap.MOTOR_LEFT_FRONT);
    	leftBackDrive = new TalonSRX(RobotMap.MOTOR_LEFT_BACK);
    	rightFrontDrive = new TalonSRX(RobotMap.MOTOR_RIGHT_FRONT);
    	rightBackDrive = new TalonSRX(RobotMap.MOTOR_RIGHT_BACK);
    	
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
    	rightEncoder.setReverseDirection(true);
    	
    	leftFrontPIDMotor = new PIDController(leftFrontDrive, leftEncoder);
    	rightFrontPIDMotor = new PIDController(rightFrontDrive, rightEncoder);
    	leftBackPIDMotor = new PIDController(leftBackDrive, leftEncoder);
    	rightBackPIDMotor = new PIDController(rightBackDrive, rightEncoder);
    	
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void arcade(double forwardSpeed, double turnSpeed) {  
    	SmartDashboard.putNumber("Speed", leftFrontPIDMotor.getPosition());
    	SmartDashboard.putNumber("Desired", leftFrontPIDMotor.getSetpoint());
    	
        power(forwardSpeed*speed - turnSpeed*speed,
        	   forwardSpeed*speed - turnSpeed*speed,
        	   forwardSpeed*speed + turnSpeed*speed,
        	   forwardSpeed*speed + turnSpeed*speed);
        
    }

    public void tank (double leftSpeed, double rightSpeed) {
        power(leftSpeed*speed, leftSpeed*speed, rightSpeed*speed, rightSpeed*speed);
    }
    
    public double getLeftEncoder() {
    	return leftEncoder.getDistance();
    }
    
    public double getRightEncoder() {
    	return rightEncoder.getDistance();
    }
    
    public void resetEncoders() {
    	leftEncoder.reset();
    	rightEncoder.reset();
    }
    
    public void power(double leftFront, double leftBack, double rightFront, double rightBack) {
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
}

