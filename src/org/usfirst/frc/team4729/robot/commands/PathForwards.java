package org.usfirst.frc.team4729.robot.commands;

import org.usfirst.frc.team4729.robot.enums.Direction;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PathForwards extends CommandGroup {

    public PathForwards() {
    	addSequential(new AutoForwards(4/*3.8155*/));
    }
}
