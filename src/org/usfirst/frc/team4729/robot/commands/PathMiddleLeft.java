package org.usfirst.frc.team4729.robot.commands;

import org.usfirst.frc.team4729.robot.enums.Direction;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PathMiddleLeft extends CommandGroup {

    public PathMiddleLeft() {
        addSequential(new AutoForwards(0.4785));
        addSequential(new AutoTurn(45, Direction.LEFT));
        addSequential(new AutoForwards(2.114));
        addSequential(new AutoTurn(45, Direction.RIGHT));
        addSequential(new AutoForwards(0.6257));
//    	addSequential(new AutoForwards(1));
//    	addSequential(new StopMotors());
    }
}
