package org.usfirst.frc.team4729.robot.commands;

import org.usfirst.frc.team4729.robot.enums.Direction;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PathRightRight extends CommandGroup {
    public PathRightRight(Direction side) {
//    	System.out.println("Executing command");
		addSequential(new AutoForwards(3.8155));
//		System.out.println("Done forwards #1");
//		System.out.println("Done turn");
//		System.out.println("Done forwards #2");
		if (side == Direction.RIGHT) {
			addSequential(new AutoTurn(90, Direction.LEFT));
			addSequential(new AutoForwards(0.53));
			addSequential(new AutoEjectCube());
		}
    }
}
