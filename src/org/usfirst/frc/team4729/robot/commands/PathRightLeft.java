package org.usfirst.frc.team4729.robot.commands;

import org.usfirst.frc.team4729.robot.enums.Direction;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PathRightLeft extends CommandGroup {
    public PathRightLeft() {
    	addSequential(new AutoForwards(5.4825));
        addSequential(new AutoTurn(90, Direction.LEFT));
        addSequential(new AutoForwards(5.5165));
        addSequential(new AutoTurn(90, Direction.LEFT));
        addSequential(new AutoForwards(1.667));
        addSequential(new AutoTurn(90, Direction.LEFT));
        addSequential(new AutoForwards(0.6085));
    }
}
