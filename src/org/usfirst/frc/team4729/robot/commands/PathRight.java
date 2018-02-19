package org.usfirst.frc.team4729.robot.commands;

import org.usfirst.frc.team4729.robot.enums.Direction;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PathRight extends CommandGroup {
    public PathRight(Direction side) {
        switch (side) {
        case LEFT:
        	addSequential(new PathRightLeft());
        	break;
        case RIGHT:
        	System.out.println("Right switch");
        	addSequential(new PathRightRight());
        	break;
        }
    }
}
