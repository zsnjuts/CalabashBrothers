package nju.zs.creature.badcharacter;

import nju.zs.Position;

import javax.swing.*;

public class SnakeGenie extends BadCharacter {
	private static ImageIcon snakeGenieIcon = new ImageIcon("src/main/resources/snakeGenie.png");
	public SnakeGenie(Position position){
		super(position, snakeGenieIcon);
	}

	@Override
	public String toString() {
		return "蛇精";
	}
}
