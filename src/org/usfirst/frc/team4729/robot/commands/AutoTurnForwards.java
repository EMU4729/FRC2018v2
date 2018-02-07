package org.usfirst.frc.team4729.robot.commands;

import org.usfirst.frc.team4729.robot.Robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoTurnForwards extends Command {

	double distance;
	int counter;
	
    public AutoTurnForwards() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    		distance = 3.5;
    		counter = 0;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    		Robot.driveSubsystem.resetEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveSubsystem.arcade(0.75, 0);
    	SmartDashboard.putNumber("Distance", (Robot.driveSubsystem.getLeftEncoder() + Robot.driveSubsystem.getRightEncoder())/2);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    		if ((Robot.driveSubsystem.getLeftEncoder() + Robot.driveSubsystem.getRightEncoder())/2 >= distance) {
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
