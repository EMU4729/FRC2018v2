package org.usfirst.frc.team4729.robot.commands;

import org.usfirst.frc.team4729.robot.Robot;
import org.usfirst.frc.team4729.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoCommand extends Command {
	Command resetEncoders = new ResetEncoders ();
    Command moveForward;
    Command moveBackwards;

    public AutoCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSubsystem);
    	String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		
    }

	// Called just before this Command runs the first time
    protected void initialize() {
    	resetEncoders.start ();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(gameData.charAt(0) == 'L')
		{
    		Robot.driveSubsystem.tank(1, 0);
		} else {
			Robot.driveSubsystem.tank(0, 1);
		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
