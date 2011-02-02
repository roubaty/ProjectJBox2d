package mindTrip;

import game.Game;
import objects.ObjectAdded;

import org.jbox2d.dynamics.contacts.ContactPoint;

import actions.ActionCollision;

public class ActionDead implements ActionCollision{
	private Controller ctrl;
	public ActionDead(Controller ctrl){
		this.ctrl=ctrl;
	}
	@Override
	public void performe(ContactPoint point, ObjectAdded object1,
			ObjectAdded object2, int type) {
			ctrl.stop_level1();
			ctrl.createLevel1();
			ctrl.start_level1();
	}

}
