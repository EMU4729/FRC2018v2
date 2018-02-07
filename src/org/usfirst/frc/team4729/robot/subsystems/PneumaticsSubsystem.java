package org.usfirst.frc.team4729.robot.subsystems;

import org.usfirst.frc.team4729.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class PneumaticsSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	DoubleSolenoid grabberLeft = new DoubleSolenoid(RobotMap.GRABBER_LEFT_A, RobotMap.GRABBER_LEFT_B);
	DoubleSolenoid grabberRight = new DoubleSolenoid(RobotMap.GRABBER_RIGHT_A, RobotMap.GRABBER_RIGHT_B);
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void Open() {
    	grabberLeft.set(DoubleSolenoid.Value.kReverse);
    	grabberRight.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void Close() {
    	grabberLeft.set(DoubleSolenoid.Value.kForward);
    	grabberRight.set(DoubleSolenoid.Value.kForward);
    }
    
    public void Neutral() {
    	grabberLeft.set(DoubleSolenoid.Value.kOff);
    	grabberRight.set(DoubleSolenoid.Value.kOff);
    }
}

