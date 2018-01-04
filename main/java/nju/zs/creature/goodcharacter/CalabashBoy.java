package nju.zs.creature.goodcharacter;

import nju.zs.Position;
import nju.zs.creature.Attack;

import javax.swing.*;

public class CalabashBoy extends GoodCharacter implements Comparable<CalabashBoy> {

	private static ImageIcon calabashBoyIcon = new ImageIcon("src/main/resources/calabashBoy.png");

	public CalabashBoy(Color color, Seniority seniority, Position position){
		super(position, calabashBoyIcon);
		this.color = color;
		this.seniority = seniority;
	}

	@Override
	protected void fight() {
	}

	public int compareTo(CalabashBoy o) {
		return this.seniority.compareTo(o.seniority);
	}

	public Color getColor(){
		return color;
	}
	public Seniority getSeniority() {
		return seniority;
	}

	@Override
	public String toString(){
		return seniority.toString().substring(1)+"娃";
	}

	private Color color;
	private Seniority seniority;
}

enum Color{
	红, 橙, 黄, 绿, 青, 蓝, 紫
}

enum Seniority{
	老大, 老二, 老三, 老四, 老五, 老六, 老七
}