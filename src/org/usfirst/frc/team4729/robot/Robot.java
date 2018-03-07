package org.usfirst.frc.team4729.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4729.robot.commands.Auto;
import org.usfirst.frc.team4729.robot.commands.MoveForwards;
import org.usfirst.frc.team4729.robot.commands.OneStickArcade;
import org.usfirst.frc.team4729.robot.commands.TwoStickArcade;
import org.usfirst.frc.team4729.robot.commands.TwoStickTank;
import org.usfirst.frc.team4729.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team4729.robot.subsystems.GyroSubsystem;
//import org.usfirst.frc.team4729.robot.subsystems.PneumaticsSubsystem;
import org.usfirst.frc.team4729.robot.subsystems.WinchSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	public static GyroSubsystem gyroSubsystem;
	public static DriveSubsystem driveSubsystem;
	public static WinchSubsystem winchSubsystem;
//	public static PneumaticsSubsystem pneumaticsSubsystem;
	public static OI oi;

	Command autonomousCommand;
	Command driveType;
	SmartDashboard smartDashboard;
	SendableChooser<String> autonomousSelector;

	SendableChooser<Command> driveModeSelector;

	// CameraServer camera;

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	public void robotInit() {
		gyroSubsystem = new GyroSubsystem();
		driveSubsystem = new DriveSubsystem();
		winchSubsystem = new WinchSubsystem();
//		pneumaticsSubsystem = new PneumaticsSubsystem();
		oi = new OI();

		autonomousSelector = new SendableChooser<String>();
		autonomousSelector.addObject("Left", "l");
		autonomousSelector.addObject("Middle", "m");
		autonomousSelector.addDefault("Right", "r");
		SmartDashboard.putData("Auto Type", autonomousSelector);

		Joystick xbox = new Joystick(0);
		driveModeSelector = new SendableChooser<Command>();
		driveModeSelector.addDefault("Two Stick Arcade", new TwoStickArcade(xbox));
		driveModeSelector.addObject("Two Stick Tank", new TwoStickTank(xbox));
		SmartDashboard.putData("Drive Type", driveModeSelector);

		new Thread(() -> {
			UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
			camera.setResolution(640, 480);
		}).start();
		// instantiate the command used for the autonomous period
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
		// schedule the autonomous command (example)
		Robot.driveSubsystem.reset();
		Robot.gyroSubsystem.resetGyro();
		String autonomousString = autonomousSelector.getSelected();
		autonomousCommand = new Auto (autonomousString);
		autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		Robot.driveSubsystem.reset();
		Robot.gyroSubsystem.resetGyro();
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		Robot.driveSubsystem.setMotorsToSpeed();
		Robot.driveSubsystem.startMotors();
		
		driveType = (Command) driveModeSelector.getSelected();
		/*
		 * Joystick leftStick = new Joystick(0); Joystick rightStick = new Joystick(1);
		 * TwoStickArcade twoStickArcade = new TwoStickArcade(leftStick, rightStick);
		 */

		driveType.start();
	}

	/**
	 * This function is called when the disabled button is hit. You can use it to
	 * reset subsystems before shutting down.
	 */
	public void disabledInit() {

	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@SuppressWarnings("deprecation")

	public void testPeriodic() {
		LiveWindow.run();
	}
}
