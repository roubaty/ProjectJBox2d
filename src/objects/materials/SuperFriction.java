package objects.materials;

public class SuperFriction extends Material {
	public SuperFriction() {
		super.setFriction(0.9f);
		super.setDensity(0.3f);
		super.setRestitution(0f);
	}
}
