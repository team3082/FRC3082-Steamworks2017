package org.usfirst.frc.team3082.robot.commands;

import org.usfirst.frc.team3082.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/* Auto mode to pressurize the pneumatic system before the start of the match */

public class RunCompressor extends Command {
	
    public RunCompressor() {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.compressor.start();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.compressor.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
