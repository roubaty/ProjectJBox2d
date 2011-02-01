package level;

import ihm.GraphicsView;

import java.util.ArrayList;
import java.util.List;
import java.util.Hashtable;
import java.util.Map;

import objects.ObjectAdded;
import objects.ObjectDescription;
import objects.types.Magnet;
import objects.types.Springboard;
import objects.visual.Box;
import objects.visual.Circle;
import objects.visual.ShapeDes;

import org.jbox2d.collision.AABB;
import org.jbox2d.collision.Shape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;
import org.w3c.dom.ls.LSInput;

import actions.ActionCollisionSpringBoard;
import actions.ActionDistanceMagnet;

import sensor.Sensor;
import sensor.SensorCollision;
import sensor.SensorCollisionSystem;
import sensor.SensorDistance;

public class Level extends Thread {
	private World world;
	private Map<String, GraphicsView> listView;
	private Map<String, ObjectAdded> listObjet;
	private Map<String, Sensor> listSensor;
	private LevelDescriptor leveldescriptor;
	private SensorCollisionSystem sensorCollisionSystem;
	public LevelDescriptor getLeveldescriptor() {
		return leveldescriptor;
	}

	public void setLeveldescriptor(LevelDescriptor leveldescriptor) {
		this.leveldescriptor = leveldescriptor;
	}

	public Level(LevelDescriptor ld) {
		this.leveldescriptor = ld;
		world = new World(ld.getWorldAabb(), ld.getGravity(), ld.isDoSleep());
		listView = new Hashtable<String, GraphicsView>();
		listObjet = new Hashtable<String, ObjectAdded>();
		listSensor = new Hashtable<String, Sensor>();
		sensorCollisionSystem = new SensorCollisionSystem();
		world.setContactListener(sensorCollisionSystem);
	}

	public void nextStep() {
		world.step(leveldescriptor.getFrameTime(), leveldescriptor.getInternalIteration());
		for (GraphicsView i : listView.values()) {
			i.repaint();
		}
		for (ObjectAdded i : listObjet.values()) {
			i.move();
		}
		for (Sensor i : listSensor.values()) {
			i.checkSenor();
		}
	}

	public boolean addAGraphicView(String name, GraphicsView view) {
		if (!listView.containsKey(name)) {
			listView.put(name, view);
			return true;
		} else {
			return false;
		}
	}

	public boolean removeAGraphicView(String name) {
		if (listView.containsKey(name)) {
			listView.remove(name);
			return true;
		} else {
			return false;
		}
		
	}

	public ObjectAdded addObject(String name, ObjectDescription objectSrc,
			float position_x, float position_y, boolean isStatic) {
		if(!listObjet.containsKey(name)){
			ObjectAdded object = objectSrc.cloneObject();
			ShapeDes shape = object.getShape();
			if ((shape instanceof Box)) {
				position_x+=objectSrc.getSize_x();
				position_y+=objectSrc.getSize_y();
			}
			object.getBodyDef().position.set(position_x, position_y);
			Body body = world.createBody(object.getBodyDef());
			if (shape instanceof Circle) {
				body.createShape(object.getCircleDef());
			} else {
				body.createShape(object.getPolyDef());
			}
			if (!isStatic) {
				body.setMassFromShapes();
			}
			object.setStatic(isStatic);
			object.setBody(body);
			
			if(objectSrc instanceof Springboard){
				object.setSpringboard(true);
				List<ObjectAdded> from = new ArrayList<ObjectAdded>();
				from.add(object);
				List<ObjectAdded> to = new ArrayList<ObjectAdded>();
				to.addAll(listObjet.values());
				Sensor s =  new SensorCollision(from, to, new ActionCollisionSpringBoard(((Springboard)objectSrc).getRestitution()));
				s.setSystem(true);
				addSenor("System_sensor_springboard_"+name,s);
			}
			if(objectSrc instanceof Magnet){
				List<ObjectAdded> from = new ArrayList<ObjectAdded>();
				from.add(object);
				List<ObjectAdded> to = new ArrayList<ObjectAdded>();
				to.addAll(listObjet.values());
				float force = ((Magnet)objectSrc).getMagnetism();
				Sensor s =  new SensorDistance(SensorDistance.SMALLERTHAN, force, from, to, new ActionDistanceMagnet(force));
				s.setSystem(true);
				addSenor("System_sensor_magnet_"+name,s);
			}
			
			object.getBody().m_userData=object;
			for (Sensor i : listSensor.values()) {
				if(i.isSystem() && i instanceof SensorCollision){
					((SensorCollision)i).addElementsInTo(object);
				}
				if(i.isSystem() && i instanceof SensorDistance){
					((SensorDistance)i).addElementsInTo(object);
				}
			}
			listObjet.put(name, object);
			return object;
		}else{
			return null;
		}
	}

	public boolean removeObject(String name) {
		if(!listObjet.containsKey(name))
			return false;
		listObjet.remove(name);
		return true;
	}

	public boolean addSenor(String name, Sensor sensor) {
		if(listSensor.containsKey(name))
			return false;
		if(sensor instanceof SensorCollision)
			sensorCollisionSystem.addSensor((SensorCollision)sensor);
		listSensor.put(name, sensor);
		return true;
	}

	public boolean removeSenor(String name) {
		if(!listSensor.containsKey(name))
			return false;
		listSensor.remove(name);
		return true;
	}

	public Shape[] getItemToPrint(Vec2 cornerDownLeft, Vec2 cornerUpRight,
			int maxShapeOnScreen) {
		Shape[] shapes = world.query(new AABB(cornerDownLeft, cornerUpRight),
				maxShapeOnScreen);
		return shapes;
	}

	@Override
	public void run() {
		while (true) {
			nextStep();
			try {
				// TODO review time of sleep
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
