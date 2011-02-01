package objects.types;

import level.Level;


import objects.ObjectDescription;

public class Portal  extends ObjectDescription{
	private Level level;
	public Portal(ObjectDescription object, Level level) {
		super(object);
		this.setLevel(level);
	}
	public void setLevel(Level level) {
		this.level = level;
	}
	public Level getLevel() {
		return level;
	}
}
