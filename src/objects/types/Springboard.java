package objects.types;

import objects.ObjectDescription;


public class Springboard  extends ObjectDescription{
	private float restitution;
	public Springboard(ObjectDescription object, float restitution) {
		super(object);
		this.setRestitution(restitution);
	}
	public void setRestitution(float restitution) {
		this.restitution = restitution;
	}
	public float getRestitution() {
		return restitution;
	}
}
