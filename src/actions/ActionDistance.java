package actions;

import objects.ObjectAdded;

public interface ActionDistance {
	public void reaction(ObjectAdded object1, ObjectAdded object2, float distance);
}
