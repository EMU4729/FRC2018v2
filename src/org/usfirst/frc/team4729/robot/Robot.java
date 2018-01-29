
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
import org.usfirst.frc.team4729.robot.commands.TwoStickArcade;
import org.usfirst.frc.team4729.robot.commands.TwoStickTank;
import org.usfirst.frc.team4729.robot.subsystems.DriveSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    public static DriveSubsystem driveSubsystem;
    public static OI oi;

    Command autonomousCommand;
    Command driveType;
    SmartDashboard smartDashboard;
    SendableChooser<Auto> autonomousSelector;
    SendableChooser<Command> driveModeSelector;
    //CameraServer camera;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        driveSubsystem = new DriveSubsystem();
        oi = new OI();
        
        autonomousSelector = new SendableChooser<Auto>();
        autonomousSelector.addDefault("Forward 2", new Auto("Forward 2"));
        autonomousSelector.addObject("Forward 4", new Auto("Forward 4"));
        SmartDashboard.putData("Auto Type", autonomousSelector);
        
        Joystick leftStick = new Joystick(0);
        Joystick rightStick = new Joystick(1);
        driveModeSelector = new SendableChooser<Command>();
        driveModeSelector.addDefault("Two Stick Arcade", new TwoStickArcade(leftStick, rightStick));
        driveModeSelector.addObject("Two Stick Tank", new TwoStickTank(leftStick, rightStick));
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
    	autonomousCommand = (Command) autonomousSelector.getSelected();
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
        if (autonomousCommand != null) autonomousCommand.cancel();
        
        driveType = (Command) driveModeSelector.getSelected();
        /*
        Joystick leftStick = new Joystick(0);
        Joystick rightStick = new Joystick(1);
        TwoStickArcade twoStickArcade = new TwoStickArcade(leftStick, rightStick);*/

        driveType.start();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

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
