package org.usfirst.frc.team4729.robot.commands;

import org.usfirst.frc.team4729.robot.enums.Direction;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PathLeftRight extends CommandGroup {
    public PathLeftRight() {
        addSequential(new AutoForwards(5.4825));
        addSequential(new AutoTurn(90, Direction.RIGHT));
        addSequential(new AutoForwards(5.5165));
        addSequential(new AutoTurn(90, Direction.RIGHT));
        addSequential(new AutoForwards(1.667));
        addSequential(new AutoTurn(90, Direction.RIGHT));
        addSequential(new AutoForwards(0.6085));
    }
}
