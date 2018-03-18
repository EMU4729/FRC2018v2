package org.usfirst.frc.team4729.robot.subsystems;

import org.usfirst.frc.team4729.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CubeSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	TalonSRX cubeMotor;
	double speed;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public CubeSubsystem() {
    	cubeMotor = new TalonSRX(RobotMap.CUBE_MOTOR);
    	speed = 0.4;
    }
    
    public void intake() {
    	cubeMotor.set(ControlMode.PercentOutput, speed);
    }
    
    public void outtake() {
    	cubeMotor.set(ControlMode.PercentOutput, -speed);
    }
    
    public void stop() {
    	cubeMotor.set(ControlMode.PercentOutput, 0);
    }
}

