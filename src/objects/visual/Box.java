package objects.visual;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;


public class Box extends ShapeDes {
	private Rectangle2D rect;
	/**
	 * Constructor
	 * @param w weight
	 * @param h height
	 * @param color color
	 */
	public Box(float w, float h, Visual visual) {
		super(visual);
		rect = new Rectangle2D.Float(-w,-h, 2 * w, 2 * h);
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
		Shape sp = af.createTransformedShape(rect);
		g.setColor(getVisual().getFillColor());
		g.fill(sp);
		g.setColor(getVisual().getBorderColor());
		g.draw(sp);
		g.setColor(entreeColor);
	}

}
