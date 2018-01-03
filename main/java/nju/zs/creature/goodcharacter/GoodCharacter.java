package nju.zs.creature.goodcharacter;

import nju.zs.Position;
import nju.zs.creature.Creature;
import nju.zs.creature.badcharacter.BadCharacter;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class GoodCharacter extends Creature {

	public static CalabashBoy[] getBrothers(){
		CalabashBoy[] boys = new CalabashBoy[7];
		for(int i=0;i<7;i++)
			boys[i] = new CalabashBoy(Color.values()[i], Seniority.values()[i], new Position(0,0));
		return boys;
	}

	public void run(){
		try{
			while(true){
				for(Creature ct:room.getCreatures())
					if(ct instanceof BadCharacter)
						this.moveTo(ct.getPosition());
				TimeUnit.MILLISECONDS.sleep(1000);
			}
		}catch (Exception e){
//			e.printStackTrace();
			System.out.println(this+"已暂停");
		}
	}

	public GoodCharacter(Position position, ImageIcon icon){
		super(position, icon);
	}
}
