package ihm;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UserEventListener implements KeyListener, MouseListener {
	ArrayList<MouseEvents> mouseEvents = new ArrayList<MouseEvents>();
	ArrayList<KeyEvents> keyEvents = new ArrayList<KeyEvents>();
	private Lock l = new ReentrantLock();

	public ArrayList<MouseEvents> getMouseEvents() {
		l.lock();
		ArrayList<MouseEvents> return_value;
		try {
			return_value = mouseEvents;
			mouseEvents.clear();
		} finally {
			l.unlock();
		}
		return return_value;
	}

	public ArrayList<KeyEvents> getKeyEvents() {
		l.lock();
		ArrayList<KeyEvents> return_value;
		try {
			return_value = keyEvents;
			keyEvents.clear();
		} finally {
			l.unlock();
		}

		return return_value;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		l.lock();
		try {
			keyEvents.add(new KeyEvents(e, KeyEvents.PRESSED));
		} finally {
			l.unlock();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		l.lock();
		try {
			keyEvents.add(new KeyEvents(e, KeyEvents.RELEASED));
		} finally {
			l.unlock();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		l.lock();
		try {
			keyEvents.add(new KeyEvents(e, KeyEvents.TYPED));
		} finally {
			l.unlock();
		}
	}

	// private ArrayList<E>

	@Override
	public void mouseClicked(MouseEvent e) {
		l.lock();
		try {
			mouseEvents.add(new MouseEvents(e, MouseEvents.CLICKED));
		} finally {
			l.unlock();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		l.lock();
		try {
			mouseEvents.add(new MouseEvents(e, MouseEvents.ENTERED));
		} finally {
			l.unlock();
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		l.lock();
		try {
			mouseEvents.add(new MouseEvents(e, MouseEvents.EXISTED));
		} finally {
			l.unlock();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		l.lock();
		try {
			mouseEvents.add(new MouseEvents(e, MouseEvents.PRESSED));
		} finally {
			l.unlock();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		l.lock();
		try {
			mouseEvents.add(new MouseEvents(e, MouseEvents.RELEASED));
		} finally {
			l.unlock();
		}
	}
}
