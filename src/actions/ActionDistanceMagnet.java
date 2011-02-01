package actions;

import org.jbox2d.common.Vec2;

import objects.ObjectAdded;

public class ActionDistanceMagnet implements ActionDistance{
	private float force;
	public ActionDistanceMagnet(float force){
		this.force=force;
	}
	public void reaction(ObjectAdded object1, ObjectAdded object2,
			float distance) {
		if(!object2.isStatic() && distance>2){
			Vec2 attration = object1.getPosition().sub(object2.getPosition());
			attration.normalize();
			attration=attration.mul((force-distance));
			attration=attration.add(object2.getBody().getLinearVelocity());
			object2.getBody().setLinearVelocity(attration);
		}
	}

}
