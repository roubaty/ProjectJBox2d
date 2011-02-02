package objects;

import objects.visual.Box;
import objects.visual.Circle;
import objects.visual.ShapeDes;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

public class ObjectAdded extends ObjectBox2D{
	private Body body;
	public ObjectAdded(ObjectBox2D object) {
		super(object);
	}
	public Body getBody() {
		return body;
	}
	public void setBody(Body body) {
		this.body=body;
	}
	public Vec2 getVitesse(){
		return body.getLinearVelocity();
	}
	public float getAngle(){
		return body.getAngle();
	}
	public float getInertia(){
		return body.getInertia();
	}
	public Vec2 getLocalCenter(){
		return body.getLocalCenter();
	}
	public float getMass(){
		return body.getMass();
	}
	public Vec2 getPosition(){
		ShapeDes sh = getShape();
		if(sh instanceof Box){
			Vec2 vec = body.getPosition();
			vec.x -= getSize_x();
			vec.y -= getSize_y();
			return vec;
		}
		if(sh instanceof Circle){
			Vec2 vec = body.getPosition();
			vec.x -= getRadius();
			vec.y -= getRadius();
			return vec;
		}
		return body.getPosition();
	}
	public float getAngularVelocity(){
		return body.getAngularVelocity();
	}
	public void applyForce(Vec2 force){
		getBody().wakeUp();
		getBody().setLinearVelocity(force.add(getBody().getLinearVelocity()));
	}
}
