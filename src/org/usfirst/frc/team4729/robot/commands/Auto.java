package org.usfirst.frc.team4729.robot.commands;

import org.usfirst.frc.team4729.robot.Robot;
import org.usfirst.frc.team4729.robot.enums.Direction;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Auto extends CommandGroup {
	
    public Auto(String autoType) {
	    	Robot.driveSubsystem.setMotorsToDistance();
	    	Robot.driveSubsystem.enable();
	    	
	    	Direction side = Direction.LEFT; // A default in case something goes not good
	    	
//	    	String gameData;
//			gameData = DriverStation.getInstance().getGameSpecificMessage();
//	    	if (gameData.charAt(0) == "L") {
//	    		side = Direction.LEFT;
//			} else if (gameData.charAt(0) == "R") {
//				side = Direction.RIGHT;
//			}
	    	
	    	switch (autoType) {
	    	case "l":
	    		addSequential(new PathLeft(side));
	    		break;
	    	case "m":
	    		addSequential(new PathMiddle(side));
	    		break;
	    	case "r":
	    		addSequential(new PathRight(side));
	    		break;
	    }
    	
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
