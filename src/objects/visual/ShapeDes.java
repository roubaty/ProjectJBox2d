package objects.visual;

import java.awt.Graphics2D;
import org.jbox2d.dynamics.Body;

public abstract class ShapeDes {
	Visual visual;
	public Visual getVisual() {
		return visual;
	}
	public void setVisual(Visual visual) {
		this.visual = visual;
	}
	public ShapeDes(Visual visual){
		this.visual=visual;
	}
	public abstract void drawShape(Body body, Graphics2D g);
}
