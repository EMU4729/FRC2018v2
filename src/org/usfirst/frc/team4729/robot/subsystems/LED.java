package org.usfirst.frc.team4729.robot.subsystems;

import org.usfirst.frc.team4729.robot.RobotMap;

import com.mindsensors.CANLight;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LED extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	CANLight led;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public LED() {
    	led = new CANLight(RobotMap.LED);
    }
    
    public void green() {
    	led.showRGB(0, 255, 0);
    }
    
    public void stop() {
    	led.showRGB(0, 0, 0);
    }
}

