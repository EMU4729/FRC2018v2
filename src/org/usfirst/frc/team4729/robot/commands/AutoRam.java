package org.usfirst.frc.team4729.robot.commands;

import org.usfirst.frc.team4729.robot.Robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoRam extends Command {

	double distance;
//	Timer timer;
//	double time;
	
    public AutoRam(double d) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		distance = d / 2;
//		timer = new Timer();
//		time = d * 0.898;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveSubsystem.reset();
//    	timer.start();
//    	System.out.println("Moving for " + distance + " units!");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	double speed = Math.max(0.4, distance-Math.abs(Robot.driveSubsystem.getLeftEncoder() + Robot.driveSubsystem.getRightEncoder())/2)/distance; //0.06 = minimum speed
    	Robot.driveSubsystem.arcade(1, 0); // Moves backwards
//    	SmartDashboard.putNumber("dist", Math.abs(Robot.driveSubsystem.getLeftEncoder() + Robot.driveSubsystem.getRightEncoder()) / 2);
//    	Robot.driveSubsystem.arcade(1, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Math.abs(Robot.driveSubsystem.getLeftEncoder() + Robot.driveSubsystem.getRightEncoder()) / 2 >= distance) {
            return true;
        } else {
            return false;
        }
//    	if (timer.hasPeriodPassed(time)) {
//    		return true;
//    	}
//    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
//    	SmartDashboard.putNumber("Distance", (Robot.driveSubsystem.getLeftEncoder() - Robot.driveSubsystem.getRightEncoder()) / 2);
    	
    	Robot.gyroSubsystem.resetGyro();
    	Robot.driveSubsystem.reset();
    	Robot.driveSubsystem.arcade(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
