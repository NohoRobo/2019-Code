package org.usfirst.frc.team3328.robot.utilities;

public class PID {
	
	double pOut;
	double iOut;
	double dOut;
	double KP;
	double KI;
	double KD;
	double error;
	double deltaError;
	double prevError;
	double integralError = 0;
	double correction;
	double timeChange;
	long lastTime;
	boolean firstTime = true;
	
	public PID(double P, double I, double D){
		KP = P;
		KI = I;
		KD = D;
	}
	
	public void adjustP(double newP){
		KP += newP;
	}
	
	public void adjustI(double newI){
		KI += newI;
	}
	
	public void adjustD(double newD){
		KD += newD;
	}
	
	public double getP(){
		return KP;
	}
	
	public double getI(){
		return KI;
	}
	
	public double getD(){
		return KD;
	}
	
	public void setError(double error){
		this.error = error;
		if (firstTime){
			prevError = error;
			firstTime = false;
		}
	}
	
	public void reset() {
		deltaError = 0;
		integralError = 0;
		firstTime = true;
	}
	
	public double getCorrection(){
		long now = System.nanoTime();
		timeChange = (double)(now - lastTime);
		timeChange /= 1000000000;
		
		deltaError = (prevError - error) / timeChange;
		
		integralError += (error * .1);
//		System.out.println(error);
		if(Math.abs(error)==0) {
			iOut=0;
		}
		
		pOut = error * KP;
		iOut = integralError * KI;
		dOut = deltaError * KD;
		
//		System.out.println("Iout " + iOut);
		
		correction = pOut + iOut - dOut;
		correction = Math.signum(correction) * Math.min(Math.abs(correction), .3);
		System.out.println(correction);
		
		lastTime = now;
		prevError = error;
		
		return correction;
	}
}
 