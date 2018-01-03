package nju.zs.creature;

import nju.zs.Position;
import nju.zs.Room;
import nju.zs.Thing2D;

import javax.swing.*;

public abstract class Creature extends Thing2D implements Runnable {

	public Creature(Position position, ImageIcon icon){
		super(position, icon);
	}

	public abstract void run();

	public void setRoom(Room room){
		this.room = room;
	}

	public void setQueuePosition(Position queuePosition){
		this.queuePosition = queuePosition;
		queuePosition.setHolder(this);
	}

	public Position getQueuePosition(){
		return queuePosition;
	}

	protected final void moveTo(Position p){
		int step = 1;
		int nx = (p.getX()==this.x()) ? 0 : ((p.getX()>this.x())?step:(-step));
		int ny = (p.getY()==this.y()) ? 0 : ((p.getY()>this.y())?step:(-step));
		this.position.setPosition(this.x()+nx, this.y()+ny);
	}

	protected Room room;
	private Position queuePosition;

}
