package level;
import java.awt.Color;

import org.jbox2d.collision.AABB;
import org.jbox2d.common.Vec2;


public class LevelDescriptor {
	private Vec2 gravity;
	private AABB worldAabb;
	private boolean doSleep;
	private float frameTime;
	private int internalIteration;
	public LevelDescriptor(Vec2 gravity){
		this.gravity=gravity;
		this.worldAabb=new AABB(new Vec2(-Integer.MIN_VALUE,-Integer.MIN_VALUE),new Vec2(Integer.MAX_VALUE,Integer.MAX_VALUE));
		this.doSleep=false;
		this.frameTime=1f/40f;
		this.internalIteration=5;
	}
	public Vec2 getGravity() {
		return gravity;
	}
	public void setGravity(Vec2 gravity) {
		this.gravity = gravity;
	}
	public AABB getWorldAabb() {
		return worldAabb;
	}
	public void setWorldAabb(AABB worldAabb) {
		this.worldAabb = worldAabb;
	}
	public boolean isDoSleep() {
		return doSleep;
	}
	public void setDoSleep(boolean doSleep) {
		this.doSleep = doSleep;
	}
	public float getFrameTime() {
		return frameTime;
	}
	public void setFrameTime(float frameTime) {
		this.frameTime = frameTime;
	}
	public int getInternalIteration() {
		return internalIteration;
	}
	public void setInternalIteration(int internalIteration) {
		this.internalIteration = internalIteration;
	}
	private Color color;
	public static int NONE=0;
	public static int BY_COLOR=2;
	private int type=NONE;
	public void setColor(Color color) {
		setType(BY_COLOR);
		this.color = color;
	}
	public Color getColor() {
		return color;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getType() {
		return type;
	}
}
