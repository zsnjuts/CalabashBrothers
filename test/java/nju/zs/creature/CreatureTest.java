package nju.zs.creature;

import nju.zs.Position;
import nju.zs.Room;
import nju.zs.creature.badcharacter.Goblin;
import nju.zs.creature.badcharacter.ScorpionGenie;
import nju.zs.creature.badcharacter.SnakeGenie;
import nju.zs.creature.goodcharacter.Grandpa;

import static org.junit.Assert.*;

public class CreatureTest {

	@org.junit.Test
	public void isCollidesWith() throws Exception {
		Grandpa gp = new Grandpa(new Position(100, 200));
		assertEquals(true, gp.isCollidesWith(new SnakeGenie(new Position(120, 200)), 10, 0));
		assertEquals(false, gp.isCollidesWith(new ScorpionGenie(new Position(120, 250)), 10, 0));
	}

	@org.junit.Test
	public void checkForward() throws Exception{
		Room room = new Room();
		room.addCreature(new Grandpa(new Position(100, 200)), 0, 0);
		room.addCreature(new Goblin(new Position(300, 400)), 300, 400);
		Goblin gb = new Goblin(new Position(100, 200));
		room.addCreature(gb, 100, 200);
		assertEquals(Creature.CheckStatus.NORMAL, gb.checkForward(10, 0));
		gb.setPosition(300, 405);
		assertEquals(Creature.CheckStatus.FRIEND, gb.checkForward(0, -5));
		gb.setPosition(5,0);
		assertEquals(Creature.CheckStatus.ENEMY, gb.checkForward(-5, 0));
	}
}