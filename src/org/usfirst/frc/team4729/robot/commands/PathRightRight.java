package org.usfirst.frc.team4729.robot.commands;

import org.usfirst.frc.team4729.robot.enums.Direction;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PathRightRight extends CommandGroup {

    public PathRightRight() {
    	addSequential(new AutoForwards(3.8155));
		addSequential(new AutoTurn(90, Direction.LEFT));
		addSequential(new AutoForwards(0.53));
    }
}
