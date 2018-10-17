package org.usfirst.frc.team3328.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;

public class PowerUpDriveSystem implements DriveSystem {

	private VictorSP _left;
	private VictorSP _right;

	public PowerUpDriveSystem(VictorSP left, VictorSP right){
		this._left = left;
		this._right = right;

	}

	@Override
	public void setMotors(double left, double right) {
		_right.set(right);
		_left.set(left);
	}

	@Override
	public void moveForward(double speed) {
		_right.set(speed);
		_left.set(speed);
	}

	@Override
	public void moveBackward(double speed) {
		speed = -speed;
		_right.set(speed);
		_left.set(speed);
	}

	@Override
	public void turnLeft(double speed) {
		_right.set(speed);
		_left.set(-speed);
	}

	  
	@Override
	public void turnRight(double speed) {
		_right.set(-speed);
		_left.set(speed);
	}

	@Override
	public void curveForwardLeft(double speed, double radius, double restraint) {
		_right.set(speed);
		_left.set(1/restraint + radius);		
	}

	@Override
	public void curveForwardRight(double speed, double radius, double restraint) {
		_left.set(speed);
		_right.set(1/restraint - radius);
	}

	@Override
	public void curveBackwardLeft(double speed, double radius, double restraint) {
		_right.set(-speed);
		_left.set(-1/restraint - radius);
	}

	@Override
	public void curveBackwardRight(double speed, double radius, double restraint) {
		_left.set(-speed);
		_right.set(1/restraint + radius);
	}

	@Override
	public void stop() {
		_right.set(0);
		_left.set(0);
	}

}
