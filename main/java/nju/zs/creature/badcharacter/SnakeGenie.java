package nju.zs.creature.badcharacter;

import nju.zs.Position;

import javax.swing.*;

public class SnakeGenie extends BadCharacter {
	private static ImageIcon snakeGenieIcon = new ImageIcon(SnakeGenie.class.getClassLoader().getResource("snakeGenie.png"));
	public SnakeGenie(Position position){
		super(position, snakeGenieIcon);
	}

	@Override
	public void setDefaultImageIcon() {
		this.icon = snakeGenieIcon;
	}

	@Override
	protected void fight() {

	}

	@Override
	public String toString() {
		return "SnakeGenie";
	}
}
