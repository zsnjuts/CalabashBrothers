package nju.zs.creature.goodcharacter;

import nju.zs.Position;
import nju.zs.creature.Creature;
import nju.zs.creature.badcharacter.BadCharacter;

import javax.swing.*;
import java.util.ArrayList;

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

	@Override
	public void setStatus(Status status) {
		this.status = status;
		if(this.status!=Status.DEAD)
			this.setDefaultImageIcon();
		else
			this.setImageIcon(goodDeadIcon);
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

		if(tgt!=null)
			this.moveTowards(tgt.getPosition());
	}

	private static ImageIcon goodDeadIcon = new ImageIcon(GoodCharacter.class.getClassLoader().getResource("goodDead.png"));
	@Override
	protected void dead() {
		this.setImageIcon(goodDeadIcon);
	}
}
