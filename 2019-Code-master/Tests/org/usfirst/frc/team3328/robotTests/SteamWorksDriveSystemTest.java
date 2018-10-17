package org.usfirst.frc.team3328.robotTests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.usfirst.frc.team3328.robot.subsystems.PowerUpDriveSystem;
import org.usfirst.frc.team3328.robot.utilities.ADIS16448_IMU;
import org.usfirst.frc.team3328.robot.utilities.DriveEncoders;
import org.usfirst.frc.team3328.robot.utilities.DriveTalons;
import org.usfirst.frc.team3328.robot.utilities.PID;
import org.usfirst.frc.team3328.robot.utilities.Tracking;

import edu.wpi.first.wpilibj.Encoder;

public class SteamWorksDriveSystemTest {
	
	DriveEncoders encoders = new DriveEncoders(new Encoder(8,9), new Encoder(6,7));
	FakeController fakeCont1 = new FakeController();
	FakeController fakeCont2 = new FakeController();
	FakeTarget target = new FakeTarget();
	FakeSpeedController fl = new FakeSpeedController();
	FakeSpeedController fr = new FakeSpeedController();
	FakeSpeedController bl = new FakeSpeedController();
	FakeSpeedController br = new FakeSpeedController();
	DriveTalons talons = new DriveTalons(fl, fr, bl, br);
	ADIS16448_IMU imu = new ADIS16448_IMU();
	PID pid = new PID(0.0 ,0.0, 0.0);
	Tracking track = new Tracking(target, null, pid, pid);
	PowerUpDriveSystem drive = new PowerUpDriveSystem(encoders, talons, track, imu, pid);

	@Test
	public void controlledMove_yLargerThanX_rightMotorTurnsBackwards() {
		fakeCont1.setY(1);
		fakeCont1.setX(0);
		//drive.controlledMove();
		assertEquals(1.0, (fr.speed),  0);
	}
	
	@Test
	public void controlledMove_xLargerThanY_rightMotorTurnsForwards() {
		fakeCont1.setY(1);
		fakeCont1.setX(0);
		//drive.controlledMove();
		assertEquals(-1.0, (fr.speed),  0);
	}
	
	@Test
	public void restrain_LBumperIsPressed_restraintIncrementedBy1(){
		fakeCont1.setlBump(true);
		//drive.restrain();
		assertTrue(drive.restraint == 2);
	}
	
	@Test
	public void autoAngle_currentIsLessThanDesired_rightMotorTurnsForwards(){
		drive.autoAngle(-90, 0);
		assertTrue(fr.speed < 0 && br.speed < 0);
	}
	
	@Test
	public void autoAngle_currentIsGreaterThanDesired_rightMotorTurnsForwards(){
		drive.autoAngle(0, 90);
		assertTrue(fr.speed < 0 && br.speed < 0);
	}
	
	@Test
	public void autoAngle_currentIsEqualToDesired_noMovement(){
		drive.autoAngle(0, 0);
		assertTrue(fr.speed == 0 && fl.speed == 0);
	}
	
}
	
	// Arrange Act Assert
	