package nju.zs.layout;

import nju.zs.Position;
import nju.zs.creature.Creature;

import java.util.ArrayList;

public class Queue {
	private ArrayList<Position> queuePositions; //阵型中的相对位置

	public int getOffsetx() {
		return offsetx;
	}

	public int getOffsety() {
		return offsety;
	}

	private int offsetx = 50;
	private int offsety = 50;

	public Queue(Creature[] creatures){
		queuePositions = new ArrayList<Position>();
		for(int i=0;i<creatures.length;i++) {
			queuePositions.add(new Position(0, i));
			creatures[i].setQueuePosition(queuePositions.get(i));
		}
	}

	public ArrayList<Position> getQueuePositions(){
		return queuePositions;
	}

	public void printAll(){
		for(Position p:queuePositions)
			System.out.println(p.getHolder().toString()+p.getHolder().x()+","+p.getHolder().y());
	}
}
