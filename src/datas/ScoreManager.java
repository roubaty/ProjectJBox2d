package datas;

import java.util.Hashtable;

public class ScoreManager {
	private Hashtable<String, Object> score = new Hashtable<String, Object>();
	public boolean add(String name, Object obj) {
		if(score.contains(name))
			return false;
		score.put(name, obj);
		return true;
	}
	public boolean update(String name, Object obj){
		if(!score.contains(name))
			return false;
		score.remove(name);
		score.put(name, obj);
		return true;
	}
	public Object consult(String name){
		return score.get(name);
	}
}
