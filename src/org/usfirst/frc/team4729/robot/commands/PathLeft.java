package org.usfirst.frc.team4729.robot.commands;

import org.usfirst.frc.team4729.robot.enums.Direction;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PathLeft extends CommandGroup {
    public PathLeft(Direction side) {
//        switch (side) {
//        case LEFT:
//        	break;
//        case RIGHT:
//        	addSequential(new PathLeftRight());
//        	break;
//        }
    	
    	addSequential(new PathLeftLeft(side));
    }
}
