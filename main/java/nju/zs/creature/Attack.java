package nju.zs.creature;

import nju.zs.Position;
import nju.zs.Thing2D;

import javax.swing.*;

public class Attack extends Thing2D {

	private static ImageIcon attackIcon = new ImageIcon("src/main/resources/fire.png");

	public Attack(Position position){
		super(position, attackIcon);
	}

	public void move(){
		this.setPosition(this.x()+10, this.y());
	}
}
