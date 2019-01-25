package org.usfirst.frc.team3328.robot.subsystems;

public interface DriveSystem {

	void moveForward(double speed);
	
	void moveBackward(double speed);
	
	void turnLeft(double speed);
	
	void turnRight(double speed);
	
	void curveForwardLeft(double speed, double radius, double restraint);
	
	void curveForwardRight(double speed, double radius, double restraint);
	
	void curveBackwardLeft(double speed, double radius, double restriant);
	
	void curveBackwardRight(double speed, double radius, double restraint);
	
	void stop();

	void setMotors(double left, double right);
}
