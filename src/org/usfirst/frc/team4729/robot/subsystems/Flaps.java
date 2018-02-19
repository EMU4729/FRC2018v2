package org.usfirst.frc.team4729.robot.subsystems;

import org.usfirst.frc.team4729.robot.Robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * servo or motor
 */
public class Flaps extends Subsystem {
	
//	TalonSRX flapleft;
//	TalonSRX flapright;
	
	Talon flapleft;
	Talon flapright;
	
    double speed = 1;
    
    public void controlFlaps() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }


	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}
}

