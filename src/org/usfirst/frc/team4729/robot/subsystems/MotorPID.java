package org.usfirst.frc.team4729.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class MotorPID extends PIDSubsystem {
	PWMSpeedController motor;
	Encoder encoder;

    // Initialize your subsystem here
    public MotorPID (PWMSpeedController motor, Encoder encoder) {
    	super ("MotorPID", 1.0, 0.0, 0.0);
    	setAbsoluteTolerance (0.05);
    	getPIDController ().setContinuous (true);
    	this.motor = motor;
    	this.encoder = encoder;
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }

    public void initDefaultCommand () {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    protected double returnPIDInput () {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return encoder.getRate ();
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	motor.set (output);
    }
}
