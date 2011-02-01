package sensor;

public abstract class Sensor {
	private boolean isSystem=false;
	public abstract void checkSenor();
	public void setSystem(boolean isSystem) {
		this.isSystem = isSystem;
	}
	public boolean isSystem() {
		return isSystem;
	}
}
