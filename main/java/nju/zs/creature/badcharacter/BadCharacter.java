package nju.zs.creature.badcharacter;

import nju.zs.Position;
import nju.zs.Room;
import nju.zs.creature.Creature;
import nju.zs.creature.goodcharacter.GoodCharacter;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public abstract class BadCharacter extends Creature {

	public static BadCharacter[] getGenies(){
		BadCharacter[] genies = new BadCharacter[8];
		genies[0] = new ScorpionGenie(new Position(0,0));
		for(int i=0;i<7;i++)
			genies[1+i] = new Goblin(new Position(0,0));
		return genies;
	}

	protected void move(){
		CheckStatus checkStatus = CheckStatus.NORMAL;
		switch (new Random().nextInt()%5){
			case 0:
			case 1: checkStatus = this.moveAStep(Direction.LEFT); break;
			case 2: checkStatus = this.moveAStep(Direction.RIGHT); break;
			case 3: checkStatus = this.moveAStep(Direction.UP); break;
			case 4: checkStatus = this.moveAStep(Direction.DOWN); break;
			default: ;
		}
		if(checkStatus==CheckStatus.ENEMY)
			this.status = Status.FIGHTING;
		System.out.println(this+" "+checkStatus);
	}

	public BadCharacter(Position position, ImageIcon icon){
		super(position, icon);
	}
}
