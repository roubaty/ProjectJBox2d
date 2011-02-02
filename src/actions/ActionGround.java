package actions;

import objects.ObjectAdded;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.contacts.ContactPoint;


public class ActionGround implements ActionCollision{
	private int count_ground_contact=0;
	private static final float SIN_MAX=0.7f;
	@Override
	public void performe(ContactPoint point, ObjectAdded object1,
			ObjectAdded object2, int type) {
		if(type==ActionCollision.ADD){
			Vec2 norm = point.normal;
			norm.normalize();
			if(norm.y>=0 && norm.x>=-SIN_MAX && norm.x<=SIN_MAX){
				count_ground_contact=1;
			}
		}
		if(type==ActionCollision.REMOVE){
			Vec2 norm = point.normal;
			norm.normalize();
			if(norm.y>=0 && norm.x>=-SIN_MAX && norm.x<=SIN_MAX){
				count_ground_contact=0;
			}
		}
	}
	public boolean onGround(){
		return (count_ground_contact>0);
	}

}
