package nju.zs.creature;

import nju.zs.Position;
import nju.zs.Thing2D;

import javax.swing.*;

public class Creature extends Thing2D {

	public Creature(Position position, ImageIcon icon){
		super(position, icon);
	}

	public void setQueuePosition(Position queuePosition){
		this.queuePosition = queuePosition;
		queuePosition.setHolder(this);
	}

	public Position getQueuePosition(){
		return queuePosition;
	}

	private Position queuePosition;

}
