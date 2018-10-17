package org.usfirst.frc.team3328.robot.utilities;

public interface IMU {

	void calibrate();

	void reset();

	void free();

	double getAngle();

	double getRate();

	double getAngleX();

	double getAngleY();

	double getAngleZ();

	double getRateX();

	double getRateY();

	double getRateZ();

	double getAccelX();

	double getAccelY();

	double getAccelZ();

	double getMagX();

	double getMagY();

	double getMagZ();

	double getPitch();

	double getRoll();

	double getYaw();

	double getLastSampleTime();

	double getBarometricPressure();

	double getTemperature();

	double getQuaternionW();

	double getQuaternionX();

	double getQuaternionY();

	double getQuaternionZ();

	double getCompAngleZ();

	void init();

	boolean getData(double data, int index, double deviation);

	double rateOfChange();

	double compFilter(double angle, double gyro, double acc, double dt);

	void printAngle();
	/* (non-Javadoc)
	* @see org.usfirst.frc.team3328.robot.IMU#printRate()
	*/

	void printRate();
	/* (non-Javadoc)
	* @see org.usfirst.frc.team3328.robot.IMU#printAccel()
	*/

	void printAccel();
	/* (non-Javadoc)
	* @see org.usfirst.frc.team3328.robot.IMU#printMag()
	*/

	void printMag();

	void updateTable();

}