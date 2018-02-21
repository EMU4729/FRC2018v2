package org.usfirst.frc.team4729.robot.commands;

import org.usfirst.frc.team4729.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StopMotors extends Command {
	double TOLERANCE = 0.01;
	Timer timer;

    public StopMotors() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	timer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveSubsystem.stopMotors();
    	timer.reset();
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (Math.abs(Robot.driveSubsystem.getLeftEncoderRate()) < TOLERANCE && Math.abs(Robot.driveSubsystem.getRightEncoderRate()) < TOLERANCE && timer.get() >=1) {
        	return true;
        } else {
        	return false;
        }
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
