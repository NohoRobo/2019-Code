package org.usfirst.frc.team3328.robot.utilities;
import com.ctre.phoenix.sensors.PigeonIMU;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;


public class PigeonGyroPIDInput extends PigeonIMU implements PIDSource {
	PIDSourceType pidSourceType;
	
	public PigeonGyroPIDInput(int port, PIDSourceType pidSourceType) {
		super(port);
		this.pidSourceType = pidSourceType;
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSourceType) {
		this.pidSourceType = pidSourceType;		
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return this.pidSourceType;
	}

	@Override
	public double pidGet() {
		double[] xyz = new double[3];
		super.getYawPitchRoll(xyz);
		return xyz[0];
	}
	
	public double getYaw() {
		return this.pidGet();
	}
	
}
