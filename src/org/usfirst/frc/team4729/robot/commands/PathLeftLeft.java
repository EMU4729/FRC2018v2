package org.usfirst.frc.team4729.robot.commands;

import org.usfirst.frc.team4729.robot.enums.Direction;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PathLeftLeft extends CommandGroup {
    public PathLeftLeft(Direction side) {
    	addSequential(new AutoForwards(3.8155));
    	if (side == Direction.LEFT) {
    		addSequential(new AutoTurn(90, Direction.RIGHT));
        	addSequential(new AutoRamWhileEjectingCube(0.8/*0.53*/));
    	}
    }
}
