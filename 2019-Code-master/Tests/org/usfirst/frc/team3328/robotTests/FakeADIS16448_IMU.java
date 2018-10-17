package org.usfirst.frc.team3328.robotTests;

import org.usfirst.frc.team3328.robot.utilities.IMU;

public class FakeADIS16448_IMU implements IMU {

	private double angleZ;
	
	@Override
	public void calibrate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

	@Override
	public void free() {
		// TODO Auto-generated method stub

	}

	@Override
	public double getAngle() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getRate() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getAngleX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getAngleY() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void setAngleZ(double angle){
		angleZ = angle;
	}
	
	@Override
	public double getAngleZ() {
		// TODO Auto-generated method stub
		return angleZ;
	}

	@Override
	public double getRateX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getRateY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getRateZ() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getAccelX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getAccelY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getAccelZ() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getMagX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getMagY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getMagZ() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getPitch() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getRoll() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getYaw() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getLastSampleTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getBarometricPressure() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getTemperature() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getQuaternionW() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getQuaternionX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getQuaternionY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getQuaternionZ() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getCompAngleZ() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean getData(double data, int index, double deviation) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double rateOfChange() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double compFilter(double angle, double gyro, double acc, double dt) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void printAngle() {
		// TODO Auto-generated method stub

	}

	@Override
	public void printRate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void printAccel() {
		// TODO Auto-generated method stub

	}

	@Override
	public void printMag() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateTable() {
		// TODO Auto-generated method stub

	}

}
