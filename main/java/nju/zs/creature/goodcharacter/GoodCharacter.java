package nju.zs.creature.goodcharacter;

import nju.zs.Position;
import nju.zs.creature.Creature;

import javax.swing.*;

public class GoodCharacter extends Creature {

	public static CalabashBoy[] getBrothers(){
		CalabashBoy[] boys = new CalabashBoy[7];
		for(int i=0;i<7;i++)
			boys[i] = new CalabashBoy(Color.values()[i], Seniority.values()[i], new Position(0,0));
		return boys;
	}

	public GoodCharacter(Position position, ImageIcon icon){
		super(position, icon);
	}
}
