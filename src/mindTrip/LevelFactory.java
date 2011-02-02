package mindTrip;

import java.awt.Color;

import level.Level;
import level.LevelDescriptor;

import org.jbox2d.common.Vec2;

import objects.FactoryObjectBox2D;
import objects.ObjectDescription;
import objects.materials.Lead;
import objects.visual.Visual;


public class LevelFactory {
		private static ObjectDescription block;
		private static ObjectDescription pike;
		private static ObjectDescription ground1;
		private static ObjectDescription ground2;
		private static ObjectDescription heroe;
		private static ObjectDescription[] down_t = new ObjectDescription[4];
		private static ObjectDescription up_t;
		private static ObjectDescription border;
		private static ObjectDescription border_up;
		
		//ground 1 constants
		public static final float GROUND1_POSITION_X = 0f;
		public static final float GROUND1_POSITION_Y = 0f;
		//ground 2 constants
		public static final float GROUND2_POSITION_X = 90f;
		public static final float GROUND2_POSITION_Y = 0f;
		//ground 4 constants
		public static final float BORDER1_POSITION_X = -5f;
		public static final float BORDER1_POSITION_Y = 0f;
		//ground 5 constants
		public static final float BORDER2_POSITION_X = 345f;
		public static final float BORDER2_POSITION_Y = 0f;
		public static final float BORDER3_POSITION_X = -5f;
		public static final float BORDER3_POSITION_Y = 120f;
		//Bloc constants
		public static final float BLOC_POSITION_INIT_X = 35f;
		public static final float BLOC_POSITION_INIT_Y = 10f;
		public static final int NUMBER_OF_BLOC_X = 3;
		public static final int NUMBER_OF_BLOC_Y = 8;
		
		//PIKE constants
		public static final float PIKE_POSITION_INIT_X = 50f;
		public static final float PIKE_POSITION_INIT_Y = 0f;
		public static final int NUMBER_OF_PIKE = 8;
		public static final float PIKE_SIZE_X = 5f;
			
		//T constants
		public static final float UP_T_POSITION_INIT_X = 120f;
		public static final float DOWN_T_POSITION_INIT_X = 125f;
		public static final float T_POSITION_INIT_Y = 10f;
		public static final int NUMBER_OF_T = 7;
		public static final float DISTANCE_BETWEEN_T = 30f;
		public static final int[] T_LIST = {0,2,2,1,3,1,3};
		public static final float PLAYER_POSITION_INIT_X = 10f;
		public static final float PLAYER_POSITION_INIT_Y = 20f;
		public static Data createLevel1(){
			init_object_description();
			Data level_data = new Data();
			LevelDescriptor ld = new LevelDescriptor(new Vec2(0,-9.81f));
			ld.setColor(Color.white);
			Level level1 = new Level(ld);
			level_data.addOnListGroud(level1.addObject("Ground1", ground1, GROUND1_POSITION_X, GROUND1_POSITION_Y, true));
			level_data.addOnListGroud(level1.addObject("Ground2", ground2, GROUND2_POSITION_X, GROUND2_POSITION_Y, true));
			for (int i = 0; i < NUMBER_OF_BLOC_X; i++) {
				for (int j = 0; j < NUMBER_OF_BLOC_Y; j++) {
					level_data.addOnListGroud(level1.addObject("Block"+i+j, block, BLOC_POSITION_INIT_X + i* BLOC_SIZE_X, BLOC_POSITION_INIT_Y + j * BLOC_SIZE_Y, false));
				}
			}
			level_data.addOnListDeadContact(level1.addObject("Border1", border, BORDER1_POSITION_X, BORDER1_POSITION_Y, true));
			level_data.addOnListDeadContact(level1.addObject("Border2", border, BORDER2_POSITION_X, BORDER2_POSITION_Y, true));
			level_data.addOnListDeadContact(level1.addObject("Border3", border_up, BORDER3_POSITION_X, BORDER3_POSITION_Y, true));
			for (int i = 0; i < NUMBER_OF_PIKE; i++) {
				level_data.addOnListDeadContact(level1.addObject("Pike"+i, pike, PIKE_POSITION_INIT_X+ i * PIKE_SIZE_X, PIKE_POSITION_INIT_Y, true));
			}
			for (int i = 0; i < NUMBER_OF_T; i++) {
				level_data.addOnListGroud(level1.addObject("UpT"+i, up_t, UP_T_POSITION_INIT_X+ i * DISTANCE_BETWEEN_T, T_POSITION_INIT_Y + T_DOWN_SIZE_Y[T_LIST[i]], false));
				level_data.addOnListDeadContact(level1.addObject("DownT"+i, down_t[T_LIST[i]], DOWN_T_POSITION_INIT_X+ i * DISTANCE_BETWEEN_T, T_POSITION_INIT_Y, true));
			}
			level_data.addOnListHeroe(level1.addObject("heroe", heroe, PLAYER_POSITION_INIT_X, PLAYER_POSITION_INIT_Y, false));
			level_data.setLevel(level1);
			return level_data;
		}
		
