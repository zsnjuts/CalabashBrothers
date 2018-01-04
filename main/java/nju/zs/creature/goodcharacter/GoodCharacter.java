package nju.zs.creature.goodcharacter;

import nju.zs.Position;
import nju.zs.Room;
import nju.zs.creature.Attack;
import nju.zs.creature.Creature;
import nju.zs.creature.badcharacter.BadCharacter;
import nju.zs.creature.badcharacter.Goblin;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.lang.Math;

public abstract class GoodCharacter extends Creature {

	public static CalabashBoy[] getBrothers(){
		CalabashBoy[] boys = new CalabashBoy[7];
		for(int i=0;i<7;i++)
			boys[i] = new CalabashBoy(Color.values()[i], Seniority.values()[i], new Position(0,0));
		return boys;
	}

	public GoodCharacter(Position position, ImageIcon icon){
		super(position, icon);
	}

	protected void move(){
		ArrayList<Creature> creatures = room.getCreatures();
		Creature tgt = null;
		int dist = Integer.MAX_VALUE;
		for(Creature ct:creatures)
			if(ct!=this && (ct instanceof BadCharacter) && ct.getStatus()==Status.RUNNING && this.distanceTo(ct.getPosition())<dist){
				tgt = ct;
				dist = this.distanceTo(tgt.getPosition());
			}
		System.out.println(this+"target:"+tgt);
		if(tgt!=null)
			this.moveTowards(tgt.getPosition());
	}

	private static ImageIcon goodDeadIcon = new ImageIcon("src/main/resources/goodDead.png");
	@Override
	protected void dead() {
		this.setImageIcon(goodDeadIcon);
	}

	private ArrayList<Attack> attacks = new ArrayList<>();
}
