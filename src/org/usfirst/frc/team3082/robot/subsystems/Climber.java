package org.usfirst.frc.team3082.robot.subsystems;

import org.usfirst.frc.team3082.robot.commands.StopClimb;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Talon;

/* This class controls the climber */

public class Climber extends Subsystem {
	
	// Actually 2 Talon SRs on a PWM splitter
	private Talon talon;
	
	
	public Climber(int port) {
		talon = new Talon(port);			
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new StopClimb());
    }
    
    public void runForward() {
    	talon.set(1);
    }
    
    public void runBackward() {
    	talon.set(-1);
    }
    
    public void stop() {
    	talon.set(0);
    }
    
    public void set(double speed) {
    	talon.set(speed);
    }
    
    public double getSpeed() {
    	return talon.get();
    }
}