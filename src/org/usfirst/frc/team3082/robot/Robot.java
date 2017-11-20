package org.usfirst.frc.team3082.robot;

import org.usfirst.frc.team3082.robot.RobotMap;
import org.usfirst.frc.team3082.robot.commands.*;
import org.usfirst.frc.team3082.robot.subsystems.*;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Compressor;

/* This is the main program that contains high level robot control */

public class Robot extends IterativeRobot {

	public static Compressor compressor;
	public static OI oi;
	public static ArduinoPixy arduinoPixy;
	public static Climber climber;
	public static GearMechanism gearMechanism;
	public static DriveTrain driveTrain;

	
	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();


	@Override
	public void robotInit() {
		compressor = new Compressor(0);
		oi = new OI();
		arduinoPixy = new ArduinoPixy();
		climber = new Climber(RobotMap.CLIMBER_PORT);
		gearMechanism = new GearMechanism(RobotMap.GEARMECH_PORT);
		driveTrain = new DriveTrain(RobotMap.DRIVETRAIN_L, RobotMap.DRIVETRAIN_R);
		
		chooser.addDefault("Baseline (Default)", new AutoBaseline());
		chooser.addObject("Left Peg", new AutoLeftPeg());
		chooser.addObject("Center Peg", new AutoCenterPeg());
		chooser.addObject("Right Peg", new AutoRightPeg());
		chooser.addObject("Run Compressor", new RunCompressor());
		SmartDashboard.putData("Auto mode", chooser);
	}

	@Override
	public void disabledInit() {
		driveTrain.stop();
		gearMechanism.close();
		climber.stop();
		SmartDashboard.putNumber("Left     Speed:", driveTrain.getLeftSpeed());
		SmartDashboard.putNumber("Right    Speed:", driveTrain.getRightSpeed());
		SmartDashboard.putNumber("Climber  Speed:", climber.getSpeed());
		SmartDashboard.putBoolean("GearMechanism:", gearMechanism.isOpen());
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		autonomousCommand = chooser.getSelected();

		if (autonomousCommand == null) {
			autonomousCommand = new AutoBaseline();  // If there is something wrong, and no auto is selected, run the default 
		}
		
		autonomousCommand.start();
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();

		SmartDashboard.putNumber("Left     Speed:", driveTrain.getLeftSpeed());
		SmartDashboard.putNumber("Right    Speed:", driveTrain.getRightSpeed());
		SmartDashboard.putNumber("Climber  Speed:", climber.getSpeed());
		SmartDashboard.putBoolean("GearMechanism:", gearMechanism.isOpen());

	}

	@Override
	public void teleopInit() {
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}
		
		compressor.stop(); // We have an offboard compressor, so turn it off when teleop begins.
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
		SmartDashboard.putNumber("Left     Speed:", driveTrain.getLeftSpeed());
		SmartDashboard.putNumber("Right    Speed:", driveTrain.getRightSpeed());
		SmartDashboard.putNumber("Climber  Speed:", climber.getSpeed());
		SmartDashboard.putBoolean("GearMechanism:", gearMechanism.isOpen());

	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
		SmartDashboard.putNumber("Left     Speed:", driveTrain.getLeftSpeed());
		SmartDashboard.putNumber("Right    Speed:", driveTrain.getRightSpeed());
		SmartDashboard.putNumber("Climber  Speed:", climber.getSpeed());
		SmartDashboard.putBoolean("GearMechanism:", gearMechanism.isOpen());
	}
}
