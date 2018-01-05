package nju.zs.creature.badcharacter;

import nju.zs.Position;

import javax.swing.*;

public class Goblin extends BadCharacter {
	private static int cnt = 0;
	private final int id = cnt++;
	private static ImageIcon goblinIcon = new ImageIcon(Goblin.class.getClassLoader().getResource("goblin.png"));
	public Goblin(Position position){
		super(position, goblinIcon);
	}

	@Override
	public void setDefaultImageIcon() {
		this.icon = goblinIcon;
	}

	@Override
	protected void fight() {

	}

	@Override
	public String toString() {
		return "Goblin"+id;
	}
}
