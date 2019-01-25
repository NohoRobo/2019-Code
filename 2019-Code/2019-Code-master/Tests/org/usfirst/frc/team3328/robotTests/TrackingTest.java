package org.usfirst.frc.team3328.robotTests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.usfirst.frc.team3328.robot.utilities.PID;
import org.usfirst.frc.team3328.robot.utilities.Tracking;

import edu.wpi.first.wpilibj.Relay;

public class TrackingTest {
	
	boolean state;
	double store;
	FakeController con = new FakeController();
	FakeTarget target = new FakeTarget();
	PID pidAngle = new PID(0, 0, 0);
	PID pidMovement = new PID(0, 0, 0);
	Tracking track = new Tracking(target, new Relay(0), pidAngle, pidMovement);
	
	@Test
	public void updateTracking_pixelWithinDeadZone_returnsFalse(){
		target.setPixel(320);
		track.updateTracking(state);
		assertTrue(!track.getTracking(state));
	}
	
	@Test
	public void getTurn_pixelWithinDeadZone_turnSpeedSetToZero(){
		target.setPixel(320);
		assertTrue(track.getTurn() == 0);
	}
	
	@Test
	public void getTurn_twoDifferentErrorLengths_largerErrorCorrectsFaster(){
		target.setPixel(300);
		store = track.getTurn();
		target.setPixel(250);
		assertTrue(track.getTurn() > store);
	}
	
	@Test
	public void getTurn_inverseErrors_inverseCorrection(){
		target.setPixel(220);
		store = track.getTurn();
		target.setPixel(420);
		assertTrue(track.getTurn() + store == 0);
	}
	
	
	
}
