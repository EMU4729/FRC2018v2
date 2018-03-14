package org.usfirst.frc.team4729.robot.commands;

import org.usfirst.frc.team4729.robot.Robot;
import org.usfirst.frc.team4729.robot.enums.Direction;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoTurn extends Command {

	double degrees;
	Direction direction;
	
    public AutoTurn(double deg, Direction dir) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	degrees = deg;
    	direction = dir;
    	if (dir == Direction.LEFT) {
    		degrees *= -1;
    	}
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.gyroSubsystem.resetGyro();
//    	Robot.driveSubsystem.setMotorsToAngle();
//    	Robot.driveSubsystem.startMotors();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double speed = Math.max(0.06, Math.abs(((degrees-Robot.gyroSubsystem.getGyroAngle())/degrees))); //0.06 = minimum speed
    	Robot.driveSubsystem.arcade(0, Math.signum(degrees)*speed);
    	SmartDashboard.putNumber("Angle", Robot.gyroSubsystem.getGyroAngle());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Math.abs(Robot.gyroSubsystem.getGyroAngle() - degrees)%360 <= 5 || Math.abs(Robot.gyroSubsystem.getGyroAngle() - degrees)%360 >= 355) {
            return true;
        } else {
            return false;
        }
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveSubsystem.arcade(0, 0);
    	Robot.gyroSubsystem.resetGyro();
    	Robot.driveSubsystem.reset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
