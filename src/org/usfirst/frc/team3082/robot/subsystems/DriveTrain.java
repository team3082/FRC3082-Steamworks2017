package org.usfirst.frc.team3082.robot.subsystems;

import org.usfirst.frc.team3082.robot.commands.DriveWithJoysticks;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.RobotDrive;

/* This class contains objects and methods associated with the drivetrain (wheels) */

public class DriveTrain extends Subsystem {
	
	// Actually 2 Talon SRs on a PWM splitter
	private Talon leftMotor;
	// Actually 2 Talon SRs on a PWM splitter
	private Talon rightMotor;
	
	private RobotDrive robotDrive;
	
    public void initDefaultCommand() {
        setDefaultCommand(new DriveWithJoysticks());
    }
	
    public DriveTrain(int leftMotorPort, int rightMotorPort) {
    	this.leftMotor = new Talon(leftMotorPort);
    	this.rightMotor = new Talon(rightMotorPort);
    	this.robotDrive = new RobotDrive(leftMotor, rightMotor);
    }

    public void driveTank(double leftSpeed, double rightSpeed) {
    	robotDrive.tankDrive(leftSpeed, rightSpeed);
    }
    
    public double getLeftSpeed() {
    	return leftMotor.get();
    }
    
    public double getRightSpeed() {
    	return rightMotor.get();
    }
    
    public void stop() {
    	robotDrive.tankDrive(0, 0);
    }
}