package org.usfirst.frc.team3328.robot.utilities;

public class DrivePID {
	//desired values
	double desiredDistance;
	double desiredAngle;
	
	double KP;
	double KI;
	double KD;
	final double KG = 1.5;//calculate this value
	
	//intermediate values for PID
	double proportionalL;
	double proportionalR;
	double integralL;
	double integralR;
	double derivativeL;
	double derivativeR;
	
	//integral sum variable
	double integralSumL = 0;
	double integralSumR = 0;
	
	//error variables
	double errorAngle = 0;
	double errorDistance = 0;
	double errorL = 0;
	double errorR = 0;
	
	//combined error variables
	double errorLwithGyro = 0;
	double errorRwithGyro = 0;
	
	//values for calculating change in error
	double oldErrorLwithGyro = 0;
	double oldErrorRwithGyro = 0;	
	
	//time storage variables
	double timeChange;
	long lastTime;
	
	//{left drive value, right drive value}
	double [] driveValues = new double[2];
	
	public DrivePID(double P, double I, double D){
		KP = P;
		KI = I;
		KD = D;
	}
	
	public void setP(double newP){
		KP = newP;
	}
	
	public void setI(double newI){
		KI = newI;
	}
	
	public void setD(double newD){
		KD = newD;
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
	
	public double setErrorAngle(double gyroSensorValue) {
		errorAngle=desiredAngle-gyroSensorValue;
		return errorAngle;
	}
	
	public double setErrorDistance(double leftEncoderSensorValue, double rightEncoderSensorValue) {
		errorDistance = desiredDistance-(leftEncoderSensorValue+rightEncoderSensorValue)/2;
		return errorDistance;
	}
	
	public void setDesiredDistance(double desiredDistance) {
		this.desiredDistance = desiredDistance;
	}
	
	public void setDesiredAngle(double desiredAngle) {
		this.desiredAngle = desiredAngle;
	}
		
	public double [] getDriveValues(){
		//calculate chang in time since last call of this function
		long now = System.nanoTime();
		timeChange = (double)(now - lastTime);
		timeChange /= 1000000000;
				
//		System.out.println(error);
		
		//combine errorDistance and errorAngle to get errors for left and right drive sides
		errorLwithGyro=errorDistance-KG*errorAngle;
		errorRwithGyro=errorDistance+KG*errorAngle;
		
		//add error to integral sums
		integralSumL += errorLwithGyro*timeChange;
		integralSumR += errorRwithGyro*timeChange;
				
		//set proportional values
		proportionalL = KP*errorLwithGyro;
		proportionalR = KP*errorRwithGyro;
		
		//set integral values
		integralL = KI*integralSumL;
		integralR = KI*integralSumR;
		
		//calculate change in time
		derivativeL = KD*(oldErrorLwithGyro - errorL) / timeChange;
		derivativeR = KD*(oldErrorRwithGyro - errorR) / timeChange;
		
		//integral reset for small or large errors
		if(Math.abs(errorLwithGyro)<2) {
			integralSumL=0;
		}
		else if(Math.abs(errorLwithGyro)>500) {//change from 500
			integralSumL=0;
		}
		if(Math.abs(errorRwithGyro)<2) {
			integralSumR=0;
		}
		else if(Math.abs(errorLwithGyro)>500) {//change from 500
			integralSumL=0;
		}
		
//		System.out.println("Iout " + iOut);
		
		//record the time that this function was run
		lastTime = now;
		
		//set drive values
		driveValues[0] = proportionalL+integralL+derivativeL;
		driveValues[1] = proportionalR+integralR+derivativeR;
		return driveValues;
	}
}
 