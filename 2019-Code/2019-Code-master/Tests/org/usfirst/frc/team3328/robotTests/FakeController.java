package org.usfirst.frc.team3328.robotTests;

import java.util.HashMap;
import java.util.Map;

import org.usfirst.frc.team3328.robot.utilities.Controller;
import org.usfirst.frc.team3328.robot.utilities.SteamWorksXbox.Buttons;

public class FakeController implements Controller {

	double xSpeed;
	double ySpeed;
	int num;
	boolean a, b, x, y, rBump, lBump;
	Map<Buttons, Boolean> buttons = new HashMap<Buttons, Boolean>(){{
		put(Buttons.A, a);
		put(Buttons.B, b);
		put(Buttons.X, x);
		put(Buttons.Y, y);
		put(Buttons.RBUMP, rBump);
		put(Buttons.LBUMP, lBump);
	}};
	
	
	@Override
	public double getX() {
		// TODO Auto-generated method stub
		return xSpeed;
	}

	@Override
	public double getLeftY() {
		// TODO Auto-generated method stub
		return ySpeed;
	}
	
	public void setX(double speed){
		xSpeed = speed;
	}
	
	public void setY(double speed){
		ySpeed = speed;
	}
	
	public void setlBump(boolean state){
		lBump = state;
	}
	
	@Override
	public boolean getButtonRelease(Buttons but) {
		num = but.value;
		return buttons.get(but);
	}

	@Override
	public boolean getButtonPress(Buttons but) {
		num = but.value;
		// TODO Auto-generated method stub
		return false;
	}

	public double getPad() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getRightTrigger() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getLeftTrigger() {
		// TODO Auto-generated method stub
		return 0;
	}

}
