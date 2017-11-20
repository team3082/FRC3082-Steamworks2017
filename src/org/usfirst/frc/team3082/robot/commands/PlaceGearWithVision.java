package org.usfirst.frc.team3082.robot.commands;

import org.usfirst.frc.team3082.robot.Robot;
import org.usfirst.frc.team3082.robot.VisionDirection;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/* Follow the vision target for a given amount of time */

public class PlaceGearWithVision extends Command {

	Timer timer;
	double driveTime; 
	VisionDirection direction;
	
    public PlaceGearWithVision(double time) {
    	requires(Robot.driveTrain);
    	requires(Robot.arduinoPixy);
    	driveTime = time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.start();
    	direction = VisionDirection.straight;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	direction = Robot.arduinoPixy.getDirection();
    	
    	switch(direction) {
    		case left:
    			Robot.driveTrain.driveTank(0.2, 0.6);
    			break;
    		case slightLeft:
    			Robot.driveTrain.driveTank(0.3, 0.5);
    			break;
    		case straight: 
    			Robot.driveTrain.driveTank(0.4, 0.4);
    			break;
    		case slightRight:
    			Robot.driveTrain.driveTank(0.5, 0.3);
    			break;
    		case right:
    			Robot.driveTrain.driveTank(0.6, 0.2);
    			break;
    		default: 
    			Robot.driveTrain.driveTank(0.4, 0.4);
    			break;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (timer.get() > driveTime);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.driveTank(0,0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}