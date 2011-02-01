package game;
import java.util.Hashtable;
import java.util.Map;

import level.Level;


public class Game {
	private Hashtable<String, Level> listLevel = new Hashtable<String, Level>();
	public Game(){
		
	}
	public Game(Map<String, Level> listLevel){
		this.listLevel=(Hashtable<String, Level>) listLevel;
	}
	public boolean addLevel(String name, Level level){
		if(listLevel.containsKey(name))
			return false;
		listLevel.put(name, level);
		return true;
	}
	public boolean removeLevel(String name){
		if(!listLevel.containsKey(name))
			return false;
		listLevel.remove(name);
		return true;
	}
	public boolean startLevel(String name){
		if(listLevel.containsKey(name)){
			Level level = (Level) listLevel.get(name);
			level.start();
			return true;
		}
		return false;
	}
	public boolean stopLevel(String name){
		if(listLevel.containsKey(name)){
			Level level = (Level) listLevel.get(name);
			level.interrupt();
			return true;
		}
		return false;
		
	}
}
