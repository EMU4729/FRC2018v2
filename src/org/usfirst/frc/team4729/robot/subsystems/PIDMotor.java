package org.usfirst.frc.team4729.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PIDMotor extends PIDSubsystem {
//	TalonSRX motor;
	Talon motor;
	Encoder encoder;
	boolean isRightMotor;
	double MAX_ENCODER_RATE = 2.5;
	
	boolean running = true;

    // Initialize your subsystem here
//    public PIDMotor(TalonSRX motor, Encoder encoder) {
    public PIDMotor(Talon motor, Encoder encoder, boolean isRightMotor) {
    	super ("PIDMotor", 0.8, 0.001, 0.25);
    	setAbsoluteTolerance (0.3);
    	getPIDController().setContinuous(false);
    	this.motor = motor;
    	this.encoder = encoder;
    	this.isRightMotor = isRightMotor;
//    	setInputRange(-1, 1);
//    	setOutputRange(-1, 1);
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }

    @Override
    public void initDefaultCommand () {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    @Override
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;

        return encoder.getRate() / MAX_ENCODER_RATE;
    }
    
    public double getPIDInput() {
    	return returnPIDInput();
    }

    @Override
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
//    	motor.set(ControlMode.PercentOutput, output);
    	
    	if (running) {
    		motor.set(output);
    	} else {
    		motor.set(0);
    	}
    }
    
    public double getDistance() {
    	return encoder.getDistance()*(0.93/0.963);
    }
    
    public void start() {
    	running = true;
    }
    
    public void stop() {
    	running = false;
    }
}
