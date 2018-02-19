package org.usfirst.frc.team4729.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
     public static int MOTOR_LEFT_FRONT = 1;
     public static int MOTOR_LEFT_BACK = 2;
     public static int MOTOR_RIGHT_FRONT = 3;
     public static int MOTOR_RIGHT_BACK = 4;
     
     public static int MOTOR_FLAP_RIGHT;
     public static int MOTOR_FLAP_LEFT;
     
     public static int ENCODER_LEFT_A = 0;
     public static int ENCODER_LEFT_B = 1;
     public static int ENCODER_RIGHT_A = 2;
     public static int ENCODER_RIGHT_B = 3;
     
     public static int GRABBER_LEFT_A = 0;
     public static int GRABBER_LEFT_B = 1;
     public static int GRABBER_RIGHT_A = 2;
     public static int GRABBER_RIGHT_B = 3;
     
     public static int WINCH_1 = 11;
     public static int WINCH_2 = 12;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	
}
