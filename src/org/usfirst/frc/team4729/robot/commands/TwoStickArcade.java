package org.usfirst.frc.team4729.robot.commands;

import org.usfirst.frc.team4729.robot.Robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class TwoStickArcade extends Command {
    Joystick xbox;
    
    public TwoStickArcade(Joystick x) {
        requires(Robot.driveSubsystem);
        xbox = x;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.driveSubsystem.arcade(xbox.getRawAxis(1), xbox.getRawAxis(4));
        SmartDashboard.putNumber("Testing Endoders: Left", Robot.driveSubsystem.getLeftEncoderRate());
        SmartDashboard.putNumber("Testing Endoders: Right", Robot.driveSubsystem.getRightEncoderRate()); 
        SmartDashboard.putNumber("leftStick", xbox.getRawAxis(1));
        SmartDashboard.putNumber("rightStick", xbox.getRawAxis(4));
        SmartDashboard.putNumber("leftEncoder", Robot.driveSubsystem.getLeftEncoderRate());
        SmartDashboard.putNumber("rightEncoder", Robot.driveSubsystem.getRightEncoderRate());
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
