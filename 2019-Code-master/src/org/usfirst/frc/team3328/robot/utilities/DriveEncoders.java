package org.usfirst.frc.team3328.robot.utilities;

import edu.wpi.first.wpilibj.Encoder;

public class DriveEncoders {
	
	Encoder right;
	Encoder left;
	
	public DriveEncoders(Encoder right, Encoder left){
		this.right = right;
		this.left = left;
		this.right.setReverseDirection(false);
		this.left.setReverseDirection(true);
		this.right.setDistancePerPulse((6*Math.PI)/256);
		this.left.setDistancePerPulse((6*Math.PI)/256);
	}
	
	public void reset(){
		right.reset();
		left.reset();
	}
	
	public double getDistance(){
		//return (rightDistance() + leftDistance()) / 2;
		//System.out.printf("Right: %f || Left: %f\n", rightDistance(), leftDistance());
		return rightDistance();
	}
	
	public double getOffset(){
		return rightDistance() - leftDistance();
	}
	
	public double rightRate(){
		return right.getRate();
	}

	public double leftRate(){
		return left.getRate();
	}	
	
	public double rightDistance(){
		return right.getDistance();
	}
	
	public double leftDistance(){
		return left.getDistance();
	}
	
}
