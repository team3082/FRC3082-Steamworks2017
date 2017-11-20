package org.usfirst.frc.team3082.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/* Drive forward to baseline */

public class AutoBaseline extends CommandGroup {

    public AutoBaseline() {
    	addParallel(new OpenGearMech());
    	addParallel(new DriveForwardTime(0.4, 5));
    	addSequential(new CloseGearMech());
    }
}
