package org.usfirst.frc.team4729.robot.commands;

import org.usfirst.frc.team4729.robot.enums.Direction;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PathRightRight extends CommandGroup {
    public PathRightRight() {
    	System.out.println("Executing command");
		addSequential(new AutoForwards(3.8155));
		addSequential(new StopMotors());
		System.out.println("Done forwards #1");
		addSequential(new AutoTurn(90, Direction.LEFT));
		addSequential(new StopMotors());
		System.out.println("Done turn");
		addSequential(new AutoForwards(0.53));
		addSequential(new StopMotors());
		System.out.println("Done forwards #2");
    }
}
