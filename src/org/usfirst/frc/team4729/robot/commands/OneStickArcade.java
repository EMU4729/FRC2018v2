package org.usfirst.frc.team4729.robot.commands;

import org.usfirst.frc.team4729.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class OneStickArcade extends Command {
    Joystick stick;
    public OneStickArcade(Joystick stick) {
        requires(Robot.driveSubsystem);
        this.stick = stick;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("Testing Endoder Distance: Left", Robot.driveSubsystem.getLeftEncoder());
    	SmartDashboard.putNumber("Testing Endoder Distance: Right", Robot.driveSubsystem.getRightEncoder());
        Robot.driveSubsystem.arcade(stick.getY(), stick.getX());
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
