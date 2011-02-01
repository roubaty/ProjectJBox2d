package objects;


import objects.materials.Material;
import objects.visual.Box;
import objects.visual.Circle;
import objects.visual.Polygon;
import objects.visual.Visual;

import org.jbox2d.collision.CircleDef;
import org.jbox2d.collision.PolygonDef;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;

public abstract class FactoryObjectBox2D {
	public static ObjectDescription createBox(float size_x, float size_y, Material material, Visual visual){
		size_x/=2;
		size_y/=2;
		PolygonDef boxDef = new PolygonDef();
		BodyDef bodyDef = new BodyDef();
		boxDef.setAsBox(size_x, size_y);
		boxDef.friction = material.getFriction();
		boxDef.restitution  = material.getRestitution();
		boxDef.density = material.getDensity();
		Box box = new Box(size_x, size_y, visual);
		ObjectDescription object = new ObjectDescription(material, boxDef, bodyDef, box);
		object.setSize_x(size_x);
		object.setSize_y(size_y);
		object.setUserDataPoly();
		return object;
	}
	public static ObjectDescription createPolygon(Vec2[] vertices, Material material, Visual visual){
		BodyDef bodyDef = new BodyDef();
		PolygonDef polyDef = new PolygonDef();
		for (Vec2 vec2 : vertices) {
			polyDef.addVertex(vec2);
		}
		polyDef.friction=material.getFriction();
		polyDef.restitution = material.getRestitution();
		polyDef.density = material.getDensity();
		Polygon polygon = new Polygon(vertices, visual);
		ObjectDescription object = new ObjectDescription(material, polyDef, bodyDef, polygon);
		object.setUserDataPoly();
		return object;
	}
	public static ObjectDescription createCircle(float radius, Material material, Visual visual){
		BodyDef bodyDef = new BodyDef();
		CircleDef polyDef = new CircleDef();
		polyDef.radius = radius;
		polyDef.density = material.getDensity();
		polyDef.restitution = material.getRestitution();
		polyDef.friction = material.getFriction();
		Circle circle = new Circle(radius, visual);
		ObjectDescription object = new ObjectDescription(material, polyDef, bodyDef, circle);
		object.setRadius(radius);
		object.setUserDataCircle();
		return object;
	}
}
