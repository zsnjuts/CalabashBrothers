package nju.zs.creature;

import nju.zs.Field;
import nju.zs.Position;
import nju.zs.Room;
import nju.zs.Thing2D;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class Attack extends Thing2D implements Runnable {

	private static ImageIcon attackIcon = new ImageIcon("src/main/resources/fire.png");
	private Room room;

	public Attack(Position position, Room room){
		super(position, attackIcon);
		this.room = room;
		this.room.addThing(this);
	}

	public void run(){
		while(this.x() < Field.getBackGroundWidth())
			this.setPosition(this.x()+10, this.y());
		try {
			TimeUnit.MILLISECONDS.sleep(50);
		}catch (Exception e){
			System.out.println("attack stopped");
		}
	}
}
