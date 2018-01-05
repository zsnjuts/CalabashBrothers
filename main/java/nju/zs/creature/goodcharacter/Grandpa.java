package nju.zs.creature.goodcharacter;

import nju.zs.Position;

import javax.swing.*;

public class Grandpa extends GoodCharacter {
	private static ImageIcon grandpaIcon = new ImageIcon(Grandpa.class.getClassLoader().getResource("grandpa.png"));

	public Grandpa(Position position){
		super(position, grandpaIcon);
	}

	@Override
	public void setDefaultImageIcon() {
		this.icon = grandpaIcon;
	}

	@Override
	protected void fight() {
	}

	@Override
	public String toString() {
		return "Grandpa";
	}
}
