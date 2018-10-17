package org.usfirst.frc.team3328.robot.utilities;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

public class PowerUpXbox implements Controller {
	
	XboxController xbox;
	Joystick stick;

	boolean[] button = new boolean[11];
	
	public enum Buttons {A(1), B(2), X(3), Y(4), LBUMP(5), RBUMP(6),
						 BACK(7), START(8), RIGHTSTICK(10); 
		public int value;
		Buttons(int value) {
			this.value = value;
		}
	}
	
	public PowerUpXbox(int channel){
		xbox = new XboxController(channel);
	}

	@Override
	public double getX() {
		return xbox.getRawAxis(0);
	}

	@Override
	public double getLeftY() {
		return xbox.getRawAxis(1);
	}

	@Override
	public double getRightY() {
		return xbox.getRawAxis(5);
	}
		
	@Override
	public double getRightTrigger() {
		return xbox.getRawAxis(3);
	}

	@Override
	public double getLeftTrigger() {
		return xbox.getRawAxis(2);
	}

	@Override
	public boolean getButtonRelease(Buttons but) {
		int num = but.value;
		if (xbox.getRawButton(num) &&  !button[num]){
			button[num] = xbox.getRawButton(num);
			return true;
		}
		button[num] = xbox.getRawButton(num);
		return false;
	}

	@Override
	public boolean getButtonPress(Buttons but) {
		int num = but.value;
		return xbox.getRawButton(num);
	}
	
	public double getPad(){
		return xbox.getPOV();
	}

}
