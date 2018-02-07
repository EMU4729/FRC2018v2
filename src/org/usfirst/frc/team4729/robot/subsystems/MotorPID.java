package org.usfirst.frc.team4729.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class MotorPID extends PIDSubsystem {
	TalonSRX motor;
	Encoder encoder;

    // Initialize your subsystem here
    public MotorPID(TalonSRX motor, Encoder encoder) {
    	super ("MotorPID", 1.0, 0.0, 0.0);
    	setAbsoluteTolerance (0.05);
    	getPIDController().setContinuous(false);
    	this.motor = motor;
    	this.encoder = encoder;
    	setInputRange(0, 100);
    	setOutputRange(0, 1);
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
    protected double returnPIDInput () {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return encoder.getDistance();
    }
    
    public double getPIDInput() {
    	return returnPIDInput();
    }

    @Override
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	motor.set(ControlMode.PercentOutput, output);
    }
}
