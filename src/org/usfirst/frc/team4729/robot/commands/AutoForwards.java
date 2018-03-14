package org.usfirst.frc.team4729.robot.commands;

import org.usfirst.frc.team4729.robot.Robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoForwards extends Command {

	double distance;
	int counter;
	
    public AutoForwards(double d) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		distance = d;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveSubsystem.reset();
//    	System.out.println("Moving for " + distance + " units!");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveSubsystem.arcade(1, 0);
//    	Robot.driveSubsystem.setMotorDistance(distance);
//    	SmartDashboard.putNumber("Distance", (Robot.driveSubsystem.getLeftEncoder() - Robot.driveSubsystem.getRightEncoder()) / 2);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if ((Robot.driveSubsystem.getLeftEncoder() - Robot.driveSubsystem.getRightEncoder()) / 2 >= distance) {
            return true;
        } else {
            return false;
        }
    }

    // Called once after isFinished returns true
    protected void end() {
//    	SmartDashboard.putNumber("Distance", (Robot.driveSubsystem.getLeftEncoder() - Robot.driveSubsystem.getRightEncoder()) / 2);
    	
    	Robot.gyroSubsystem.resetGyro();
    	Robot.driveSubsystem.reset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
