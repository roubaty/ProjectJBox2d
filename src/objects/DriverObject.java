package objects;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import org.jbox2d.common.Vec2;

import actions.ActionGround;

import ihm.KeyEvents;
import ihm.UserEventListener;

public class DriverObject implements Driver {
	private static final int CODE_MOVING_FORWARD = KeyEvent.VK_D;
	private static final int CODE_MOVING_BACKWARD = KeyEvent.VK_A;
	private static final int CODE_JUMPING = KeyEvent.VK_W;
	private static final boolean ON=true;
	private static final boolean OFF=false;
	private static final Vec2 FORCE_MOVING=new Vec2(5f, 0);
	private static final Vec2 FORCE_JUMPING=new Vec2(0, 10f);
	private static final float MAXSPEED = 20f;
	private UserEventListener commandListener;
	private ObjectAdded object;
	private boolean isJumping;
	private boolean ismoving_forward;
	private boolean ismoving_backgound;
	ActionGround actionGround;
	public DriverObject(UserEventListener commandListener, ObjectAdded object, ActionGround actionGround) {
		this.commandListener = commandListener;
		this.object = object;
		this.actionGround = actionGround;
	}

	@Override
	public void move() {
		applyStatus();
		applyAction();
	}
	private void applyStatus(){
		ArrayList<KeyEvents> list = commandListener.getKeyEvents();
		
		for (KeyEvents keyEvents : list) {
			System.out.println("type : "+keyEvents.getType()+" code : "+keyEvents.getEvent().getKeyCode());
			if (keyEvents.getType() == KeyEvents.PRESSED) {
				if (keyEvents.getEvent().getKeyCode() == CODE_MOVING_FORWARD) {
					ismoving_forward=ON;
				}
				if (keyEvents.getEvent().getKeyCode() == CODE_MOVING_BACKWARD) {
					ismoving_backgound=ON;
				}
				if (keyEvents.getEvent().getKeyCode() == CODE_JUMPING) {
					isJumping=ON;
				}
			}
			if (keyEvents.getType() == KeyEvents.RELEASED) {
				if (keyEvents.getEvent().getKeyCode() == CODE_MOVING_FORWARD) {
					ismoving_forward=OFF;
				}
				if (keyEvents.getEvent().getKeyCode() == CODE_MOVING_BACKWARD) {
					ismoving_backgound=OFF;
				}
				if (keyEvents.getEvent().getKeyCode() == CODE_JUMPING) {
					isJumping=OFF;
				}
			}
		}
	}
	private void applyAction(){
		if(ismoving_forward && object.getVitesse().x<=MAXSPEED){
			object.applyForce(FORCE_MOVING);
		}
		if(ismoving_backgound && object.getVitesse().x>=-MAXSPEED){
			object.applyForce(FORCE_MOVING.mul(-1));
		}
		if(isJumping && actionGround.onGround()){
			object.applyForce(FORCE_JUMPING);
		}
	}
}
