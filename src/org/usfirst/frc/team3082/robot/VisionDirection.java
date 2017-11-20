package org.usfirst.frc.team3082.robot;

/* Simple ENUMerated type to communicate data about which direction the robot should move when
 * under autonomous vision-based targeting (gear placement). See more at subsystems/ArduinoPixy
 */

public enum VisionDirection {
	left, slightLeft, straight, slightRight, right;
}