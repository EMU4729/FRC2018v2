package org.usfirst.frc.team4729.robot.commands;

import org.usfirst.frc.team4729.robot.Robot;
import org.usfirst.frc.team4729.robot.enums.Direction;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Auto extends CommandGroup {

    public Auto(String autoType) {
//    	Robot.driveSubsystem.setMotorsToDistance();
//    	Robot.driveSubsystem.enable();
    	
    	Direction side = Direction.LEFT; // A default in case something goes not good
    	
    	String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
    	if (gameData.charAt(0) == 'L') {
    		side = Direction.LEFT;
		} else if (gameData.charAt(0) == 'R') {
			side = Direction.RIGHT;
		}
    	
    	SmartDashboard.putString("Switch side", side.toString());
    	
    	switch (autoType) {
    	case "l":
    		addSequential(new PathLeft(side));
    		break;
    	case "m":
    		addSequential(new PathMiddle(side));
    		break;
    	case "r":
    		System.out.println("Right position");
    		addSequential(new PathRight(side));
    		break;
	    }
    	
    	super.initialize();
    }
}
