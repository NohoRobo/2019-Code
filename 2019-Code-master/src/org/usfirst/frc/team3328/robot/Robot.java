package org.usfirst.frc.team3328.robot;

import org.usfirst.frc.team3328.robot.subsystems.Climb;
import org.usfirst.frc.team3328.robot.subsystems.DriveSystem;
import org.usfirst.frc.team3328.robot.subsystems.Lift;
import org.usfirst.frc.team3328.robot.subsystems.PowerUpClimb;
import org.usfirst.frc.team3328.robot.subsystems.PowerUpDriveSystem;
import org.usfirst.frc.team3328.robot.subsystems.PowerUpLift;
import org.usfirst.frc.team3328.robot.subsystems.PowerUpSheeder;
import org.usfirst.frc.team3328.robot.subsystems.Sheeder;
import org.usfirst.frc.team3328.robot.utilities.PowerUpXbox;
import org.usfirst.frc.team3328.robot.utilities.PigeonGyroPIDInput;

import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.sensors.PigeonIMU;
import com.ctre.phoenix.sensors.PigeonIMU.CalibrationMode;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PIDSourceType;

public class Robot extends IterativeRobot {
	ControllerLogic logic;
	Auton auto;

	DriveSystem driveSystem;
	Lift lift;
	Sheeder sheeder;
	Climb climb;

	Encoder leftEncoder;
	Encoder rightEncoder;
	VictorSP left;
	VictorSP right;

	CameraServer stream;
	UsbCamera usbCam;

	PIDController leftPID;
	PIDController rightPID;
	PIDController rightTurningPID;
	PIDController leftTurningPID;
	
	PigeonGyroPIDInput gyro;
	
	boolean doneWithAuto = false;
	boolean firstTimeRunning = true;

	@Override
	public void robotInit() {
		stream = CameraServer.getInstance();
		usbCam = stream.startAutomaticCapture();
		
		rightEncoder = new Encoder(2,3);
		leftEncoder = new Encoder(0,1, true);		
		gyro = new PigeonGyroPIDInput(0, PIDSourceType.kDisplacement);//this is definitely wrong

		left = new VictorSP(0);
		right = new VictorSP(1);
		right.setInverted(true);

		double KP = 0;
		double KI = 0;
		double KD = 0;
		
		double turningKP = 0;
		double turningKI = 0;
		double turningKD = 0;

		leftPID = new PIDController(KP, KI, KD, //tuned on carpet: (-0.02, 0, -0.0001)
				leftEncoder, left);
		rightPID = new PIDController(KP, KI, KD, //tuned on carpet: (-0.02, 0, -0.0001)
				rightEncoder, right);
		leftTurningPID = new PIDController(turningKP, turningKI, turningKD,
				gyro, left);
		rightTurningPID = new PIDController(turningKP, turningKI, turningKD,
				gyro, right);
		
		driveSystem = new PowerUpDriveSystem(left, right);
		sheeder = new PowerUpSheeder(
				new PWMVictorSPX(4), 
				new PWMVictorSPX(5));
		lift = new PowerUpLift(
				0.07, 0, 0,
				new TalonSRX(3), 
				new DigitalInput(6));
		climb = new PowerUpClimb(
				new Spark(3),
				new Spark(2));
		
		logic = new ControllerLogic(
				leftEncoder,
				rightEncoder,
				leftPID,
				rightPID,
				driveSystem,
				sheeder, 
				lift, 
				climb,
				new PowerUpXbox(0),
				new PowerUpXbox(1));

		auto = new Auton(leftPID, rightPID, leftTurningPID, rightTurningPID, 
				leftEncoder, rightEncoder, gyro, lift, sheeder);

		lift.init();
		gyro.setFusedHeading(0, 10);
		gyro.enterCalibrationMode(CalibrationMode.BootTareGyroAccel, 10);
		gyro.enterCalibrationMode(CalibrationMode.Temperature, 10);
	}

	@Override
	public void autonomousInit() {
		auto.init();
	}

	@Override
	public void autonomousPeriodic() {
		if(!doneWithAuto) {
			auto.run();
			doneWithAuto = true;
		}
		System.out.println("Lift Encoder Value" + lift.getEncoderValue());
		SmartDashboard.putNumber("RightEncoder", rightEncoder.getDistance());
		SmartDashboard.putNumber("LeftEncoder", leftEncoder.getDistance());
	}  

	@Override
	public void teleopPeriodic() {
//		System.out.printf("RightDistance\t%.3f\t|\tLeftDistance\t%.3f\n", rightEncoder.getDistance(), leftEncoder.getDistance());
		if(firstTimeRunning) {
			leftPID.disable();
			rightPID.disable();
			leftTurningPID.disable();
			rightTurningPID.disable();
//			lift.calibrate();
//			lift.autoMoveTo(lift.getExchangeFeed());
			firstTimeRunning = false;
		}
		logic.run();
		SmartDashboard.putNumber("RightEncoder", rightEncoder.getDistance());
		SmartDashboard.putNumber("LeftEncoder", leftEncoder.getDistance());
		SmartDashboard.putNumber("Gyro Value", gyro.getYaw());
	}

	@Override
	public void testPeriodic() {
	}


}