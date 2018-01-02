package nju.zs;

import nju.zs.creature.badcharacter.BadCharacter;
import nju.zs.creature.goodcharacter.GoodCharacter;
import nju.zs.layout.ChangsheLayout;
import nju.zs.layout.Layout;
import nju.zs.layout.Queue;

public class Main {
	public static void main(String[] args){
		Queue geniesQueue = new Queue(BadCharacter.getGenies());
		Layout layout = new ChangsheLayout();
		layout.place(geniesQueue);
		geniesQueue.printAll();
	}
}
