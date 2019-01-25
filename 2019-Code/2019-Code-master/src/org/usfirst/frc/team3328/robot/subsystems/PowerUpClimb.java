package org.usfirst.frc.team3328.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;

public class PowerUpClimb implements Climb {
	
	Spark _leftWinch;
	Spark _rightWinch;
	
	double restraint = 1;
	
	public PowerUpClimb(Spark leftWinch, Spark rightWinch){
		this._leftWinch = leftWinch;
		this._rightWinch = rightWinch;
	}

	@Override
	public void winch(double power) {
		_leftWinch.set(power / restraint);
		_rightWinch.set(-power / restraint);
	}

}
