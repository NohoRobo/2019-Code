package org.usfirst.frc.team3328.robot;

import org.usfirst.frc.team3328.robot.subsystems.Lift;
import org.usfirst.frc.team3328.robot.subsystems.Climb;
import org.usfirst.frc.team3328.robot.subsystems.DriveSystem;
import org.usfirst.frc.team3328.robot.subsystems.PowerUpLift;
import org.usfirst.frc.team3328.robot.subsystems.Sheeder;
import org.usfirst.frc.team3328.robot.utilities.LogLevel;
import org.usfirst.frc.team3328.robot.utilities.Logger;
import org.usfirst.frc.team3328.robot.utilities.Controller;
import org.usfirst.frc.team3328.robot.utilities.PowerUpXbox.Buttons;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ControllerLogic {
	
	DriveSystem _driveSystem;
	Sheeder _sheeder;
	Lift _lifter;
	Climb _climb;
	
	Controller _driveCont;
	Controller _utilCont;
	TalonSRX _talon;
	Encoder _leftTeleopEncoder;
	Encoder _rightTeleopEncoder;
	PIDController _leftTeleopPID;
	PIDController _rightTeleopPID;
	DigitalInput _liftSwitch;
	DigitalInput _sheedSwitch;
	
	Timer _sheederTimer = new Timer();
	Timer driveMacroTimer = new Timer();

	final double deadzone = .15;
	double restraint = 1;
	int maxHeight = 36400;
	int minHeight = 0;
	
	boolean macroControl = false;
	boolean manualFeeder = false;
	boolean highSpeed = true;
	boolean climbEnabled = false;

	public ControllerLogic(Encoder leftTeleopEncoder, Encoder rightTeleopEncoder, PIDController leftTeleopPID,
						   PIDController rightTeleopPID, DriveSystem driveSystem,
						   Sheeder sheeder, Lift lifter, Climb climb, Controller driveCont, Controller utilCont) {
		this._leftTeleopEncoder = leftTeleopEncoder;
		this._rightTeleopEncoder = rightTeleopEncoder;
		this._leftTeleopPID = leftTeleopPID;
		this._rightTeleopPID = rightTeleopPID;
		_leftTeleopPID.disable();
		_rightTeleopPID.disable();
		
		this._driveSystem = driveSystem;
		this._lifter = lifter;
		this._sheeder = sheeder;
		this._climb = climb;
		
		this._driveCont = driveCont;
		this._utilCont = utilCont;
		_sheederTimer.stop();
	}

	public void run() {
		
		//drive
		if(!macroControl) {
			_driveSystem.setMotors(speedOf(
				_driveCont.getRightTrigger()-_driveCont.getLeftTrigger()+
				(Math.abs(_driveCont.getX())>0.2?_driveCont.getX():0)),speedOf(//left
						_driveCont.getRightTrigger()-_driveCont.getLeftTrigger()-
						(Math.abs(_driveCont.getX())>0.2?_driveCont.getX():0)));//right
		}
		//changeSpeedRestraint
		if(_driveCont.getButtonRelease(Buttons.RBUMP)) {
			setHighSpeed();
			highSpeed = true;
		} else if(_driveCont.getButtonRelease(Buttons.LBUMP)) {
			setLowSpeed();
			highSpeed = false;
		}
		
		//sheeder - choo choo
	/*	if (_utilCont.getButtonRelease(Buttons.BACK)) {
			_sheederTimer.reset();
			_sheederTimer.start();
		}
		if(_sheederTimer.get() >= 0.3) { 
			_sheeder.stop();
			_sheederTimer.stop();
			_sheederTimer.reset();
		} else if(_sheederTimer.get() >= 0.09) {
			_sheeder.stop();
			_sheeder.feed();
		} else if(_sheederTimer.get() > 0.0) {
			_sheeder.stop();
			_sheeder.shoot();
		}
	*/
		
		//sheeder - rotate box
		if (_utilCont.getButtonRelease(Buttons.BACK)) {
			_sheederTimer.reset();
			_sheederTimer.start();
		}
		if(_sheederTimer.get() >= 0.2) {
			_sheeder.stop();
			_sheederTimer.stop();
			_sheederTimer.reset();
		} else if (_sheederTimer.get() > 0.0){
			_sheeder.rotateBox();
		}
		
		//sheeder
		if (_utilCont.getLeftTrigger() > .2)
			_sheeder.feed();
		else if (_utilCont.getRightTrigger() > .2)
			_sheeder.shoot();
		else if(_sheederTimer.get()==0)
			_sheeder.hold();
		 		
		//lift
		if(Math.abs(_utilCont.getLeftY()) > 0.2) {
			manualFeeder = true;
			if(isMaxHeight() && _utilCont.getLeftY()<0) {
				_lifter.controlledMove(0);
			}
			else if(isMinHeight() && _utilCont.getLeftY()>0) {
				_lifter.controlledMove(0);
			} 
			else {
				_lifter.controlledMove(_utilCont.getLeftY());
			}
		} 
		else if(manualFeeder = true) {
			if(_lifter.getEncoderValue()< 500) {
				SmartDashboard.putBoolean("iftHoldOn", true);
				_lifter.controlledMove(0.2);
			}
			else {
				SmartDashboard.putBoolean("iftHoldOn", false);
				_lifter.controlledMove(0);
			}
		}
/*		if(_utilCont.getButtonPress(Buttons.X)) {
			_lifter.autoMoveTo(_lifter.getSwitch()); 
			manualFeeder = false;
		} else if(_utilCont.getButtonPress(Buttons.A)) {
			_lifter.autoMoveTo(_lifter.getExchangeFeed()); 
			manualFeeder = false;
		} else if(_utilCont.getButtonPress(Buttons.B)) {
			_lifter.autoMoveTo(_lifter.getScaleMid()); 
			manualFeeder = false;
		} else if(_utilCont.getButtonPress(Buttons.Y)) { 
			_lifter.autoMoveTo(_lifter.getScaleHigh()); 
			manualFeeder = false;
		} else if(_utilCont.getButtonPress(Buttons.START)) {
			_lifter.autoMoveTo(_lifter.getGround()); 
			manualFeeder = false;
		} else if(_utilCont.getButtonPress(Buttons.RBUMP)) {
			_lifter.autoMoveTo(_lifter.getExchangeShoot());
			manualFeeder = false;
		}
*/		
		//button for exchange?
				
		//climb - testing
		if(_utilCont.getButtonRelease(Buttons.RIGHTSTICK)) {
			climbEnabled = true;
		}
		if( _utilCont.getRightY() < -0.2 && climbEnabled) {
			_climb.winch(_utilCont.getRightY());
		} else if (_utilCont.getRightY() > 0.2 && _utilCont.getButtonPress(Buttons.LBUMP) && _utilCont.getButtonPress(Buttons.RBUMP)){
			_climb.winch(_utilCont.getRightY());
		} else {
			_climb.winch(0);
		} 
		
//		SmartDashboard.putNumber("choo choo timer", _sheederTimer.get());
//		SmartDashboard.putNumber("X Joystick Value", _driveCont.getX());
//		SmartDashboard.putNumber("Y Joystick Value", _driveCont.getLeftY());
//		SmartDashboard.putNumber("Right Trigger Value", _driveCont.getRightTrigger());
//		SmartDashboard.putNumber("Left Trigger Value", _driveCont.getLeftTrigger());
		SmartDashboard.putBoolean("High Speed Mode", highSpeed);	
		SmartDashboard.putNumber("Lift Encoder Value-teleop", _lifter.getEncoderValue());
		}

	private double speedOf(double speed) {
		return (speed * speed * Math.signum(speed)) / restraint;
	}

	private void setHighSpeed() {
		restraint = 1;
	}

	private void setLowSpeed() {
		restraint = 3;
	}
	
	private boolean isMaxHeight() {
		return _lifter.getEncoderValue() >= maxHeight;
	}
	
	private boolean isMinHeight() {
		return _lifter.getEncoderValue() <= minHeight;
	}
}


