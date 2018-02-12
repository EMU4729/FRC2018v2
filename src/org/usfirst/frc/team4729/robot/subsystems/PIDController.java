package org.usfirst.frc.team4729.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class PIDController extends PIDSubsystem {
	PIDMotor motor;
	boolean speedMode;

    // Initialize your subsystem here
    public PIDController(Talon motor, Encoder encoder) {
    	super ("PIDDistance", 1.0, 0.0, 0.0);
    	setAbsoluteTolerance (0.05);
    	getPIDController().setContinuous(false);
    	this.motor = new PIDMotor(motor, encoder);
    	speedMode = false;
    	
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
        return motor.getDistance();
    }
    
    public double getPIDInput() {
    	return returnPIDInput();
    }

    @Override
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	if (speedMode) {
    		motor.setSetpoint(getSetpoint());
    	} else {
    		motor.setSetpoint(output);
    	}
    }
    
    public void useSpeed() {
    	speedMode = true;
    }
    
    public void useDistance() {
    	speedMode = false;
    }
}

