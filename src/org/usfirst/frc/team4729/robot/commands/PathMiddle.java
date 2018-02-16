package org.usfirst.frc.team4729.robot.commands;

import org.usfirst.frc.team4729.robot.enums.Direction;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PathMiddle extends CommandGroup {

    public PathMiddle(Direction side) {
    		switch (side) {
    		case LEFT:
    			addSequential(new PathMiddleLeft());
    			break;
    		case RIGHT:
    			addSequential(new PathMiddleRight());
    			break;
    		}
    }
}
