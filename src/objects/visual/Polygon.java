package objects.visual;



import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;


/**
 * graphic Triangle
 * @author Roubaty
 *
 */
public class Polygon extends ShapeDes {
	private java.awt.Polygon polygon;
	/**
	 * Constructor
	 * @param vertices vertices of the triangle
	 */
	public Polygon(Vec2[] vertices, Visual visual) {
		super(visual);
		float x1 = vertices[0].x;
		float y1 = vertices[0].y;
		int size = vertices.length;
		int[] x = new int[size];
		int[] y = new int[size];
		for (int i = 0; i < size; i++) {
			x[i] = (int) (vertices[i].x - x1);
			y[i] = (int) (vertices[i].y - y1);
		}
		polygon = new java.awt.Polygon(x, y, size);
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
		af.concatenate(AffineTransform.getRotateInstance(body.getAngle()));
		Shape sp = af.createTransformedShape(polygon);
		g.setColor(getVisual().getFillColor());
		g.fill(sp);
		g.setColor(getVisual().getBorderColor());
		g.draw(sp);
		g.setColor(entreeColor);
	}

}
