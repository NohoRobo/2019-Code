package org.usfirst.frc.team3328.robot.subsystems;

import org.usfirst.frc.team3328.robot.utilities.LogLevel;
import org.usfirst.frc.team3328.robot.utilities.Logger;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PowerUpLift implements Lift {

	TalonSRX _talon;
	DigitalInput _limitSwitch;
	
	public double restraint = 1;
	int _talonTimeout = 10;
    int _talonLoopIdx = 0;
    int _talonSlotIdx = 0;
    
    public double _KP;
    public double _KI;
    public double _KD;
	
	public static final int SCALE_HIGH_POSITION = 35500; //80-78 in
	public static final int SCALE_MID_POSITION = 30500; 
	public static final int SCALE_LOW_POSITION = 25750;
	public static final int SWITCH_POSITION = 15120;
	public static final int EXCHANGE_FEED = 0; // 20 in
	public static final int EXCHANGE_SHOOT = 0; //2 in
	public static final int EXCHANGE_POSITION = 0;
	
	public PowerUpLift(double KP, double KI, double KD, 
					   TalonSRX talon, DigitalInput limitSwitch) {
		this._KP = KP;
		this._KI = KI;
		this._KD = KD;
		this._talon = talon;
		this._limitSwitch = limitSwitch;
	}
		
	@Override
	public void init() {
		_talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,_talonLoopIdx,_talonTimeout);
		_talon.setSensorPhase(false);
		_talon.setInverted(true);
		_talon.configNominalOutputForward(0,_talonTimeout);
		_talon.configNominalOutputReverse(0, _talonTimeout);
		_talon.configPeakOutputForward(1, _talonTimeout);
		_talon.configPeakOutputReverse(-1, _talonTimeout);
		_talon.configAllowableClosedloopError(_talonSlotIdx, 0, _talonTimeout);
		_talon.config_kF(_talonSlotIdx, 0.0, _talonTimeout);
		_talon.config_kP(_talonSlotIdx, _KP, _talonTimeout);
		_talon.config_kI(_talonSlotIdx, _KI, _talonTimeout);
		_talon.config_kD(_talonSlotIdx, _KD, _talonTimeout);
		_talon.setSelectedSensorPosition(0, _talonLoopIdx, _talonTimeout);
	}
	
	@Override 
	public void autoMoveTo(int position) {
		_talon.set(ControlMode.Position, position);
		SmartDashboard.putNumber("Lift Setpoint", _talon.getClosedLoopTarget(_talonSlotIdx));

	}
	
	@Override
	public int getScaleHigh() {
		return SCALE_HIGH_POSITION;
	}
	
	@Override
	public int getScaleMid() {
		return SCALE_MID_POSITION;
	}
	
	@Override
	public int getScaleLow() {
		return SCALE_LOW_POSITION;
	}
	
	@Override
	public int getExchangeFeed() {
		return EXCHANGE_POSITION;
	}
	
	@Override
	public int getExchangeShoot() {
		return EXCHANGE_POSITION;
	}
	
	@Override
	public int getSwitch() {
		return SWITCH_POSITION;
	}
	
	@Override
	public int getGround() {
		return EXCHANGE_POSITION;
	}
	
	@Override
	public void controlledMove(double power) {
		_talon.set(ControlMode.PercentOutput, -power / restraint);
	}
	
	@Override
	public void resetLimitIfAtBottom() {
		if(_limitSwitch.get()) {
			_talon.setSelectedSensorPosition(0, _talonLoopIdx, _talonTimeout);
		}
	}
	
	@Override
	public int getEncoderValue() {
		return _talon.getSelectedSensorPosition(_talonLoopIdx);
	}
	
	@Override
	public void calibrate() {
		_talon.set(ControlMode.Velocity,-1.0/7.0);
		Timer timer = new Timer();
		timer.reset();
		timer.start();
		while(!_limitSwitch.get() && timer.get()< 2) {;}
		_talon.set(ControlMode.Position, 0);
		_talon.setSelectedSensorPosition(0, _talonLoopIdx, _talonTimeout);
	}
	public void unfold() {
		_talon.set(ControlMode.Position, 4000);
		Timer timer = new Timer();
		timer.reset();
		timer.start();
		while(timer.get()< 2) {;}
//		this.calibrate();
	}
}
