package nju.zs.creature.badcharacter;

import nju.zs.Position;

import javax.swing.*;

public class Goblin extends BadCharacter {
	private static int cnt = 0;
	private final int id = cnt++;
	private static ImageIcon goblinIcon = new ImageIcon("src/main/resources/goblinIcon.png");
	public Goblin(Position position){
		super(position, goblinIcon);
	}

	@Override
	public String toString() {
		return "喽啰"+id;
	}
}
