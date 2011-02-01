package objects.visual;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;



public class Circle extends ShapeDes {
	private Ellipse2D circle;
	/**
	 * Constructor
	 * @param r radius
	 */
	public Circle(float r, Visual visual) {
		super(visual);
		circle = new Ellipse2D.Float(-r,-r,r*2,r*2);
	}
	/**
	 * Draw the shape
	 * @param body
	 * @param g
	 */
	public void drawShape(Body body, Graphics2D g) {
		Color entreeColor = g.getColor();
		Vec2 pos = body.getPosition();
		AffineTransform af = AffineTransform.getTranslateInstance(pos.x, pos.y);
		Shape sp = af.createTransformedShape(circle);
		g.setColor(getVisual().getFillColor());
		g.fill(sp);
		g.setColor(getVisual().getBorderColor());
		g.draw(sp);
		g.setColor(entreeColor);
	}
}
