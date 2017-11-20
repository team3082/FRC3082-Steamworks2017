package org.usfirst.frc.team3082.robot.commands;

import org.usfirst.frc.team3082.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/* This command will make the robot drive forward for a given number of seconds */

public class DriveForwardTime extends Command {

	private Timer timer;
	private double finishTime;
	private double driveSpeed;
			
    public DriveForwardTime(double speed, double seconds) {
    	requires(Robot.driveTrain);
    	finishTime = seconds;
    	driveSpeed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.driveTank(driveSpeed, driveSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (timer.get() > finishTime);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.driveTank(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
