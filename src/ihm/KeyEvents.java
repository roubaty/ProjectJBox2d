package ihm;

import java.awt.event.KeyEvent;

public class KeyEvents {
	private KeyEvent event;
	private int type;
	public static int PRESSED=0;
	public static int RELEASED=1;
	public static int TYPED=2;
	
	public KeyEvents(KeyEvent event, int type){
		this.event=event;
		this.type=type;
	}
	public void setEvent(KeyEvent event) {
		this.event = event;
	}
	public KeyEvent getEvent() {
		return event;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getType() {
		return type;
	}
	
	
}
