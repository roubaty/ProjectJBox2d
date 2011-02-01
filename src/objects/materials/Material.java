package objects.materials;

public class Material {
	private float friction;
	private float density;
	private float restitution;
	public float getFriction() {
		return friction;
	}

	public float getDensity() {
		return density;
	}

	public float getRestitution() {
		return restitution;
	}
	
	public void setFriction(float friction) {
		this.friction = friction;
	}

	public void setDensity(float density) {
		this.density = density;
	}

	public void setRestitution(float restitution) {
		this.restitution = restitution;
	}

}
