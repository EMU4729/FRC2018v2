package org.usfirst.frc.team4729.robot.commands;

import org.usfirst.frc.team4729.robot.Robot;
import org.usfirst.frc.team4729.robot.enums.Direction;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoTurnTurn extends Command {

	double degrees;
	Direction direction;
	
    public AutoTurnTurn(double deg, Direction dir) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	degrees = deg;
    	direction = dir;
    	if (dir == Direction.RIGHT) {
    		degrees *= -1;
    	}
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.gyroSubsystem.resetGyro();
    	Robot.driveSubsystem.setMotorAngle(degrees);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("Angle", Robot.gyroSubsystem.getGyroAngle());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    		if (Math.abs(Robot.gyroSubsystem.getGyroAngle() - degrees) <= .5) {
            return true;
        } else {
            return false;
        }
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.gyroSubsystem.resetGyro();
    	Robot.driveSubsystem.resetEncoders();
    	Robot.driveSubsystem.arcade(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
