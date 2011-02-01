package sensor;

import java.util.List;

import objects.ObjectAdded;

import org.jbox2d.dynamics.Body;

import actions.ActionSpeed;

public class SensorSpeed extends Sensor {
	public static final int SMALLERTHEN=0;
	public static final int GREATERTHAN=1;
	private List<ObjectAdded> objects;
	private int type;
	private float value;
	private ActionSpeed action;
	public SensorSpeed(int type, float value, List<ObjectAdded> objects, ActionSpeed action){
		this.objects=objects;
		this.type=type;
		this.value=value;
		this.action = action;
	}
	public void checkSenor(){
		Body body;
		float speed_x;
		float speed_y;
		float speed;
		for (ObjectAdded obj : objects) {
			body = obj.getBody();
			speed_x = body.getLinearVelocity().x;
			speed_y = body.getLinearVelocity().y;
			speed =(float) Math.sqrt((double)(speed_x*speed_x+speed_y*speed_y));
			if(type==GREATERTHAN){
				if(speed>=value){
					action.reaction(obj, speed);
				}
			}
			if(type==SMALLERTHEN){
				if(speed<=value){
					action.reaction(obj, speed);
				}
			}
		}
	}
}
