package org.usfirst.frc.team3082.robot.subsystems;

import org.usfirst.frc.team3082.robot.commands.CloseGearMech;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Solenoid;

/* This class controls the gear mechanism */

public class GearMechanism extends Subsystem {
	
	private Solenoid solenoid; 
	
	public GearMechanism(int port) {
		solenoid = new Solenoid(port);	
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new CloseGearMech());
    }
    
    public void open() {
    	solenoid.set(true);
    }
    
    public void close() {
    	solenoid.set(false);
    }
    
    public boolean isOpen() {
    	return solenoid.get();
    }
}