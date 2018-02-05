package org.usfirst.frc.team4729.robot.commands;

import org.usfirst.frc.team4729.robot.Robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoLeft extends Command {
	
	double distance;
	double ticksPerDistance;
	double ticksToMove;
	Encoder[] encoder;
	
    public AutoLeft(Encoder[] encoder) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    		distance = 3.5;
    		ticksPerDistance = 7;
    		ticksToMove = distance*ticksPerDistance;
    		this.encoder = encoder;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    		Robot.driveSubsystem.resetEncoders();
    		Robot.driveSubsystem.arcade(ticksToMove, 0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    		if ((encoder[0].getDistance() + encoder[2].getDistance())/2 >= ticksToMove) {
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
    }
}
