package nju.zs.creature.badcharacter;

import nju.zs.Position;

import javax.swing.*;

public class ScorpionGenie extends BadCharacter {
	private static ImageIcon scorpionGenieIcon = new ImageIcon("src/main/resources/scorpionGenie.png");
	public ScorpionGenie(Position position){
		super(position, scorpionGenieIcon);
	}

	@Override
	public void setDefaultImageIcon() {
		this.icon = scorpionGenieIcon;
	}

	@Override
	protected void fight() {

	}

	@Override
	public String toString() {
		return "蝎精";
	}
}