		private static Visual visual_red = new Visual(Color.RED, Color.red); 
		private static Visual visual_green = new Visual(Color.GREEN, Color.green); 
		private static Visual visual_cyan = new Visual(Color.CYAN, Color.cyan); 
		private static Visual visual_yellow = new Visual(Color.YELLOW, Color.yellow);
		public static final float BLOC_SIZE_X = 5f;
		public static final float BLOC_SIZE_Y = 5f;
		public static final float VERTICE0_X = 0f;
		public static final float VERTICE0_Y = 0f;
		public static final float VERTICE1_X = 5f;
		public static final float VERTICE1_Y = 0f;
		public static final float VERTICE2_X = 2.5f;
		public static final float VERTICE2_Y = 10f;
		public static final float GROUND1_SIZE_X = 50f;
		public static final float GROUND1_SIZE_Y = 10f;
		public static final float GROUND2_SIZE_X = 257f;
		public static final float GROUND2_SIZE_Y = 10f;
		public static final float BORDER_SIZE_X = 5f;
		public static final float BORDER_SIZE_Y = 120f;
		public static final float BORDER_UP_SIZE_X = 360f;
		public static final float BORDER_UP_SIZE_Y = 5f;
		public static final float PLAYER_RADIUS = 2.5f;
		public static final float T_UP_SIZE_X = 15f;
		public static final float T_UP_SIZE_Y = 2.5f;
		public static final float T_DOWN_SIZE_X = 5f;
		public static final float[] T_DOWN_SIZE_Y = {4f,8f,12f,15f};
		private static void init_object_description(){
			block = FactoryObjectBox2D.createBox(BLOC_SIZE_X, BLOC_SIZE_Y, new Lead(), visual_cyan);
			Vec2[] vertices = new Vec2[3];
			vertices[0]=new Vec2(VERTICE0_X, VERTICE0_Y);
			vertices[1]=new Vec2(VERTICE1_X, VERTICE1_Y);
			vertices[2]=new Vec2(VERTICE2_X, VERTICE2_Y);
			pike = FactoryObjectBox2D.createPolygon(vertices, new Lead(), visual_red);
			ground1 = FactoryObjectBox2D.createBox(GROUND1_SIZE_X, GROUND1_SIZE_Y, new Lead(), visual_green);
			ground2 = FactoryObjectBox2D.createBox(GROUND2_SIZE_X, GROUND2_SIZE_Y, new Lead(), visual_green);
			heroe = FactoryObjectBox2D.createCircle(PLAYER_RADIUS, new Lead(), visual_yellow);
			border = FactoryObjectBox2D.createBox(BORDER_SIZE_X, BORDER_SIZE_Y, new Lead(), visual_red);
			border_up = FactoryObjectBox2D.createBox(BORDER_UP_SIZE_X, BORDER_UP_SIZE_Y, new Lead(), visual_red);
			down_t[0] = FactoryObjectBox2D.createBox(T_DOWN_SIZE_X, T_DOWN_SIZE_Y[0], new Lead(), visual_red);
			down_t[1] = FactoryObjectBox2D.createBox(T_DOWN_SIZE_X, T_DOWN_SIZE_Y[1], new Lead(), visual_red);
			down_t[2] = FactoryObjectBox2D.createBox(T_DOWN_SIZE_X, T_DOWN_SIZE_Y[2], new Lead(), visual_red);
			down_t[3] = FactoryObjectBox2D.createBox(T_DOWN_SIZE_X, T_DOWN_SIZE_Y[3], new Lead(), visual_red);
			up_t = FactoryObjectBox2D.createBox(T_UP_SIZE_X, T_UP_SIZE_Y, new Lead(), visual_cyan);
			
		}
}
