package actions;

import objects.ObjectAdded;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.contacts.ContactPoint;

public class ActionCollisionSpringBoard implements ActionCollision{
	private float force;
	public ActionCollisionSpringBoard(float force){
		this.force=force;
	}
	@Override
	public void performe(ContactPoint point, ObjectAdded object1,
			ObjectAdded object2, int type) {
		if(type==ActionCollision.ADD){
			Vec2 norm = point.normal;
			norm.normalize();
			if(object1.isSpringboard()){
				Vec2 currentSpeed = object2.getVitesse();
				Vec2 impluse = new Vec2(currentSpeed.x*(-2),currentSpeed.y*(-2));
				impluse = impluse.add(norm.mul(force));
				object2.getBody().setLinearVelocity(object2.getBody().getLinearVelocity().add(impluse));
			}
			if(object2.isSpringboard()){
				Vec2 currentSpeed = object1.getVitesse();
				Vec2 impluse = new Vec2(currentSpeed.x*(-2),currentSpeed.y*(-2));
				impluse = impluse.add(norm.mul(force));
				object1.getBody().setLinearVelocity(object1.getBody().getLinearVelocity().add(impluse));
			}
		}
	}
}
