package org.usfirst.frc.team4729.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRamWhileEjectingCube extends CommandGroup {

    public AutoRamWhileEjectingCube(double distance) {
        addParallel(new AutoRam(distance));
        addParallel(new AutoEjectCube());
    }
}
