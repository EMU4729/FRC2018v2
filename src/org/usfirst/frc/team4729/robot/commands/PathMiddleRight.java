package org.usfirst.frc.team4729.robot.commands;

import org.usfirst.frc.team4729.robot.enums.Direction;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PathMiddleRight extends CommandGroup {
    public PathMiddleRight() {
    	addSequential(new AutoForwards(0.4785));
    	addSequential(new StopMotors());
        addSequential(new AutoTurn(45, Direction.RIGHT));
        addSequential(new StopMotors());
        addSequential(new AutoForwards(1.7685));
        addSequential(new StopMotors());
        addSequential(new AutoTurn(45, Direction.LEFT));
        addSequential(new StopMotors());
        addSequential(new AutoForwards(0.4715));
        addSequential(new StopMotors());
    }
}
