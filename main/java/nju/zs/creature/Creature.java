package nju.zs.creature;

import nju.zs.Position;
import nju.zs.Room;
import nju.zs.Thing2D;
import nju.zs.creature.badcharacter.BadCharacter;
import nju.zs.creature.goodcharacter.GoodCharacter;

import javax.swing.*;
import java.util.Random;
import java.util.TimerTask;
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
						case DEAD: dead(); break;
						default: ;
					}
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}
		}catch (Exception e){
			System.out.println(this+"被中断，状态:"+this.status);
		}
	}

	protected enum Status{
		RUNNING, FIGHTING, DEAD
	}
	protected Status status = Status.RUNNING;
	protected abstract void move();
	protected abstract void fight();
	protected abstract void dead();
	public Status getStatus() {
		return status;
	}
	public void setFight(boolean flag){
		if(flag) {
			status = Status.RUNNING;
		} else {
			status = Status.FIGHTING;
			new java.util.Timer().schedule(new TimerTask() {
				@Override
				public void run() { //1秒后就变为死亡状态
					status = Status.DEAD;
				}
			}, 500);
		}
	}

	private final int step = 5;
	protected final void moveTowards(Position p){
		int nx = (p.getX()==this.x()) ? 0 : ((p.getX()>this.x())?step:(-step));
		int ny = (p.getY()==this.y()) ? 0 : ((p.getY()>this.y())?step:(-step));
		CheckStatus status = CheckStatus.NORMAL;
		if(new Random().nextBoolean())
			status = checkForward(nx, 0);
		else
			status = checkForward(0, ny);
		if(status==CheckStatus.NORMAL)
			this.setPosition(this.x() + nx, this.y() + ny);
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
	private synchronized CheckStatus checkForward(int offsetX, int offsetY){
		boolean flag = this instanceof GoodCharacter;
		for(Creature ct:room.getCreatures())
			if(ct!=this && ct.getStatus()==Status.RUNNING && this.isCollidesWith(ct, offsetX, offsetY)) {
				if(flag == (ct instanceof GoodCharacter)) {
					System.out.println(this+" with "+ct);
					this.position.setPosition(this.x()-offsetX, this.y()-offsetY);
					return CheckStatus.FRIEND;
				} else {
					boolean alive = new Random().nextBoolean();
					ct.setFight(alive);
					this.setFight(!alive);
					System.out.println(this+"setFight="+alive+"  "+ct+"="+!alive);
					return CheckStatus.ENEMY;
				}
			}
		System.out.println(this+"no collide");
		return CheckStatus.NORMAL;
	}

	/* 新位置上是否会和指定Creature相撞 */
	private boolean isCollidesWith(Creature ct, int offsetX, int offsetY){
//		boolean flagX1 = offsetX>=0 && this.x()+this.getWidth()<=ct.x() && this.x()+this.getWidth()+offsetX>=ct.x(); //向右
//		boolean flagX2 = offsetX<0 && this.x()>ct.x()+ct.getWidth() && this.x()+offsetX<=ct.x()+ct.getWidth(); //向左
//		boolean flagY = Math.abs(this.y()+offsetY-ct.y())<=5;
//		return flagY && (flagX1||flagX2);
		return this.x()+this.getWidth()>ct.x() && ct.x()+ct.getWidth()>this.x()
				&& this.y()+10>ct.y() && ct.y()+10>this.y();
	}
}
