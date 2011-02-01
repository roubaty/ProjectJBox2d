package ihm;


import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;


import javax.swing.JPanel;
import level.Level;
import level.LevelDescriptor;
import objects.ObjectAdded;
import objects.ObjectDescription;
import objects.visual.ShapeDes;

import org.jbox2d.collision.Shape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;



public class GraphicsView extends JPanel {
	private static final long serialVersionUID = 1L;
	private int typeOfView;
	private static final int FIX=0;
	private static final int AUTOFOLLOW=1;
	private Vec2 cornerDownLeft;
	private ObjectAdded objectToFollow;
	private Level monde;
	private int maxObjectOnScreen;
	private double zoom;
	public GraphicsView(Level monde, Vec2 cornerDownLeft, double zoom){
		this.maxObjectOnScreen = 10000;
		this.monde = monde;
		this.cornerDownLeft=cornerDownLeft;
		typeOfView=FIX;
		this.zoom=zoom;
	}
	public GraphicsView(Level monde, ObjectAdded objectToFollow, double zoom){
		this.maxObjectOnScreen = 10000;
		this.monde = monde;
		this.objectToFollow=objectToFollow;
		typeOfView=AUTOFOLLOW;
		this.zoom=zoom;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Vec2 cornerUpRight;
		double size_x_frame = getWidth();
		double size_y_frame = getHeight();
		if(monde.getLeveldescriptor().getType()==LevelDescriptor.BY_COLOR){
			setBackground(monde.getLeveldescriptor().getColor());
		}
		Graphics2D g2d = (Graphics2D) g;
//		double size_x_new_frame=0;
//		double size_y_new_frame=0;
		if(typeOfView==AUTOFOLLOW) {
			float pos_x = objectToFollow.getBody().getPosition().x;
			float pos_y = objectToFollow.getBody().getPosition().y;
			
//			double size_x_zoom = size_x_frame*zoom;
//			double size_y_zoom = size_y_frame*zoom;
//			double size_x_new_frame = (size_x_zoom-size_x_frame)/2;
//			double size_y_new_frame = (size_y_zoom-size_y_frame)/2;
			pos_x *= zoom;
			pos_y *= zoom;
			//System.out.println("x : "+pos_x+" y : "+pos_y);
			cornerDownLeft = new Vec2((float) (pos_x-(size_x_frame/2)),(float) (pos_y-(size_y_frame/2)));
			cornerUpRight = new Vec2((float) (pos_x+(size_x_frame/2)),(float) (pos_y+(size_y_frame/2)));
		}else{
			cornerUpRight = new Vec2((float)(cornerDownLeft.x+size_x_frame), (float)(cornerDownLeft.y+size_y_frame));
		}
		Shape[] shapes = monde.getItemToPrint(cornerDownLeft, cornerUpRight, maxObjectOnScreen);
//		AffineTransform af = AffineTransform.getTranslateInstance(-cornerDownLeft.x,getHeight()+cornerDownLeft.y);
//		af.concatenate(AffineTransform.getScaleInstance(zoom,-zoom));
		AffineTransform af = AffineTransform.getTranslateInstance(0, getHeight());
		af.concatenate(AffineTransform.getScaleInstance(zoom,-zoom));
		af.translate(-cornerDownLeft.x, -cornerDownLeft.y);
		g2d.setTransform(af);
		Body body;
		ShapeDes sp;
		ObjectDescription obj;
		for (Shape shape : shapes) {
			obj =  (ObjectDescription) shape.m_userData;
			body = shape.getBody(); 
			sp = obj.getShape();
			sp.drawShape(body, g2d);
		}
	}
	public void addKeyListener(KeyListener kl){
		this.addKeyListener(kl);
	}
	public void addMouseListener(MouseListener ml){
		this.addMouseListener(ml);
	}
}
