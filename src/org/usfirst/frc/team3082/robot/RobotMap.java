package org.usfirst.frc.team3082.robot;

/* All "magic numbers" (constants) are stored here, with descriptive names. */

public class RobotMap {
	
	// Joystick Ports
	public static int JOYSTICK_PORT_DRIVER = 0;
	public static int JOYSTICK_PORT_OPERATOR = 1;
	
	// Buttons
	public static int DRIVER_STRAIGHT = 0;
	public static int OPERATOR_CLIMB = 0;
	public static int OPERATOR_GEAR = 1;
	
	// Drivetrain PWM ports
	public static int DRIVETRAIN_L = 0;
	public static int DRIVETRAIN_R = 1;
	
	public static double DRIVE_DEADBAND = 0.15;
	
	// ArduinoPixy DIO Ports
	public static int AP_LEFT = 0;
	public static int AP_CENTER = 1;
	public static int AP_RIGHT = 2;	
	public static int AP_STATUS = 3;
	public static int AP_ENABLE = 4;
	
	public static int CLIMBER_PORT = 2;
	public static int GEARMECH_PORT = 0;
}
