package objects;

import objects.materials.Material;
import objects.visual.ShapeDes;

import org.jbox2d.collision.CircleDef;
import org.jbox2d.collision.PolygonDef;
import org.jbox2d.dynamics.BodyDef;


public class ObjectDescription extends ObjectBox2D{
	
	public ObjectDescription(Material material, PolygonDef polyDef, BodyDef bodyDef, ShapeDes shape){
		super(material,polyDef,bodyDef,shape);
	}
	public ObjectDescription(Material material, CircleDef circleDef, BodyDef bodyDef, ShapeDes shape){
		super(material,circleDef,bodyDef,shape);
	}
	public ObjectDescription(ObjectDescription object) {
		super(object);
	}
	public ObjectAdded cloneObject(){
		ObjectAdded object = (ObjectAdded) super.cloneObject();
		return object;
	}

}
