package nju.zs;

import nju.zs.creature.Creature;
import nju.zs.layout.Queue;

import java.util.ArrayList;

public class Room {

	public void addQueue(Queue queue, int beginX, int beginY){
		ArrayList<Position> queuePositions = queue.getQueuePositions();
		for(Position p:queuePositions){
			p.getHolder().setPosition(beginX + p.getX(), beginY + p.getY());
			creatures.add(p.getHolder());
		}
	}

	public void addCreature(Creature creature, int x, int y){
		creature.setPosition(x, y);
		creatures.add(creature);
	}

	public ArrayList<Creature> getCreatures(){
		return creatures;
	}

	private ArrayList<Creature> creatures = new ArrayList<Creature>();
}
