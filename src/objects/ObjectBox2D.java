package objects;

import objects.materials.Material;
import objects.visual.ShapeDes;

import org.jbox2d.collision.CircleDef;
import org.jbox2d.collision.PolygonDef;
import org.jbox2d.dynamics.BodyDef;

public class ObjectBox2D {
	private Driver driver;
	private Material material;
	private ShapeDes shape;
	private int type;
	private PolygonDef polyDef;
	private CircleDef circleDef;
	private BodyDef bodyDef;
	private Object information;
	private boolean isStatic;
	private float size_x;
	private float size_y;
	private float radius;
	private boolean isSpringboard;
	public ObjectBox2D(Material material, PolygonDef polyDef, BodyDef bodyDef, ShapeDes shape){
		this.material=material;
		this.polyDef = polyDef;
		this.bodyDef=bodyDef;
		this.shape=shape;
	}
	public ObjectBox2D(Material material, CircleDef circleDef, BodyDef bodyDef, ShapeDes shape){
		this.material=material;
		this.circleDef = circleDef;
		this.bodyDef=bodyDef;
		this.shape=shape;
	}
	public ObjectBox2D(ObjectBox2D object) {
		driver = object.driver;
		material = object.material;
		shape = object.shape;
		type = object.type;
		polyDef = object.polyDef;
		circleDef = object.circleDef;
		bodyDef = object.bodyDef;
		size_x = object.size_x;
		size_y = object.size_y;
		radius = object.radius;
		isStatic = object.isStatic; 
	}
	public PolygonDef getPolyDef() {
		return polyDef;
	}
	public CircleDef getCircleDef() {
		return circleDef;
	}
	public BodyDef getBodyDef() {
		return bodyDef;
	}
	public void setShape(ShapeDes shape) {
		this.shape = shape;
	}
	public void move() {
		if(driver!=null){
			driver.move();
		}
	}
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	public Material getMaterial() {
		return material;
	}
	public ShapeDes getShape() {
		return shape;
	}
	public int getType() {
		return type;
	}
	public ObjectAdded cloneObject(){
		return new ObjectAdded(this);
	}
	public void setInformation(Object information) {
		this.information = information;
	}
	public Object getInformation() {
		return information;
	}
	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}
	public boolean isStatic() {
		return isStatic;
	}
	public void setUserDataPoly(){
		polyDef.userData=this;
	}
	public void setUserDataCircle(){
		circleDef.userData=this;
	}
	public float getSize_x() {
		return size_x;
	}
	public void setSize_x(float size_x) {
		this.size_x = size_x;
	}
	public float getSize_y() {
		return size_y;
	}
	public void setSize_y(float size_y) {
		this.size_y = size_y;
	}
	public float getRadius() {
		return radius;
	}
	public void setRadius(float radius) {
		this.radius = radius;
	}
	public void setSpringboard(boolean isSpringboard) {
		this.isSpringboard = isSpringboard;
	}
	public boolean isSpringboard() {
		return isSpringboard;
	}
}
