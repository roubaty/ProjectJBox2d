package objects.visual;

import java.awt.Color;

public class Visual {
	private Color borderColor;
	private Color fillColor;
	public Color getBorderColor() {
		return borderColor;
	}
	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}
	public Color getFillColor() {
		return fillColor;
	}
	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}
	public Visual(Color borderColor, Color fillColor){
		this.borderColor=borderColor;
		this.fillColor=fillColor;
	}
}
