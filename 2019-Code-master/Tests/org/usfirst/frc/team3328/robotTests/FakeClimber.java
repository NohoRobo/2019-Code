package org.usfirst.frc.team3328.robotTests;

import org.usfirst.frc.team3328.robot.subsystems.Climber;

public class FakeClimber implements Climber {

	@Override
	public void controlClimber(double xAxis) {
		// TODO Auto-generated method stub

	}
	
	public double setNum(double num){
		return num;
	}

}
