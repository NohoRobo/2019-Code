package org.usfirst.frc.team3328.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PWMVictorSPX;

public class PowerUpSheeder implements Sheeder {
	
	PWMVictorSPX ls;
	PWMVictorSPX rs;
	DigitalInput _limitswitch;
	private double shootSpeed = .3; //subject to change and must be less than 1
	private double feedSpeed = .5;
	
	public PowerUpSheeder (/*DigitalInput limitswitch,*/ PWMVictorSPX leftSheeder, PWMVictorSPX rightSheeder) {
//		this._limitswitch = limitswitch;	
		this.ls = leftSheeder;
		this.rs = rightSheeder;
	}
	
	@Override
	public void setTo(double _speed) {
		ls.set(_speed); 
		rs.set(-_speed);
	}
	
	@Override
	public void feed() {
		ls.set(feedSpeed); 
		rs.set(-feedSpeed);
	}
	
	@Override 
	public void hold() {
		ls.set(0.1);
		rs.set(-0.1);
	}
	
	@Override
	public void shoot() {
		ls.set(-shootSpeed); 
		rs.set(shootSpeed);
	}

	@Override
	public void stop() {
		ls.set(0); 
		rs.set(0);
	}
	
	@Override
	public void rotateBox() {
		ls.set(0.7);
		rs.set(0.2);
	}
}
