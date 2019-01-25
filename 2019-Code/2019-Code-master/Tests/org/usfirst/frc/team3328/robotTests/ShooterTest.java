package org.usfirst.frc.team3328.robotTests;

//import org.usfirst.frc.team3328.robot.utilities.ShooterTalons;

import edu.wpi.first.wpilibj.Encoder;

public class ShooterTest {
	
	Encoder encoder = new Encoder(8,9);
	FakeController con = new FakeController();
	FakeSpeedController first = new FakeSpeedController();
	FakeSpeedController second = new FakeSpeedController();
//	ShooterTalons talons = new ShooterTalons(first, second);
	//SteamWorksShooter shooter = new SteamWorksShooter(encoder, talons, con);
	
}
