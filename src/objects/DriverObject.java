package objects;

import ihm.UserEventListener;

public class DriverObject implements Driver {
	private UserEventListener commandListener;
	public DriverObject(UserEventListener commandListener){
		this.commandListener=commandListener;
	}
	@Override
	public void move() {
		
	}

}
