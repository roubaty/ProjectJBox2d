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
		Vec2 transition;
		double size_x_frame = getWidth();
		double size_y_frame = getHeight();
		if(monde.getLeveldescriptor().getType()==LevelDescriptor.BY_COLOR){
			setBackground(monde.getLeveldescriptor().getColor());
		}
		Graphics2D g2d = (Graphics2D) g;
		if(typeOfView==AUTOFOLLOW) {
			float pos_x = objectToFollow.getBody().getPosition().x;
			float pos_y = objectToFollow.getBody().getPosition().y;
			double supposed_size_x = size_x_frame/zoom;
			double supposed_size_y = size_y_frame/zoom;
			float shift_x = (float) ((size_x_frame-supposed_size_x)/2);
			float shift_y = (float) ((size_y_frame-supposed_size_y)/2);
			System.out.println(zoom+" : "+size_x_frame+" : "+size_y_frame+" : "+supposed_size_x+" : "+supposed_size_y+" : "+shift_x+" : "+shift_y);
			cornerDownLeft = new Vec2((float) (pos_x-(size_x_frame/(2*zoom))),(float) (pos_y-(size_y_frame/(2*zoom))));
			cornerUpRight = new Vec2((float) (pos_x+(size_x_frame/(2*zoom))),(float) (pos_y+(size_y_frame/(2*zoom))));
			
			transition = cornerDownLeft.sub(new Vec2(-shift_x, -shift_y));
		}else{
			cornerUpRight = new Vec2((float)((cornerDownLeft.x+size_x_frame)/zoom), (float) ((cornerDownLeft.y+size_y_frame)/zoom));
			transition = cornerDownLeft;
		}
		Shape[] shapes = monde.getItemToPrint(cornerDownLeft, cornerUpRight, maxObjectOnScreen);
		AffineTransform af = AffineTransform.getTranslateInstance(0, getHeight());
		af.concatenate(AffineTransform.getScaleInstance(zoom,-zoom));
		af.translate(-transition.x, -transition.y);
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
