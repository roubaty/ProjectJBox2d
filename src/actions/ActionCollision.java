package actions;

import objects.ObjectAdded;

import org.jbox2d.dynamics.contacts.ContactPoint;

public interface ActionCollision{
	public static final int ADD=0;
	public static final int PERSIST=1;
	public static final int REMOVE=2;
	public static final int RESULT=3;
	public void performe(ContactPoint point, ObjectAdded object1,
			ObjectAdded object2, int type);
}
