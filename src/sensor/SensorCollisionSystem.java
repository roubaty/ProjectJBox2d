package sensor;

import java.util.ArrayList;
import java.util.List;

import objects.ObjectAdded;

import org.jbox2d.dynamics.ContactListener;
import org.jbox2d.dynamics.contacts.ContactPoint;
import org.jbox2d.dynamics.contacts.ContactResult;

import actions.ActionCollision;

public class SensorCollisionSystem  implements ContactListener{
	private List<SensorCollision> sensor = new ArrayList<SensorCollision>();
	@Override
	public void add(ContactPoint point) {
		callAction(point, ActionCollision.ADD);
	}

	@Override
	public void persist(ContactPoint point) {
		callAction(point, ActionCollision.PERSIST);
	}

	@Override
	public void remove(ContactPoint point) {
		callAction(point, ActionCollision.REMOVE);
	}
	
	private void callAction(ContactPoint point, int type){
		ObjectAdded object1 = (ObjectAdded) point.shape1.getBody().getUserData();
		ObjectAdded object2 = (ObjectAdded) point.shape2.getBody().getUserData();
		for (SensorCollision i : sensor) {
			i.callAction(object1, object2, point, type);
		}
	}
	
	@Override
	public void result(ContactResult point) {
		//Isn't use
	}

	public void setSensor(List<SensorCollision> sensor) {
		this.sensor = sensor;
	}

	public List<SensorCollision> getSensor() {
		return sensor;
	}
	public void addSensor(SensorCollision new_sensor){
		sensor.add(new_sensor);
	}
}
