package objects.materials;

public class Lead extends Material {
	public Lead(){
		super.setFriction(0.2f);
		super.setDensity(0.3f);
		super.setRestitution(0f);
	}
}
