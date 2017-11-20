package org.usfirst.frc.team3082.robot.commands;

import org.usfirst.frc.team3082.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/* This command opens the gear mechanism. */

public class OpenGearMech extends Command {
	
    public OpenGearMech() {
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.gearMechanism.open();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false; 
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}