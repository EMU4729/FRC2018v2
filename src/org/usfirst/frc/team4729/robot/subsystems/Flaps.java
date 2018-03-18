package org.usfirst.frc.team4729.robot.subsystems;

import org.usfirst.frc.team4729.robot.Robot;
import org.usfirst.frc.team4729.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * servo or motor
 */
public class Flaps extends Subsystem {
	
//	Spark flapMotor;
	TalonSRX flapMotor;
//	Talon flapleft;
//	Talon flapright;
	
    double speed;
    
    public Flaps() {
//    	flapMotor = new Spark(RobotMap.MOTOR_FLAP);
    	flapMotor = new TalonSRX(RobotMap.MOTOR_FLAP);
    	speed = 0.45;
    }
    
    public void turn() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	flapMotor.set(ControlMode.PercentOutput, speed);
    }
    
    public void stop() {
    	flapMotor.set(ControlMode.PercentOutput, 0);
    }


	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}
}

