package objects.types;

import objects.ObjectDescription;

public class Bullet extends ObjectDescription{
	public Bullet(ObjectDescription object) {
		super(object);
		object.getBodyDef().isBullet=true;
	}
}
