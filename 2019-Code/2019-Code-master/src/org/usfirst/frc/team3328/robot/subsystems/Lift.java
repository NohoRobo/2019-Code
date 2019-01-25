package org.usfirst.frc.team3328.robot.subsystems;

import edu.wpi.first.wpilibj.Timer;

public interface Lift {

	void controlledMove(double power);

	int getEncoderValue();

	void init();

	void autoMoveTo(int position);

	int getScaleHigh();

	int getGround();

	int getSwitch();

	int getScaleLow();

	int getScaleMid();

	void resetLimitIfAtBottom();

	int getExchangeFeed();

	int getExchangeShoot();

	void calibrate();

	void unfold();
	
}
