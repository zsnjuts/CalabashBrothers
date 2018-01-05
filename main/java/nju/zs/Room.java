package nju.zs;

import nju.zs.creature.Creature;
import nju.zs.layout.Queue;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;

public class Room {

	public void addQueue(Queue queue, int beginX, int beginY){
		ArrayList<Position> queuePositions = queue.getQueuePositions();
		for(Position p:queuePositions){
			Creature ct = (Creature)p.getHolder();
			ct.setPosition(beginX + p.getX(), beginY + p.getY());
			ct.setRoom(this);
			creatures.add((Creature)p.getHolder());
			things.add(p.getHolder());
		}
	}

	public void addCreature(Creature creature, int x, int y){
		creature.setPosition(x, y);
		creature.setRoom(this);
		creatures.add(creature);
		things.add(creature);
	}

	public void addThing(Thing2D thing){
		things.add(thing);
	}

	public ArrayList<Creature> getCreatures(){
		return creatures;
	}

	public ArrayList<Thing2D> getThings(){
		return things;
	}

	private ArrayList<Creature> creatures = new ArrayList<Creature>();
	private ArrayList<Thing2D> things = new ArrayList<Thing2D>();
	private ExecutorService exec;
}
