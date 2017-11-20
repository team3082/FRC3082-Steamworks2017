package org.usfirst.frc.team3082.robot.subsystems;

import org.usfirst.frc.team3082.robot.VisionDirection;
import org.usfirst.frc.team3082.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;

/* Very sketchy vision implementation. Still got 100% autonomous gear placement with it, though!
 * Uses the pixy camera (CMUcam5) with an Arduino as a mediator. The Arduino does the processing
 * to decide which direction the robot should move, and sends the data over using digital I/O.
 */

public class ArduinoPixy extends Subsystem {

	private DigitalInput left;
	private DigitalInput straight;
	private DigitalInput right;	
	private DigitalInput status;
	private DigitalOutput enable;
	
    public ArduinoPixy() {
    	left = new DigitalInput(RobotMap.AP_LEFT);
    	straight = new DigitalInput(RobotMap.AP_CENTER);
    	right = new DigitalInput(RobotMap.AP_RIGHT);
    	status = new DigitalInput(RobotMap.AP_STATUS);
    	enable = new DigitalOutput(RobotMap.AP_ENABLE);
    }

    public void initDefaultCommand() {
    }
    
    public void enable() {
    	enable.set(true);
    }
    
    public void disable() {
    	enable.set(false);
    }
    
    public boolean getStatus() {
    	return status.get();
    }
    
    public VisionDirection getDirection() {
    	enable();
    	VisionDirection direction;
    	int digitalReads = getDigitalReads();
    	
    	// See getDigitalReads() for an explanation of how this works.
    	switch (digitalReads) {
    	case 0b1001: // Status and Left pins are high 
    		direction = VisionDirection.left;
    		break;
    	case 0b1011: // Status, Straight, and Left pins are high
    		direction = VisionDirection.slightLeft;
    		break;
    	case 0b1010: // Status and Straight pins are high
    		direction = VisionDirection.straight;
    		break; 
    	case 0b1110: // Status, Straight, and Right pins are high 
    		direction = VisionDirection.slightRight;
    		break;
    	case 0b1100: // Status and Right pins are high 
    		direction = VisionDirection.right;
    		break;
    	default: // Anything else, for example status pin is low (Arduino Error), wire is unplugged, etc.
    		direction = VisionDirection.straight;
    		break;
    	}
    	
    	return direction;
    }

    // Private Helper Methods 
    
    public int getDigitalReads() {
    	int digitalReads = 0b0000; // Binary 0 with a length of 4 bits.
    	 
    	/* Bit shifting.
    	 * If you shift the bit 1 to the left by two bits, notated as 1 << 2, you get 100 (in binary)
    	 * Setting bits: If you want to set the second bit (from the right) of 0b0000, you would write
    	 * 0b0000 | 0b0010. Each bit is "OR'd" with each corresponding bit in the other number. 
    	 * 0 OR 0 = 0; 0 OR 1 = 1; 1 OR 0 = 1; 1 OR 1 = 1.
    	 * 
    	 * So essentially, the digitalReads integer encodes the statuses of the four integers, with the four
    	 * bits from left to right being Status, Right, Straight, Left.
    	 */
    	
    	digitalReads = digitalReads | digitalReadInt(left); // Set the first bit from the right if the Left pin is high.
    	digitalReads = digitalReads | digitalReadInt(straight) << 1; // Set the second bit from the right if the Straight pin is high.
    	digitalReads = digitalReads | digitalReadInt(right) << 2; // Set the third bit from the right if the Right pin is high.
    	digitalReads = digitalReads | digitalReadInt(status) << 3; // Set the fourth bit from the right if the Status pin is high.
    
    	return digitalReads;
    }
    
    private int digitalReadInt(DigitalInput port) {
    	if (port.get()) {  // Cast boolean status of DigitalInput to an integer (0 or 1).
    		return 1; 
    	}
    	else {
    		return 0;
    	}
    }
}