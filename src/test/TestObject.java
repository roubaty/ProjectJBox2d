package test;

import game.Game;
import ihm.GraphicsView;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import level.Level;
import level.LevelDescriptor;

import org.jbox2d.common.Vec2;

import objects.FactoryObjectBox2D;
import objects.ObjectAdded;
import objects.ObjectDescription;
import objects.materials.Lead;
import objects.types.Decorate;
import objects.types.Heroe;
import objects.types.Magnet;
import objects.types.Portal;
import objects.types.Springboard;
import objects.visual.Visual;
import sensor.SensorSpeed;

public class TestObject {
	public static void main(String[] args) throws InterruptedException{
		//Visual creation
		
		Visual visual2 = new Visual(Color.black, Color.yellow);
		
		//Object creation
		Visual visual = new Visual(Color.black, Color.cyan);
		ObjectDescription obj1 = FactoryObjectBox2D.createBox(100, 20, new Lead(), visual);
		
		ObjectDescription obj2 = FactoryObjectBox2D.createCircle(10, new Lead(), visual);
		Vec2[] vertices = new Vec2[3];
		vertices[0] = new Vec2(0, 0);
		vertices[1] = new Vec2(20, 0);
		vertices[2] = new Vec2(10, 15);
		ObjectDescription obj3 = FactoryObjectBox2D.createPolygon(vertices, new Lead(), visual);
		ObjectDescription obj4 = FactoryObjectBox2D.createBox(100, 20, new Lead(), visual2);
		ObjectDescription obj5 = FactoryObjectBox2D.createBox(20, 20, new Lead(), visual2);
		ObjectDescription obj6 = FactoryObjectBox2D.createBox(700, 20, new Lead(), visual);
		
		//Define type of objects
		Heroe hero = new Heroe(obj2);
		Decorate ground1 = new Decorate(obj1);
		Springboard ground2 = new Springboard(obj4, 20f);
		Magnet magnet = new Magnet(obj3, 20f);
		
		//Level descriptor creation
		LevelDescriptor ld = new LevelDescriptor(new Vec2(0f,-9.81f));
		LevelDescriptor ld2 = new LevelDescriptor(new Vec2(0f,-9.81f));
		ld.setColor(Color.white);
		ld2.setColor(Color.yellow);
		//Level creation
		Level level = new Level(ld);
		Decorate ground3 = new Decorate(obj6);
		
		//Define type of objects
		Portal portal = new Portal(obj5,level);
		
		//Add objects to the level
//		Visual visual = new Visual(Color.black, Color.cyan);
//		ObjectBox2D obj1 = FactoryObjectBox2D.createBox(100, 20, new Lead(), visual);
//		Decorate ground1 = new Decorate(obj1);
		ObjectAdded ground1Added = level.addObject("ground1", ground1, 0, 0, true);
		ObjectAdded ground2Added = level.addObject("ground2", ground2, 120, 0, true);
		ObjectAdded ground3Added = level.addObject("ground3", ground1, 400, 300, true);
		ObjectAdded ground4Added = level.addObject("ground5", ground3, 250, 0, true);
		ObjectAdded square1Added = level.addObject("Square1", magnet, 20, 50, false);
		ObjectAdded player3Added = level.addObject("player3", hero, 260, 30, false);
		ObjectAdded player1Added = level.addObject("player1", hero, 10, 100, false);
		ObjectAdded player2Added = level.addObject("player2", hero, 130, 70, false);
		
//		ObjectBox2D pikeAdded = level.addObjectBox2D("pike1", magnet, 90, 60, false);
//		GraphicsView view6 = new GraphicsView(level, new Vec2(0,0), 1);
//		level.addAGraphicView("Fix View6", view6);
//		JFrame frame6 = new JFrame("Frame 1");
//		frame6.setLocation(0, 0);
//		frame6.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame6.setSize(1900,1100);
//		frame6.add(view6);
//		GraphicsView view7 = new GraphicsView(level, new Vec2(0,0), 2);
//		level.addAGraphicView("Fix View7", view7);
//		JFrame frame7 = new JFrame("Frame 2");
//		frame7.setLocation(0, 0);
//		frame7.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame7.setSize(1900,1100);
//		frame7.add(view7);
		//View creation
		GraphicsView view1 = new GraphicsView(level, new Vec2(100,0), 3);
		GraphicsView view2 = new GraphicsView(level,player2Added, 3);
		GraphicsView view3 = new GraphicsView(level, new Vec2(100,0), 1);
		GraphicsView view4 = new GraphicsView(level,player2Added, 1);
		GraphicsView view5 = new GraphicsView(level, new Vec2(0,0), 0.1);
		//GraphicsView view1 = new GraphicsView(level,new Vec2(-200,-240),new Vec2(300,260),1);
		//Add view to the level
		
		level.addAGraphicView("Fix View1", view1);
		level.addAGraphicView("Fix View2", view2);
		level.addAGraphicView("Fix View3", view3);
		level.addAGraphicView("Fix View4", view4);
		level.addAGraphicView("Fix View5", view5);
		
		JFrame frame1 = new JFrame("Frame 1");
		JFrame frame2 = new JFrame("Frame 2");
		frame2.setLocation(500, 0);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setSize(500,500);
		frame1.add(view1);
		frame2.add(view2);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.setSize(500,500);
		
		JFrame frame3 = new JFrame("Frame 3");
		JFrame frame4 = new JFrame("Frame 4");
		frame3.setLocation(0, 500);
		frame4.setLocation(500, 500);
		frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame3.setSize(500,500);
		frame3.add(view3);
		frame4.add(view4);
		frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame4.setSize(500,500);
		
		JFrame frame5 = new JFrame("Frame 5");
		frame5.setLocation(0, 1000);
		frame5.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame5.setSize(1000,100);
		frame5.add(view5);
		//Creation of the game
		Game game = new Game();
		
		//Sensor creation
		ActionListenerSpeed actionLis = new ActionListenerSpeed();
		List<ObjectAdded> speedSensitive = new ArrayList<ObjectAdded>();
		SensorSpeed speed = new SensorSpeed(SensorSpeed.GREATERTHAN, 10f, speedSensitive, actionLis);
		
		//Add sensor to the level
		level.addSenor("isAtMaxSpeed", speed);
		//start a level
		game.addLevel("Level 1", level);
		game.startLevel("Level 1");
		
		frame1.setVisible(true);
		frame2.setVisible(true);
		frame3.setVisible(true);
		frame4.setVisible(true);
		frame5.setVisible(true);
		while(true){
			
			Thread.sleep(10);
		}
//		frame6.setVisible(true);
//		frame7.setVisible(true);
	}
}
