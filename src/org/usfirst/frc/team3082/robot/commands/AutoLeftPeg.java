package org.usfirst.frc.team3082.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/* Place gear on left peg */

public class AutoLeftPeg extends CommandGroup {

    public AutoLeftPeg() {
    	addParallel(new OpenGearMech());
        addParallel(new DriveForwardTime(0.4, 2));
        addSequential(new RotateLeftTime(0.4, 1));
        addSequential(new PlaceGearWithVision(8));
        addSequential(new CloseGearMech());
    }
}