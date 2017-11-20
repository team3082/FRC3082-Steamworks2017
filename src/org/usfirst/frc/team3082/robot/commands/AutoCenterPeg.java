package org.usfirst.frc.team3082.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/* Place gear on center peg */

public class AutoCenterPeg extends CommandGroup {

    public AutoCenterPeg() {
    	addParallel(new OpenGearMech());
        addParallel(new DriveForwardTime(0.4, 1));
        addSequential(new PlaceGearWithVision(8));
        addSequential(new CloseGearMech());
    }
}