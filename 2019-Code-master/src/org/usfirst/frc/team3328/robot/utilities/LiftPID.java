package org.usfirst.frc.team3328.robot.utilities;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;

public class LiftPID extends Thread{
	//desired values
	public volatile double desiredValue;
	Encoder encoder;
	DigitalInput limitSwitch;
	TalonSRX talon;
	double KP;
	double KI;
	double KD;
		
	//intermediate values for PID
	double proportional;
	double integral;
	double derivative;
	
	double integralSum = 0;
	
	//error variables
	double error = 0;
	double errorOld = 0;
	
	//time storage variables
	double timeChange;
	long lastTime = System.nanoTime();
	
	//{left drive value, right drive value}
	double motorValue;
	
	public LiftPID(double KP, double KI, double KD, Encoder encoder, DigitalInput limitSwitch, TalonSRX talon) {
		this.KP = KP;
		this.KI = KI;
		this.KD = KD;
		this.encoder = encoder;
		this.limitSwitch = limitSwitch;
		this.talon = talon;
	}
	
	public void run() {
		while(true) {
//			talon.set(ControlMode.PercentOutput, getMotorValue());
			talon.set(ControlMode.PercentOutput, 0);

			Logger.log("lift encoder value ", encoder.get(), LogLevel.debug);
		}
	}
	
	public double getMotorValue(){
		//calculate change in time since last call of this function
		if(limitSwitch.get()) {
			encoder.reset();
		}
		error = desiredValue - encoder.get();
		long now = System.nanoTime();
		timeChange = (double)(now - lastTime);
		timeChange /= 1000000000;
				
//		System.out.println(error);
		
		
		//add error to integral sums
		integralSum += error*timeChange;
				
		//set proportional values
		proportional = KP*error;
		
		//set integral values
		integral = KI*integralSum;
		
		//calculate change in time
		derivative = timeChange == 0 ?  KD*(errorOld - error) / timeChange : 0;
		

		//integral reset for small or large errors
		if(Math.abs(error)<2) {
			integralSum=0;
		}
		else if(Math.abs(error)>500) {//change from 500
			integralSum=0;
		}
				
//		System.out.println("Iout " + iOut);
		
		//record the time that this function was run
		lastTime = now;
		
		//set drive values
		motorValue = proportional+integral+derivative;
		return motorValue;
	}
}
 