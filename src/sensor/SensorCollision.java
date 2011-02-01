package sensor;

import java.util.List;

import org.jbox2d.dynamics.contacts.ContactPoint;

import objects.ObjectAdded;
import actions.ActionCollision;

public class SensorCollision extends Sensor {
	private List<ObjectAdded> from; 
	private List<ObjectAdded> to; 
	private ActionCollision action;
	public SensorCollision(List<ObjectAdded> from, List<ObjectAdded> to, ActionCollision action){
		this.from=from;
		this.to=to;
		this.action=action;
	}
	
	public void callAction(ObjectAdded object1, ObjectAdded object2, ContactPoint point, int type){
		boolean obj1InTo=false;
		boolean obj2InTo=false;
		for (ObjectAdded obj : to) {
			if(object1.equals(obj)){
				obj1InTo=true;
			}
			if(object2.equals(obj)){
				obj2InTo=true;
			}
		}
		boolean obj1InFrom=false;
		boolean obj2InFrom=false;
		for (ObjectAdded obj : from) {
			if(object1.equals(obj)){
				obj1InFrom=true;
			}
			if(object2.equals(obj)){
				obj2InFrom=true;
			}
		}
		if((obj1InFrom && obj2InTo) || (obj2InFrom && obj1InTo)){
			action.performe(point,object1,object2,type);
		}
	}
	
	public void addElementsInTo(ObjectAdded obj){
		to.add(obj);
	}
	
	public List<ObjectAdded> getFrom() {
		return from;
	}
	
	public List<ObjectAdded> getTo() {
		return to;
	}
	
	public ActionCollision getAction() {
		return action;
	}

	@Override
	public void checkSenor() {
		//isn't used
	}
}
