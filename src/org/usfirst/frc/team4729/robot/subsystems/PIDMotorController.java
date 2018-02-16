package org.usfirst.frc.team4729.robot.subsystems;

import org.usfirst.frc.team4729.robot.enums.PIDDriveMode;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class PIDMotorController extends PIDSubsystem {
	PIDMotor motor;
	ADXRS450_Gyro gyro;
	PIDDriveMode mode;
	boolean isRightMotor;

    // Initialize your subsystem here
    public PIDMotorController(TalonSRX motor, Encoder encoder, ADXRS450_Gyro gyro, boolean isRightMotor) {
    	super ("PIDDistance", 1.0, 0.0, 0.0);
    	setAbsoluteTolerance (0.05);
    	getPIDController().setContinuous(false);
    	
    	this.motor = new PIDMotor(motor, encoder);
    	this.gyro = gyro;
    	this.isRightMotor = isRightMotor;
    	
    	mode = PIDDriveMode.SPEED;
    	
    	setInputRange(0, 100);
    	setOutputRange(0, 1);
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
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	switch (mode) {
		case DISTANCE:
    		return motor.getDistance();
		case SPEED:
			return 0;
		case ANGLE:
			return gyro.getAngle();
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
    	switch (mode) {
		case SPEED:
			motor.setSetpoint(getSetpoint());
			break;
		case DISTANCE:
			motor.setSetpoint(output);
			break;
		case ANGLE:
			if (isRightMotor) {
				motor.setSetpoint(-output);
			} else {
				motor.setSetpoint(output);
			}
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
}

