package nju.zs.creature.badcharacter;

import nju.zs.Position;
import nju.zs.creature.Creature;

import javax.swing.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BadCharacter extends Creature {

	public static BadCharacter[] getGenies(){
		BadCharacter[] genies = new BadCharacter[6];
		genies[0] = new ScorpionGenie(new Position(0,0));
		for(int i=0;i<5;i++)
			genies[1+i] = new Goblin(new Position(0,0));
		return genies;
	}

	public void run(){
		try{
			Random random = new Random();
			while(true){
				switch (random.nextInt()%6) {
					case 0: break; //不动
					case 1:
					case 2: this.position.setPosition(x()-5, y()); break; //左
					case 3: this.position.setPosition(x()+5, y()); break; //右
					case 4: this.position.setPosition(x(), y()-5); break; //上
					case 5: this.position.setPosition(x(), y()+5); break; //下
				}
				TimeUnit.MILLISECONDS.sleep(200);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public BadCharacter(Position position, ImageIcon icon){
		super(position, icon);
	}
}
