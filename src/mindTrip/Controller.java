package mindTrip;

import game.Game;
import ihm.GraphicsView;
import ihm.UserEventListener;

import javax.swing.JFrame;

import objects.DriverObject;

import org.jbox2d.common.Vec2;

import sensor.SensorCollision;
import actions.ActionGround;

public class Controller {
	private Data level1;
	private JFrame frame = new JFrame();
	private Game game= new Game();
	GraphicsView panel_game;
	public Controller(){
		createLevel1();
		start_level1();
		frame.setVisible(true);
	}
	public void createLevel1(){
		level1=LevelFactory.createLevel1();
		panel_game = new GraphicsView(level1.getLevel(),new Vec2(-10, 0), 4);
		level1.getLevel().addAGraphicView("Main_view", panel_game);
		
		ActionGround ag = new ActionGround();
		SensorCollision sc = new SensorCollision(level1.getList_heroe(),level1.getList_ground(), ag);
		level1.getLevel().addSenor("System_sensor_ground", sc);
		UserEventListener userListener = new UserEventListener();
		level1.getList_heroe().get(0).setDriver(new DriverObject(userListener, level1.getList_heroe().get(0), ag));
		
		ActionDead ad = new ActionDead(this);
		SensorCollision sc2 = new SensorCollision(level1.getList_DeadContact(), level1.getList_heroe(), ad);
		level1.getLevel().addSenor("System_sensor_dead", sc2);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800,600);
		
		frame.add(panel_game);
		frame.addKeyListener(userListener);
	}
	public void start_level1(){
		game.addLevel("Level1", level1.getLevel());
		
		game.startLevel("Level1");
	}
	public void stop_level1(){
		game.stopLevel("Level1");
		frame.remove(panel_game);
		game.removeLevel("Level1");
	}
	public static void main(String[] args){
		new Controller();
	}
}
