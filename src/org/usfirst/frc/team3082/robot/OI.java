package org.usfirst.frc.team3082.robot;

import org.usfirst.frc.team3082.robot.RobotMap;
import org.usfirst.frc.team3082.robot.commands.*;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;

/* This class contains all Operator Input related objects and methods */

public class OI {

	
	private Joystick driver = new Joystick(RobotMap.JOYSTICK_PORT_DRIVER);
	private JoystickButton straightButton = new JoystickButton(driver, RobotMap.DRIVER_STRAIGHT);	
	
	private Joystick operator = new Joystick(RobotMap.JOYSTICK_PORT_OPERATOR);
	private JoystickButton climbButton = new JoystickButton(operator, RobotMap.OPERATOR_CLIMB);
	private JoystickButton gearButton = new JoystickButton(operator, RobotMap.OPERATOR_GEAR);
	
	// Main command control
	
	public OI() {
		climbButton.whileHeld(new Climb());
		climbButton.whenReleased(new StopClimb());
		gearButton.whileHeld(new OpenGearMech());
		gearButton.whenReleased(new CloseGearMech());
	}
	
	// Public Methods
	
	public double getLeftY() {
		double raw = driver.getRawAxis(1); 
		return normalizeDrive(raw);
	}

	public double getRightY() {
		if (straightButton.get()) { // Press A to drive straight
			return (getLeftY());
		}
		else {
			double raw = driver.getRawAxis(5); 
			return normalizeDrive(raw);		
		}
	}
	
	// Private Helper Methods 
	
	private double normalizeDrive(double value) {
		
		if (value > 0) {
			value = value * value; // Square input for more precise control
		}
		else {
			value = -1 * value * value; // Preserve sign, as you lose it when squaring
		}
	
		if ((value < RobotMap.DRIVE_DEADBAND) && (value > -1 * RobotMap.DRIVE_DEADBAND)) {
			return 0; // Discard small inputs
		}
		else {
			return value;
		}
	}
}