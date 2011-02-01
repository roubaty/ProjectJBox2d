package sensor;

import java.util.List;

import org.jbox2d.collision.Distance;
import org.jbox2d.collision.Shape;
import org.jbox2d.common.Vec2;
import org.jbox2d.common.XForm;

import actions.ActionDistance;

import objects.ObjectAdded;

public class SensorDistance extends Sensor{
	public static final int SMALLERTHAN=0;
	public static final int GREATERTHAN=1;
	private List<ObjectAdded> from;
	private List<ObjectAdded> to;
	private int type;
	private float value;
	private ActionDistance action;
	public SensorDistance(int type, float value, List<ObjectAdded> from, List<ObjectAdded> to, ActionDistance action){
		this.from=from;
		this.to=to;
		this.type=type;
		this.value=value;
		this.action=action;
		for (ObjectAdded i : from) {
			for (ObjectAdded j : to) {
				if(i.equals(j)){
					to.remove(j);
				}
			}
		}
	}
	public void checkSenor(){
		Vec2 x1 = new Vec2();
		Vec2 x2 = new Vec2();
		for (ObjectAdded i : from) {
			for (ObjectAdded j : to) {
				Shape shape1 = i.getBody().getShapeList();
				Shape shape2 = j.getBody().getShapeList();
				XForm xf1 = i.getBody().getXForm();
				XForm xf2 = j.getBody().getXForm();
				Distance.distance(x1, x2, shape1, xf1, shape2, xf2);
				float distance = (float)Math.sqrt(((x1.x-x2.x)*(x1.x-x2.x)+(x1.y-x2.y)*(x1.y-x2.y)));
				if(distance!=0 && ((type==SMALLERTHAN && distance<value) || type==GREATERTHAN && distance>value)){
					action.reaction(i, j, distance);
				}
			}
		}
	}
	public void addElementsInTo(ObjectAdded obj){
		if(!from.contains(obj))
			to.add(obj);
	}
}
