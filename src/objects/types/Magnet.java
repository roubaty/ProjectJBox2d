package objects.types;

import objects.ObjectDescription;

public class Magnet  extends ObjectDescription{
	private float magnetism;
	public Magnet(ObjectDescription object, float magnetism) {
		super(object);
		this.setMagnetism(magnetism);
		
	}
	public void setMagnetism(float magnetism) {
		this.magnetism = magnetism;
	}
	public float getMagnetism() {
		return magnetism;
	}

}
