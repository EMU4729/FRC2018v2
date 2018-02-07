package org.usfirst.frc.team4729.robot.subsystems;

import org.usfirst.frc.team4729.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Piston extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	DoubleSolenoid solenoid = new DoubleSolenoid(RobotMap.SOLENOID_A, RobotMap.SOLENOID_B);
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void Open() {
   			solenoid.set(DoubleSolenoid.Value.kReverse);
   	}
    
    public void close() {
			solenoid.set(DoubleSolenoid.Value.kForward);
    }
}

