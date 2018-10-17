package org.usfirst.frc.team3328.robot.utilities;

import org.usfirst.frc.team3328.robot.utilities.PowerUpXbox.Buttons;

public interface Controller {

	//returns the x axis value for the joystick
	//returns the right trigger minus the left trigger for the xbox to allow for forwards and reverse
	double getX();

	//returns the y axis for the joystick
	//returns the x axis for the xbox with a deadband of .1
	double getLeftY();
	
	double getRightTrigger();
	
	double getLeftTrigger();
	
	//returns true when the button with the index "num" has been released
	boolean getButtonRelease(Buttons but);

	boolean getButtonPress(Buttons but);

	double getRightY();
}