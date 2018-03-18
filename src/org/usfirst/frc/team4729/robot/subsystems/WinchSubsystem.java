package org.usfirst.frc.team4729.robot.subsystems;

import org.usfirst.frc.team4729.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class WinchSubsystem extends Subsystem {
	TalonSRX winch1;
	TalonSRX winch2;
	
//	Talon winch1;
//	Talon winch2;
	
	double normalSpeed;
	double carrySpeed;
	double upSpeed;
	
	public WinchSubsystem() {
		winch1 = new TalonSRX(RobotMap.WINCH_1);
		winch2 = new TalonSRX(RobotMap.WINCH_2);
//		winch1 = new Talon(RobotMap.WINCH_1);
//		winch2 = new Talon(RobotMap.WINCH_2);
		
		normalSpeed = 0.7;
		carrySpeed = 1;
		upSpeed = 0.5;
	}

    public void initDefaultCommand() {
    }
    
    public void up() {
    	winch1.set(ControlMode.PercentOutput, -upSpeed);
    	winch2.set(ControlMode.PercentOutput, -upSpeed);
//    	winch1.set(-SPEED);
//    	winch2.set(-SPEED);
    }
    
    public void down(boolean carry) {
    	if (carry) {
    		winch1.set(ControlMode.PercentOutput, carrySpeed);
    		winch2.set(ControlMode.PercentOutput, carrySpeed);
    	} else {
    		winch1.set(ControlMode.PercentOutput, normalSpeed);
    		winch2.set(ControlMode.PercentOutput, normalSpeed);
    	}
//    	winch1.set(SPEED);
//    	winch2.set(SPEED);
    }
    
    public void stop() {
    	winch1.set(ControlMode.PercentOutput, 0);
    	winch2.set(ControlMode.PercentOutput, 0);
//    	winch1.set(0);
//    	winch2.set(0);
    }
}

