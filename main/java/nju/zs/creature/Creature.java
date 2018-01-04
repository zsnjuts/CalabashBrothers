package nju.zs.creature;

import nju.zs.Position;
import nju.zs.Room;
import nju.zs.Thing2D;
import nju.zs.creature.badcharacter.BadCharacter;
import nju.zs.creature.goodcharacter.GoodCharacter;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public abstract class Creature extends Thing2D implements Runnable {

	public Creature(Position position, ImageIcon icon){
		super(position, icon);
	}

	public void setRoom(Room room){
		this.room = room;
	}

	private Position queuePosition;
	public void setQueuePosition(Position queuePosition){
		this.queuePosition = queuePosition;
		queuePosition.setHolder(this);
	}

	public Position getQueuePosition(){
		return queuePosition;
	}

	/* 设计模式：模板方法（Template Method） */
	public final void run(){
		try{
			while(true){
				synchronized (room) {
					switch (status){
						case RUNNING: move(); break;
						case FIGHTING: fight(); break;
						default: ;
					}
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}
		}catch (Exception e){
			System.out.println(this+"被中断");
		}
	}

	protected enum Status{
		RUNNING, FIGHTING
	}
	protected Status status = Status.RUNNING;
	protected abstract void move();
	protected abstract void fight();

	private final int step = 5;
	protected final CheckStatus moveTowards(Position p){
		int nx = (p.getX()==this.x()) ? 0 : ((p.getX()>this.x())?step:(-step));
		int ny = (p.getY()==this.y()) ? 0 : ((p.getY()>this.y())?step:(-step));
		CheckStatus status = checkForward(nx,ny);
		if(status==CheckStatus.NORMAL)
			this.setPosition(this.x() + nx, this.y() + ny);
		return status;
	}

	protected final CheckStatus moveAStep(Direction d){
		int nx = 0, ny = 0;
		switch (d) {
			case LEFT: nx -= step; break;
			case RIGHT: nx += step; break;
			case UP: ny -= step; break;
			case DOWN: ny += step; break;
			default: ;
		}
		CheckStatus checkStatus = checkForward(nx,ny);
		if(checkStatus==CheckStatus.NORMAL)
			this.setPosition(this.x() + nx, this.y() + ny);
		return checkStatus;
	}

	protected Room room;
	protected enum Direction{
		LEFT, RIGHT, UP, DOWN
	}

	protected enum CheckStatus{
		NORMAL, FRIEND, ENEMY
	}

	/* 对当前前进方向进行检测是否会与其他Creature相撞，offsetX,offsetY均为偏移量 */
	private CheckStatus checkForward(int offsetX, int offsetY){
		boolean flag = this instanceof GoodCharacter;
		for(Creature ct:room.getCreatures())
			if(ct!=this && this.isCollidesWith(ct, offsetX, offsetY)) {
				if(flag == (ct instanceof GoodCharacter))
					return CheckStatus.FRIEND;
				else {
					System.out.println(this.x()+","+this.y()+" "+ct.x()+","+ct.y());
					return CheckStatus.ENEMY;
				}
			}
		return CheckStatus.NORMAL;
	}

	/* 在当前方向上是否会和指定Creature相撞 */
	private boolean isCollidesWith(Creature ct, int offsetX, int offsetY){
		boolean flagX1 = offsetX>=0 && this.x()+this.getWidth()<=ct.x() && this.x()+this.getWidth()+offsetX>=ct.x(); //向右
		boolean flagX2 = offsetX<0 && this.x()>ct.x()+ct.getWidth() && this.x()+offsetX<=ct.x()+ct.getWidth(); //向左
		boolean flagY = Math.abs(this.y()+offsetY-ct.y())<=5;
		return flagY && (flagX1||flagX2);
	}
}
