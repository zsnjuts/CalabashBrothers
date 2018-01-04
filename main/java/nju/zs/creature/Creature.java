package nju.zs.creature;

import nju.zs.Position;
import nju.zs.Room;
import nju.zs.Thing2D;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public abstract class Creature extends Thing2D implements Runnable {

	public Creature(Position position, ImageIcon icon){
		super(position, icon);
	}

	public final void run(){
		try{
			while(true){
				synchronized (room) {
					refresh();
				}
				TimeUnit.MILLISECONDS.sleep(200);
			}
		}catch (Exception e){
			System.out.println(this+"被中断");
		}
	}

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

	protected abstract void refresh();

	private final int step = 5;
	protected final void moveTowards(Position p){
		int nx = (p.getX()==this.x()) ? 0 : ((p.getX()>this.x())?step:(-step));
		int ny = (p.getY()==this.y()) ? 0 : ((p.getY()>this.y())?step:(-step));
		if(!isConflict(nx,ny))
			this.position.setPosition(this.x()+nx, this.y()+ny);
		else
			System.out.println("跳过");
	}

	protected final void moveAStep(Direction d){
		int nx = 0, ny = 0;
		switch (d) {
			case LEFT: nx -= step; break;
			case RIGHT: nx += step; break;
			case UP: ny -= step; break;
			case DOWN: ny += step; break;
			default: ;
		}
		if(!isConflict(nx,ny))
			this.setPosition(this.x()+nx, this.y()+ny);
		else
			System.out.println("跳过");
	}

	protected Room room;
	protected enum Direction{
		LEFT, RIGHT, UP, DOWN
	}
	private Position queuePosition;

	/* 对当前前进方向进行检测，offsetX,offsetY均为偏移量 */
	private boolean isConflict(int offsetX, int offsetY){
		for(Creature ct:room.getCreatures())
			if(ct!=this
					&& ((ct.x()>=this.x()+offsetX && ct.x()<=this.x()) || (ct.x()>=this.x() && ct.x()<=this.x()+offsetX))
					&& ((ct.y()>=this.y()+offsetY && ct.y()<=this.y()) || (ct.y()>=this.y() && ct.y()<=this.y()+offsetY))) {
				System.out.println("conflicting");
				return true;
			}
		return false;
	}
}
