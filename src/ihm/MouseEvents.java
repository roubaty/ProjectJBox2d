package ihm;

import java.awt.event.MouseEvent;

public class MouseEvents {
	public static int CLICKED=0;
	public static int ENTERED=0;
	public static int EXISTED=0;
	public static int PRESSED=0;
	public static int RELEASED=0;
	private MouseEvent event;
	private int type;
	public MouseEvents(MouseEvent event, int type){
		this.type=type;
		this.event=event;
	}
	public MouseEvent getEvent() {
		return event;
	}
	
	public int getType() {
		return type;
	}

}
