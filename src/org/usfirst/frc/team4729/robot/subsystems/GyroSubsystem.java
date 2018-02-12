package org.usfirst.frc.team4729.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GyroSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	ADXRS450_Gyro gyro;
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    }
    
    public GyroSubsystem() {
    	gyro = new ADXRS450_Gyro();
    	gyro.calibrate();
    	
    }
    
    public double getGyroAngle() {
    	return gyro.getAngle();
    }
    
    public void resetGyro() {
    	gyro.reset();
    }
    
    public ADXRS450_Gyro getGyro() {
    	return gyro;
    }
}

