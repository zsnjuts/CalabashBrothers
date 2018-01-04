package nju.zs;

import javax.swing.*;
import java.awt.Image;

public class Thing2D {

//	public Thing2D(int x, int y) {
//		this.position = new Position(x, y);
//	}

	public Thing2D(Position position, ImageIcon icon){
		this.position = position;
		this.icon = icon;
	}

	public int x() {
		return this.position.getX();
	}
	public int y() {
		return this.position.getY();
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(int x, int y){
		this.position.setPosition(x, y);
	}

	public void setImageIcon(ImageIcon icon){
		this.icon = icon;
	}
	public ImageIcon getImageIcon() {
		return this.icon;
	}
	public int getWidth(){
		return icon.getIconWidth();
	}
	public int getHeight(){
		return icon.getIconHeight();
	}

	public int distanceTo(Position p){
		return Math.abs(p.getX()-this.x())+Math.abs(p.getY()-this.y());
	}

	protected Position position;
	protected ImageIcon icon;
}