package org.usfirst.frc.team4729.robot.subsystems;

import org.usfirst.frc.team4729.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class WinchSubsystem extends Subsystem {
	TalonSRX winch1;
	TalonSRX winch2;
	
	double SPEED;
	
	public WinchSubsystem() {
		winch1 = new TalonSRX(RobotMap.WINCH_1);
		winch2 = new TalonSRX(RobotMap.WINCH_2);
		SPEED = 1;
	}

    public void initDefaultCommand() {
    }
    
    public void up() {
    	winch1.set(ControlMode.PercentOutput, -SPEED);
    	winch2.set(ControlMode.PercentOutput, -SPEED);
    }
    
    public void down() {
    	winch1.set(ControlMode.PercentOutput, SPEED);
    	winch2.set(ControlMode.PercentOutput, SPEED);
    }
    
    public void stop() {
    	winch1.set(ControlMode.PercentOutput, 0);
    	winch2.set(ControlMode.PercentOutput, 0);
    }
}

