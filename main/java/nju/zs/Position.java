package nju.zs;

import nju.zs.creature.Creature;

public class Position {

	public Position(int x, int y){
		this.x = x;
		this.y = y;
		this.empty = true;
	}

	public void setPosition(int x, int y){
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isEmpty() {
		return empty;
	}

	public void setEmpty() {
		this.empty = true;
	}

	public Thing2D getHolder() {
		return holder;
	}

	public void setHolder(Creature holder) {
		this.holder = holder;
		this.empty = false;
	}

	@Override
	public String toString() {
		return "("+x+","+y+")";
	}

	private int x;
	private int y;
	private boolean empty;
	private Thing2D holder;
}
