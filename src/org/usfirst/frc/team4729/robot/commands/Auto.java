package org.usfirst.frc.team4729.robot.commands;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Auto extends CommandGroup {

    public Auto(String autoType) {
	    	//String mode = SmartDashboard.getString("Auto Type", "forwards 2");
	    	
	    	switch (autoType) {
	    	case "AutoLeft":
	    		addSequential (new AutoLeft());
	    		break;
	    	case "Auto Turn":
	    		addSequential (new AutoTurn());
	    		break;
	    }

    	
//    	if(gameData.charAt(0) == 'L')
//		{
//    		Robot.driveSubsystem.tank(1, 0);
//		} else {
//			Robot.driveSubsystem.tank(0, 1);
//		}
    	
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
