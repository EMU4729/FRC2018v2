package org.usfirst.frc.team4729.robot.commands;

import org.usfirst.frc.team4729.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoTurnTurn extends Command {

	double degrees;
	
    public AutoTurnTurn() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    		degrees = 90;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    		Robot.gyroSubsystem.resetGyro();
    		Robot.driveSubsystem.arcade(0, 0.75);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    		if (Robot.gyroSubsystem.getGyroAngle() >= degrees) {
            return true;
        } else {
            return false;
        }
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveSubsystem.arcade(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
