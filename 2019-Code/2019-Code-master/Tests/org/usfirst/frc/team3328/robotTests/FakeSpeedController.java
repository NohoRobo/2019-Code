package org.usfirst.frc.team3328.robotTests;

import edu.wpi.first.wpilibj.SpeedController;

public class FakeSpeedController implements SpeedController {
	
	public double speed;
	
	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub

	}

	@Override
	public double get() {
		// TODO Auto-generated method stub
		return speed;
	}

	@Override
	public void set(double velocity) {
		speed = velocity;
	}

	@Override
	public void setInverted(boolean isInverted) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean getInverted() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void disable() {
		// TODO Auto-generated method stub

	}

	@Override
	public void stopMotor() {
		// TODO Auto-generated method stub

	}

}
