package mindTrip;

import java.util.ArrayList;

import level.Level;

import objects.ObjectAdded;

public class Data {
	private ArrayList<ObjectAdded> list_ground = new ArrayList<ObjectAdded>();
	private ArrayList<ObjectAdded> list_heroe = new ArrayList<ObjectAdded>();
	private ArrayList<ObjectAdded> list_DeadContact = new ArrayList<ObjectAdded>();
	private ArrayList<ObjectAdded> list_other = new ArrayList<ObjectAdded>();
	private Level level;
	public ArrayList<ObjectAdded> getList_ground() {
		return list_ground;
	}
	public void addOnListGroud(ObjectAdded ground) {
		this.list_ground.add(ground);
	}
	public ArrayList<ObjectAdded> getList_heroe() {
		return list_heroe;
	}
	public void addOnListHeroe(ObjectAdded heroe) {
		this.list_heroe.add(heroe);
	}
	public ArrayList<ObjectAdded> getList_DeadContact() {
		return list_DeadContact;
	}
	public void addOnListDeadContact(ObjectAdded deadContact) {
		this.list_DeadContact.add(deadContact);
	}
	public ArrayList<ObjectAdded> getList_other() {
		return list_other;
	}
	public void setList_other(ArrayList<ObjectAdded> list_other) {
		this.list_other = list_other;
	}
	public void setLevel(Level level) {
		this.level = level;
	}
	public Level getLevel() {
		return level;
	}
	
}
