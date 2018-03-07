package org.usfirst.frc.team4729.robot.subsystems;

import org.usfirst.frc.team4729.robot.enums.PIDDriveMode;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PIDMotorController extends PIDSubsystem {
	PIDMotor motor;
	ADXRS450_Gyro gyro;
	PIDDriveMode mode;
	boolean isRightMotor;

    // Initialize your subsystem here
//    public PIDMotorController(TalonSRX motor, Encoder encoder, ADXRS450_Gyro gyro, boolean isRightMotor) {
    public PIDMotorController(Talon motor, Encoder encoder, ADXRS450_Gyro gyro, boolean isRightMotor) {
    	super ("PIDDistance", 1.25, 0.002, 1.25);
    	setAbsoluteTolerance (0.01);
    	getPIDController().setContinuous(false);
    	
    	this.motor = new PIDMotor(motor, encoder, isRightMotor);
    	this.motor.enable();
    	this.gyro = gyro;
    	this.isRightMotor = isRightMotor;
    	
    	mode = PIDDriveMode.SPEED;
    	
//    	setInputRange(-1, 1);
//    	setOutputRange(-1, 1);
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    @Override
    protected double returnPIDInput() {
//    	System.out.println("Mode: " + mode + " dist " + motor.getDistance() + " angle " + gyro.getAngle());
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	switch (mode) {
		case DISTANCE:
    		return motor.getDistance();
		case SPEED:
			return 0;
		case ANGLE:
			return 0;
    	}
		return 0;
    }
    
    public double getPIDInput() {
    	return returnPIDInput();
    }

    @Override
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	SmartDashboard.putString("Mode: ", mode.toString());
    	SmartDashboard.putNumber("Output", output/Math.abs(output));
    	switch (mode) {
		case SPEED:
			motor.setSetpoint(getSetpoint());
			break;
		case DISTANCE:
			motor.setSetpoint(output);
			break;
		case ANGLE:
//			if (isRightMotor) {
//				motor.setSetpoint(-output);
//			} else {
				motor.setSetpoint(getSetpoint());
//			}
			break;	
    	}
    }
    
    public void useSpeed() {
    	mode = PIDDriveMode.SPEED;
    }
    
    public void useDistance() {
    	mode = PIDDriveMode.DISTANCE;
    }
    
    public void useAngle() {
    	mode = PIDDriveMode.ANGLE;
    }
    
    public void start() {
    	motor.start();
    }
    
    public void stop() {
    	motor.stop();
    	setSetpoint(0);
    }
    
    public double getDistance() {
    	return motor.getDistance();
    }
}

