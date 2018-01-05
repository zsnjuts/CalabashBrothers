package nju.zs.creature.badcharacter;

import nju.zs.Position;
import nju.zs.creature.Creature;

import javax.swing.*;
import java.util.Random;

public abstract class BadCharacter extends Creature {

	public static BadCharacter[] getGenies(){
		BadCharacter[] genies = new BadCharacter[8];
		genies[0] = new ScorpionGenie(new Position(0,0));
		for(int i=0;i<7;i++)
			genies[1+i] = new Goblin(new Position(0,0));
		return genies;
	}

	@Override
	public void setStatus(Status status) {
		this.status = status;
		if(this.status!=Status.DEAD)
			this.setDefaultImageIcon();
		else
			this.setImageIcon(badDeadIcon);
	}

	protected void move(){
		switch (new Random().nextInt(5)){
			case 0:
			case 1: this.moveAStep(Direction.LEFT); break;
			case 2: this.moveAStep(Direction.RIGHT); break;
			case 3: this.moveAStep(Direction.UP); break;
			case 4: this.moveAStep(Direction.DOWN); break;
			default: ;
		}
	}

	private static ImageIcon badDeadIcon = new ImageIcon(BadCharacter.class.getClassLoader().getResource("badDead.png"));
	@Override
	protected void dead() {
		this.setImageIcon(badDeadIcon);
	}

	public BadCharacter(Position position, ImageIcon icon){
		super(position, icon);
	}
}
