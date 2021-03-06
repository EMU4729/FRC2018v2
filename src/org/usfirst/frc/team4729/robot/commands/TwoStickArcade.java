package org.usfirst.frc.team4729.robot.commands;

import org.usfirst.frc.team4729.robot.Robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class TwoStickArcade extends Command {
    Joystick leftStick;
    Joystick rightStick;
    
    public TwoStickArcade(Joystick leftStick, Joystick rightStick) {
        requires(Robot.driveSubsystem);
        this.leftStick = leftStick;
        this.rightStick = rightStick;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.driveSubsystem.arcade(leftStick.getY(), rightStick.getX());
        SmartDashboard.putNumber("Testing Endoders: Left", Robot.driveSubsystem.getLeftEncoder());
        SmartDashboard.putNumber("Testing Endoders: Right", Robot.driveSubsystem.getRightEncoder());        
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
