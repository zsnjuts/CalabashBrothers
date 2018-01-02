package nju.zs.creature;

import nju.zs.Position;
import nju.zs.Thing2D;

import javax.swing.*;

public abstract class Creature extends Thing2D implements Runnable {

	public Creature(Position position, ImageIcon icon){
		super(position, icon);
	}

	public abstract void run();

	public void setQueuePosition(Position queuePosition){
		this.queuePosition = queuePosition;
		queuePosition.setHolder(this);
	}

	public Position getQueuePosition(){
		return queuePosition;
	}

	private Position queuePosition;

}
