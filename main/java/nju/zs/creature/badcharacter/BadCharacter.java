package nju.zs.creature.badcharacter;

import nju.zs.Position;
import nju.zs.creature.Creature;

import javax.swing.*;

public class BadCharacter extends Creature {

	public static BadCharacter[] getGenies(){
		BadCharacter[] genies = new BadCharacter[6];
		genies[0] = new ScorpionGenie(new Position(0,0));
		for(int i=0;i<5;i++)
			genies[1+i] = new Goblin(new Position(0,0));
		return genies;
	}

	public BadCharacter(Position position, ImageIcon icon){
		super(position, icon);
	}
}
